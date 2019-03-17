/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import client.ClientRemote;
import game.model.Client;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ayman
 */
public interface RemoteServer extends Remote {

    // must throw RemoteException
    void addUsername(String username, ClientRemote player) throws RemoteException;

    void login(String userName, String password, ClientRemote player) throws RemoteException;

    void register(String userName, String password, ClientRemote player) throws RemoteException;
      void updateScore(String userName ) throws RemoteException ;

    ArrayList<String> getAllUsers(String username) throws RemoteException;
// to know the location and player

    void playMove(int position, String username) throws RemoteException;

    void request(String player1Username, String player2Username) throws RemoteException;

    void requestStatus(String player1Username, String player2Username, boolean status) throws RemoteException;

    void closeGame(String player2Username) throws RemoteException;

    void closeRoom(String playerName) throws RemoteException;

    void resetGame(String player2Username) throws RemoteException;

}
