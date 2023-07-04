package com.NikolaTabas94rn.cinema.exceptions;

public class UniqueViolationException extends ResourceException{
    public UniqueViolationException(ErrorInfo.ResourceType resourceType, String message) {
        super(resourceType, message);
    }

}
