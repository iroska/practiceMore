package com.endava.twitt.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.endava.twitt.dao.UserDaoInterface;

@Component
public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String>{
	
	
	private UserDaoInterface userDaoValidator;
	@Autowired
	@Qualifier("userDaoValidator")
	public void setUserDao(UserDaoInterface userDaoValidator) {
		this.userDaoValidator = userDaoValidator;
	}

	@Override
	public void initialize(UniqueUser constraintAnnotation) {		
		
	}

	@Override
	public boolean isValid(String userEmail, ConstraintValidatorContext context) {
				
		return userDaoValidator.getUserByName(userEmail)==null;
	}

}
