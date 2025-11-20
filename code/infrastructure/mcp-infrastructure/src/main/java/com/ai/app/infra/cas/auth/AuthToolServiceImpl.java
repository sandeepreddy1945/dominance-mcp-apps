package com.ai.app.infra.cas.auth;

import static com.ai.app.common.util.ResponseUtils.extractResponse;

import com.ai.app.cas.gen.api.AuthenticationApi;
import com.ai.app.cas.gen.model.Token;
import com.ai.app.cas.gen.model.UserCreate;
import com.ai.app.cas.gen.model.UserLogin;
import com.ai.app.cas.gen.model.UserResponse;
import com.ai.app.exception.auth.UnAuthorizedUserException;
import com.ai.app.exception.auth.UserAlreadyExistsException;
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
    UserLogin userLoginDetails = new UserLogin().username(username).password(password);
    ResponseSpec responseSpec =
        this.authenticationApi.loginApiAuthLoginPostWithResponseSpec(userLoginDetails);
    ParameterizedTypeReference<Token> parameterizedTypeReference =
        new ParameterizedTypeReference<>() {};
    Token token =
        extractResponse(
            responseSpec,
            parameterizedTypeReference,
            401,
            new UnAuthorizedUserException("Incorrect username or password supplied"));
    return this.authToolMapper.fromToken(token);
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
    ParameterizedTypeReference<UserResponse> parameterizedTypeReference =
        new ParameterizedTypeReference<>() {};
    UserResponse userResponse =
        extractResponse(
            responseSpec,
            parameterizedTypeReference,
            400,
            new UserAlreadyExistsException("The username or email specified already exists."));

    return this.userToolMapper.toUserResponseDTO(userResponse);
  }
}
