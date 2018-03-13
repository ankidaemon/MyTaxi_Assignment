package com.mytaxi.service.car;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some car specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    private final CarRepository carRepository;


    public DefaultCarService(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }


    /**
     * Selects a car by id.
     *
     * @param carId
     * @return found car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    /**
     * Creates a new car.
     *
     * @param carDO
     * @return created car
     * @throws ConstraintsViolationException if a car already exists with the given licensePlate, ... .
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        CarDO car;
        try
        {
            car = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException | ConstraintViolationException e)
        {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }


    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setDeleted(true);
    }


    /**
     * Find all cars by their status.
     *
     * @param carStatus
     * @return list of cars based on their status
     */
    @Override
    public List<CarDO> find(CarStatus carStatus)
    {
        return carRepository.findByCarStatus(carStatus);
    }


    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = carRepository.findOne(carId);
        if (carDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + carId);
        }
        return carDO;
    }


    /**
     * Update rating of an existing car by id.
     *
     * @param carId, rating
     * @throws EntityNotFoundException 
     */
	@Override
	@Transactional
	public void updateRating(long carId, BigDecimal rating) throws EntityNotFoundException {
		CarDO carDO=findCarChecked(carId);
		//Rating can be a class having methods to find average rating based on no of people rated.
		carDO.setRating(rating);
	}

	/**
     * Find All cars based on specification
     *
     * @param carSpecification
     * @return list of cars based on car specification
     */
	@Override
	public List<CarDO> findAll(Specification<CarDO> spec) {
		return carRepository.findAll(spec);
	}

}
