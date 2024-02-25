package com.alibou.security.sevices;



import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.UserDto;

import java.io.IOException;

public interface UserService {
    ApiResponse createUser(UserDto userDto) throws IOException;
    ApiResponse updateUser(long id,UserDto userDto) throws IOException;
    ApiResponse deleteUser(long id);
    ApiResponse getUser(long id);
    ApiResponse allUser();
}
