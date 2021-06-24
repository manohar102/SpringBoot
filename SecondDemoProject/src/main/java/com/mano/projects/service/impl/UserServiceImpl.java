package com.mano.projects.service.impl;

import com.mano.projects.dto.UserDto;
import com.mano.projects.dto.mapper.UserMapper;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mano.projects.model.User;
import com.mano.projects.repository.UserRepository;
import com.mano.projects.service.UserService;
import java.util.Optional;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Transactional
@Service @RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
        
        private final UserMapper userMapper;

        private final PasswordEncoder passwordEncoder;
	
        @Override
	public UserDto createUser(User user) throws Exception{
                UserDto userDto = null;
                try{
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    User user1 = userRepository.save(user);
                    userDto = userMapper.userToUserDto(user1);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    throw new Exception("User Creation unsuccessfull");
                }
		return userDto;
	}
        
        @Override
	public UserDto getUserById(int id) throws Exception{
		Optional<User> user = userRepository.findById(id);
                if(user.isEmpty()){
                    throw new Exception("User Not Found");
                }
                UserDto userDto = userMapper.userToUserDto(user.get());
		return userDto;
	}
        
	@Override
	public UserDto updateUser(int id, User user) throws Exception {
		UserDto user1 = null;
                try{
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user = userRepository.save(user);
                    user1 = userMapper.userToUserDto(user);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    throw new Exception("User Updation unsuccessfull");
                }

		return user1;
	}

	@Override
	public void deleteUser(int id) throws Exception {
            try{
                userRepository.removeUserByUserid(id);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                throw new Exception("User deletion unsuccessfull");
            }
	}
        
        @Override
        public boolean userAlreadyExist(String email) {
            User user = userRepository.findByEmail(email);
            if(user!=null){
                return true;
            }
            return false;
        }
        
        @Override
        public boolean userAlreadyExist(int id) {
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                return true;
            }
            return false;
        }

        @Override
	public List<UserDto> getAllUsers() {
		return userMapper.userToUserDto(userRepository.findAll());
	}
}
