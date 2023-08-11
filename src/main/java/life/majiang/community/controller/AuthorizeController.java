package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.provider.GithubPeovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubPeovider githubPeovider;
    @Autowired
    GithubUser githubUser;
    @Value("${github.client.id}")
    private String ClientID;
    @Value("${github.redirect_uri}")
    private String RedirectUri;
    @Value("${github.clent.secret}")
    private String ClientSecret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(ClientID);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(RedirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(ClientSecret);
        String token = githubPeovider.GetAccessToken(accessTokenDTO);
        GithubUser user = githubPeovider.getUser(token);

        System.out.println(user.toString());
        return "hello";
    }
}