package com.mano.projects.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="user") @Data @NoArgsConstructor		
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="user_generator")
        @SequenceGenerator(name="user_generator", sequenceName = "user_seq")
	private Integer userid;
	private String email;
	private String password;
	private String firstname;
	private String lastname;

}
