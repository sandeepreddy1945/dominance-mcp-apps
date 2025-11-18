package com.ai.app.common.props;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestBuilderProps {

  private String baseUrl;

  private int connectTimeout;

  private int readTimeout;

  private String token;
}
