package com.cmpt276.staffratings;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffRatingController {

    @Autowired
    private StaffRatingService service; //th service layer object

    // to Show all ratings
    @GetMapping
    public String listRatings(Model model) {
        
        model.addAttribute("ratings", service.getAll());
        
        return "index";
        
    }

    // to show the create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        
        model.addAttribute("rating", new StaffRating());
        return "form";
        
    }

    // to save rating 
    @PostMapping
    public String saveRating(@Valid @ModelAttribute("rating") StaffRating rating,
                             BindingResult result) { //captures the validation errors

        if (result.hasErrors()) { //gets the validation errors
            return "form";
            
        }

        service.save(rating); //saves to the databse
        return "redirect:/staff"; //to redirect
        
    }

    // For viewing details
    
    @GetMapping("/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        
        StaffRating rating = service.getById(id); //fetches from the database
        model.addAttribute("rating", rating); //gets the model
        return "detail";
        
    }

    // to show the edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        
        StaffRating rating = service.getById(id); //gets the existing rating
        model.addAttribute("rating", rating); //adds to the model
        return "form"; //redirects to the list
        
    }

    // to Delete rating
    @GetMapping("/delete/{id}")
    
    public String deleteRating(@PathVariable Long id) {
        
        service.delete(id); //to delete from database
        return "redirect:/staff"; //to redirect
        
    }
}
