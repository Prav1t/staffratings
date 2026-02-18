package com.cmpt276.staffratings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*; //for the assertion methods

@DataJpaTest
class StaffRatingRepositoryTest { //the repository test class

    @Autowired
    private StaffRatingRepository repository; //

    @Test
    void saveAndFindRating() {
        
        //to create a new valid entity using all the info gathered below
        
        StaffRating rating = new StaffRating();
        
        rating.setName("Test");
        
        rating.setEmail("test123@test.com");
        
        rating.setRoleType(RoleType.PROF);
        
        rating.setClarity(7);
        
        rating.setNiceness(7);
        
        rating.setKnowledgeableScore(7);

        

        // Saves it
        StaffRating saved = repository.save(rating);

        

        // Verifies the saved info
        assertNotNull(saved.getId());

        

        List<StaffRating> all = repository.findAll(); //gets all entries
        
        assertEquals(1, all.size()); //checks the size
        
        assertEquals("Test", all.get(0).getName()); //checks the name
        
        assertEquals("test123@test.com", all.get(0).getEmail()); //checks the email
        
        assertEquals(RoleType.PROF, all.get(0).getRoleType()); //checks the role
        
    }

    

    @Test
    void deleteRemovesEntry() { //for deleting

        
        // Creates a valid entity using everything below
        
        StaffRating rating = new StaffRating();
        
        rating.setName("Delete Me");
        
        rating.setEmail("delete@test.com");
        
        rating.setRoleType(RoleType.TA);
        
        rating.setClarity(6);
        
        rating.setNiceness(6);
        
        rating.setKnowledgeableScore(6);

        
        StaffRating saved = repository.save(rating); //saves it
        assertEquals(1, repository.findAll().size()); //verifies it

        // Delete
        repository.deleteById(saved.getId());

        // Verify removed
        assertEquals(0, repository.findAll().size());

        
    }
}
