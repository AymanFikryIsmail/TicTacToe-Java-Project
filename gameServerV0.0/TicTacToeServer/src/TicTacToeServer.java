/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayman
 */
public class TicTacToeServer {
     public static void main(String [] args) {
            try { 
            RemoteServerImpl server = new RemoteServerImpl();
            Registry reg = LocateRegistry.createRegistry(6000);
            reg.rebind("service",server);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
