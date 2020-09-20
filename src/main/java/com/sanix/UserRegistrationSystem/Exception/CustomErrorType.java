package com.sanix.UserRegistrationSystem.Exception;

import com.sanix.UserRegistrationSystem.dto.UserDTO;

public class CustomErrorType extends UserDTO {

    private String errorMessage;

    public CustomErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
