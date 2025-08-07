package com.ctream.scholar.pois.api.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo {

    private String id;

    private String comment;
    private String createdAt;
    private String author;
}
