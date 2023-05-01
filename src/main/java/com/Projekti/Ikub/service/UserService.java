package com.Projekti.Ikub.service;

import com.Projekti.Ikub.dto.user.UserDTO;
import com.Projekti.Ikub.dto.user.UserUpdateDTO;
import com.Projekti.Ikub.entity.User;

public interface UserService {

    User findById(Integer id);

    UserDTO registerUser(UserDTO userDTO,String userRole);

    UserUpdateDTO updateUser(Integer id,UserUpdateDTO upd);


}
