package com.ctream.scholar.profile;

import com.ctream.scholar.profiles.api.ProfilesApi;
import com.ctream.scholar.profiles.api.model.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProfileRestController implements ProfilesApi {

    @Override
    public ResponseEntity<UserProfile> getProfileOfCurrentUser() {
        var jwt = (Jwt) ( (JwtAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication() )
                .getPrincipal();

        var sub = jwt.getClaimAsString( "sub" );

        var profile = new UserProfile();
        profile.setUserId( UUID.randomUUID() );
        profile.setRole( "ROLE_USER" );
        profile.setUsername( sub );

        return ResponseEntity.ok().body( profile );
    }
}
