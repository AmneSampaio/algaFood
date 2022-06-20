package com.algaworks.ecommerce.domain.exception;

public class EntityInUseException extends RuntimeException{

    public EntityInUseException(String message) {
        super(message);
    }
}
