/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.interfaces;

import java.sql.Connection;
import java.util.ArrayList;
import game.model.Client;

/**
 *
 * @author ayman
 */
public interface GenericClient<T> {

    public int create(T obj);

    public ArrayList<T> retreiveAll();
    public T retrieve(String name);

    public int update(String obj);

    public boolean login(T obj);

}
