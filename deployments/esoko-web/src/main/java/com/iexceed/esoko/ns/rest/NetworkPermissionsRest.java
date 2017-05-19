package com.iexceed.esoko.ns.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ns.QueryAllNwkGroupsResWrap;
import com.iexceed.esoko.jaxb.ns.QueryNwkPermissionsResWrap;
import com.iexceed.esoko.jaxb.ns.SaveNwkPermissionsReqWrap;
import com.iexceed.esoko.jaxb.ns.SaveNwkPermissionsResWrap;
import com.iexceed.esoko.ne.service.NetworkPermissionService;

/*
 * @author Ankur
 */
@Path("NetworkPermissionService")
@Component
public class NetworkPermissionsRest {

	public static Logger log = LoggerUtils.getLogger();

	public NetworkPermissionsRest() {

	}

	@Autowired
	NetworkPermissionService nwPermissionService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllNwkGroups")
	public QueryAllNwkGroupsResWrap queryAllNwkGroups(
			@QueryParam("networkId") String networkId) {
		log.info("Inside QueryAllNwkGroupsResWrap -> queryAllNwkGroups");
		QueryAllNwkGroupsResWrap grpResWrap = new QueryAllNwkGroupsResWrap();
		grpResWrap.setQueryAllNwkGroupsRes(nwPermissionService
				.queryAllNwkGroups(networkId));
		return grpResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkPermissions")
	public QueryNwkPermissionsResWrap queryNwkPermissions(
			@QueryParam("groupId") String groupId,
			@QueryParam("networkId") String networkId) {
		log.info("Inside QueryNwkPermissionsResWrap -> queryNwkPermissions");
		QueryNwkPermissionsResWrap premissionResWrap = new QueryNwkPermissionsResWrap();
		premissionResWrap.setQueryNwkPermissionsRes(nwPermissionService
				.queryNwkPermission(groupId, networkId));
		return premissionResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("saveNwkPermissions")
	public SaveNwkPermissionsResWrap saveNwkPermissions(
			SaveNwkPermissionsReqWrap req) {
		log.info("Inside SaveNwkPermissionsResWrap -> saveNwkPermissions");
		SaveNwkPermissionsResWrap savePermissionResWrap = new SaveNwkPermissionsResWrap();
		savePermissionResWrap.setSaveNwkPermissionsRes(nwPermissionService
				.saveNwkPermissions(req.getSaveNwkPermissionsReq()));
		return savePermissionResWrap;
	}

}
