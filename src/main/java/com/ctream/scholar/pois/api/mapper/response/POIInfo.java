package com.ctream.scholar.pois.api.mapper.response;

import com.ctream.scholar.pois.models.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class POIInfo {

    private String id;

    private String name;
    private Location location;
    private String category;
}
