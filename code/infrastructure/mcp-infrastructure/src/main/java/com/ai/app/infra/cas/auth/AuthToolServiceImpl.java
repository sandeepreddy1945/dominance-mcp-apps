package com.ai.app.infra.cas.auth;

import com.ai.app.cas.gen.api.AuthenticationApi;
import com.ai.app.cas.gen.model.UserLogin;
import com.ai.app.model.cas.auth.TokenDTO;
import com.ai.app.model.cas.auth.UserFullDetailsDTO;
import com.ai.app.service.cas.auth.AuthToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthToolServiceImpl implements AuthToolService {

    private final AuthenticationApi authenticationApi;

    private final AuthToolMapper authToolMapper;

    @Override
    public TokenDTO authenticateUser(String username, String password) {
        return this.authToolMapper.fromToken(this.authenticationApi.loginApiAuthLoginPost(
                new UserLogin().username(username).password(password)));
    }

    @Override
    public UserFullDetailsDTO getCurrentUserInfo() {
        return this.authToolMapper.fromUserFullResponse(this.authenticationApi.getCurrentUserInfoApiAuthMeGet());
    }

    @Override
    public TokenDTO refreshToken() {
        return this.authToolMapper.fromToken(this.authenticationApi.refreshTokenApiAuthRefreshPost());
    }
}
