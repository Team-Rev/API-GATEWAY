package rev.team.API_GATEWAY.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rev.team.API_GATEWAY.models.AuthenticationRequest;
import rev.team.API_GATEWAY.models.AuthenticationResponse;
import rev.team.API_GATEWAY.user.domain.RevUser;
import rev.team.API_GATEWAY.user.service.RevUserService;
import rev.team.API_GATEWAY.util.JwtUtil;

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

    @PostMapping("/signup")
    public String signUp(@RequestBody RevUser user){
        return userDetailsService.save(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
