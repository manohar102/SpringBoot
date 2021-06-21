/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
    private int uid;
    private String first_name,last_name,email;
}
