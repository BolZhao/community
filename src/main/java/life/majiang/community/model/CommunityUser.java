package life.majiang.community.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Data
@NoArgsConstructor
@Component
public class CommunityUser {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    Long gmtCreate;
    Long gmtModified;

    public CommunityUser(Integer id, String accountId, String name, String token, Long gmtCreate, Long gmtModified) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.token = token;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
