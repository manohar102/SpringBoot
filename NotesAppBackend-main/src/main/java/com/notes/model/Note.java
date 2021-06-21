/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="note")
 @NoArgsConstructor
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     @Getter @Setter private int note_id;

    @Getter @Setter private String title,content,created_date,remainder_date;
 boolean remainder;
    
    @ManyToOne
    @JoinColumn(name = "uid")
    @Setter
    private User user;
    


    public Note(int note_id, String title, String content, 
    		String created_date, String remainder_date, boolean remainder) {
        this.note_id = note_id;
        this.title = title;
        this.content = content;
        this.created_date = created_date;
        this.remainder_date = remainder_date;
        this.remainder = remainder;
        //this.user = user;
    }
    public int getNote_id()
    {
        return this.note_id;
    }
	public void setUser(User userData) {
		this.user = userData;
		
	}

    
}