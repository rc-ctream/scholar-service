package com.ctream.scholar.pois.api.controller;

import com.ctream.scholar.pois.api.mapper.IPOIMapper;
import com.ctream.scholar.pois.api.mapper.response.POIInfo;
import com.ctream.scholar.pois.api.mapper.response.UserInfo;
import com.ctream.scholar.pois.models.POI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static com.ctream.scholar.pois.utils.SchoolarUtils.createPois;

@RestController
@RequiredArgsConstructor
@Tag( name = "POI Management", description = "Endpoints for managing Points of Interest (POIs)" )
public class PoisRestController {

    private static List<POI> POIS = createPois();
    private final IPOIMapper ipoiMapper;

    @GetMapping( "/api/v1/pois" )
    @Operation( summary = "Retrieve a list of POIs", description = "Returns all POIs or filters by liked status and category" )
    @ApiResponses( value = {
        @ApiResponse( responseCode = "200", description = "Successfully retrieved POIs",
            content = @Content( mediaType = "application/json", schema = @Schema( implementation = POIInfo.class ) ) )
    } )
    public ResponseEntity<List<POIInfo>> getPois( @RequestParam( required = false ) Boolean isLiked,
                                                  @RequestParam( required = false ) String category ) {
        var filteredPOIs = POIS.stream()
            .filter( poi -> ( isLiked == null || Objects.equals( poi.getIsLiked(), isLiked ) ) )
            .filter( poi -> ( category == null || category.equalsIgnoreCase( poi.getCategory() ) ) )
            .toList();
        return ResponseEntity.ok( ipoiMapper.mapPOIList( filteredPOIs ) );
    }

    @GetMapping( "/api/v1/pois/categories" )
    @Operation( summary = "Retrieve a list of all existing categories", description = "Retrieve a list of all existing categories" )
    @ApiResponses( value = {
        @ApiResponse( responseCode = "200", description = "Successfully retrieved categories",
            content = @Content( mediaType = "application/json",
                array = @ArraySchema( schema = @Schema( type = "string" ) ) ) )
    } )
    ResponseEntity<List<String>> getAllCategories() {
        var categories = POIS.stream()
            .map( POI::getCategory )
            .filter( Objects::nonNull ).distinct().toList();
        return ResponseEntity.ok( categories );
    }

    @GetMapping( "/api/v1/pois/{poiId}" )
    @Operation( summary = "Retrieve a POI by ID", description = "Returns a single POI by its ID" )
    @ApiResponses( value = {
        @ApiResponse( responseCode = "200", description = "Successfully retrieved POI",
            content = @Content( mediaType = "application/json", schema = @Schema( implementation = POI.class ) ) ),
        @ApiResponse( responseCode = "404", description = "POI not found" )
    } )
    public ResponseEntity<POI> getPoi( @PathVariable String poiId ) {
        return ResponseEntity.ok( POIS.stream()
            .filter( poi -> poi.getId().equals( poiId ) )
            .findFirst().orElseThrow() );
    }

    @PostMapping( "/api/v1/pois/{poiId}" )
    @Operation( summary = "Like or unlike a POI", description = "Sets the liked status of a POI" )
    @ApiResponses( value = {
        @ApiResponse( responseCode = "204", description = "Successfully updated POI status" ),
        @ApiResponse( responseCode = "404", description = "POI not found" )
    } )
    public ResponseEntity<Void> likePoi( @PathVariable String poiId, @RequestParam Boolean isLiked ) {
        var currentPoi = POIS.stream()
            .filter( poi -> poi.getId().equals( poiId ) )
            .findFirst().orElseThrow();

        currentPoi.setIsLiked( isLiked );

        return ResponseEntity.noContent().build();
    }

    @GetMapping( "/api/v1/users" )
    @Operation( summary = "Retrieve user info", description = "Returns the authenticated user's details" )
    @ApiResponses( value = {
        @ApiResponse( responseCode = "200", description = "Successfully retrieved user info",
            content = @Content( mediaType = "application/json", schema = @Schema( implementation = UserInfo.class ) ) ),
        @ApiResponse( responseCode = "401", description = "Unauthorized" )
    } )
    public ResponseEntity<List<UserInfo>> getUsers( Authentication authentication ) {
        var jwt = ( Jwt ) authentication.getPrincipal();
        var user = UserInfo.builder()
            .id( jwt.getClaimAsString( "sub" ) )
            .name( jwt.getClaimAsString( "nickname" ) )
            .email( jwt.getClaimAsString( "email" ) )
            .build();
        return ResponseEntity.ok( List.of( user ) );
    }
}
