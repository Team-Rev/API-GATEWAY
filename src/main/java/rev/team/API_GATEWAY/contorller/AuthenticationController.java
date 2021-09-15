package rev.team.API_GATEWAY.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import rev.team.API_GATEWAY.models.AuthenticationRequest;
import rev.team.API_GATEWAY.models.AuthenticationResponse;
import rev.team.API_GATEWAY.user.service.RevUserService;
import rev.team.API_GATEWAY.util.JwtUtil;

import java.util.Objects;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final RevUserService userDetailsService;
    private final JwtUtil jwtTokenUtil;

    @Autowired
    public AuthenticationController(RevUserService userDetailsService
            , JwtUtil jwtTokenUtil
            ,AuthenticationManager authenticationManager){
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserId(), authenticationRequest.getPassword())
            );
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.OK);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserId());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        String nickname = userDetailsService.getNickname(authenticationRequest.getUserId());
        return ResponseEntity.ok(new AuthenticationResponse(jwt, nickname));
    }
}
