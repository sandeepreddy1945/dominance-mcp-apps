package com.ai.app.props;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.dominance")
@Component
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
