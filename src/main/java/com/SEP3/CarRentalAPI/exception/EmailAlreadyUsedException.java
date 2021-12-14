package com.SEP3.CarRentalAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyUsedException extends Exception
{
    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException(String message)
    {
        super(message);
    }
}
