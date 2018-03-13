package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long> {

	List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

	@Query(value = "SELECT * FROM DRIVER WHERE DRIVER.CAR_ID = ?1", nativeQuery = true)
	DriverDO findByAndCarDO_Id(Long carId);

}
