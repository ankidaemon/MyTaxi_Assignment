package com.mytaxi.service.driver;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ErrorCode;
import com.mytaxi.exception.ProhibitedOperationException;

@Service
public class CarMapperDriverService extends DefaultDriverService {

	public CarMapperDriverService(DriverRepository driverRepository) {
		super(driverRepository);
	}

	private static final String MESSAGE="Could not map car with id ";
	/**
     * Map driver with a car.
     *
     * @param driverDO
     * @param carDO
	 * @throws CarAlreadyInUseException 
	 * @throws ProhibitedOperationException 
     */
	@Override
	@Transactional
	public DriverDO mapCar(DriverDO driverDO, CarDO carDO) throws CarAlreadyInUseException, ProhibitedOperationException {
		
			if(driverDO.getOnlineStatus()==OnlineStatus.OFFLINE){
				throw new ProhibitedOperationException("Please be online in order to map a car.",ErrorCode.OFFLINEDRIVER);
			}else if(carDO.getCarStatus()==CarStatus.MAP){
				throw new CarAlreadyInUseException(MESSAGE+carDO.getId()+", Already in Use.");
			}else{
				driverDO.setCarDO(carDO);
				carDO.setCarStatus(CarStatus.MAP);
			}
		return driverDO;
	}
	
	/**
     * Un-Map driver with a car.
     *
     * @param driverDO
     * @param carDO
     * @return driverDO
	 * @throws ProhibitedOperationException 
     */
	@Override
	@Transactional
	public DriverDO unMapCar(DriverDO driverDO, CarDO carDO) throws ProhibitedOperationException {
		
		if(driverDO.getCarDO()!=null && driverDO.getCarDO().getId()==carDO.getId()){
			driverDO.setCarDO(null);
			carDO.setCarStatus(CarStatus.UNMAP);
		}else{
			throw new ProhibitedOperationException("Car with id-"+carDO.getId()+", Not mapped to you.",ErrorCode.NOTMAPPED);
		}
		return driverDO;
	}

	/**
     * Find drivers by car ID.
     *
     * @param carID
     * @return driverDO
     */
	@Override
	public DriverDO findByCar(Long carID) {
		// TODO Auto-generated method stub
		return driverRepository.findByAndCarDO_Id(carID);
	}
}
