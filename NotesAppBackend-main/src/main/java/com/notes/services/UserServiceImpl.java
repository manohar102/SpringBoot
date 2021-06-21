package com.notes.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notes.repository.NoteRepository;
import com.notes.repository.UserRepository;
import com.notes.model.Note;
import com.notes.model.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	NoteRepository noteRepo;
        
        @Override
        public User getUserByEmail(String email)
        {
            Iterable<User> users = this.userRepo.findAll();
            for(User u: users)
            {
                if(u.getEmail().equals(email))
                {
                    return u;
                }
            }
            return null;
            
        }


	public void saveUser(User user) {
		userRepo.save(user);	
	}
        
        public void deleteUser(User user)
        {
            userRepo.deleteById(user.getUid());
        }

}
