package com.mytaxi.service.car;

import java.math.BigDecimal;
import java.util.List;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    List<CarDO> find(CarStatus carStatus);

	void updateRating(long carId, BigDecimal rating) throws EntityNotFoundException;

}
