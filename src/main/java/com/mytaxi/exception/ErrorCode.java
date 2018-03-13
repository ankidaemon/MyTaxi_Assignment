package com.mytaxi.exception;

public enum ErrorCode {

	OFFLINEDRIVER(011, "Offline driver cannot map car."),
	NOTINSERVICE(012, "Car is out of service."),
	NOTMAPPED(013, "Car is not mapped with the driver.");

	private final int id;
	private final String description;

	private ErrorCode(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return id + ": " + description;
	}
}