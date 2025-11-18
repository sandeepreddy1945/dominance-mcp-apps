package com.ai.app.application.cas.user;

import com.ai.app.model.cas.user.UserResponseDTO;
import com.ai.app.service.cas.user.UserToolService;
import com.ai.app.usecase.cas.user.UserToolUseCase;
import lombok.RequiredArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToolUseCaseImpl implements UserToolUseCase {

    private final UserToolService userToolService;

    @Override
    @McpTool(name = "who_am_i", description = "Get the current logged in user details")
    public UserResponseDTO getUserDetails() {
        return this.userToolService.getUserDetails();
    }
}
