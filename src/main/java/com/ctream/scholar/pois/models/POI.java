package com.ctream.scholar.pois.models;

import com.ctream.scholar.pois.api.mapper.response.CommentInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class POI {

    private String id;

    private String name;
    private Location location;
    private String phone;
    private String email;
    private String website;
    private String category;
    private double rates;
    private List<CommentInfo> comments;
    private double distance;
    private Boolean isLiked;
}
