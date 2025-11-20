package com.ai.app.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient.ResponseSpec;

@UtilityClass
public class ResponseUtils {

  public static <T> T extractResponse(
      ResponseSpec responseSpec,
      ParameterizedTypeReference<T> bodyType,
      int errorCode,
      RuntimeException cause) {
    responseSpec.onStatus(
        status -> status.value() == errorCode,
        (req, res) -> {
          throw cause;
        });
    return responseSpec.body(bodyType);
  }

  public static <T> T extractResponse(
      ResponseSpec responseSpec, Class<T> bodyType, int errorCode, RuntimeException cause) {
    responseSpec.onStatus(
        status -> status.value() == errorCode,
        (req, res) -> {
          throw cause;
        });
    return responseSpec.body(bodyType);
  }
}
