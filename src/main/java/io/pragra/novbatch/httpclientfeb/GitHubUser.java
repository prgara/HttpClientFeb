package io.pragra.novbatch.httpclientfeb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GitHubUser {

    private String login;
    private String  id;
    private String  node_id;
    private String   avatar_url;
    private String   gravatar_id;
    private String  url;
    private String html_url;
    private String  followers_url;
    private String following_url;

}
