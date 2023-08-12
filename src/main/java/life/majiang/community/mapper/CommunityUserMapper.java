package life.majiang.community.mapper;

import life.majiang.community.model.CommunityUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommunityUserMapper {

    @Select("select * from user")
    List<CommunityUser> show();

}
