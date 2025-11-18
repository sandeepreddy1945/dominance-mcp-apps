package com.ai.app.service.cas.auth;

import com.ai.app.model.cas.auth.TokenDTO;
import com.ai.app.model.cas.auth.UserFullDetailsDTO;

public interface AuthToolService {
  TokenDTO authenticateUser(String username, String password);

  UserFullDetailsDTO getCurrentUserInfo();

  TokenDTO refreshToken();
}
