package com.flight.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_plane"
)
public class Plane {
    @Id
    @SequenceGenerator(
            name="plane_sequence",
            sequenceName = "plane_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plane_sequence"
    )
    private long planeId;

    private String airline;

    private String source;

    private String destination;


    private Date date;

    private String arrival;
    private String departure;
    private String duration;
    private double price;
}
