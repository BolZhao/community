package life.majiang.community.mapper;

import life.majiang.community.model.CommunityUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommunityUserMapper {

    @Select("select * from user")
    List<CommunityUser> show();

    @Insert("INSERT INTO user ( account_id, name, token, gmt_create, gmt_modified) " +
            "VALUES ( #{account_id}, #{name}, #{token}, #{gmt_create}, #{gmt_modified})")
    void insert(CommunityUser user);

    @Select("select * from user where token=#{token}")
    CommunityUser  findByToken(@Param("token") String token); //这里不是类的时候需要加上注解
}
