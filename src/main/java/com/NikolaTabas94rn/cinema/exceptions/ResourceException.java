package com.NikolaTabas94rn.cinema.exceptions;

import lombok.Getter;

@Getter
public abstract class ResourceException extends Exception{

    protected ErrorInfo.ResourceType resourceType;

    public ResourceException(ErrorInfo.ResourceType resourceType, String message) {
        super(message);
        this.resourceType = resourceType;
    }
}
