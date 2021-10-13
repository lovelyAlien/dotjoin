package com.dangsan.dotjoin.infra.oauth;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //loadUser: 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // userRequest 는 code를 받아서 accessToken을 응답 받은 객체
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회

        // code를 통해 구성한 정보
        System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());//로그인 기본 정보 제공. 어떤 OAuth로 로그인했는지 확인 가능
        System.out.println("userRequest.getAccessToken(): "+ userRequest.getAccessToken().getTokenValue());


        OAuth2User oauth2User= super.loadUser(userRequest);
        System.out.println("getAttributes: "+ oauth2User.getAttributes());
        //구글 로그인 버튼 클릭-> 구글 로그인 창-> 로그인 완료-> code를 리턴(OAuth-Client 라이브러리)->AccessToken 요청
        //userRequest 정보->loadUser함수 호출->구글로부터 회원 프로필 받음

        String provider= userRequest.getClientRegistration().getClientId(); //google
        String providerId=oauth2User.getAttribute("sub");
        String username=provider+"_"+providerId;
        String password=bCryptPasswordEncoder.encode("GOOGLE_PASSWORD");
        String email=oauth2User.getAttribute("email");
        String role="ROLE_USER";
        Account userEntity=accountRepository.findByEmail(email);

//        if(userEntity==null){
//            System.out.println("구글 로그인이 최초입니다.");
//            userEntity = User.builder()
//                    .username(username)
//                    .email(email)
//                    .role(role)
//                    .provider(provider)
//                    .providerId(providerId)
//                    .build();
//            userRepository.save(userEntity);
//        }
//        else{
//            System.out.println("구글 로그인을 이미 한 적 있습니다. 자동 회원가입이 되어 있습니다.");
//
//        }
//
//        return new PrincipalDetails(userEntity, oauth2User.getAttributes());
        // token을 통해 응답받은 회원정보
		System.out.println("oAuth2User : " + oauth2User);
        return oauth2User;
//		return processOAuth2User(userRequest, oauth2User);
    }

//	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
//
//		// Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
//		OAuth2UserInfo oAuth2UserInfo = null;
//		if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
//			System.out.println("구글 로그인 요청~~");
//			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
//		} else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
//			System.out.println("페이스북 로그인 요청~~");
//			oAuth2UserInfo = new FaceBookUserInfo(oAuth2User.getAttributes());
//		} else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
//			System.out.println("네이버 로그인 요청~~");
//			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
//		} else {
//			System.out.println("우리는 구글과 페이스북만 지원해요 ㅎㅎ");
//		}
//
//		//System.out.println("oAuth2UserInfo.getProvider() : " + oAuth2UserInfo.getProvider());
//		//System.out.println("oAuth2UserInfo.getProviderId() : " + oAuth2UserInfo.getProviderId());
//		Optional<User> userOptional =
//				userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());
//
//		User user;
//		if (userOptional.isPresent()) {
//			user = userOptional.get();
//			// user가 존재하면 update 해주기
//			user.setEmail(oAuth2UserInfo.getEmail());
//			userRepository.save(user);
//		} else {
//			// user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
//			user = User.builder()
//					.username(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
//					.email(oAuth2UserInfo.getEmail())
//					.role("ROLE_USER")
//					.provider(oAuth2UserInfo.getProvider())
//					.providerId(oAuth2UserInfo.getProviderId())
//					.build();
//			userRepository.save(user);
//		}
//
//		return new PrincipalDetails(user, oAuth2User.getAttributes());
//	}
}
