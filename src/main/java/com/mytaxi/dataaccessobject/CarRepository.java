package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.CarStatus;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends JpaRepository<CarDO, Long>, JpaSpecificationExecutor<CarDO> {

	List<CarDO> findByCarStatus(CarStatus carStatus);
}
