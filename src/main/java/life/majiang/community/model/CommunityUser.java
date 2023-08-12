package life.majiang.community.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component

public class CommunityUser {
    private Integer id;
    private String account_id;
    private String name;
    private String token;
    Long gmt_create;
    Long gmt_modified;

    public CommunityUser(Integer id, String account_id, String name, String token, Long gmt_create, Long gmtModified) {
        this.id = id;
        this.account_id = account_id;
        this.name = name;
        this.token = token;
        this.gmt_create = gmt_create;
        this.gmt_modified = gmtModified;
    }
}
