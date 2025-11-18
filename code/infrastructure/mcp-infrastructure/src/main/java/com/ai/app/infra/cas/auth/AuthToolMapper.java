package com.ai.app.infra.cas.auth;

import com.ai.app.cas.gen.model.Token;
import com.ai.app.cas.gen.model.UserFullResponse;
import com.ai.app.model.cas.auth.TokenDTO;
import com.ai.app.model.cas.auth.UserFullDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthToolMapper {

  @Mapping(source = "user", target = "userDetails")
  TokenDTO fromToken(Token token);

  UserFullDetailsDTO fromUserFullResponse(UserFullResponse userFullDetails);
}
