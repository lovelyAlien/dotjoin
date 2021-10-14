package com.dangsan.dotjoin.infra.oauth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
@Builder(access = AccessLevel.PRIVATE)
@Getter
class OAuth2Attribute {
    private Map<String, Object> attributes;
    private String provider;
    private String attributeKey;
    private String email;
    private String name;
    private String picture;

    static OAuth2Attribute of(String provider, String attributeKey,
                              Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return ofGoogle(provider,attributeKey, attributes); //sub
            case "kakao":
                return ofKakao(provider,"email", attributes);
            case "naver":
                return ofNaver(provider,"id", attributes);
            case "github":
                return ofGitHub(provider,attributeKey, attributes); //id
            default:
                throw new RuntimeException();
        }
    }

    private static OAuth2Attribute ofGoogle(String provider, String attributeKey,
                                            Map<String, Object> attributes) {
        return OAuth2Attribute.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .provider(provider)
                .attributes(attributes)
                .attributeKey((String) attributes.get(attributeKey))
                .build();
    }

    private static OAuth2Attribute ofGitHub(String provider,String attributeKey,
                                            Map<String, Object> attributes) {
        return OAuth2Attribute.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("login"))
                .picture((String)attributes.get("avatar_url"))
                .provider(provider)
                .attributes(attributes)
                .attributeKey(attributes.get(attributeKey).toString())
                .build();
    }

    private static OAuth2Attribute ofKakao(String provider,String attributeKey,
                                           Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2Attribute.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .picture((String)kakaoProfile.get("profile_image_url"))
                .provider(provider)
                .attributes(kakaoAccount)
                .attributeKey((String) attributes.get(attributeKey))
                .build();
    }

    private static OAuth2Attribute ofNaver(String provider,String attributeKey,
                                           Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2Attribute.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .provider(provider)
                .attributes(response)
                .attributeKey((String) attributes.get(attributeKey))
                .build();
    }

    Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("provider",provider);
        map.put("name", name);
        map.put("email", email);
        map.put("picture", picture);

        return map;
    }
}

