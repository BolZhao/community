package life.majiang.community.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.CommunityUserMapper;
import life.majiang.community.model.CommunityUser;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.service.CommunityUserService;
import life.majiang.community.service.GithubAuthService;
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
    GithubAuthService githubAuthService;
    @Autowired
    CommunityUserService communityUserService;
    @Autowired
    CommunityUserMapper communityUserMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String accessToken = githubAuthService.getAccessToken(code, state);
        GithubUser user = githubAuthService.getUser(accessToken);
        System.out.println(user.toString());

        if (user.getName() !=null){
            communityUserService.insertUser(user);
            response.addCookie(new Cookie("token",accessToken));
            request.getSession().setAttribute("user",user);
            return "redirect:"; //如果不加redirect，就会只渲染，而地址不会变
        }
        else {
            return "redirect:";
        }
    }
}