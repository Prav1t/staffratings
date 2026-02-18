package com.cmpt276.staffratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StaffRatingRepository extends JpaRepository<StaffRating, Long> {

    
    Optional<StaffRating> findByEmail(String email);

}
