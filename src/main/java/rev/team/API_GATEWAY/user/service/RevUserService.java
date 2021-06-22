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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RestTemplate api = new RestTemplate();
        return api.getForEntity("http://localhost:8775/user?username="+username, RevUser.class).getBody();
    }

}
