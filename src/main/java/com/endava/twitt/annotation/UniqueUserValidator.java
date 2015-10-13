package com.endava.twitt.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.endava.twitt.dao.UserDaoInterface;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String>{
	
	@Autowired
	private UserDaoInterface userDao;

	@Override
	public void initialize(UniqueUser constraintAnnotation) {		
		
	}

	@Override
	public boolean isValid(String userEmail, ConstraintValidatorContext context) {
				
		return userDao.getUserByName(userEmail)==null;
	}

}
