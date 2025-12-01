package com.ai.app.infra.cas.user;

import com.ai.app.cas.gen.model.UserResponse;
import com.ai.app.cas.gen.model.UserUpdate;
import com.ai.app.model.cas.user.UserResponseDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserToolMapper {
  UserResponseDTO toUserResponseDTO(UserResponse currentUserProfileApiUsersMeGet);

  UserUpdate toUpdateUserProfile(String fullName, String locale, List<String> interests);
}
