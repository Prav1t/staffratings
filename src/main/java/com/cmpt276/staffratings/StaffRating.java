package com.cmpt276.staffratings;

import jakarta.persistence.*; //the jpa annotation
import jakarta.validation.constraints.*; //for validation

import java.time.LocalDateTime; //to get time


@Entity //Marks this class as a JPA entity (database table)
@Table(name = "staff_ratings") //the explicit table name
public class StaffRating implements StaffMemberProfile {

    

    @Id // Marks as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    
    // Required Name
   

    @NotBlank(message = "Name is required") // Cannot be null or empty
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    
    // now for Email 
 

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email address")
    @Column(unique = true) 
    private String email;

  
    //the Role Type (Enum)
    

    @Enumerated(EnumType.STRING) // Store enum as String in DB
    @NotNull(message = "Role is required")
    private RoleType roleType;

    
    // Scores (1–10 required)
    

    @Min(value = 1, message = "Clarity has to be at least 1")
    @Max(value = 10, message = "Clarity cannot be more than 10")
    private int clarity;

    @Min(value = 1, message = "Niceness has to be at least 1")
    @Max(value = 10, message = "Niceness cannot be more than 10")
    private int niceness;

    @Min(value = 1, message = "Knowledge has to be at least 1")
    @Max(value = 10, message = "Knowledge cannot be more than 10")
    private int knowledgeableScore;

    
    // for the Comment
    

    @Size(max = 500, message = "Comment cannot exceed 500 characters")
    private String comment;

   

    private LocalDateTime createdAt; //creation timestamp
    private LocalDateTime updatedAt; //update timestamp if updated

  

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

   
    // using Polymorphic Method
  

    @Override
    public String displayTitle() {
        return roleType + ": " + name;
    }

 

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

   
    public double getOverallScore() { //to get the overall score
        return (clarity + niceness + knowledgeableScore) / 3.0; //calculats it
    }
}
