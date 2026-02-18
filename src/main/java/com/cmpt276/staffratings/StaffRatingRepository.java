package com.cmpt276.staffratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for StaffRating.
 * 
 * Extends JpaRepository which automatically provides:
 * - save()
 * - findAll()
 * - findById()
 * - deleteById()
 * - count()
 * and many more.
 */
@Repository
public interface StaffRatingRepository extends JpaRepository<StaffRating, Long> {

    /**
     * Custom query method to find by email.
     * This supports our validation rule (unique email).
     */
    Optional<StaffRating> findByEmail(String email);

}
