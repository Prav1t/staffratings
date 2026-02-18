package com.cmpt276.staffratings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StaffRatingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getIndexReturns200AndHasRatingsModelAttribute() throws Exception {
        mockMvc.perform(get("/staff"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratings"))
                .andExpect(view().name("index"));
    }

    @Test
    void getNewFormReturns200AndHasEmptyRating() throws Exception {
        mockMvc.perform(get("/staff/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"))
                .andExpect(view().name("form"));
    }

    @Test
    void postCreateSuccessRedirectsToStaff() throws Exception {
        mockMvc.perform(post("/staff")
                        .param("name", "Integration Test")
                        .param("email", "integration@test.com")
                        .param("clarity", "7")
                        .param("niceness", "7")
                        .param("knowledgeableScore", "7")
                        .param("roleType", "TA")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/staff"));
    }

    @Test
    void postCreateFailureReturnsFormWithErrors() throws Exception {
        // Missing required fields (like roleType, maybe name)
        mockMvc.perform(post("/staff")
                        .param("email", "bademail")     // invalid email
                        .param("clarity", "50")          // out of range
                )
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().hasErrors());
    }
}
