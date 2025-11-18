package com.ai.app.common.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "app.dominance")
@Data
public class ClientProps {

  private final Map<String, RestBuilderProps> clients = new HashMap<>();

  public RestBuilderProps get(String name) {
    return clients.get(name);
  }

  public Map<String, RestBuilderProps> getClient() {
    return clients;
  }
}
