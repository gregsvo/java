package com.thesoftwarequild.addresslistmvc.validation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;


public class ValidationErrorContainer {
    
    private List <ValidationError> errors = new ArrayList<>();
    
    public void addError(String message, String fieldName) {
        errors.add(new ValidationError(fieldName, message));
    }
    
    public List<ValidationError> getErrors(){
        
        return errors;
    }

}
