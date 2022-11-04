package com.flight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_booking"
)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Booking {
    @Id
    @SequenceGenerator(
            name="booking_sequence",
            sequenceName = "booking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "booking_sequence"
    )
    private long bookingId;
    @Column(name="first_name",nullable = false)
    private String fname;
    @Column(name="last_name",nullable = false)
    private String lname;
    @Column(name="booking_email",nullable = false)
    private String email;
    @Column(name="booking_phno",nullable = false)
    private String phno;
    @Column(name="address",nullable = false)
    private String address;
    @Column(name="boardingPass",nullable = false)
    private int pass;
    private String sourceBooking;
    private String destinationBooking;
    private Date dateBooking;
    private String username;
    private long planeId;
//    @OneToOne(
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER,
//            optional = false
//    )
//    @JoinColumn(
//            name="booking_Id",
//            referencedColumnName = "bookingId"
//    )
//    private BoardingPass boardingPass;
//    @JsonBackReference
//    @ManyToOne(
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER
//    )
//    @JoinColumn(
//            name = "user_id",
//            referencedColumnName = "userId"
//    )
//    private User user;
}
