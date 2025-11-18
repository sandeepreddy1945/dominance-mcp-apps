package com.ai.app.common.props;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
