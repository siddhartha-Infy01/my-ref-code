package com.iexceed.esoko.engine.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class InvoiceReport {
	public static Logger log = LoggerUtils.getLogger();
	private static double totalAmount = 0;
	private static double discountAmount = 0;
	private static double vatAmount = 0;
	private static double finalAmount = 0;
	private static String FILE = "FirstPdf.pdf";
	private static Font headerFont = new Font(
			Font.getFamily("Metrisch Medium"), 12, Font.NORMAL, BaseColor.BLACK);
	private static Font normalFont = new Font(
			Font.getFamily("liberation mono"), 10, Font.NORMAL, BaseColor.BLACK);
	private static Font redFont = new Font(Font.getFamily("liberation mono"),
			10, Font.NORMAL, BaseColor.RED);
	private static Font titleFont = new Font(Font.getFamily("liberation mono"),
			10, Font.BOLD, new BaseColor(201, 101, 23));
	private static Font boldFont = new Font(Font.getFamily("liberation mono"),
			10, Font.BOLD, BaseColor.BLACK);
	private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	@SuppressWarnings("static-access")
	public byte[] generatePDF(List<Map<String, Object>> invoiceList) {
		Document document = null;
		byte[] bytes = null;
		try {
			log.info("Inside Invoice Report");
			document = new Document(PageSize.A4, 36, 36, 5, 5);
			File file = new File(FILE);
			file.createNewFile();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addTitlePage(document, invoiceList);
			document.close();
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum); // no doubt here is 0
			}
			bytes = bos.toByteArray();

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	private static void addMetaData(Document document) {
		document.addTitle("Invoice Report");
		document.addSubject("Using iText");
		document.addKeywords("Esoko");
		document.addAuthor("I-Exceed Technology");
		document.addCreator("Gangadhar");
	}

	private static void addTitlePage(Document document,
			List<Map<String, Object>> invoiceList) throws DocumentException,
			MalformedURLException, IOException {
		// FontFactory.getFont("Times-Roman", 12, Font.BOLD);
		String date = null;
		String invoiceId = null;
		String discount = null;
		String vat = null;
		log.debug("invoiceList::" + invoiceList);
		for (Map<String, Object> map : invoiceList) {
			if (map.get("created_ts") != null) {
				date = map.get("created_ts").toString();
			}
			if (map.get("invoice_id") != null) {
				invoiceId = map.get("invoice_id").toString();
			}
			if (map.get("discount") != null) {
				discount = map.get("discount").toString();
			}
			if (map.get("vat") != null) {
				vat = map.get("vat").toString();
			}
			log.debug("map::" + map);
		}
		Font f = new Font(Font.getFamily("Verdana"), 12.0f, Font.BOLD,
				BaseColor.WHITE);
		Chunk c = new Chunk("Esoko", f);
		c.setBackground(new BaseColor(201, 101, 23), 65.0f, 8.0f, 525.0f, 12.0f);
		Paragraph preface = new Paragraph(c);
		c = new Chunk("Esoko", f);
		// preface.set
		// We add one empty line
		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Bill " + invoiceId, headerFont));
		addEmptyLine(preface, 0);
		preface.add(new Paragraph("Generated: " + date, normalFont));
		// String imageUrl = "C:/Users/User/Desktop/icons/icon-paid.png";
		// Image image1 = Image.getInstance(imageUrl);
		// image1.setAbsolutePosition(350f, 710f);
		// document.add(image1);
		LineSeparator separator = new LineSeparator();
		separator.setPercentage(5000f / 50f);
		separator.setLineWidth(0.5f);
		Chunk linebreak = new Chunk(separator);
		document.add(preface);
		document.add(linebreak);
		document.add(addressTable(invoiceList));
		document.add(Chunk.NEWLINE);
		document.add(createFirstTable(invoiceList));
		addEmptyLine(preface, 1);
		document.add(discountVatTable(discount, vat, totalAmount));
		totalAmount = 0;
		document.add(txnDtlsTable(preface));
		// Setting T&C
		preface = new Paragraph();
		preface.add(new Paragraph(
				"Please keep a copy of this bill for your records and for future reference.",
				normalFont));
		addEmptyLine(preface, 0);
		preface.add(new Paragraph(
				"To upgrade,downgrade or change your billing information visit:",
				normalFont));
		addEmptyLine(preface, 0);
		preface.add(new Paragraph("Please send billing questions to ",
				normalFont));
		addEmptyLine(preface, 0);
		preface.add(new Paragraph("and technical support questions to ",
				normalFont));
		addEmptyLine(preface, 1);
		document.add(preface);
		document.add(new Chunk("Thank you for your business ", normalFont));
		document.add(new Phrase("The Esoko Team  ", titleFont));
		document.add(insertImage(document));
		document.newPage();
	}

	public static PdfPTable discountVatTable(String discount, String vat,
			double totalAmount) throws DocumentException {
		discountAmount = totalAmount * Double.parseDouble(discount) / 100;
		vatAmount = totalAmount * Double.parseDouble(vat) / 100;
		log.debug("discountAmount::" + discountAmount);
		log.debug("vatAmount::" + vatAmount);
		log.debug("totalAmount::" + totalAmount);
		finalAmount = totalAmount - discountAmount + vatAmount;
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(50);
		table.setWidths(new float[] { 0.6f, 0.6f });
		PdfPCell cell1 = new PdfPCell(new Paragraph("Discount " + discount
				+ "%", normalFont));
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1.setBorder(Rectangle.NO_BORDER);
		PdfPCell cell2 = new PdfPCell(new Paragraph("-"
				+ Math.round(discountAmount * 100.0) / 100.0, redFont));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBorder(Rectangle.NO_BORDER);
		PdfPCell cell3 = new PdfPCell(new Paragraph("VAT Tax " + vat + "%",
				normalFont));
		cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell3.setBorder(Rectangle.NO_BORDER);
		PdfPCell cell4 = new PdfPCell(new Paragraph(
				Math.round(vatAmount * 100.0) / 100.0 + "", normalFont));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.setHorizontalAlignment(Element.ALIGN_RIGHT);
		return table;
	}

	public static PdfPTable addressTable(List<Map<String, Object>> invoiceList)
			throws DocumentException {
		String invoiceFrom = null;
		String company = null;
		String town = null;
		String country = null;
		for (Map<String, Object> map : invoiceList) {
			if (map.get("invoice_from") != null) {
				invoiceFrom = map.get("invoice_from").toString();
			}
			if (map.get("company") != null) {
				company = map.get("company").toString();
			}
			if (map.get("town") != null) {
				town = map.get("town").toString();
			}
			if (map.get("country") != null) {
				country = map.get("country").toString();
			}
		}
		PdfPTable table = new PdfPTable(2);
		PdfPCell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8;
		table.setWidthPercentage(100);
		cell1 = new PdfPCell(new Phrase(invoiceFrom, new Font(
				Font.getFamily("Verdana"), 10.0f, Font.BOLD)));
		cell1.setBorder(Rectangle.NO_BORDER);
		cell2 = new PdfPCell(new Phrase("Billed to:", new Font(
				Font.getFamily("Verdana"), 10.0f, Font.BOLD)));
		cell2.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell1);
		table.addCell(cell2);

		cell3 = new PdfPCell(new Phrase(company, normalFont));
		cell3.setBorder(Rectangle.NO_BORDER);
		cell4 = new PdfPCell(new Phrase(town, normalFont));
		cell4.setBorder(Rectangle.NO_BORDER);
		cell5 = new PdfPCell(new Phrase("sample", normalFont));
		cell5.setBorder(Rectangle.NO_BORDER);
		cell6 = new PdfPCell(new Phrase("Esoko", normalFont));
		cell6.setBorder(Rectangle.NO_BORDER);
		cell7 = new PdfPCell(new Phrase(country, normalFont));
		cell7.setBorder(Rectangle.NO_BORDER);
		cell8 = new PdfPCell(new Phrase("Esoko Country", normalFont));
		cell8.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell3);
		table.addCell(cell6);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell7);
		table.addCell(cell8);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		return table;
	}

	public static PdfPTable createFirstTable(
			List<Map<String, Object>> invoiceList) throws DocumentException {
		// a table with three columns
		PdfPTable table = new PdfPTable(4);
		// the cell object
		Double total = 0.00;

		PdfPCell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8;
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.6f, 3.0f, 0.8f, 0.8f });

		// we add a cell with colspan 3
		cell1 = new PdfPCell(new Phrase("Quantity", new Font(
				Font.getFamily("Verdana"), 10.0f, Font.BOLD)));
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.setBackgroundColor(new BaseColor(220, 220, 220));
		cell2 = new PdfPCell(new Phrase("Description", new Font(
				Font.getFamily("Verdana"), 10.0f, Font.BOLD)));
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.setBackgroundColor(new BaseColor(220, 220, 220));
		cell3 = new PdfPCell(new Phrase("Item Price", new Font(
				Font.getFamily("Verdana"), 10.0f, Font.BOLD)));
		cell3.setBorder(Rectangle.NO_BORDER);
		cell3.setBackgroundColor(new BaseColor(220, 220, 220));
		cell4 = new PdfPCell(new Phrase("Total", new Font(
				Font.getFamily("Verdana"), 10.0f, Font.BOLD)));
		cell4.setBorder(Rectangle.NO_BORDER);
		cell4.setBackgroundColor(new BaseColor(220, 220, 220));
		// adding to table
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		// we add the four remaining cells with addCell()
		for (Map<String, Object> map : invoiceList) {
			if (map.get("total") != null) {
				if (map.get("quantity") != null) {
					cell5 = new PdfPCell(new Phrase(map.get("quantity")
							.toString(), normalFont));
					cell5.setBorder(Rectangle.BOTTOM);
					table.addCell(cell5);
				}
				if (map.get("description") != null) {
					log.debug("description::"
							+ map.get("description").toString());
					cell6 = new PdfPCell(new Phrase(map.get("description")
							.toString(), normalFont));
					cell6.setBorder(Rectangle.BOTTOM);
					table.addCell(cell6);
				}
				if (map.get("total") != null) {
					cell7 = new PdfPCell(new Phrase("$"
							+ map.get("total").toString(), normalFont));
					cell7.setBorder(Rectangle.BOTTOM);
					table.addCell(cell7);
				} else {
					cell7 = new PdfPCell(new Phrase());
					cell7.setBorder(Rectangle.BOTTOM);
					table.addCell(cell7);
				}
				if (map.get("total") != null) {
					total = Double.parseDouble(map.get("quantity").toString())
							* Double.parseDouble(map.get("total").toString());
					log.debug("Total:: "
							+ Double.parseDouble(map.get("quantity").toString())
							* Double.parseDouble(map.get("total").toString()));
					log.debug("quantity:: "
							+ Double.parseDouble(map.get("quantity").toString()));
					log.debug("Total:: "
							+ Double.parseDouble(map.get("total").toString()));
					cell8 = new PdfPCell(new Phrase("$" + total + "",
							normalFont));
					cell8.setBorder(Rectangle.BOTTOM);
					table.addCell(cell8);
					totalAmount = totalAmount + Double.parseDouble(total + "");
					log.debug("totalAmount11::" + totalAmount);
				} else {
					cell8 = new PdfPCell(new Phrase());
					cell8.setBorder(Rectangle.BOTTOM);
					table.addCell(cell8);
				}
			}
		}
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		return table;
	}

	public static PdfPTable txnDtlsTable(Paragraph preface)
			throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		PdfPCell cell1, cell2, cell3, cell4, cell5, cell6;
		table.setWidthPercentage(100);
		cell1 = new PdfPCell(new Phrase("AMOUNT PAID :  $"
				+ Math.round(finalAmount * 100.0) / 100.0, boldFont));
		cell1.setBorder(Rectangle.NO_BORDER);
		cell2 = new PdfPCell(new Phrase(" Total    $"
				+ Math.round(finalAmount * 100.0) / 100.0, normalFont));
		cell2.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell1);
		table.addCell(cell2);

		cell5 = new PdfPCell(new Phrase(null, boldFont));
		cell5.setBorder(Rectangle.NO_BORDER);
		cell3 = new PdfPCell(new Phrase("CREDIT CARD BILLED : ", boldFont));
		cell3.setBorder(Rectangle.NO_BORDER);
		cell6 = new PdfPCell(new Phrase(null, boldFont));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell6.setBorder(Rectangle.NO_BORDER);
		cell4 = new PdfPCell(new Phrase(" TRANSACTION ID : ", boldFont));
		cell4.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell3);
		table.addCell(cell6);
		table.addCell(cell4);
		table.addCell(cell5);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		addEmptyLine(preface, 2);
		return table;
	}

	public static Image insertImage(Document document) {
		Image image = null;
		try {
			String imageUrl = "http://www.ghanacurrentjobs.com/wp-content/uploads/2012/09/esoko-Jobs-in-Ghana.png";

			image = Image.getInstance(new URL(imageUrl));
			image.setAbsolutePosition(335f, 395f);
			image.scaleToFit(100, 250);
			document.add(image);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
