package com.ai.app.service.cas.user;

import com.ai.app.model.cas.user.UserResponseDTO;
import java.util.List;

public interface UserToolService {

  UserResponseDTO getUserDetails();

  UserResponseDTO updateUserPreferences(String fullName, List<String> interests, String locale);
}
