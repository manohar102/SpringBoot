package com.mano.projects.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mano.projects.dto.UserDto;
import com.mano.projects.model.User;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto userToUserDto(User user);
	
	User userDtoToUser(UserDto user);
        
        List<UserDto>  userToUserDto(Collection<User> users);
	
}
