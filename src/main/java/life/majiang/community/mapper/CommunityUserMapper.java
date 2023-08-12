package life.majiang.community.mapper;

import life.majiang.community.model.CommunityUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommunityUserMapper {

    @Select("select * from user")
    List<CommunityUser> show();

    @Insert("INSERT INTO user ( account_id, name, token, gmt_create, gmt_modified) " +
            "VALUES ( #{account_id}, #{name}, #{token}, #{gmt_create}, #{gmt_modified})")
    void insert(CommunityUser user);
}
