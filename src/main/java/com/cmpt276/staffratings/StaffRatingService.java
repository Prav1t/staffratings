package com.cmpt276.staffratings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffRatingService {

    @Autowired
    private StaffRatingRepository repository;

    public List<StaffRating> getAll() {
        return repository.findAll();
    }

    public StaffRating getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public StaffRating save(StaffRating rating) {
        return repository.save(rating);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

