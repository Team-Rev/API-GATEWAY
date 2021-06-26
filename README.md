프로젝트 전체 구조 : <https://github.com/Team-Rev/MSA-SERVER>

# API GATEWAY 서비스
MSA 구조로 구현되고 있는 API 서버에서 사용되는 API-GATEWAY이다.<br/>
모든 요청은 해당 서비스를 지나야 하고, 해당 서비스에서 URL을 기준으로 서비스를 라우팅 한다.

#### 사용된 라이브러리
    * Spring Cloud Zuul : API-GATEWAY를 구현하기 위한 라이브러리. 
    * Srping Security : 인증 및 권한을 관리하기 위해서 사용.
    * JWT : Access Token과 Refresh Token을 발급하여 사용자 인증에 사용.


