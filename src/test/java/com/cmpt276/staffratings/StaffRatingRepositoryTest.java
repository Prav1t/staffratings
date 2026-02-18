package com.cmpt276.staffratings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StaffRatingRepositoryTest {

    @Autowired
    private StaffRatingRepository repository;

    @Test
    void saveAndFindRating() {
        // Create a valid entity
        StaffRating rating = new StaffRating();
        rating.setName("Test");
        rating.setEmail("test123@test.com");
        rating.setRoleType(RoleType.PROF);
        rating.setClarity(7);
        rating.setNiceness(7);
        rating.setKnowledgeableScore(7);

        // Save
        StaffRating saved = repository.save(rating);

        // Verify saved
        assertNotNull(saved.getId());

        List<StaffRating> all = repository.findAll();
        assertEquals(1, all.size());
        assertEquals("Test", all.get(0).getName());
        assertEquals("test123@test.com", all.get(0).getEmail());
        assertEquals(RoleType.PROF, all.get(0).getRoleType());
    }

    @Test
    void deleteRemovesEntry() {
        // Create valid entity
        StaffRating rating = new StaffRating();
        rating.setName("Delete Me");
        rating.setEmail("delete@test.com");
        rating.setRoleType(RoleType.TA);
        rating.setClarity(6);
        rating.setNiceness(6);
        rating.setKnowledgeableScore(6);

        // Save
        StaffRating saved = repository.save(rating);
        assertEquals(1, repository.findAll().size());

        // Delete
        repository.deleteById(saved.getId());

        // Verify removed
        assertEquals(0, repository.findAll().size());
    }
}
