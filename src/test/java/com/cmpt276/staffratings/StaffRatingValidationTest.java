package com.cmpt276.staffratings;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StaffRatingValidationTest { //the validation test class

    private Validator validator; //the validator instance

    @BeforeEach
    void setUp() {
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); //builds factory
        validator = factory.getValidator(); //gets the validator
        
    }

    @Test
    void nameCannotBeBlank() {
        
        StaffRating rating = new StaffRating(); //
        rating.setName(""); // invalid
        rating.setEmail("test@test.com");
        rating.setRoleType(RoleType.INSTRUCTOR); 
        rating.setClarity(5);
        rating.setNiceness(5);
        rating.setKnowledgeableScore(5);

        Set<ConstraintViolation<StaffRating>> violations =
                validator.validate(rating); //runs the validation

        assertFalse(violations.isEmpty()); //expects errors

        
    }


    
    @Test
    void validRatingPassesValidation() { //

        
        StaffRating rating = new StaffRating();
        rating.setName("John Doe");
        rating.setEmail("john@test.com");
        rating.setRoleType(RoleType.INSTRUCTOR); // REQUIRED
        rating.setClarity(8);
        rating.setNiceness(9);
        rating.setKnowledgeableScore(7);

        Set<ConstraintViolation<StaffRating>> violations =
                validator.validate(rating);

        assertTrue(violations.isEmpty());

        
    }
}
