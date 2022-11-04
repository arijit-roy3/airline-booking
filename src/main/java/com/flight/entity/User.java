package com.flight.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_user",
        uniqueConstraints = @UniqueConstraint(
                columnNames={"email_address", "phone_No"}
        )
)
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;
    @Column(nullable = false)
    @NotBlank(message = "Please add name")
    private String name;
    @Column(name="email_address",nullable = false)
    @Email
    private String emailId;
    @Column(name="phone_No",nullable = false)
    private String phoneNo;
    @Column(nullable = false)
    private String password;
//    @JsonManagedReference
//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
////    @JoinColumn(
////            name = "booking_id",
////            referencedColumnName = "bookingId"
////    )
//    private List<Booking> bookingList;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "roleId"))

    private Role role;

    public User(String name, String emailId, String phoneNo, String encode, Role role) {
        this.name=name;
        this.emailId=emailId;
        this.phoneNo=phoneNo;
        this.password=encode;
        this.role=role;
    }
}
