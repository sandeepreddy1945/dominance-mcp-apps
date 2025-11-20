package com.ai.app.usecase.cas.auth;

import io.modelcontextprotocol.spec.McpSchema.GetPromptResult;

public interface AuthPromptsUseCase {

  GetPromptResult getUserAuthPrompt();
}
