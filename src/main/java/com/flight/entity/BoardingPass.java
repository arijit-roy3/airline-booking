package com.flight.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_boarding_pass")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BoardingPass {
    @Id
    @SequenceGenerator(
            name="boarding_pass_sequence",
            sequenceName = "boarding_pass_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "boarding_pass_sequence"
    )
    private long boardingPassId;
    private int seatNo;
    private char sequence;
    private String splServices;
    private long bookingId;
    @OneToOne()
    private Booking booking;
}
