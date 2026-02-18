package com.cmpt276.staffratings;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

/**
 * Entity representing a staff rating.
 * This maps to a database table automatically.
 */
@Entity // Marks this class as a JPA entity (database table)
@Table(name = "staff_ratings") // Explicit table name
public class StaffRating implements StaffMemberProfile {

    // ========================
    // Primary Key
    // ========================

    @Id // Marks as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    // ========================
    // Required Name
    // ========================

    @NotBlank(message = "Name is required") // Cannot be null or empty
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    // ========================
    // Email (Required + Valid + Unique)
    // ========================

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email address")
    @Column(unique = true) // Enforces uniqueness at DB level
    private String email;

    // ========================
    // Role Type (Enum)
    // ========================

    @Enumerated(EnumType.STRING) // Store enum as String in DB
    @NotNull(message = "Role is required")
    private RoleType roleType;

    // ========================
    // Scores (1–10 required)
    // ========================

    @Min(value = 1, message = "Clarity must be at least 1")
    @Max(value = 10, message = "Clarity cannot exceed 10")
    private int clarity;

    @Min(value = 1, message = "Niceness must be at least 1")
    @Max(value = 10, message = "Niceness cannot exceed 10")
    private int niceness;

    @Min(value = 1, message = "Knowledge must be at least 1")
    @Max(value = 10, message = "Knowledge cannot exceed 10")
    private int knowledgeableScore;

    // ========================
    // Optional Comment
    // ========================

    @Size(max = 500, message = "Comment cannot exceed 500 characters")
    private String comment;

    // ========================
    // Timestamps
    // ========================

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ========================
    // Lifecycle Hooks
    // ========================

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ========================
    // Polymorphic Method
    // ========================

    @Override
    public String displayTitle() {
        return roleType + ": " + name;
    }

    // ========================
    // Getters and Setters
    // ========================

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public RoleType getRoleType() { return roleType; }

    public void setRoleType(RoleType roleType) { this.roleType = roleType; }

    public int getClarity() { return clarity; }

    public void setClarity(int clarity) { this.clarity = clarity; }

    public int getNiceness() { return niceness; }

    public void setNiceness(int niceness) { this.niceness = niceness; }

    public int getKnowledgeableScore() { return knowledgeableScore; }

    public void setKnowledgeableScore(int knowledgeableScore) { this.knowledgeableScore = knowledgeableScore; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    /**
     * Calculates overall average score.
     * Used in index page.
     */
    public double getOverallScore() {
        return (clarity + niceness + knowledgeableScore) / 3.0;
    }
}
