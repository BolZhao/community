package life.majiang.community.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import life.majiang.community.mapper.CommunityUserMapper;
import life.majiang.community.model.CommunityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class indexController {
    @Autowired
    private CommunityUserMapper communityUserMapper;
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                CommunityUser communityUser = communityUserMapper.findByToken(token);
                if (communityUser!=null){
                    request.getSession().setAttribute("user",communityUser);
                }
                break;
            }
        }
        return "index";
    }
}
