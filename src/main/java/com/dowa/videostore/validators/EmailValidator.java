/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dowa.videostore.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlInputText;

/**
 *
 * @author Andres
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w+");
        Matcher matcher = pattern.matcher((CharSequence) value);
        HtmlInputText htmlInputText = (HtmlInputText) component;
        String label;
        
        if(htmlInputText.getLabel() == null
            || htmlInputText.getLabel().trim().equals("")){
            label = htmlInputText.getId();
        }else{
            label = htmlInputText.getLabel();
        }
        
        if(!matcher.matches()){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,label,"Dirección de correo invalida");
            throw new ValidatorException(facesMessage);
        }        
    }
}