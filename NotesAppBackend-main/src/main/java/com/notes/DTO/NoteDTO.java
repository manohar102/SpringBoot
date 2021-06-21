/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.DTO;

import com.notes.model.User;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

public class NoteDTO {
     @Getter @Setter private int note_id;
     @Getter @Setter private String title,content,created_date,remainder_date;
     @Getter @Setter boolean remainder;
    @Setter
    private User user;
    
}
