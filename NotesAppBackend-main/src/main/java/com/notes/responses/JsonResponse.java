package com.notes.responses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.notes.tables.User;


public class JsonResponse {


	
	Boolean success;
        String message;
	List data;

    public JsonResponse(Boolean success, String message, List data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
        
 
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public ArrayList<User> getData() {
		return (ArrayList<User>) data;
	}
	public void setData(Iterable iterable) {
		this.data = (List) iterable;
	}
	
	
}
