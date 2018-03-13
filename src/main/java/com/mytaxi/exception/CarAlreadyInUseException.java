package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not map the car, Already in use.")
public class CarAlreadyInUseException extends Exception
{
    
	private static final long serialVersionUID = -687991492884005033L;

	public CarAlreadyInUseException(String message)
    {
        super(message);
    }

}
