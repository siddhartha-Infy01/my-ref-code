package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

import java.util.Arrays;
import java.util.Date;

/**
 * The persistent class for the survey database table.
 * 
 */
@Entity
@Table(name = "survey")
@NamedQuery(name = "Survey.findAll", query = "SELECT i FROM Survey i")
public class Survey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "survey_id")
	private int surveyId;

	@Column(name = "survey_name")
	private String surveyName;

	@Column(name = "survey_type")
	private String surveyType;

	@Column(name = "phase")
	private String phase;

	@Column(name = "network_id")
	private String networkId;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "total_score")
	private String totalScore;

	@Column(name = "secured_score")
	private String securedScore;

	@Column(name = "record_status")
	private String recordStatus;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_ts")
	private Date createdTs;

	@Column(name = "auth_by")
	private String authBy;

	@Column(name = "auth_stat")
	private String authStat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auth_ts")
	private Date authTs;

	private String txt_qns_1;

	private String txt_qns_2;

	private String txt_qns_3;

	private String txt_qns_4;

	private String txt_qns_5;

	private String txt_qns_6;

	private String txt_qns_7;

	private String txt_qns_8;

	private String txt_qns_9;

	private String txt_qns_10;

	private String txt_qns_11;

	private String txt_qns_12;

	private String txt_qns_13;

	private String txt_qns_14;

	private String txt_qns_15;

	private String txt_qns_16;

	private String txt_qns_17;

	private String txt_qns_18;

	private String txt_qns_19;

	private String txt_qns_20;

	private String txt_qns_21;

	private String txt_qns_22;

	private String txt_qns_23;

	private String txt_qns_24;

	private String txt_qns_25;

	private String txt_qns_26;

	private String txt_qns_27;

	private String txt_qns_28;

	private String txt_qns_29;

	private String txt_qns_30;

	private String txt_qns_31;

	private String txt_qns_32;

	private String txt_qns_33;

	private String txt_qns_34;

	private String txt_qns_35;

	private String txt_qns_36;

	private String txt_qns_37;

	private String txt_qns_38;

	private String txt_qns_39;

	private String txt_qns_40;

	private String txt_qns_41;

	private String txt_qns_42;

	private String txt_qns_43;

	private String txt_qns_44;

	private String txt_qns_45;

	private String txt_qns_46;

	private String txt_qns_47;

	private String txt_qns_48;

	private String txt_qns_49;

	private String txt_qns_50;

	private String txt_fld_1;

	private String txt_fld_2;

	private String txt_fld_3;

	private String txt_fld_4;

	private String txt_fld_5;

	private String txt_fld_6;

	private String txt_fld_7;

	private String txt_fld_8;

	private String txt_fld_9;

	private String txt_fld_10;

	private String txt_fld_11;

	private String txt_fld_12;

	private String txt_fld_13;

	private String txt_fld_14;

	private String txt_fld_15;

	private String txt_fld_16;

	private String txt_fld_17;

	private String txt_fld_18;

	private String txt_fld_19;

	private String txt_fld_20;

	private String txt_fld_21;

	private String txt_fld_22;

	private String txt_fld_23;

	private String txt_fld_24;

	private String txt_fld_25;

	private String txt_fld_26;

	private String txt_fld_27;

	private String txt_fld_28;

	private String txt_fld_29;

	private String txt_fld_30;

	private String txt_fld_31;

	private String txt_fld_32;

	private String txt_fld_33;

	private String txt_fld_34;

	private String txt_fld_35;

	private String txt_fld_36;

	private String txt_fld_37;

	private String txt_fld_38;

	private String txt_fld_39;

	private String txt_fld_40;

	private String txt_fld_41;

	private String txt_fld_42;

	private String txt_fld_43;

	private String txt_fld_44;

	private String txt_fld_45;

	private String txt_fld_46;

	private String txt_fld_47;

	private String txt_fld_48;

	private String txt_fld_49;

	private String txt_fld_50;

	@Lob
	private byte[] blob_fld_1;

	@Lob
	private byte[] blob_fld_2;

	@Lob
	private byte[] blob_fld_3;

	@Lob
	private byte[] blob_fld_4;

	@Lob
	private byte[] blob_fld_5;

	@Lob
	private byte[] blob_fld_6;

	@Lob
	private byte[] blob_fld_7;

	@Lob
	private byte[] blob_fld_8;

	@Lob
	private byte[] blob_fld_9;

	@Lob
	private byte[] blob_fld_10;

	@Column(name = "gis_fld_1", columnDefinition = "Point")
	@Type(type = "org.hibernatespatial.GeometryUserType")
	private Point gis_fld_1;

	@Column(name = "gis_fld_2", columnDefinition = "Point")
	@Type(type = "org.hibernatespatial.GeometryUserType")
	private Point gis_fld_2;

	@Column(name = "gis_fld_3", columnDefinition = "Point")
	@Type(type = "org.hibernatespatial.GeometryUserType")
	private Point gis_fld_3;

	@Column(name = "gis_fld_4", columnDefinition = "Point")
	@Type(type = "org.hibernatespatial.GeometryUserType")
	private Point gis_fld_4;

	@Column(name = "gis_fld_5", columnDefinition = "Point")
	@Type(type = "org.hibernatespatial.GeometryUserType")
	private Point gis_fld_5;

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	

	public String getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getAuthBy() {
		return authBy;
	}

	public void setAuthBy(String authBy) {
		this.authBy = authBy;
	}

	public String getAuthStat() {
		return authStat;
	}

	public void setAuthStat(String authStat) {
		this.authStat = authStat;
	}

	public Date getAuthTs() {
		return authTs;
	}

	public void setAuthTs(Date authTs) {
		this.authTs = authTs;
	}

	public String getTxt_qns_1() {
		return txt_qns_1;
	}

	public void setTxt_qns_1(String txt_qns_1) {
		this.txt_qns_1 = txt_qns_1;
	}

	public String getTxt_qns_2() {
		return txt_qns_2;
	}

	public void setTxt_qns_2(String txt_qns_2) {
		this.txt_qns_2 = txt_qns_2;
	}

	public String getTxt_qns_3() {
		return txt_qns_3;
	}

	public void setTxt_qns_3(String txt_qns_3) {
		this.txt_qns_3 = txt_qns_3;
	}

	public String getTxt_qns_4() {
		return txt_qns_4;
	}

	public void setTxt_qns_4(String txt_qns_4) {
		this.txt_qns_4 = txt_qns_4;
	}

	public String getTxt_qns_5() {
		return txt_qns_5;
	}

	public void setTxt_qns_5(String txt_qns_5) {
		this.txt_qns_5 = txt_qns_5;
	}

	public String getTxt_qns_6() {
		return txt_qns_6;
	}

	public void setTxt_qns_6(String txt_qns_6) {
		this.txt_qns_6 = txt_qns_6;
	}

	public String getTxt_qns_7() {
		return txt_qns_7;
	}

	public void setTxt_qns_7(String txt_qns_7) {
		this.txt_qns_7 = txt_qns_7;
	}

	public String getTxt_qns_8() {
		return txt_qns_8;
	}

	public void setTxt_qns_8(String txt_qns_8) {
		this.txt_qns_8 = txt_qns_8;
	}

	public String getTxt_qns_9() {
		return txt_qns_9;
	}

	public void setTxt_qns_9(String txt_qns_9) {
		this.txt_qns_9 = txt_qns_9;
	}

	public String getTxt_qns_10() {
		return txt_qns_10;
	}

	public void setTxt_qns_10(String txt_qns_10) {
		this.txt_qns_10 = txt_qns_10;
	}

	public String getTxt_qns_11() {
		return txt_qns_11;
	}

	public void setTxt_qns_11(String txt_qns_11) {
		this.txt_qns_11 = txt_qns_11;
	}

	public String getTxt_qns_12() {
		return txt_qns_12;
	}

	public void setTxt_qns_12(String txt_qns_12) {
		this.txt_qns_12 = txt_qns_12;
	}

	public String getTxt_qns_13() {
		return txt_qns_13;
	}

	public void setTxt_qns_13(String txt_qns_13) {
		this.txt_qns_13 = txt_qns_13;
	}

	public String getTxt_qns_14() {
		return txt_qns_14;
	}

	public void setTxt_qns_14(String txt_qns_14) {
		this.txt_qns_14 = txt_qns_14;
	}

	public String getTxt_qns_15() {
		return txt_qns_15;
	}

	public void setTxt_qns_15(String txt_qns_15) {
		this.txt_qns_15 = txt_qns_15;
	}

	public String getTxt_qns_16() {
		return txt_qns_16;
	}

	public void setTxt_qns_16(String txt_qns_16) {
		this.txt_qns_16 = txt_qns_16;
	}

	public String getTxt_qns_17() {
		return txt_qns_17;
	}

	public void setTxt_qns_17(String txt_qns_17) {
		this.txt_qns_17 = txt_qns_17;
	}

	public String getTxt_qns_18() {
		return txt_qns_18;
	}

	public void setTxt_qns_18(String txt_qns_18) {
		this.txt_qns_18 = txt_qns_18;
	}

	public String getTxt_qns_19() {
		return txt_qns_19;
	}

	public void setTxt_qns_19(String txt_qns_19) {
		this.txt_qns_19 = txt_qns_19;
	}

	public String getTxt_qns_20() {
		return txt_qns_20;
	}

	public void setTxt_qns_20(String txt_qns_20) {
		this.txt_qns_20 = txt_qns_20;
	}

	public String getTxt_qns_21() {
		return txt_qns_21;
	}

	public void setTxt_qns_21(String txt_qns_21) {
		this.txt_qns_21 = txt_qns_21;
	}

	public String getTxt_qns_22() {
		return txt_qns_22;
	}

	public void setTxt_qns_22(String txt_qns_22) {
		this.txt_qns_22 = txt_qns_22;
	}

	public String getTxt_qns_23() {
		return txt_qns_23;
	}

	public void setTxt_qns_23(String txt_qns_23) {
		this.txt_qns_23 = txt_qns_23;
	}

	public String getTxt_qns_24() {
		return txt_qns_24;
	}

	public void setTxt_qns_24(String txt_qns_24) {
		this.txt_qns_24 = txt_qns_24;
	}

	public String getTxt_qns_25() {
		return txt_qns_25;
	}

	public void setTxt_qns_25(String txt_qns_25) {
		this.txt_qns_25 = txt_qns_25;
	}

	public String getTxt_qns_26() {
		return txt_qns_26;
	}

	public void setTxt_qns_26(String txt_qns_26) {
		this.txt_qns_26 = txt_qns_26;
	}

	public String getTxt_qns_27() {
		return txt_qns_27;
	}

	public void setTxt_qns_27(String txt_qns_27) {
		this.txt_qns_27 = txt_qns_27;
	}

	public String getTxt_qns_28() {
		return txt_qns_28;
	}

	public void setTxt_qns_28(String txt_qns_28) {
		this.txt_qns_28 = txt_qns_28;
	}

	public String getTxt_qns_29() {
		return txt_qns_29;
	}

	public void setTxt_qns_29(String txt_qns_29) {
		this.txt_qns_29 = txt_qns_29;
	}

	public String getTxt_qns_30() {
		return txt_qns_30;
	}

	public void setTxt_qns_30(String txt_qns_30) {
		this.txt_qns_30 = txt_qns_30;
	}

	public String getTxt_qns_31() {
		return txt_qns_31;
	}

	public void setTxt_qns_31(String txt_qns_31) {
		this.txt_qns_31 = txt_qns_31;
	}

	public String getTxt_qns_32() {
		return txt_qns_32;
	}

	public void setTxt_qns_32(String txt_qns_32) {
		this.txt_qns_32 = txt_qns_32;
	}

	public String getTxt_qns_33() {
		return txt_qns_33;
	}

	public void setTxt_qns_33(String txt_qns_33) {
		this.txt_qns_33 = txt_qns_33;
	}

	public String getTxt_qns_34() {
		return txt_qns_34;
	}

	public void setTxt_qns_34(String txt_qns_34) {
		this.txt_qns_34 = txt_qns_34;
	}

	public String getTxt_qns_35() {
		return txt_qns_35;
	}

	public void setTxt_qns_35(String txt_qns_35) {
		this.txt_qns_35 = txt_qns_35;
	}

	public String getTxt_qns_36() {
		return txt_qns_36;
	}

	public void setTxt_qns_36(String txt_qns_36) {
		this.txt_qns_36 = txt_qns_36;
	}

	public String getTxt_qns_37() {
		return txt_qns_37;
	}

	public void setTxt_qns_37(String txt_qns_37) {
		this.txt_qns_37 = txt_qns_37;
	}

	public String getTxt_qns_38() {
		return txt_qns_38;
	}

	public void setTxt_qns_38(String txt_qns_38) {
		this.txt_qns_38 = txt_qns_38;
	}

	public String getTxt_qns_39() {
		return txt_qns_39;
	}

	public void setTxt_qns_39(String txt_qns_39) {
		this.txt_qns_39 = txt_qns_39;
	}

	public String getTxt_qns_40() {
		return txt_qns_40;
	}

	public void setTxt_qns_40(String txt_qns_40) {
		this.txt_qns_40 = txt_qns_40;
	}

	public String getTxt_qns_41() {
		return txt_qns_41;
	}

	public void setTxt_qns_41(String txt_qns_41) {
		this.txt_qns_41 = txt_qns_41;
	}

	public String getTxt_qns_42() {
		return txt_qns_42;
	}

	public void setTxt_qns_42(String txt_qns_42) {
		this.txt_qns_42 = txt_qns_42;
	}

	public String getTxt_qns_43() {
		return txt_qns_43;
	}

	public void setTxt_qns_43(String txt_qns_43) {
		this.txt_qns_43 = txt_qns_43;
	}

	public String getTxt_qns_44() {
		return txt_qns_44;
	}

	public void setTxt_qns_44(String txt_qns_44) {
		this.txt_qns_44 = txt_qns_44;
	}

	public String getTxt_qns_45() {
		return txt_qns_45;
	}

	public void setTxt_qns_45(String txt_qns_45) {
		this.txt_qns_45 = txt_qns_45;
	}

	public String getTxt_qns_46() {
		return txt_qns_46;
	}

	public void setTxt_qns_46(String txt_qns_46) {
		this.txt_qns_46 = txt_qns_46;
	}

	public String getTxt_qns_47() {
		return txt_qns_47;
	}

	public void setTxt_qns_47(String txt_qns_47) {
		this.txt_qns_47 = txt_qns_47;
	}

	public String getTxt_qns_48() {
		return txt_qns_48;
	}

	public void setTxt_qns_48(String txt_qns_48) {
		this.txt_qns_48 = txt_qns_48;
	}

	public String getTxt_qns_49() {
		return txt_qns_49;
	}

	public void setTxt_qns_49(String txt_qns_49) {
		this.txt_qns_49 = txt_qns_49;
	}

	public String getTxt_qns_50() {
		return txt_qns_50;
	}

	public void setTxt_qns_50(String txt_qns_50) {
		this.txt_qns_50 = txt_qns_50;
	}

	public String getTxt_fld_1() {
		return txt_fld_1;
	}

	public void setTxt_fld_1(String txt_fld_1) {
		this.txt_fld_1 = txt_fld_1;
	}

	public String getTxt_fld_2() {
		return txt_fld_2;
	}

	public void setTxt_fld_2(String txt_fld_2) {
		this.txt_fld_2 = txt_fld_2;
	}

	public String getTxt_fld_3() {
		return txt_fld_3;
	}

	public void setTxt_fld_3(String txt_fld_3) {
		this.txt_fld_3 = txt_fld_3;
	}

	public String getTxt_fld_4() {
		return txt_fld_4;
	}

	public void setTxt_fld_4(String txt_fld_4) {
		this.txt_fld_4 = txt_fld_4;
	}

	public String getTxt_fld_5() {
		return txt_fld_5;
	}

	public void setTxt_fld_5(String txt_fld_5) {
		this.txt_fld_5 = txt_fld_5;
	}

	public String getTxt_fld_6() {
		return txt_fld_6;
	}

	public void setTxt_fld_6(String txt_fld_6) {
		this.txt_fld_6 = txt_fld_6;
	}

	public String getTxt_fld_7() {
		return txt_fld_7;
	}

	public void setTxt_fld_7(String txt_fld_7) {
		this.txt_fld_7 = txt_fld_7;
	}

	public String getTxt_fld_8() {
		return txt_fld_8;
	}

	public void setTxt_fld_8(String txt_fld_8) {
		this.txt_fld_8 = txt_fld_8;
	}

	public String getTxt_fld_9() {
		return txt_fld_9;
	}

	public void setTxt_fld_9(String txt_fld_9) {
		this.txt_fld_9 = txt_fld_9;
	}

	public String getTxt_fld_10() {
		return txt_fld_10;
	}

	public void setTxt_fld_10(String txt_fld_10) {
		this.txt_fld_10 = txt_fld_10;
	}

	public String getTxt_fld_11() {
		return txt_fld_11;
	}

	public void setTxt_fld_11(String txt_fld_11) {
		this.txt_fld_11 = txt_fld_11;
	}

	public String getTxt_fld_12() {
		return txt_fld_12;
	}

	public void setTxt_fld_12(String txt_fld_12) {
		this.txt_fld_12 = txt_fld_12;
	}

	public String getTxt_fld_13() {
		return txt_fld_13;
	}

	public void setTxt_fld_13(String txt_fld_13) {
		this.txt_fld_13 = txt_fld_13;
	}

	public String getTxt_fld_14() {
		return txt_fld_14;
	}

	public void setTxt_fld_14(String txt_fld_14) {
		this.txt_fld_14 = txt_fld_14;
	}

	public String getTxt_fld_15() {
		return txt_fld_15;
	}

	public void setTxt_fld_15(String txt_fld_15) {
		this.txt_fld_15 = txt_fld_15;
	}

	public String getTxt_fld_16() {
		return txt_fld_16;
	}

	public void setTxt_fld_16(String txt_fld_16) {
		this.txt_fld_16 = txt_fld_16;
	}

	public String getTxt_fld_17() {
		return txt_fld_17;
	}

	public void setTxt_fld_17(String txt_fld_17) {
		this.txt_fld_17 = txt_fld_17;
	}

	public String getTxt_fld_18() {
		return txt_fld_18;
	}

	public void setTxt_fld_18(String txt_fld_18) {
		this.txt_fld_18 = txt_fld_18;
	}

	public String getTxt_fld_19() {
		return txt_fld_19;
	}

	public void setTxt_fld_19(String txt_fld_19) {
		this.txt_fld_19 = txt_fld_19;
	}

	public String getTxt_fld_20() {
		return txt_fld_20;
	}

	public void setTxt_fld_20(String txt_fld_20) {
		this.txt_fld_20 = txt_fld_20;
	}

	public String getTxt_fld_21() {
		return txt_fld_21;
	}

	public void setTxt_fld_21(String txt_fld_21) {
		this.txt_fld_21 = txt_fld_21;
	}

	public String getTxt_fld_22() {
		return txt_fld_22;
	}

	public void setTxt_fld_22(String txt_fld_22) {
		this.txt_fld_22 = txt_fld_22;
	}

	public String getTxt_fld_23() {
		return txt_fld_23;
	}

	public void setTxt_fld_23(String txt_fld_23) {
		this.txt_fld_23 = txt_fld_23;
	}

	public String getTxt_fld_24() {
		return txt_fld_24;
	}

	public void setTxt_fld_24(String txt_fld_24) {
		this.txt_fld_24 = txt_fld_24;
	}

	public String getTxt_fld_25() {
		return txt_fld_25;
	}

	public void setTxt_fld_25(String txt_fld_25) {
		this.txt_fld_25 = txt_fld_25;
	}

	public String getTxt_fld_26() {
		return txt_fld_26;
	}

	public void setTxt_fld_26(String txt_fld_26) {
		this.txt_fld_26 = txt_fld_26;
	}

	public String getTxt_fld_27() {
		return txt_fld_27;
	}

	public void setTxt_fld_27(String txt_fld_27) {
		this.txt_fld_27 = txt_fld_27;
	}

	public String getTxt_fld_28() {
		return txt_fld_28;
	}

	public void setTxt_fld_28(String txt_fld_28) {
		this.txt_fld_28 = txt_fld_28;
	}

	public String getTxt_fld_29() {
		return txt_fld_29;
	}

	public void setTxt_fld_29(String txt_fld_29) {
		this.txt_fld_29 = txt_fld_29;
	}

	public String getTxt_fld_30() {
		return txt_fld_30;
	}

	public void setTxt_fld_30(String txt_fld_30) {
		this.txt_fld_30 = txt_fld_30;
	}

	public String getTxt_fld_31() {
		return txt_fld_31;
	}

	public void setTxt_fld_31(String txt_fld_31) {
		this.txt_fld_31 = txt_fld_31;
	}

	public String getTxt_fld_32() {
		return txt_fld_32;
	}

	public void setTxt_fld_32(String txt_fld_32) {
		this.txt_fld_32 = txt_fld_32;
	}

	public String getTxt_fld_33() {
		return txt_fld_33;
	}

	public void setTxt_fld_33(String txt_fld_33) {
		this.txt_fld_33 = txt_fld_33;
	}

	public String getTxt_fld_34() {
		return txt_fld_34;
	}

	public void setTxt_fld_34(String txt_fld_34) {
		this.txt_fld_34 = txt_fld_34;
	}

	public String getTxt_fld_35() {
		return txt_fld_35;
	}

	public void setTxt_fld_35(String txt_fld_35) {
		this.txt_fld_35 = txt_fld_35;
	}

	public String getTxt_fld_36() {
		return txt_fld_36;
	}

	public void setTxt_fld_36(String txt_fld_36) {
		this.txt_fld_36 = txt_fld_36;
	}

	public String getTxt_fld_37() {
		return txt_fld_37;
	}

	public void setTxt_fld_37(String txt_fld_37) {
		this.txt_fld_37 = txt_fld_37;
	}

	public String getTxt_fld_38() {
		return txt_fld_38;
	}

	public void setTxt_fld_38(String txt_fld_38) {
		this.txt_fld_38 = txt_fld_38;
	}

	public String getTxt_fld_39() {
		return txt_fld_39;
	}

	public void setTxt_fld_39(String txt_fld_39) {
		this.txt_fld_39 = txt_fld_39;
	}

	public String getTxt_fld_40() {
		return txt_fld_40;
	}

	public void setTxt_fld_40(String txt_fld_40) {
		this.txt_fld_40 = txt_fld_40;
	}

	public String getTxt_fld_41() {
		return txt_fld_41;
	}

	public void setTxt_fld_41(String txt_fld_41) {
		this.txt_fld_41 = txt_fld_41;
	}

	public String getTxt_fld_42() {
		return txt_fld_42;
	}

	public void setTxt_fld_42(String txt_fld_42) {
		this.txt_fld_42 = txt_fld_42;
	}

	public String getTxt_fld_43() {
		return txt_fld_43;
	}

	public void setTxt_fld_43(String txt_fld_43) {
		this.txt_fld_43 = txt_fld_43;
	}

	public String getTxt_fld_44() {
		return txt_fld_44;
	}

	public void setTxt_fld_44(String txt_fld_44) {
		this.txt_fld_44 = txt_fld_44;
	}

	public String getTxt_fld_45() {
		return txt_fld_45;
	}

	public void setTxt_fld_45(String txt_fld_45) {
		this.txt_fld_45 = txt_fld_45;
	}

	public String getTxt_fld_46() {
		return txt_fld_46;
	}

	public void setTxt_fld_46(String txt_fld_46) {
		this.txt_fld_46 = txt_fld_46;
	}

	public String getTxt_fld_47() {
		return txt_fld_47;
	}

	public void setTxt_fld_47(String txt_fld_47) {
		this.txt_fld_47 = txt_fld_47;
	}

	public String getTxt_fld_48() {
		return txt_fld_48;
	}

	public void setTxt_fld_48(String txt_fld_48) {
		this.txt_fld_48 = txt_fld_48;
	}

	public String getTxt_fld_49() {
		return txt_fld_49;
	}

	public void setTxt_fld_49(String txt_fld_49) {
		this.txt_fld_49 = txt_fld_49;
	}

	public String getTxt_fld_50() {
		return txt_fld_50;
	}

	public void setTxt_fld_50(String txt_fld_50) {
		this.txt_fld_50 = txt_fld_50;
	}

	public byte[] getBlob_fld_1() {
		return blob_fld_1;
	}

	public void setBlob_fld_1(byte[] blob_fld_1) {
		this.blob_fld_1 = blob_fld_1;
	}

	public byte[] getBlob_fld_2() {
		return blob_fld_2;
	}

	public void setBlob_fld_2(byte[] blob_fld_2) {
		this.blob_fld_2 = blob_fld_2;
	}

	public byte[] getBlob_fld_3() {
		return blob_fld_3;
	}

	public void setBlob_fld_3(byte[] blob_fld_3) {
		this.blob_fld_3 = blob_fld_3;
	}

	public byte[] getBlob_fld_4() {
		return blob_fld_4;
	}

	public void setBlob_fld_4(byte[] blob_fld_4) {
		this.blob_fld_4 = blob_fld_4;
	}

	public byte[] getBlob_fld_5() {
		return blob_fld_5;
	}

	public void setBlob_fld_5(byte[] blob_fld_5) {
		this.blob_fld_5 = blob_fld_5;
	}

	public byte[] getBlob_fld_6() {
		return blob_fld_6;
	}

	public void setBlob_fld_6(byte[] blob_fld_6) {
		this.blob_fld_6 = blob_fld_6;
	}

	public byte[] getBlob_fld_7() {
		return blob_fld_7;
	}

	public void setBlob_fld_7(byte[] blob_fld_7) {
		this.blob_fld_7 = blob_fld_7;
	}

	public byte[] getBlob_fld_8() {
		return blob_fld_8;
	}

	public void setBlob_fld_8(byte[] blob_fld_8) {
		this.blob_fld_8 = blob_fld_8;
	}

	public byte[] getBlob_fld_9() {
		return blob_fld_9;
	}

	public void setBlob_fld_9(byte[] blob_fld_9) {
		this.blob_fld_9 = blob_fld_9;
	}

	public byte[] getBlob_fld_10() {
		return blob_fld_10;
	}

	public void setBlob_fld_10(byte[] blob_fld_10) {
		this.blob_fld_10 = blob_fld_10;
	}

	public Point getGis_fld_1() {
		return gis_fld_1;
	}

	public void setGis_fld_1(Point gis_fld_1) {
		this.gis_fld_1 = gis_fld_1;
	}

	public Point getGis_fld_2() {
		return gis_fld_2;
	}

	public void setGis_fld_2(Point gis_fld_2) {
		this.gis_fld_2 = gis_fld_2;
	}

	public Point getGis_fld_3() {
		return gis_fld_3;
	}

	public void setGis_fld_3(Point gis_fld_3) {
		this.gis_fld_3 = gis_fld_3;
	}

	public Point getGis_fld_4() {
		return gis_fld_4;
	}

	public void setGis_fld_4(Point gis_fld_4) {
		this.gis_fld_4 = gis_fld_4;
	}

	public Point getGis_fld_5() {
		return gis_fld_5;
	}

	public void setGis_fld_5(Point gis_fld_5) {
		this.gis_fld_5 = gis_fld_5;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getSecuredScore() {
		return securedScore;
	}

	public void setSecuredScore(String securedScore) {
		this.securedScore = securedScore;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", surveyName=" + surveyName
				+ ", surveyType=" + surveyType + ", phase=" + phase
				+ ", networkId=" + networkId + ", userId=" + userId
				+ ", totalScore=" + totalScore + ", securedScore="
				+ securedScore + ", recordStatus=" + recordStatus
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", authBy=" + authBy + ", authStat=" + authStat + ", authTs="
				+ authTs + ", txt_qns_1=" + txt_qns_1 + ", txt_qns_2="
				+ txt_qns_2 + ", txt_qns_3=" + txt_qns_3 + ", txt_qns_4="
				+ txt_qns_4 + ", txt_qns_5=" + txt_qns_5 + ", txt_qns_6="
				+ txt_qns_6 + ", txt_qns_7=" + txt_qns_7 + ", txt_qns_8="
				+ txt_qns_8 + ", txt_qns_9=" + txt_qns_9 + ", txt_qns_10="
				+ txt_qns_10 + ", txt_qns_11=" + txt_qns_11 + ", txt_qns_12="
				+ txt_qns_12 + ", txt_qns_13=" + txt_qns_13 + ", txt_qns_14="
				+ txt_qns_14 + ", txt_qns_15=" + txt_qns_15 + ", txt_qns_16="
				+ txt_qns_16 + ", txt_qns_17=" + txt_qns_17 + ", txt_qns_18="
				+ txt_qns_18 + ", txt_qns_19=" + txt_qns_19 + ", txt_qns_20="
				+ txt_qns_20 + ", txt_qns_21=" + txt_qns_21 + ", txt_qns_22="
				+ txt_qns_22 + ", txt_qns_23=" + txt_qns_23 + ", txt_qns_24="
				+ txt_qns_24 + ", txt_qns_25=" + txt_qns_25 + ", txt_qns_26="
				+ txt_qns_26 + ", txt_qns_27=" + txt_qns_27 + ", txt_qns_28="
				+ txt_qns_28 + ", txt_qns_29=" + txt_qns_29 + ", txt_qns_30="
				+ txt_qns_30 + ", txt_qns_31=" + txt_qns_31 + ", txt_qns_32="
				+ txt_qns_32 + ", txt_qns_33=" + txt_qns_33 + ", txt_qns_34="
				+ txt_qns_34 + ", txt_qns_35=" + txt_qns_35 + ", txt_qns_36="
				+ txt_qns_36 + ", txt_qns_37=" + txt_qns_37 + ", txt_qns_38="
				+ txt_qns_38 + ", txt_qns_39=" + txt_qns_39 + ", txt_qns_40="
				+ txt_qns_40 + ", txt_qns_41=" + txt_qns_41 + ", txt_qns_42="
				+ txt_qns_42 + ", txt_qns_43=" + txt_qns_43 + ", txt_qns_44="
				+ txt_qns_44 + ", txt_qns_45=" + txt_qns_45 + ", txt_qns_46="
				+ txt_qns_46 + ", txt_qns_47=" + txt_qns_47 + ", txt_qns_48="
				+ txt_qns_48 + ", txt_qns_49=" + txt_qns_49 + ", txt_qns_50="
				+ txt_qns_50 + ", txt_fld_1=" + txt_fld_1 + ", txt_fld_2="
				+ txt_fld_2 + ", txt_fld_3=" + txt_fld_3 + ", txt_fld_4="
				+ txt_fld_4 + ", txt_fld_5=" + txt_fld_5 + ", txt_fld_6="
				+ txt_fld_6 + ", txt_fld_7=" + txt_fld_7 + ", txt_fld_8="
				+ txt_fld_8 + ", txt_fld_9=" + txt_fld_9 + ", txt_fld_10="
				+ txt_fld_10 + ", txt_fld_11=" + txt_fld_11 + ", txt_fld_12="
				+ txt_fld_12 + ", txt_fld_13=" + txt_fld_13 + ", txt_fld_14="
				+ txt_fld_14 + ", txt_fld_15=" + txt_fld_15 + ", txt_fld_16="
				+ txt_fld_16 + ", txt_fld_17=" + txt_fld_17 + ", txt_fld_18="
				+ txt_fld_18 + ", txt_fld_19=" + txt_fld_19 + ", txt_fld_20="
				+ txt_fld_20 + ", txt_fld_21=" + txt_fld_21 + ", txt_fld_22="
				+ txt_fld_22 + ", txt_fld_23=" + txt_fld_23 + ", txt_fld_24="
				+ txt_fld_24 + ", txt_fld_25=" + txt_fld_25 + ", txt_fld_26="
				+ txt_fld_26 + ", txt_fld_27=" + txt_fld_27 + ", txt_fld_28="
				+ txt_fld_28 + ", txt_fld_29=" + txt_fld_29 + ", txt_fld_30="
				+ txt_fld_30 + ", txt_fld_31=" + txt_fld_31 + ", txt_fld_32="
				+ txt_fld_32 + ", txt_fld_33=" + txt_fld_33 + ", txt_fld_34="
				+ txt_fld_34 + ", txt_fld_35=" + txt_fld_35 + ", txt_fld_36="
				+ txt_fld_36 + ", txt_fld_37=" + txt_fld_37 + ", txt_fld_38="
				+ txt_fld_38 + ", txt_fld_39=" + txt_fld_39 + ", txt_fld_40="
				+ txt_fld_40 + ", txt_fld_41=" + txt_fld_41 + ", txt_fld_42="
				+ txt_fld_42 + ", txt_fld_43=" + txt_fld_43 + ", txt_fld_44="
				+ txt_fld_44 + ", txt_fld_45=" + txt_fld_45 + ", txt_fld_46="
				+ txt_fld_46 + ", txt_fld_47=" + txt_fld_47 + ", txt_fld_48="
				+ txt_fld_48 + ", txt_fld_49=" + txt_fld_49 + ", txt_fld_50="
				+ txt_fld_50 + ", blob_fld_1=" + Arrays.toString(blob_fld_1)
				+ ", blob_fld_2=" + Arrays.toString(blob_fld_2)
				+ ", blob_fld_3=" + Arrays.toString(blob_fld_3)
				+ ", blob_fld_4=" + Arrays.toString(blob_fld_4)
				+ ", blob_fld_5=" + Arrays.toString(blob_fld_5)
				+ ", blob_fld_6=" + Arrays.toString(blob_fld_6)
				+ ", blob_fld_7=" + Arrays.toString(blob_fld_7)
				+ ", blob_fld_8=" + Arrays.toString(blob_fld_8)
				+ ", blob_fld_9=" + Arrays.toString(blob_fld_9)
				+ ", blob_fld_10=" + Arrays.toString(blob_fld_10)
				+ ", gis_fld_1=" + gis_fld_1 + ", gis_fld_2=" + gis_fld_2
				+ ", gis_fld_3=" + gis_fld_3 + ", gis_fld_4=" + gis_fld_4
				+ ", gis_fld_5=" + gis_fld_5 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authBy == null) ? 0 : authBy.hashCode());
		result = prime * result
				+ ((authStat == null) ? 0 : authStat.hashCode());
		result = prime * result + ((authTs == null) ? 0 : authTs.hashCode());
		result = prime * result + Arrays.hashCode(blob_fld_1);
		result = prime * result + Arrays.hashCode(blob_fld_10);
		result = prime * result + Arrays.hashCode(blob_fld_2);
		result = prime * result + Arrays.hashCode(blob_fld_3);
		result = prime * result + Arrays.hashCode(blob_fld_4);
		result = prime * result + Arrays.hashCode(blob_fld_5);
		result = prime * result + Arrays.hashCode(blob_fld_6);
		result = prime * result + Arrays.hashCode(blob_fld_7);
		result = prime * result + Arrays.hashCode(blob_fld_8);
		result = prime * result + Arrays.hashCode(blob_fld_9);
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((gis_fld_1 == null) ? 0 : gis_fld_1.hashCode());
		result = prime * result
				+ ((gis_fld_2 == null) ? 0 : gis_fld_2.hashCode());
		result = prime * result
				+ ((gis_fld_3 == null) ? 0 : gis_fld_3.hashCode());
		result = prime * result
				+ ((gis_fld_4 == null) ? 0 : gis_fld_4.hashCode());
		result = prime * result
				+ ((gis_fld_5 == null) ? 0 : gis_fld_5.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result + ((phase == null) ? 0 : phase.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result
				+ ((securedScore == null) ? 0 : securedScore.hashCode());
		result = prime * result + surveyId;
		result = prime * result
				+ ((surveyName == null) ? 0 : surveyName.hashCode());
		result = prime * result
				+ ((surveyType == null) ? 0 : surveyType.hashCode());
		result = prime * result
				+ ((totalScore == null) ? 0 : totalScore.hashCode());
		result = prime * result
				+ ((txt_fld_1 == null) ? 0 : txt_fld_1.hashCode());
		result = prime * result
				+ ((txt_fld_10 == null) ? 0 : txt_fld_10.hashCode());
		result = prime * result
				+ ((txt_fld_11 == null) ? 0 : txt_fld_11.hashCode());
		result = prime * result
				+ ((txt_fld_12 == null) ? 0 : txt_fld_12.hashCode());
		result = prime * result
				+ ((txt_fld_13 == null) ? 0 : txt_fld_13.hashCode());
		result = prime * result
				+ ((txt_fld_14 == null) ? 0 : txt_fld_14.hashCode());
		result = prime * result
				+ ((txt_fld_15 == null) ? 0 : txt_fld_15.hashCode());
		result = prime * result
				+ ((txt_fld_16 == null) ? 0 : txt_fld_16.hashCode());
		result = prime * result
				+ ((txt_fld_17 == null) ? 0 : txt_fld_17.hashCode());
		result = prime * result
				+ ((txt_fld_18 == null) ? 0 : txt_fld_18.hashCode());
		result = prime * result
				+ ((txt_fld_19 == null) ? 0 : txt_fld_19.hashCode());
		result = prime * result
				+ ((txt_fld_2 == null) ? 0 : txt_fld_2.hashCode());
		result = prime * result
				+ ((txt_fld_20 == null) ? 0 : txt_fld_20.hashCode());
		result = prime * result
				+ ((txt_fld_21 == null) ? 0 : txt_fld_21.hashCode());
		result = prime * result
				+ ((txt_fld_22 == null) ? 0 : txt_fld_22.hashCode());
		result = prime * result
				+ ((txt_fld_23 == null) ? 0 : txt_fld_23.hashCode());
		result = prime * result
				+ ((txt_fld_24 == null) ? 0 : txt_fld_24.hashCode());
		result = prime * result
				+ ((txt_fld_25 == null) ? 0 : txt_fld_25.hashCode());
		result = prime * result
				+ ((txt_fld_26 == null) ? 0 : txt_fld_26.hashCode());
		result = prime * result
				+ ((txt_fld_27 == null) ? 0 : txt_fld_27.hashCode());
		result = prime * result
				+ ((txt_fld_28 == null) ? 0 : txt_fld_28.hashCode());
		result = prime * result
				+ ((txt_fld_29 == null) ? 0 : txt_fld_29.hashCode());
		result = prime * result
				+ ((txt_fld_3 == null) ? 0 : txt_fld_3.hashCode());
		result = prime * result
				+ ((txt_fld_30 == null) ? 0 : txt_fld_30.hashCode());
		result = prime * result
				+ ((txt_fld_31 == null) ? 0 : txt_fld_31.hashCode());
		result = prime * result
				+ ((txt_fld_32 == null) ? 0 : txt_fld_32.hashCode());
		result = prime * result
				+ ((txt_fld_33 == null) ? 0 : txt_fld_33.hashCode());
		result = prime * result
				+ ((txt_fld_34 == null) ? 0 : txt_fld_34.hashCode());
		result = prime * result
				+ ((txt_fld_35 == null) ? 0 : txt_fld_35.hashCode());
		result = prime * result
				+ ((txt_fld_36 == null) ? 0 : txt_fld_36.hashCode());
		result = prime * result
				+ ((txt_fld_37 == null) ? 0 : txt_fld_37.hashCode());
		result = prime * result
				+ ((txt_fld_38 == null) ? 0 : txt_fld_38.hashCode());
		result = prime * result
				+ ((txt_fld_39 == null) ? 0 : txt_fld_39.hashCode());
		result = prime * result
				+ ((txt_fld_4 == null) ? 0 : txt_fld_4.hashCode());
		result = prime * result
				+ ((txt_fld_40 == null) ? 0 : txt_fld_40.hashCode());
		result = prime * result
				+ ((txt_fld_41 == null) ? 0 : txt_fld_41.hashCode());
		result = prime * result
				+ ((txt_fld_42 == null) ? 0 : txt_fld_42.hashCode());
		result = prime * result
				+ ((txt_fld_43 == null) ? 0 : txt_fld_43.hashCode());
		result = prime * result
				+ ((txt_fld_44 == null) ? 0 : txt_fld_44.hashCode());
		result = prime * result
				+ ((txt_fld_45 == null) ? 0 : txt_fld_45.hashCode());
		result = prime * result
				+ ((txt_fld_46 == null) ? 0 : txt_fld_46.hashCode());
		result = prime * result
				+ ((txt_fld_47 == null) ? 0 : txt_fld_47.hashCode());
		result = prime * result
				+ ((txt_fld_48 == null) ? 0 : txt_fld_48.hashCode());
		result = prime * result
				+ ((txt_fld_49 == null) ? 0 : txt_fld_49.hashCode());
		result = prime * result
				+ ((txt_fld_5 == null) ? 0 : txt_fld_5.hashCode());
		result = prime * result
				+ ((txt_fld_50 == null) ? 0 : txt_fld_50.hashCode());
		result = prime * result
				+ ((txt_fld_6 == null) ? 0 : txt_fld_6.hashCode());
		result = prime * result
				+ ((txt_fld_7 == null) ? 0 : txt_fld_7.hashCode());
		result = prime * result
				+ ((txt_fld_8 == null) ? 0 : txt_fld_8.hashCode());
		result = prime * result
				+ ((txt_fld_9 == null) ? 0 : txt_fld_9.hashCode());
		result = prime * result
				+ ((txt_qns_1 == null) ? 0 : txt_qns_1.hashCode());
		result = prime * result
				+ ((txt_qns_10 == null) ? 0 : txt_qns_10.hashCode());
		result = prime * result
				+ ((txt_qns_11 == null) ? 0 : txt_qns_11.hashCode());
		result = prime * result
				+ ((txt_qns_12 == null) ? 0 : txt_qns_12.hashCode());
		result = prime * result
				+ ((txt_qns_13 == null) ? 0 : txt_qns_13.hashCode());
		result = prime * result
				+ ((txt_qns_14 == null) ? 0 : txt_qns_14.hashCode());
		result = prime * result
				+ ((txt_qns_15 == null) ? 0 : txt_qns_15.hashCode());
		result = prime * result
				+ ((txt_qns_16 == null) ? 0 : txt_qns_16.hashCode());
		result = prime * result
				+ ((txt_qns_17 == null) ? 0 : txt_qns_17.hashCode());
		result = prime * result
				+ ((txt_qns_18 == null) ? 0 : txt_qns_18.hashCode());
		result = prime * result
				+ ((txt_qns_19 == null) ? 0 : txt_qns_19.hashCode());
		result = prime * result
				+ ((txt_qns_2 == null) ? 0 : txt_qns_2.hashCode());
		result = prime * result
				+ ((txt_qns_20 == null) ? 0 : txt_qns_20.hashCode());
		result = prime * result
				+ ((txt_qns_21 == null) ? 0 : txt_qns_21.hashCode());
		result = prime * result
				+ ((txt_qns_22 == null) ? 0 : txt_qns_22.hashCode());
		result = prime * result
				+ ((txt_qns_23 == null) ? 0 : txt_qns_23.hashCode());
		result = prime * result
				+ ((txt_qns_24 == null) ? 0 : txt_qns_24.hashCode());
		result = prime * result
				+ ((txt_qns_25 == null) ? 0 : txt_qns_25.hashCode());
		result = prime * result
				+ ((txt_qns_26 == null) ? 0 : txt_qns_26.hashCode());
		result = prime * result
				+ ((txt_qns_27 == null) ? 0 : txt_qns_27.hashCode());
		result = prime * result
				+ ((txt_qns_28 == null) ? 0 : txt_qns_28.hashCode());
		result = prime * result
				+ ((txt_qns_29 == null) ? 0 : txt_qns_29.hashCode());
		result = prime * result
				+ ((txt_qns_3 == null) ? 0 : txt_qns_3.hashCode());
		result = prime * result
				+ ((txt_qns_30 == null) ? 0 : txt_qns_30.hashCode());
		result = prime * result
				+ ((txt_qns_31 == null) ? 0 : txt_qns_31.hashCode());
		result = prime * result
				+ ((txt_qns_32 == null) ? 0 : txt_qns_32.hashCode());
		result = prime * result
				+ ((txt_qns_33 == null) ? 0 : txt_qns_33.hashCode());
		result = prime * result
				+ ((txt_qns_34 == null) ? 0 : txt_qns_34.hashCode());
		result = prime * result
				+ ((txt_qns_35 == null) ? 0 : txt_qns_35.hashCode());
		result = prime * result
				+ ((txt_qns_36 == null) ? 0 : txt_qns_36.hashCode());
		result = prime * result
				+ ((txt_qns_37 == null) ? 0 : txt_qns_37.hashCode());
		result = prime * result
				+ ((txt_qns_38 == null) ? 0 : txt_qns_38.hashCode());
		result = prime * result
				+ ((txt_qns_39 == null) ? 0 : txt_qns_39.hashCode());
		result = prime * result
				+ ((txt_qns_4 == null) ? 0 : txt_qns_4.hashCode());
		result = prime * result
				+ ((txt_qns_40 == null) ? 0 : txt_qns_40.hashCode());
		result = prime * result
				+ ((txt_qns_41 == null) ? 0 : txt_qns_41.hashCode());
		result = prime * result
				+ ((txt_qns_42 == null) ? 0 : txt_qns_42.hashCode());
		result = prime * result
				+ ((txt_qns_43 == null) ? 0 : txt_qns_43.hashCode());
		result = prime * result
				+ ((txt_qns_44 == null) ? 0 : txt_qns_44.hashCode());
		result = prime * result
				+ ((txt_qns_45 == null) ? 0 : txt_qns_45.hashCode());
		result = prime * result
				+ ((txt_qns_46 == null) ? 0 : txt_qns_46.hashCode());
		result = prime * result
				+ ((txt_qns_47 == null) ? 0 : txt_qns_47.hashCode());
		result = prime * result
				+ ((txt_qns_48 == null) ? 0 : txt_qns_48.hashCode());
		result = prime * result
				+ ((txt_qns_49 == null) ? 0 : txt_qns_49.hashCode());
		result = prime * result
				+ ((txt_qns_5 == null) ? 0 : txt_qns_5.hashCode());
		result = prime * result
				+ ((txt_qns_50 == null) ? 0 : txt_qns_50.hashCode());
		result = prime * result
				+ ((txt_qns_6 == null) ? 0 : txt_qns_6.hashCode());
		result = prime * result
				+ ((txt_qns_7 == null) ? 0 : txt_qns_7.hashCode());
		result = prime * result
				+ ((txt_qns_8 == null) ? 0 : txt_qns_8.hashCode());
		result = prime * result
				+ ((txt_qns_9 == null) ? 0 : txt_qns_9.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Survey other = (Survey) obj;
		if (authBy == null) {
			if (other.authBy != null)
				return false;
		} else if (!authBy.equals(other.authBy))
			return false;
		if (authStat == null) {
			if (other.authStat != null)
				return false;
		} else if (!authStat.equals(other.authStat))
			return false;
		if (authTs == null) {
			if (other.authTs != null)
				return false;
		} else if (!authTs.equals(other.authTs))
			return false;
		if (!Arrays.equals(blob_fld_1, other.blob_fld_1))
			return false;
		if (!Arrays.equals(blob_fld_10, other.blob_fld_10))
			return false;
		if (!Arrays.equals(blob_fld_2, other.blob_fld_2))
			return false;
		if (!Arrays.equals(blob_fld_3, other.blob_fld_3))
			return false;
		if (!Arrays.equals(blob_fld_4, other.blob_fld_4))
			return false;
		if (!Arrays.equals(blob_fld_5, other.blob_fld_5))
			return false;
		if (!Arrays.equals(blob_fld_6, other.blob_fld_6))
			return false;
		if (!Arrays.equals(blob_fld_7, other.blob_fld_7))
			return false;
		if (!Arrays.equals(blob_fld_8, other.blob_fld_8))
			return false;
		if (!Arrays.equals(blob_fld_9, other.blob_fld_9))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (gis_fld_1 == null) {
			if (other.gis_fld_1 != null)
				return false;
		} else if (!gis_fld_1.equals(other.gis_fld_1))
			return false;
		if (gis_fld_2 == null) {
			if (other.gis_fld_2 != null)
				return false;
		} else if (!gis_fld_2.equals(other.gis_fld_2))
			return false;
		if (gis_fld_3 == null) {
			if (other.gis_fld_3 != null)
				return false;
		} else if (!gis_fld_3.equals(other.gis_fld_3))
			return false;
		if (gis_fld_4 == null) {
			if (other.gis_fld_4 != null)
				return false;
		} else if (!gis_fld_4.equals(other.gis_fld_4))
			return false;
		if (gis_fld_5 == null) {
			if (other.gis_fld_5 != null)
				return false;
		} else if (!gis_fld_5.equals(other.gis_fld_5))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (phase == null) {
			if (other.phase != null)
				return false;
		} else if (!phase.equals(other.phase))
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		if (securedScore == null) {
			if (other.securedScore != null)
				return false;
		} else if (!securedScore.equals(other.securedScore))
			return false;
		if (surveyId != other.surveyId)
			return false;
		if (surveyName == null) {
			if (other.surveyName != null)
				return false;
		} else if (!surveyName.equals(other.surveyName))
			return false;
		if (surveyType == null) {
			if (other.surveyType != null)
				return false;
		} else if (!surveyType.equals(other.surveyType))
			return false;
		if (totalScore == null) {
			if (other.totalScore != null)
				return false;
		} else if (!totalScore.equals(other.totalScore))
			return false;
		if (txt_fld_1 == null) {
			if (other.txt_fld_1 != null)
				return false;
		} else if (!txt_fld_1.equals(other.txt_fld_1))
			return false;
		if (txt_fld_10 == null) {
			if (other.txt_fld_10 != null)
				return false;
		} else if (!txt_fld_10.equals(other.txt_fld_10))
			return false;
		if (txt_fld_11 == null) {
			if (other.txt_fld_11 != null)
				return false;
		} else if (!txt_fld_11.equals(other.txt_fld_11))
			return false;
		if (txt_fld_12 == null) {
			if (other.txt_fld_12 != null)
				return false;
		} else if (!txt_fld_12.equals(other.txt_fld_12))
			return false;
		if (txt_fld_13 == null) {
			if (other.txt_fld_13 != null)
				return false;
		} else if (!txt_fld_13.equals(other.txt_fld_13))
			return false;
		if (txt_fld_14 == null) {
			if (other.txt_fld_14 != null)
				return false;
		} else if (!txt_fld_14.equals(other.txt_fld_14))
			return false;
		if (txt_fld_15 == null) {
			if (other.txt_fld_15 != null)
				return false;
		} else if (!txt_fld_15.equals(other.txt_fld_15))
			return false;
		if (txt_fld_16 == null) {
			if (other.txt_fld_16 != null)
				return false;
		} else if (!txt_fld_16.equals(other.txt_fld_16))
			return false;
		if (txt_fld_17 == null) {
			if (other.txt_fld_17 != null)
				return false;
		} else if (!txt_fld_17.equals(other.txt_fld_17))
			return false;
		if (txt_fld_18 == null) {
			if (other.txt_fld_18 != null)
				return false;
		} else if (!txt_fld_18.equals(other.txt_fld_18))
			return false;
		if (txt_fld_19 == null) {
			if (other.txt_fld_19 != null)
				return false;
		} else if (!txt_fld_19.equals(other.txt_fld_19))
			return false;
		if (txt_fld_2 == null) {
			if (other.txt_fld_2 != null)
				return false;
		} else if (!txt_fld_2.equals(other.txt_fld_2))
			return false;
		if (txt_fld_20 == null) {
			if (other.txt_fld_20 != null)
				return false;
		} else if (!txt_fld_20.equals(other.txt_fld_20))
			return false;
		if (txt_fld_21 == null) {
			if (other.txt_fld_21 != null)
				return false;
		} else if (!txt_fld_21.equals(other.txt_fld_21))
			return false;
		if (txt_fld_22 == null) {
			if (other.txt_fld_22 != null)
				return false;
		} else if (!txt_fld_22.equals(other.txt_fld_22))
			return false;
		if (txt_fld_23 == null) {
			if (other.txt_fld_23 != null)
				return false;
		} else if (!txt_fld_23.equals(other.txt_fld_23))
			return false;
		if (txt_fld_24 == null) {
			if (other.txt_fld_24 != null)
				return false;
		} else if (!txt_fld_24.equals(other.txt_fld_24))
			return false;
		if (txt_fld_25 == null) {
			if (other.txt_fld_25 != null)
				return false;
		} else if (!txt_fld_25.equals(other.txt_fld_25))
			return false;
		if (txt_fld_26 == null) {
			if (other.txt_fld_26 != null)
				return false;
		} else if (!txt_fld_26.equals(other.txt_fld_26))
			return false;
		if (txt_fld_27 == null) {
			if (other.txt_fld_27 != null)
				return false;
		} else if (!txt_fld_27.equals(other.txt_fld_27))
			return false;
		if (txt_fld_28 == null) {
			if (other.txt_fld_28 != null)
				return false;
		} else if (!txt_fld_28.equals(other.txt_fld_28))
			return false;
		if (txt_fld_29 == null) {
			if (other.txt_fld_29 != null)
				return false;
		} else if (!txt_fld_29.equals(other.txt_fld_29))
			return false;
		if (txt_fld_3 == null) {
			if (other.txt_fld_3 != null)
				return false;
		} else if (!txt_fld_3.equals(other.txt_fld_3))
			return false;
		if (txt_fld_30 == null) {
			if (other.txt_fld_30 != null)
				return false;
		} else if (!txt_fld_30.equals(other.txt_fld_30))
			return false;
		if (txt_fld_31 == null) {
			if (other.txt_fld_31 != null)
				return false;
		} else if (!txt_fld_31.equals(other.txt_fld_31))
			return false;
		if (txt_fld_32 == null) {
			if (other.txt_fld_32 != null)
				return false;
		} else if (!txt_fld_32.equals(other.txt_fld_32))
			return false;
		if (txt_fld_33 == null) {
			if (other.txt_fld_33 != null)
				return false;
		} else if (!txt_fld_33.equals(other.txt_fld_33))
			return false;
		if (txt_fld_34 == null) {
			if (other.txt_fld_34 != null)
				return false;
		} else if (!txt_fld_34.equals(other.txt_fld_34))
			return false;
		if (txt_fld_35 == null) {
			if (other.txt_fld_35 != null)
				return false;
		} else if (!txt_fld_35.equals(other.txt_fld_35))
			return false;
		if (txt_fld_36 == null) {
			if (other.txt_fld_36 != null)
				return false;
		} else if (!txt_fld_36.equals(other.txt_fld_36))
			return false;
		if (txt_fld_37 == null) {
			if (other.txt_fld_37 != null)
				return false;
		} else if (!txt_fld_37.equals(other.txt_fld_37))
			return false;
		if (txt_fld_38 == null) {
			if (other.txt_fld_38 != null)
				return false;
		} else if (!txt_fld_38.equals(other.txt_fld_38))
			return false;
		if (txt_fld_39 == null) {
			if (other.txt_fld_39 != null)
				return false;
		} else if (!txt_fld_39.equals(other.txt_fld_39))
			return false;
		if (txt_fld_4 == null) {
			if (other.txt_fld_4 != null)
				return false;
		} else if (!txt_fld_4.equals(other.txt_fld_4))
			return false;
		if (txt_fld_40 == null) {
			if (other.txt_fld_40 != null)
				return false;
		} else if (!txt_fld_40.equals(other.txt_fld_40))
			return false;
		if (txt_fld_41 == null) {
			if (other.txt_fld_41 != null)
				return false;
		} else if (!txt_fld_41.equals(other.txt_fld_41))
			return false;
		if (txt_fld_42 == null) {
			if (other.txt_fld_42 != null)
				return false;
		} else if (!txt_fld_42.equals(other.txt_fld_42))
			return false;
		if (txt_fld_43 == null) {
			if (other.txt_fld_43 != null)
				return false;
		} else if (!txt_fld_43.equals(other.txt_fld_43))
			return false;
		if (txt_fld_44 == null) {
			if (other.txt_fld_44 != null)
				return false;
		} else if (!txt_fld_44.equals(other.txt_fld_44))
			return false;
		if (txt_fld_45 == null) {
			if (other.txt_fld_45 != null)
				return false;
		} else if (!txt_fld_45.equals(other.txt_fld_45))
			return false;
		if (txt_fld_46 == null) {
			if (other.txt_fld_46 != null)
				return false;
		} else if (!txt_fld_46.equals(other.txt_fld_46))
			return false;
		if (txt_fld_47 == null) {
			if (other.txt_fld_47 != null)
				return false;
		} else if (!txt_fld_47.equals(other.txt_fld_47))
			return false;
		if (txt_fld_48 == null) {
			if (other.txt_fld_48 != null)
				return false;
		} else if (!txt_fld_48.equals(other.txt_fld_48))
			return false;
		if (txt_fld_49 == null) {
			if (other.txt_fld_49 != null)
				return false;
		} else if (!txt_fld_49.equals(other.txt_fld_49))
			return false;
		if (txt_fld_5 == null) {
			if (other.txt_fld_5 != null)
				return false;
		} else if (!txt_fld_5.equals(other.txt_fld_5))
			return false;
		if (txt_fld_50 == null) {
			if (other.txt_fld_50 != null)
				return false;
		} else if (!txt_fld_50.equals(other.txt_fld_50))
			return false;
		if (txt_fld_6 == null) {
			if (other.txt_fld_6 != null)
				return false;
		} else if (!txt_fld_6.equals(other.txt_fld_6))
			return false;
		if (txt_fld_7 == null) {
			if (other.txt_fld_7 != null)
				return false;
		} else if (!txt_fld_7.equals(other.txt_fld_7))
			return false;
		if (txt_fld_8 == null) {
			if (other.txt_fld_8 != null)
				return false;
		} else if (!txt_fld_8.equals(other.txt_fld_8))
			return false;
		if (txt_fld_9 == null) {
			if (other.txt_fld_9 != null)
				return false;
		} else if (!txt_fld_9.equals(other.txt_fld_9))
			return false;
		if (txt_qns_1 == null) {
			if (other.txt_qns_1 != null)
				return false;
		} else if (!txt_qns_1.equals(other.txt_qns_1))
			return false;
		if (txt_qns_10 == null) {
			if (other.txt_qns_10 != null)
				return false;
		} else if (!txt_qns_10.equals(other.txt_qns_10))
			return false;
		if (txt_qns_11 == null) {
			if (other.txt_qns_11 != null)
				return false;
		} else if (!txt_qns_11.equals(other.txt_qns_11))
			return false;
		if (txt_qns_12 == null) {
			if (other.txt_qns_12 != null)
				return false;
		} else if (!txt_qns_12.equals(other.txt_qns_12))
			return false;
		if (txt_qns_13 == null) {
			if (other.txt_qns_13 != null)
				return false;
		} else if (!txt_qns_13.equals(other.txt_qns_13))
			return false;
		if (txt_qns_14 == null) {
			if (other.txt_qns_14 != null)
				return false;
		} else if (!txt_qns_14.equals(other.txt_qns_14))
			return false;
		if (txt_qns_15 == null) {
			if (other.txt_qns_15 != null)
				return false;
		} else if (!txt_qns_15.equals(other.txt_qns_15))
			return false;
		if (txt_qns_16 == null) {
			if (other.txt_qns_16 != null)
				return false;
		} else if (!txt_qns_16.equals(other.txt_qns_16))
			return false;
		if (txt_qns_17 == null) {
			if (other.txt_qns_17 != null)
				return false;
		} else if (!txt_qns_17.equals(other.txt_qns_17))
			return false;
		if (txt_qns_18 == null) {
			if (other.txt_qns_18 != null)
				return false;
		} else if (!txt_qns_18.equals(other.txt_qns_18))
			return false;
		if (txt_qns_19 == null) {
			if (other.txt_qns_19 != null)
				return false;
		} else if (!txt_qns_19.equals(other.txt_qns_19))
			return false;
		if (txt_qns_2 == null) {
			if (other.txt_qns_2 != null)
				return false;
		} else if (!txt_qns_2.equals(other.txt_qns_2))
			return false;
		if (txt_qns_20 == null) {
			if (other.txt_qns_20 != null)
				return false;
		} else if (!txt_qns_20.equals(other.txt_qns_20))
			return false;
		if (txt_qns_21 == null) {
			if (other.txt_qns_21 != null)
				return false;
		} else if (!txt_qns_21.equals(other.txt_qns_21))
			return false;
		if (txt_qns_22 == null) {
			if (other.txt_qns_22 != null)
				return false;
		} else if (!txt_qns_22.equals(other.txt_qns_22))
			return false;
		if (txt_qns_23 == null) {
			if (other.txt_qns_23 != null)
				return false;
		} else if (!txt_qns_23.equals(other.txt_qns_23))
			return false;
		if (txt_qns_24 == null) {
			if (other.txt_qns_24 != null)
				return false;
		} else if (!txt_qns_24.equals(other.txt_qns_24))
			return false;
		if (txt_qns_25 == null) {
			if (other.txt_qns_25 != null)
				return false;
		} else if (!txt_qns_25.equals(other.txt_qns_25))
			return false;
		if (txt_qns_26 == null) {
			if (other.txt_qns_26 != null)
				return false;
		} else if (!txt_qns_26.equals(other.txt_qns_26))
			return false;
		if (txt_qns_27 == null) {
			if (other.txt_qns_27 != null)
				return false;
		} else if (!txt_qns_27.equals(other.txt_qns_27))
			return false;
		if (txt_qns_28 == null) {
			if (other.txt_qns_28 != null)
				return false;
		} else if (!txt_qns_28.equals(other.txt_qns_28))
			return false;
		if (txt_qns_29 == null) {
			if (other.txt_qns_29 != null)
				return false;
		} else if (!txt_qns_29.equals(other.txt_qns_29))
			return false;
		if (txt_qns_3 == null) {
			if (other.txt_qns_3 != null)
				return false;
		} else if (!txt_qns_3.equals(other.txt_qns_3))
			return false;
		if (txt_qns_30 == null) {
			if (other.txt_qns_30 != null)
				return false;
		} else if (!txt_qns_30.equals(other.txt_qns_30))
			return false;
		if (txt_qns_31 == null) {
			if (other.txt_qns_31 != null)
				return false;
		} else if (!txt_qns_31.equals(other.txt_qns_31))
			return false;
		if (txt_qns_32 == null) {
			if (other.txt_qns_32 != null)
				return false;
		} else if (!txt_qns_32.equals(other.txt_qns_32))
			return false;
		if (txt_qns_33 == null) {
			if (other.txt_qns_33 != null)
				return false;
		} else if (!txt_qns_33.equals(other.txt_qns_33))
			return false;
		if (txt_qns_34 == null) {
			if (other.txt_qns_34 != null)
				return false;
		} else if (!txt_qns_34.equals(other.txt_qns_34))
			return false;
		if (txt_qns_35 == null) {
			if (other.txt_qns_35 != null)
				return false;
		} else if (!txt_qns_35.equals(other.txt_qns_35))
			return false;
		if (txt_qns_36 == null) {
			if (other.txt_qns_36 != null)
				return false;
		} else if (!txt_qns_36.equals(other.txt_qns_36))
			return false;
		if (txt_qns_37 == null) {
			if (other.txt_qns_37 != null)
				return false;
		} else if (!txt_qns_37.equals(other.txt_qns_37))
			return false;
		if (txt_qns_38 == null) {
			if (other.txt_qns_38 != null)
				return false;
		} else if (!txt_qns_38.equals(other.txt_qns_38))
			return false;
		if (txt_qns_39 == null) {
			if (other.txt_qns_39 != null)
				return false;
		} else if (!txt_qns_39.equals(other.txt_qns_39))
			return false;
		if (txt_qns_4 == null) {
			if (other.txt_qns_4 != null)
				return false;
		} else if (!txt_qns_4.equals(other.txt_qns_4))
			return false;
		if (txt_qns_40 == null) {
			if (other.txt_qns_40 != null)
				return false;
		} else if (!txt_qns_40.equals(other.txt_qns_40))
			return false;
		if (txt_qns_41 == null) {
			if (other.txt_qns_41 != null)
				return false;
		} else if (!txt_qns_41.equals(other.txt_qns_41))
			return false;
		if (txt_qns_42 == null) {
			if (other.txt_qns_42 != null)
				return false;
		} else if (!txt_qns_42.equals(other.txt_qns_42))
			return false;
		if (txt_qns_43 == null) {
			if (other.txt_qns_43 != null)
				return false;
		} else if (!txt_qns_43.equals(other.txt_qns_43))
			return false;
		if (txt_qns_44 == null) {
			if (other.txt_qns_44 != null)
				return false;
		} else if (!txt_qns_44.equals(other.txt_qns_44))
			return false;
		if (txt_qns_45 == null) {
			if (other.txt_qns_45 != null)
				return false;
		} else if (!txt_qns_45.equals(other.txt_qns_45))
			return false;
		if (txt_qns_46 == null) {
			if (other.txt_qns_46 != null)
				return false;
		} else if (!txt_qns_46.equals(other.txt_qns_46))
			return false;
		if (txt_qns_47 == null) {
			if (other.txt_qns_47 != null)
				return false;
		} else if (!txt_qns_47.equals(other.txt_qns_47))
			return false;
		if (txt_qns_48 == null) {
			if (other.txt_qns_48 != null)
				return false;
		} else if (!txt_qns_48.equals(other.txt_qns_48))
			return false;
		if (txt_qns_49 == null) {
			if (other.txt_qns_49 != null)
				return false;
		} else if (!txt_qns_49.equals(other.txt_qns_49))
			return false;
		if (txt_qns_5 == null) {
			if (other.txt_qns_5 != null)
				return false;
		} else if (!txt_qns_5.equals(other.txt_qns_5))
			return false;
		if (txt_qns_50 == null) {
			if (other.txt_qns_50 != null)
				return false;
		} else if (!txt_qns_50.equals(other.txt_qns_50))
			return false;
		if (txt_qns_6 == null) {
			if (other.txt_qns_6 != null)
				return false;
		} else if (!txt_qns_6.equals(other.txt_qns_6))
			return false;
		if (txt_qns_7 == null) {
			if (other.txt_qns_7 != null)
				return false;
		} else if (!txt_qns_7.equals(other.txt_qns_7))
			return false;
		if (txt_qns_8 == null) {
			if (other.txt_qns_8 != null)
				return false;
		} else if (!txt_qns_8.equals(other.txt_qns_8))
			return false;
		if (txt_qns_9 == null) {
			if (other.txt_qns_9 != null)
				return false;
		} else if (!txt_qns_9.equals(other.txt_qns_9))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
}