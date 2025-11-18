package com.ai.app.infra.cas.user;

import com.ai.app.cas.gen.api.UsersApi;
import com.ai.app.model.cas.user.UserResponseDTO;
import com.ai.app.service.cas.user.UserToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserToolServiceImpl implements UserToolService {

    private final UsersApi usersApi;

    private final UserToolMapper userToolMapper;

    @Override
    public UserResponseDTO getUserDetails() {
        return this.userToolMapper.toUserResponseDTO(this.usersApi.getCurrentUserProfileApiUsersMeGet());
    }
}
