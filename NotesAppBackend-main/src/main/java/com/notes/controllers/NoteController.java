/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.controllers;

import com.notes.services.NoteService;
import com.notes.model.Note;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    NoteService noteService;
    @PostMapping("/{uid}")
    public ResponseEntity<?> createNote(@PathVariable("uid") int uid,@RequestBody Note note)
    {
        noteService.saveOrUpdate(note,uid);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/{uid}")
    public ResponseEntity<?> getAllNotes(@PathVariable("uid") int uid)
    {
        Set<Note> notes = noteService.getNotesById(uid);
        return new ResponseEntity(notes,HttpStatus.OK);
    }
    @PutMapping("/{uid}")
    public ResponseEntity<?> updateNotes(@PathVariable("uid") int uid,@RequestBody Note note)
    {
        noteService.saveOrUpdate(note, uid);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/{uid}/{nid}")
    public ResponseEntity<?> deleteNotes(@PathVariable("uid") int uid, @PathVariable int nid)
    {
        noteService.delete(uid, nid);
        return new ResponseEntity(HttpStatus.OK);
    }
}
