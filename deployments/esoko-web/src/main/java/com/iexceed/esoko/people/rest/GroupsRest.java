package com.iexceed.esoko.people.rest;

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
import com.iexceed.esoko.jaxb.people.AddToGroupReqWrap;
import com.iexceed.esoko.jaxb.people.AddToGroupResWrap;
import com.iexceed.esoko.jaxb.people.CheckGroupNameResWrap;
import com.iexceed.esoko.jaxb.people.CreateGroupReqWrap;
import com.iexceed.esoko.jaxb.people.CreateGroupResWrap;
import com.iexceed.esoko.jaxb.people.DeleteFromGroupReqWrap;
import com.iexceed.esoko.jaxb.people.DeleteFromGroupResWrap;
import com.iexceed.esoko.jaxb.people.DeleteGroupReqWrap;
import com.iexceed.esoko.jaxb.people.DeleteGroupResWrap;
import com.iexceed.esoko.jaxb.people.EditGroupReqWrap;
import com.iexceed.esoko.jaxb.people.EditGroupResWrap;
import com.iexceed.esoko.jaxb.people.GetSmrtGrpCntReqWrap;
import com.iexceed.esoko.jaxb.people.GetSmrtGrpCntResWrap;
import com.iexceed.esoko.jaxb.people.QueryAllGroupsResWrap;
import com.iexceed.esoko.jaxb.people.ViewGroupDetailResWrap;
import com.iexceed.esoko.jaxb.people.ViewSmrtGrpDtlResWrap;
import com.iexceed.esoko.people.service.GroupsService;

/*
 * @author Ankur
 */
@Path("GroupsService")
@Component
public class GroupsRest {
	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	private GroupsService groups;

	public GroupsRest() {

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllGroups")
	public QueryAllGroupsResWrap queryAllGroups(
			@QueryParam("networkId") String networkId) {
		log.info("Insisde QueryAllGroupsResWrap -> queryAllGroups");
		QueryAllGroupsResWrap groupRes = new QueryAllGroupsResWrap();
		groupRes.setQueryAllGroupsRes(groups.queryAllGroups(networkId));
		return groupRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteGroup")
	public DeleteGroupResWrap deleteGroup(DeleteGroupReqWrap req) {
		log.info("Inside DeleteGroupResWrap -> deleteGroup");
		DeleteGroupResWrap groupRes = new DeleteGroupResWrap();
		groupRes.setDeleteGroupRes(groups.deleteGroup(req.getDeleteGroupReq()));
		return groupRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("editGroup")
	public EditGroupResWrap editGroup(EditGroupReqWrap req) {
		log.info("Inside EditGroupResWrap -> editGroup");
		EditGroupResWrap groupRes = new EditGroupResWrap();
		groupRes.setEditGroupRes(groups.editGroup(req.getEditGroupReq()));
		return groupRes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("viewGroupDetail")
	public ViewGroupDetailResWrap viewGroupDetail(
			@QueryParam("networkId") String networkId,
			@QueryParam("groupId") String groupId) {
		log.info("Inside ViewGroupDetailResWrap -> viewGroupDetail");
		ViewGroupDetailResWrap groupRes = new ViewGroupDetailResWrap();
		groupRes.setViewGroupDetailRes(groups.viewGroupDetail(networkId,
				groupId));
		return groupRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createGroup")
	public CreateGroupResWrap createGroup(CreateGroupReqWrap req) {
		log.info("Inside CreateGroupResWrap -> createGroup");
		CreateGroupResWrap groupRes = new CreateGroupResWrap();
		groupRes.setCreateGroupRes(groups.createGroup(req.getCreateGroupReq()));
		return groupRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("addToGroup")
	public AddToGroupResWrap addToGroup(AddToGroupReqWrap req) {
		log.info("Inside CreateGroupResWrap -> createGroup");
		AddToGroupResWrap groupRes = new AddToGroupResWrap();
		groupRes.setAddToGroupRes(groups.addToGroup(req.getAddToGroupReq()));
		return groupRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteFromGroup")
	public DeleteFromGroupResWrap deleteFromGroup(DeleteFromGroupReqWrap req) {
		log.info("Inside DeleteFromGroupResWrap -> deleteFromGroup");
		DeleteFromGroupResWrap groupRes = new DeleteFromGroupResWrap();
		groupRes.setDeleteFromGroupRes(groups.deleteFromGroup(req
				.getDeleteFromGroupReq()));
		return groupRes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("viewSmrtGrpDtl")
	public ViewSmrtGrpDtlResWrap viewSmrtGrpDtl(
			@QueryParam("networkId") String networkId,
			@QueryParam("groupId") String groupId) {
		log.info("Inside ViewSmrtGrpDtlResWrap -> viewSmrtGrpDtl");
		ViewSmrtGrpDtlResWrap groupRes = new ViewSmrtGrpDtlResWrap();
		groupRes.setViewSmrtGrpDtlRes(groups.viewSmrtGrpDtl(networkId, groupId));
		return groupRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("getSmrtGrpCnt")
	public GetSmrtGrpCntResWrap getSmrtGrpCnt(GetSmrtGrpCntReqWrap req) {
		log.info("Inside GroupRest -> getSmrtGrpCnt");
		GetSmrtGrpCntResWrap groupRes = new GetSmrtGrpCntResWrap();
		groupRes.setGetSmrtGrpCntRes(groups.getSmrtGrpCnt(req
				.getGetSmrtGrpCntReq()));
		return groupRes;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("checkGroupName")
	public CheckGroupNameResWrap checkGroupName(
			@QueryParam("groupName") String groupName,
			@QueryParam("networkId") String networkId) {
		log.info("Inside GroupsRest -> checkGroupName");
		CheckGroupNameResWrap resWrap = new CheckGroupNameResWrap();
		resWrap.setCheckGroupNameRes(groups
				.checkGroupName(groupName, networkId));
		return resWrap;
	}
}
