package io.rrs.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.rrs.dao.TableDAO;
import io.rrs.exception.AppException;
import io.rrs.model.Table;

@Path("/table")
public class TableController {
	@PUT
	@Path("/{tableNumber}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void update(@PathParam("tableNumber") int tableNumber) {
		try {
			Table table = null;
			TableDAO dao = new TableDAO();
			table = dao.findOne(tableNumber);
			if (table != null) {
				dao.update(tableNumber);
			} else {
				System.err.println("Reservation not found!");
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Path("/{tableNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Table findOne(@PathParam("tableNumber") int tableNumber) {
		Table tab = null;
		try {
			TableDAO dao = new TableDAO();
			tab = dao.findOne(tableNumber);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return tab;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Table> findAllTables() {
		List<Table> tab = null;
		TableDAO dao = new TableDAO();
		try {
			tab = dao.findAllTables();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return tab;
	}
}
