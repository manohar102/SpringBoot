package com.notes.controllers;

import com.google.gson.JsonObject;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.repos.UserRepository;
import com.notes.services.ServiceClass;
import com.notes.tables.Note;
import com.notes.tables.User;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class ClassController {

	@Autowired
	UserRepository repo;
	@Autowired
	ServiceClass userService;

		@CrossOrigin(origins = "http://localhost:4200")
        @PostMapping("/login")
        public ResponseEntity login(@RequestBody User user)
        {
           
            User userDetails = userService.getUserbyEmail(user.getEmail());
            if(userDetails==null)
            {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            if(userDetails.getPassword().equals(user.getPassword()))
            {
                try{
                    Map<String,Object> map = new HashMap<>();
                    map.put("uid",userDetails.getUid());
                    map.put("first_name",userDetails.getFirst_name());
                    map.put("last_name",userDetails.getLast_name());
                    map.put("email",userDetails.getEmail());
                    return new ResponseEntity(map,HttpStatus.OK);
                }
                catch(Exception e){}
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                
            }
           return new ResponseEntity(HttpStatus.FORBIDDEN);
            
            
        }
        
        @CrossOrigin(origins = "http://localhost:4200")
        @PostMapping("/register")
        public ResponseEntity register(@RequestBody User u)
        {
            User tempUser = userService.getUserbyEmail(u.getEmail());
            if(tempUser!=null)
            {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
                
            }
            try
            {
                userService.saveUser(u);
                return new ResponseEntity(HttpStatus.OK);
            }
            catch(Exception e){}
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
	
	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/notes/getNotes/{uid}")  
	private ResponseEntity getNotes(@PathVariable("uid") int uid)   
	{  
            try{
                Map<String,Object> map = new HashMap<>();
                map.put("notes", userService.getNotesById(uid));
		return new ResponseEntity(map,HttpStatus.OK);
            }
            catch(Exception e){}
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/notes/{uid}/save")  
	private ResponseEntity saveNotes(@RequestBody Note note,@PathVariable("uid") int uid) throws Exception   { 
              try
              {
                    userService.saveOrUpdate(note,uid);  
                    Map<String,Integer> map = new HashMap<>();
                    map.put("note_id",note.getNote_id());
                    return new ResponseEntity(map,HttpStatus.OK);
              }
              catch(Exception e){}
              return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}  
//	@PostMapping("/user/save")  
//	private ResponseEntity saveUser(@RequestBody User user)   {  
//		try
//                {
//                userService.saveUser(user);
//                Map<String,Integer> map = new HashMap<String,Integer>();
//                map.put("user_id",user.getUid());
//		return new ResponseEntity(map,HttpStatus.OK);
//                }
//                catch(Exception e){}
//                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
//	}
 
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/notes/delete/{uid}/{nid}")  
	private ResponseEntity deleteNotes(@PathVariable("uid") int uid,@PathVariable("nid") int nid)   
	{  
		try
                {
		userService.delete(uid,nid);
                return new ResponseEntity(HttpStatus.OK);
                }
                catch(Exception e){}
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                
	}  
	
	
	@RequestMapping(value = "/getAllUsers")
	public ResponseEntity loginReturn() {
//		LoginJson loginJson = new LoginJson();
//		loginJson.setMessage("Hello Deepak");
//		loginJson.setSuccess(true);
//		loginJson.setData(this.repo.findAll());
		//System.out.println(new Gson().toJson(loginJson).toString());
		Map<String,Object> map = new HashMap<>();
                map.put("data",this.repo.findAll());
		return new ResponseEntity(map,HttpStatus.OK);
	}
}
