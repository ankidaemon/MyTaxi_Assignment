package com.mytaxi.datatransferobject;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.EngineType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "License plate can not be null!")
    private String licensePlate;

    @NotNull(message = "Seat-Count can not be null!")
    private int seatCount;

    @NotNull(message = "Please provide Engine Type- Gas/Electric/Diesel")
    private EngineType engineType;
    
    @NotNull(message = "Manufacturer cannot be null.")
    private String manufacturer;
    
    private BigDecimal rating=null;
    
    private Boolean convertible=false;

    private CarDTO()
    {
    }

	public CarDTO(Long id, String licensePlate, int seatCount, EngineType engineType, String manufacturer, BigDecimal rating, Boolean convertible) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.seatCount = seatCount;
		this.engineType = engineType;
		this.manufacturer = manufacturer;
		this.rating = rating;
		this.convertible = convertible;
	}

	public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }

    public String getLicensePlate() {
		return licensePlate;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public String getManufacturer() {
		return manufacturer;
	}
	

	public BigDecimal getRating() {
		return rating;
	}

	public Boolean getConvertible() {
		return convertible;
	}


	public static class CarDTOBuilder
    {
        private Long id;
        private String licensePlate;
        private int seatCount;
        private EngineType engineType;
        private String manufacturer;
        private BigDecimal rating;
        private Boolean convertible;


        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }

        public CarDTOBuilder setLicensePlate(String licensePlate) {
			this.licensePlate = licensePlate;
			return this;
		}

		public CarDTOBuilder setSeatCount(int seatCount) {
			this.seatCount = seatCount;
			return this;
		}

		public CarDTOBuilder setEngineType(EngineType engineType) {
			this.engineType = engineType;
			return this;
		}

		public CarDTOBuilder setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}
		
		public CarDTOBuilder setRating(BigDecimal rating) {
			this.rating = rating;
			return this;
		}

		public CarDTOBuilder setConvertible(Boolean convertible) {
			this.convertible = convertible;
			return this;
		}

		public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate, seatCount, engineType, manufacturer, rating, convertible);
        }

    }
}
