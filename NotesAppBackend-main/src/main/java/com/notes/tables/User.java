/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.tables;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "user_seq")
    private int uid;
    private String first_name,last_name,email,password;
    
    @OneToMany(mappedBy = "user")
    private Set<Note> notes;
    
 
    public User(){
    	
    }

    public User(int uid, String first_name, String last_name, 
    		String email, String password,LinkedHashSet<Note> notes) {
        this.uid = uid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.notes = notes;
    }

    public int getUid() {
        return uid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(LinkedHashSet<Note> notes) {
		this.notes = notes;
	}
    
}
