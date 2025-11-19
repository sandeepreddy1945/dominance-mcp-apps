package com.ai.app.service.cas.auth;

import com.ai.app.model.cas.auth.TokenDTO;
import com.ai.app.model.cas.auth.UserCreateDTO;
import com.ai.app.model.cas.auth.UserFullDetailsDTO;
import com.ai.app.model.cas.user.UserResponseDTO;

public interface AuthToolService {
  TokenDTO authenticateUser(String username, String password);

  UserFullDetailsDTO getCurrentUserInfo();

  TokenDTO refreshToken();

  UserResponseDTO registerUser(UserCreateDTO userCreateDTO);
}
