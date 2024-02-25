package com.alibou.security.sevices.ServiceImp;

import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.UserDto;
import com.alibou.security.model.SalonsProf;
import com.alibou.security.model.UserImages;
import com.alibou.security.model.Users;
import com.alibou.security.repository.FileDataRepository;
import com.alibou.security.repository.SalonProfRepository;
import com.alibou.security.repository.UserImageRepository;
import com.alibou.security.repository.UsersRepository;
import com.alibou.security.sevices.SalonProfService;
import com.alibou.security.sevices.UserService;
import com.alibou.security.template.Position;
import com.alibou.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UsersRepository userRepository;

    private final UserImageRepository userImageRepository;

    private final SalonProfRepository salonProfRepository;

    private final SalonProfService salonProfService;

    private final StorageService storageService;

    private final FileDataRepository fileDataRepository;


    @Override
    public ApiResponse createUser(UserDto userDto)throws IOException {
        boolean b = userRepository.existsByUserName(userDto.getUserName());
        if (b) return new ApiResponse("Already registered",false);
        Users users =new Users();
        users.setName(userDto.getName());
        users.setPhoneNumber(userDto.getPhoneNumber());
        users.setDistrict(userDto.getDistrict());
        users.setAddress(userDto.getAddress());

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        UserImages userImages=new UserImages();
        userImages.setOriginalName(userDto.getUserImage().getName());
        userImages.setContentType(userDto.getUserImage().getContentType());
        userImages.setImageByte(userDto.getUserImage().getBytes());
        userImageRepository.save(userImages);

        users.setUserImage(userImages);

        users.setUserName(userDto.getUserName());
        users.setUserName(userDto.getUserName());
        users.setPassword(userDto.getPassword());
        users.setPosition(Position.USER);
        userRepository.save(users);
        return new ApiResponse("Successfully registered",true);
    }
    @Override
    public ApiResponse updateUser(long id, UserDto userDto) throws IOException {
        Optional<Users> byId = userRepository.findById(id);
        boolean b = userRepository.existsByIdNotAndUserName(id, userDto.getUserName());
        if (byId.isEmpty()) return new ApiResponse("User Not Found",false);
        if (b)return new ApiResponse("Already user register with userName",false);
        Users users=byId.get();
        users.setName(userDto.getName());
        users.setPhoneNumber(userDto.getPhoneNumber());
        users.setDistrict(userDto.getDistrict());
        users.setAddress(userDto.getAddress());

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        UserImages userImages=byId.get().getUserImage();
        userImages.setOriginalName(userImages.getOriginalName());
        userImages.setContentType(userImages.getContentType());
        userImages.setImageByte(userImages.getImageByte());
        userImageRepository.save(userImages);

        users.setUserImage(userImages);

        users.setUserName(userDto.getUserName());
        users.setPassword(userDto.getPassword());
        users.setPosition(Position.USER);
        userRepository.save(users);
        return new ApiResponse("Successfully update Users",true);

    }

    @Override
    public ApiResponse deleteUser(long id) {
        Optional<Users> byId = userRepository.findById(id);

        if(byId.isEmpty()) return new ApiResponse("User NoFound",false);
        for (SalonsProf salonsProf : salonProfRepository.findByUsersId(id)) {
            salonProfService.deleteSalonProf(salonsProf.getUsersId());
        }
        userRepository.deleteById(id);
        return new ApiResponse("User deleted",true,byId);
    }

    @Override
    public ApiResponse getUser(long id) {
        Optional<Users> byId = userRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("user not found",true,byId);
        return new ApiResponse("user "+id,true,byId);
    }

    @Override
    public ApiResponse allUser() {
        List<Users> all = userRepository.findAll();
        return new ApiResponse("All Users", true, all);
    }
}
