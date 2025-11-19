package com.ai.app.infra.cas.auth;

import com.ai.app.cas.gen.api.AuthenticationApi;
import com.ai.app.cas.gen.model.UserCreate;
import com.ai.app.cas.gen.model.UserLogin;
import com.ai.app.cas.gen.model.UserResponse;
import com.ai.app.exception.UserAlreadyExists;
import com.ai.app.infra.cas.user.UserToolMapper;
import com.ai.app.model.cas.auth.TokenDTO;
import com.ai.app.model.cas.auth.UserCreateDTO;
import com.ai.app.model.cas.auth.UserFullDetailsDTO;
import com.ai.app.model.cas.user.UserResponseDTO;
import com.ai.app.service.cas.auth.AuthToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient.ResponseSpec;

@Service
@RequiredArgsConstructor
public class AuthToolServiceImpl implements AuthToolService {

  private final AuthenticationApi authenticationApi;

  private final AuthToolMapper authToolMapper;

  private final UserToolMapper userToolMapper;

  @Override
  public TokenDTO authenticateUser(String username, String password) {
    return this.authToolMapper.fromToken(
        this.authenticationApi.loginApiAuthLoginPost(
            new UserLogin().username(username).password(password)));
  }

  @Override
  public UserFullDetailsDTO getCurrentUserInfo() {
    return this.authToolMapper.fromUserFullResponse(
        this.authenticationApi.getCurrentUserInfoApiAuthMeGet());
  }

  @Override
  public TokenDTO refreshToken() {
    return this.authToolMapper.fromToken(this.authenticationApi.refreshTokenApiAuthRefreshPost());
  }

  @Override
  public UserResponseDTO registerUser(UserCreateDTO userCreateDTO) {
    UserCreate createUserEntity = this.authToolMapper.toCreateUserEntity(userCreateDTO);
    ResponseSpec responseSpec =
        this.authenticationApi.registerApiAuthRegisterPostWithResponseSpec(createUserEntity);
    responseSpec.onStatus(
        status -> status.value() == 400,
        (req, res) -> {
          throw new UserAlreadyExists("The username or email specified already exists.");
        });
    ParameterizedTypeReference<UserResponse> localVarReturnType =
        new ParameterizedTypeReference<>() {};
    return this.userToolMapper.toUserResponseDTO(responseSpec.body(localVarReturnType));
  }
}
