package rev.team.API_GATEWAY.user.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rev.team.API_GATEWAY.user.domain.RevUser;

import javax.transaction.Transactional;

@Service
@Transactional
public class RevUserService implements UserDetailsService {
        private final static String AUTH_SERVER = "10.178.0.2";

    private String getURL(){
        return "http://" + AUTH_SERVER + ":8775";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RestTemplate api = new RestTemplate();
        return api.getForEntity(getURL() + "/userInfo?userId=" + username, RevUser.class).getBody();
    }

    public String getNickname(String username){
        RestTemplate api = new RestTemplate();
        return api.getForEntity(getURL() + "/nickname?userId=" + username, String.class).getBody();
    }
}
