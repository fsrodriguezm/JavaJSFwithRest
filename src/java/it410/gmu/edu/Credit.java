/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it410.gmu.edu;

/**
 *
 * @author gmuclass
 */
public class Credit {
    private String description;
    private String code;

    public Credit() {
    }

    public Credit(String description, String code) {
        this.description = description;
        this.code = code;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
}
