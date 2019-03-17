/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import java.io.Serializable;

/**
 *
 * @author ayman
 */
public class Client implements Serializable{
    
    private int id;
    private String name;
    private int  score;
    private String password;

    public Client() {
    }

    public Client(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Client(int id, String name, int score ) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getPassword() {
        return password;
    }
    
    
}
