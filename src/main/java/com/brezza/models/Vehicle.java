package com.brezza.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID vehicleId;
	
	private String licensePlate;
	
	private String chassis;
	
	private String brand;
	
	private String model;
	
	private int manufacturingYear;
	
	private String color;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User owner;

	public UUID getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(UUID vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getChassis() {
		return chassis;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getManufacturingYear() {
		return manufacturingYear;
	}

	public void setManufacturingYear(int manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	

}
