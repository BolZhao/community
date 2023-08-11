package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthorizeCallback;
import java.io.IOException;

@Component
public class GithubPeovider {
    public String GetAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {

                String string = response.body().string();
                System.out.println(string);
                String[] parts = string.split("&");
                for (String part : parts) {
                    if (part.startsWith("access_token=")) {
                        string = part.split("=")[1];
                        System.out.println(string);
                        break;
                    }
                }
                return string;
            }
    }
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.github.com/user?"+ accessToken)
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build();
        System.out.println("request = " + request.toString());
            try (Response response = client.newCall(request).execute()) {
               String string = response.body().string();
                System.out.println(string);
                GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
                return githubUser;
            } catch (IOException e) {}
            return null;
    }
}
