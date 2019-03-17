/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ayman
 */
public interface ClientRemote  extends Remote {
     void play(int loc) throws RemoteException;
    
    void removePlayerFromMenu(String user) throws RemoteException;
    
    void addPlayerToMenu(String user) throws RemoteException;
    
    void checkRequest(String username) throws RemoteException;
    
    void setTurn(boolean turn) throws RemoteException;
    
    void startGameWindow(String player1Username, String player2Username, boolean trun) throws RemoteException;
    
    String returnUsername() throws RemoteException;
   
    void opponentClosed() throws RemoteException;

     void resetGame() throws RemoteException ;
    
    void requestDenied() throws RemoteException;


}
