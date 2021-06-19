package com.notes.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notes.repos.NoteRepository;
import com.notes.repos.UserRepository;
import com.notes.tables.Note;
import com.notes.tables.User;
import java.util.List;

@Service
public class ServiceClass {

	@Autowired
	UserRepository userRepo;
	@Autowired
	NoteRepository noteRepo;
	
        	
        public User getUserbyEmail(String email)
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
	public void delete(int uid, int nid) {
		User user = userRepo.findById(uid).get();
		Set<Note> notes = user.getNotes();
		for(Note n :notes) {
			
			if(n.getNote_id()==nid) {
				noteRepo.deleteById(nid);
			}
		}
		
	}
	public Set<Note> getNotesById(int uid) {
	
		User user = userRepo.findById(uid).get();
		Set<Note> notes = user.getNotes();
//		
//		for(Note n :notes) {
//			System.out.println(n.getContent()+" "+n.getCreated_date()+" "+n.getNote_id());
//		}
		return notes;
	
	}
	public void saveOrUpdate(Note note, int uid) throws Exception {
		Optional<User> userOptional = userRepo.findById(uid);
		if(!userOptional.isPresent())  
		{  
			throw new Exception("User not found");  
		}  
		User userData=userOptional.get(); 
		note.setUser(userData);
		noteRepo.save(note);  
		
	}
	public void saveUser(User user) {
		userRepo.save(user);	
	}


}
