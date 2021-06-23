package com.mano.projects.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")		
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "user_seq")
	private @Getter @Setter Integer userid;
	@Getter @Setter private String email;
	@Getter @Setter private String password;
	@Getter @Setter private String firstname;
	@Getter @Setter private String lastname;

}
