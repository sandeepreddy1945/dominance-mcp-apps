package com.ai.app.config;

import com.ai.app.common.props.ClientProps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {

  @Bean
  public ClientProps clientProps() {
    return new ClientProps();
  }
}
