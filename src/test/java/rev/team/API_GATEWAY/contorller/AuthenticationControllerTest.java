package rev.team.API_GATEWAY.contorller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import rev.team.API_GATEWAY.user.domain.RevUser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest {

    @LocalServerPort
    int port;

    TestRestTemplate client = new TestRestTemplate();

    @DisplayName("1. Sign Up success")
    @Test
    void test1(){
        RevUser user = RevUser.builder()
                .userId("user@naver.com")
                .password("12345678")
                .nickname("nickname")
                .name("name")
                .DOB(LocalDate.now())
                .address("Daegu Bukgu")
                .phone("01012345678").build();

        System.out.println(user);
        String result = client.postForObject("http://localhost:" + port + "/signup"
                                    ,user
                                    ,String.class);

        assertEquals("SUCCESS", result);
    }
}