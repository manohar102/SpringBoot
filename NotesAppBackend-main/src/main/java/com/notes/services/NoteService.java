/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.services;

import com.notes.model.*;
import java.util.Set;
import org.springframework.stereotype.Service;

public interface NoteService {
   public void delete(int uid,int nid);
   public Set<Note> getNotesById(int uid);
   public void saveOrUpdate(Note note,int uid);
    
}
