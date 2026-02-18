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
    private StaffRatingService service;

    // Show all ratings
    @GetMapping
    public String listRatings(Model model) {
        model.addAttribute("ratings", service.getAll());
        return "index";
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("rating", new StaffRating());
        return "form";
    }

    // Save rating (create or update)
    @PostMapping
    public String saveRating(@Valid @ModelAttribute("rating") StaffRating rating,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "form";
        }

        service.save(rating);
        return "redirect:/staff";
    }

    // View details
    @GetMapping("/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        StaffRating rating = service.getById(id);
        model.addAttribute("rating", rating);
        return "detail";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        StaffRating rating = service.getById(id);
        model.addAttribute("rating", rating);
        return "form";
    }

    // Delete rating
    @GetMapping("/delete/{id}")
    public String deleteRating(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/staff";
    }
}
