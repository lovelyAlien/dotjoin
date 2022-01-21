package com.dangsan.dotjoin.infra.oauth.provider;

import java.util.Map;

public class GitHubUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public GitHubUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("id").toString();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }


    @Override
    public String getProvider() {
        return "github";
    }
}
