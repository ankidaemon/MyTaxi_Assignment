package com.mytaxi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.ProhibitedOperationException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import com.mytaxi.specification.CarSpecificationBuilder;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
@Secured("ROLE_ADMIN")
public class DriverController
{

    private final DriverService driverService;
    private final CarService carService;

    @Autowired
    public DriverController(final DriverService driverService,final CarService carService)
    {
        this.driverService = driverService;
        this.carService = carService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }
    
    //Driver select/deselect a Car
    @Secured({"ROLE_DRIVER","ROLE_ADMIN"})
    @PatchMapping("/{driverId}")
    public ResponseEntity<DriverDTO> mapUnMapCar(
        @Valid @PathVariable long driverId, @RequestParam long carId, @RequestParam CarStatus action)
        throws ConstraintsViolationException, EntityNotFoundException, CarAlreadyInUseException, ProhibitedOperationException
    {
        DriverDO driverDO=driverService.find(driverId);
        CarDO carDO=carService.find(carId);
        if(action==CarStatus.MAP)
        	driverDO=driverService.mapCar(driverDO, carDO);
        else 
        	driverDO=driverService.unMapCar(driverDO, carDO);
		return new ResponseEntity<>(DriverMapper.makeDriverDTO(driverDO),HttpStatus.ACCEPTED);
    }
    
    //Driver based on car Attributes
    @GetMapping("/cars")
    @ResponseBody
    public Set<DriverDTO> findDriversByCar(@RequestParam(value = "search", required = false) String search)
        throws ConstraintsViolationException, EntityNotFoundException
    {
    	CarSpecificationBuilder builder = new CarSpecificationBuilder();
    	List<DriverDTO> driverDTOList = new ArrayList<>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(=|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            Specification<CarDO> spec= builder.build();
            List<CarDO> carDOList = carService.findAll(spec);
            
            for(CarDO carDO : carDOList){
            	DriverDO driverDO = driverService.findByCar(carDO.getId());
	            	if(driverDO != null){
	            		driverDTOList.add(DriverMapper.makeDriverDTO(driverDO));
	            	}
            }
        }
        
        return new TreeSet<>(driverDTOList);
    }
}
