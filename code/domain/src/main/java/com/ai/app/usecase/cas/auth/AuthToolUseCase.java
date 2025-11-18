package com.ai.app.usecase.cas.auth;

import com.ai.app.model.cas.auth.TokenDTO;
import com.ai.app.model.cas.auth.UserFullDetailsDTO;

public interface AuthToolUseCase {

    TokenDTO authenticateUser(String username, String password);

    UserFullDetailsDTO getCurrentUserInfo();

    TokenDTO refreshToken();
}
