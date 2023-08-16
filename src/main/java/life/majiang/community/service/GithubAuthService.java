package life.majiang.community.service;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

// GithubAuthService.java
@Service
public class GithubAuthService {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String ClientID;
    @Value("${github.redirect_uri}")
    private String RedirectUri;
    @Value("${github.client.secret}")
    private String ClientSecret;
    public String getAccessToken(String code, String state) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(ClientID);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(RedirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(ClientSecret);
        return githubProvider.GetAccessToken(accessTokenDTO);
    }

    public GithubUser getUser(String token) {
        return githubProvider.getUser(token);
    }
}
