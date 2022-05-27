package com.docente.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.docente.model.Chef;
import com.docente.service.ChefService;

@Component
public class ChefValidator implements Validator {
	
	@Autowired private ChefService chefService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if (this.chefService.alreadyExists((Chef)o)) {
			errors.reject("chef.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return ChefService.class.equals(aClass);
	}

}
