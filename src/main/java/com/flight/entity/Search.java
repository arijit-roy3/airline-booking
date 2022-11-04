package com.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Search {
    private String source;
    private String destination;
    private Date date;
}
