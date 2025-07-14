package io.github.alberes.register.manager.frontend.client.controllers.exceptions.dto;

public class RegisterManagerException extends RuntimeException{

    private StandardErrorDto standardError;

    public RegisterManagerException() {
        super();
    }

    public RegisterManagerException(StandardErrorDto standardError){
        super(standardError.getMessage());
        this.standardError = standardError;
    }

    public StandardErrorDto getStandardError() {
        return standardError;
    }
}
