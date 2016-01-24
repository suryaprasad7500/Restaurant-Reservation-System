package io.rrs.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.rrs.dao.ReservationDAO;
import io.rrs.exception.AppException;
import io.rrs.model.Reservation;

@Path("/reservation")
public class ReservationController {
	@PUT
	@Path("/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void update(Reservation res, @PathParam("email") String email) {
		try {
			Reservation reservation = null;
			ReservationDAO dao = new ReservationDAO();
			// System.out.println(email);
			reservation = dao.findOneByEmail(email);
			if (reservation != null) {
				dao.update(res);
			} else {
				System.err.println("Reservation not found!");
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> findAllReservations() {
		List<Reservation> res = null;
		ReservationDAO dao = new ReservationDAO();
		try {
			res = dao.findAllReservations();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

	/*
	 * @GET
	 * 
	 * @Path("/{id}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Reservation
	 * findOne(@PathParam("id") int id) { Reservation res = null; try {
	 * ReservationDAO dao = new ReservationDAO(); res = dao.findOne(id); } catch
	 * (AppException e) { e.printStackTrace(); throw new
	 * WebApplicationException(Status.INTERNAL_SERVER_ERROR); } return res; }
	 */

	@GET
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Reservation findOneByEmail(@PathParam("email") String email) {
		Reservation res = null;
		try {
			ReservationDAO dao = new ReservationDAO();
			res = dao.findOneByEmail(email);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

	@DELETE
	@Path("/{confNumber}")
	public void delete(@PathParam("confNumber") int confNumber) {
		ReservationDAO dao = new ReservationDAO();
		try {
			dao.delete(confNumber);
		} catch (AppException e) {
			e.printStackTrace();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation create(Reservation res) {
		try {
			ReservationDAO dao = new ReservationDAO();
			res = dao.create(res);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
}
