package com.cmpt276.staffratings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List; //list colection

@Service
public class StaffRatingService {

    @Autowired
    private StaffRatingRepository repository;

    public List<StaffRating> getAll() { //gets all ratings
        
        return repository.findAll(); //gets from the database
        
    }

    public StaffRating getById(Long id) { //gets it by the id
        
        return repository.findById(id).orElseThrow(); //to throw error
        
    }

    public StaffRating save(StaffRating rating) { //to save or update the rating
        
        return repository.save(rating); //stores it in the database
        
    }

    public void delete(Long id) { //to delete by id
        
        repository.deleteById(id); //removes it from the db
        
    }

    
}

