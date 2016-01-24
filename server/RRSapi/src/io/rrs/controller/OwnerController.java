package io.rrs.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.rrs.dao.OwnerDAO;
import io.rrs.exception.AppException;
import io.rrs.model.Owner;

@Path("/owner")
public class OwnerController {
	@PUT
	@Path("/{restName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void update(@PathParam("restName") String restName) {
		try {
			Owner owner = null;
			OwnerDAO dao = new OwnerDAO();
			owner = dao.findOne(restName);
			if (owner != null) {
				dao.update(restName);
			} else {
				System.err.println("Restaurant not found!");
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}	
}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Owner getOwnerInfo() throws AppException {
		Owner owner = null;
		OwnerDAO dao = new OwnerDAO();
		owner = dao.findOwnerDetails();
		return owner;
	}
}
