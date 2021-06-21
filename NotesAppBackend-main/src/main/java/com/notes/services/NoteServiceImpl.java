/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.services;

import com.notes.repository.NoteRepository;
import com.notes.repository.UserRepository;
import com.notes.model.Note;
import com.notes.model.User;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public void delete(int uid, int nid) {
        		User user = userRepository.findById(uid).get();
		Set<Note> notes = user.getNotes();
		for(Note n :notes) {
			
			if(n.getNote_id()==nid) {
				noteRepository.deleteById(nid);
                               
			}
		}
		             
        
    }

    @Override
    public Set<Note> getNotesById(int uid) {
        	User user = userRepository.findById(uid).get();
		Set<Note> notes = user.getNotes();
		return notes;
        
    }

    @Override
    public void saveOrUpdate(Note note, int uid) {
        	Optional<User> userOptional = userRepository.findById(uid);
		if(!userOptional.isPresent())  
		{  
			return;  
		}  
		User userData=userOptional.get(); 
		note.setUser(userData);
		noteRepository.save(note); 
    }
    
    
}
