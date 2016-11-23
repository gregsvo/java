package com.thesoftwareguild.dvdlibrary.validation;

/**
 *
 * @author apprentice
 */
public class ValidationError {
     private String fieldName;
 private String message;

 public ValidationError(String fieldName, String message) {

 this.fieldName = fieldName;
 this.message = message;
 }
 public String getFieldName() {
 return fieldName;
 }
 public String getMessage() {
 return message;
 }
}

