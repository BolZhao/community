package life.majiang.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.CommunityUserMapper;
import life.majiang.community.model.CommunityUser;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    GithubUser githubUser;


    @Autowired
    CommunityUserMapper communityUserMapper;
    @Value("${github.client.id}")
    private String ClientID;
    @Value("${github.redirect_uri}")
    private String RedirectUri;
    @Value("${github.clent.secret}")
    private String ClientSecret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(ClientID);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(RedirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(ClientSecret);
        String token = githubProvider.GetAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(token);
        System.out.println(user.toString());
        CommunityUser communityUser = new CommunityUser(
                198,
                String.valueOf(user.getId()),
                user.getName(),
                UUID.randomUUID().toString(),
                100L,
                100L
        );
        communityUserMapper.insert(communityUser);
        List<CommunityUser> userList = communityUserMapper.show();
        for (CommunityUser u : userList) {
            System.out.println(u);
        }
        if (user !=null){
            request.getSession().setAttribute("user",user);
            return "redirect:"; //如果不加redirect，就会只渲染，而地址不会变
        }
        else {
            return "redirect:";
        }
    }
}