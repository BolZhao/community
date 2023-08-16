package life.majiang.community.service;

import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.CommunityUserMapper;
import life.majiang.community.model.CommunityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommunityUserService {
    @Autowired
    CommunityUserMapper communityUserMapper;
    public void insertUser(GithubUser githubUser){
        CommunityUser communityUser = new CommunityUser(
                198,
                String.valueOf(githubUser.getId()),
                githubUser.getName(),
                UUID.randomUUID().toString(),
                100L,
                100L
        );
        communityUserMapper.insert(communityUser);
    }

}
