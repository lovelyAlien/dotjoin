package com.dangsan.dotjoin.infra.oauth.provider;

import com.dangsan.dotjoin.infra.oauth.provider.OAuth2UserInfo;

import java.util.LinkedHashMap;
import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;
    private LinkedHashMap<String, Object> properties;
    private LinkedHashMap<String, Object> kakao_account;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        properties= (LinkedHashMap<String, Object>) this.attributes.get("properties");
        kakao_account= (LinkedHashMap<String, Object>) this.attributes.get("kakao_account");
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("id").toString();
    }

    @Override
    public String getName() {
        return (String) properties.get("nickname");
    }


    @Override
    public String getEmail() {
        return (String) kakao_account.get("email");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }
}
