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

import io.rrs.dao.CustomerDAO;
import io.rrs.exception.AppException;
import io.rrs.model.Customer;

@Path("/customers")
public class CustomerController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> findAllCust() {
		List<Customer> customers = null;
		CustomerDAO dao = new CustomerDAO();
		try {
			customers = dao.findAllCust();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return customers;
	}

	@GET
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer findOne(@PathParam("email") String email) {
		Customer customer = null;
		try {
			CustomerDAO dao = new CustomerDAO();
			customer = dao.findOne(email);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return customer;
	}

	@PUT
	@Path("/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void update(Customer cust, @PathParam("email") String email) {
		try {
			Customer temp = new Customer();
			CustomerDAO dao = new CustomerDAO();
			temp = dao.findOne(email);
			if (temp != null) {
				dao.update(cust);
			} else {
				System.err.println("Customer not found!");
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

}
