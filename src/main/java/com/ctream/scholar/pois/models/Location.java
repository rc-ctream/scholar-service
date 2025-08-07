package com.ctream.scholar.pois.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private String street;
    private String postalCode;
    private String city;
    private String country;
    private double latitude;
    private double longitude;
}
