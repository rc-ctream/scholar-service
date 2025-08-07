package com.ctream.scholar.pois.api.mapper;

import com.ctream.scholar.pois.api.mapper.response.POIInfo;
import com.ctream.scholar.pois.models.POI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper( componentModel = "spring" )
public interface IPOIMapper {

    POIInfo mapPOI( POI poi );

    List<POIInfo> mapPOIList( List<POI> poiList );
}
