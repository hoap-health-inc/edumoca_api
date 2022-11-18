package com.edumoca.soma.services.controllers;
import java.util.Objects;

import com.edumoca.soma.services.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edumoca.soma.services.config.JwtTokenUtil;
import com.edumoca.soma.services.security.models.JwtRequest;
import com.edumoca.soma.services.security.models.JwtResponse;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
//@Api(tags = "Authentication")
@OpenAPIDefinition(tags = @Tag(name = "Authentication"))
@AllArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService jwtInMemoryUserDetailsService;
    private final UsersService usersService;


    @Operation(
	    		tags = {"Authenticate User"},
	    		summary = "Authenticate User summary",
	    		description = "Authenticate User desc"
	    )

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(token);
        for(GrantedAuthority role : userDetails.getAuthorities())
        if(role.getAuthority().equals("ROLE_STUDENT")) {
            jwtResponse.setData(usersService.showStudentDetails(userDetails.getUsername()));
        }else if(role.getAuthority().equals("ROLE_TEACHER")){
            jwtResponse.setData(usersService.showTeacherDetails(userDetails.getUsername()));
        }else if(role.getAuthority().equals("ROLE_HOMEROOMTEACHER")){
            jwtResponse.setData(usersService.showHomeRoomDetails(userDetails.getUsername()));
        }else if(role.getAuthority().equals("ROLE_SUBJECTHEAD")){
            jwtResponse.setData(usersService.showSubjectHeadDetails(userDetails.getUsername()));
        }else if(role.getAuthority().equals("ROLE_SCHOOLADMIN")){
            jwtResponse.setData(usersService.showSchoolAdminByLoginId(userDetails.getUsername()));
        }else if(role.getAuthority().equals("ROLE_SUPERADMIN")){
            jwtResponse.setData(usersService.showSuperAdminByLoginId(userDetails.getUsername()));
        }else if(role.getAuthority().equals("ROLE_PARENT")){
            jwtResponse.setData(usersService.showParentByLoginId(userDetails.getUsername()));
        }else if(role.getAuthority().equals("ROLE_PRINCIPAL")){
            jwtResponse.setData(usersService.showPrincipalByLoginId(userDetails.getUsername()));
        }
        return ResponseEntity.ok(jwtResponse);
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
