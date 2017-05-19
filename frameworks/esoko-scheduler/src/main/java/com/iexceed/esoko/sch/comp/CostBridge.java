package com.iexceed.esoko.sch.comp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.dao.LocationRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao2.SubscriptionPriceRepo;
import com.iexceed.esoko.domain.dao3.OperatorRepo;
import com.iexceed.esoko.domain.dao3.OperatorTemplateRepo;
import com.iexceed.esoko.domain.entity.Location;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.objects.Cost;

@Component
public class CostBridge implements ICostBridge {

	@Autowired
	OperatorTemplateRepo templateRepo;
	@Autowired
	OperatorRepo operatorRepo;
	@Autowired
	SubscriptionPriceRepo subscriptionRepo;
	@Autowired
	NetworkRepo networkRepo;
	@Autowired
	LocationRepo locRepo;

	Map<String, String> operatorsMap;
	List<Map<String, Object>> subPrcDtls;

	public MessageObjectBean deriveCostAndRoute(MessageObjectBean bean) {

		Cost cost = deriveCost(bean);
		bean.setMsgCost(cost);
		bean.setRouteId(operatorsMap.get("routeId"));
		bean.setSmsc(operatorsMap.get("smsc"));
		if (!bean.getType().equalsIgnoreCase("PA"))
			bean.setFcy(operatorsMap.get("currency"));
		bean.setLcy(operatorRepo.deriveLocalCurrency(bean.getNetworkId()));
		return bean;
	}

	public Cost deriveCost(MessageObjectBean bean) {
		log.debug("Entered Derive cost");
		Cost cost = new Cost();
		deriveOperatorsDetails(bean);
		if (!bean.isReversal()) {
			if (bean.isSurchargeable()) {
				Map<String, Double> costMap = operatorRepo.deriveFinalCost(
						bean.getNetworkId(), "Y", bean.getOperatorId());
				cost.setBaseCost(costMap.get("baseCost") * bean.getNoOfMsgs());
				cost.setRetailPrice(costMap.get("retailCost")
						* bean.getNoOfMsgs());
				cost.setWholesalePrice(costMap.get("wholesaleCost")
						* bean.getNoOfMsgs());
			} else {
				Map<String, Double> costMap = operatorRepo.deriveFinalCost(
						bean.getNetworkId(), "N", bean.getOperatorId());
				cost.setBaseCost(costMap.get("baseCost") * bean.getNoOfMsgs());
				cost.setRetailPrice(costMap.get("retailCost")
						* bean.getNoOfMsgs());
				cost.setWholesalePrice(costMap.get("wholesaleCost")
						* bean.getNoOfMsgs());

			}
		} else {

			if (bean.isSurchargeable()) {
				Map<String, Double> costMap = operatorRepo.deriveFinalCost(
						bean.getNetworkId(), "Y", bean.getOperatorId());
				cost.setBaseCost(-costMap.get("baseCost") * bean.getNoOfMsgs());
				cost.setRetailPrice(-costMap.get("retailCost")
						* bean.getNoOfMsgs());
				cost.setWholesalePrice(-costMap.get("wholesaleCost")
						* bean.getNoOfMsgs());
			} else {
				Map<String, Double> costMap = operatorRepo.deriveFinalCost(
						bean.getNetworkId(), "N", bean.getOperatorId());
				cost.setBaseCost(-costMap.get("baseCost") * bean.getNoOfMsgs());
				cost.setRetailPrice(-costMap.get("retailCost")
						* bean.getNoOfMsgs());
				cost.setWholesalePrice(-costMap.get("wholesaleCost")
						* bean.getNoOfMsgs());

			}

		}
		return cost;
	}

	private Double deriveOperatorsDetails(MessageObjectBean bean) {
		log.debug("Entered Derive Base cost");
		operatorsMap = operatorRepo.getOperatorByOperatorid(bean
				.getOperatorId());
		Double basecost = Double.parseDouble(operatorsMap.get("cost"));
		log.debug("Base Cost::" + basecost);
		return basecost;
	}
	
}
