package io.rrs.model;

import java.util.Date;

public class Table {
	private int tableNumber;

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBookedSince() {
		return bookedSince;
	}

	public void setBookedSince(Date bookedSince) {
		this.bookedSince = bookedSince;
	}

	public int getConfNumber() {
		return confNumber;
	}

	public void setConfNumber(int confNumber) {
		this.confNumber = confNumber;
	}

	private int size;
	private String status;
	private Date bookedSince;
	private int confNumber;
}
