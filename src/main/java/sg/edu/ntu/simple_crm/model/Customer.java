package sg.edu.ntu.simple_crm.model;
//import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import java.util.List;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// @JsonPropertyOrder({ "id", "firstName", "lastName", "email", "contactNo", "jobTitle", "yearOfBirth" })


@Data
@Builder 
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "customer")
public class Customer {

    // private final String id = UUID.randomUUID().toString();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
    @Column(name = "email")
    private String email;

    @Size(min = 8, max = 8, message = "Contact number must be 8 characters")
    @Column(name = "contact_no")
    private String contactNo;

    @Size(min = 10, max = 50, message = "Job title must be between 10 and 50 characters")
    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interaction> interactions;

    public List<Interaction> getInteractions() {
        return interactions;
    }

    

}
