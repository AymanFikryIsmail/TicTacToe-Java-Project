/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import client.ClientRemote;
import service.RemoteServer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ayman
 */
public class RemoteServerImpl extends UnicastRemoteObject implements RemoteServer {

    private List<String> users;
    private HashMap<String, ClientRemote> players;
    private HashMap<String, String> playersUsernameInGame;

//// laaaaaazm el constructor 
    public RemoteServerImpl() throws RemoteException {
        users = new ArrayList<>();
        players = new HashMap<>();
        playersUsernameInGame = new HashMap<>();
    }

    /**
     * to Add user To all players
     *
     * @param username
     * @param player
     *
     */
    @Override
    public synchronized void addUsername(String username, ClientRemote player) throws RemoteException {
        for (String user : users) {
            players.get(user).addPlayerToMenu(username);
        }
        users.add(username);
        players.put(username, player);

    }

    /**
     * to Add user To all players
     *
     * @param username
     * @return allUsers      *
     */
    @Override
    public synchronized ArrayList<String> getAllUsers(String username) throws RemoteException {
        ArrayList<String> allUsers = new ArrayList<>();
        for (String user : users) {
            if (!user.equals(username)) {
                allUsers.add(user);
            }
        }
        return allUsers;
    }

    /**
     * Sends a challenge to a specific player
     *
     * @param player1Username
     * @param player2Username
     * @throws RemoteException
     */
    @Override
    public void request(String playerOneName, String playerTwoName) throws RemoteException {
        players.get(playerTwoName).checkRequest(playerOneName);
    }

    /**
     * to put the token at specific position
     *
     * @param position
     * @param username
     * @throws RemoteException
     */
    @Override
    public synchronized void playMove(int position, String username) throws RemoteException {
        players.get(username).play(position);
        System.out.print(username + " , loc :" + position);
    }

    /**
     * to check the request is accepted or denied
     *
     * @param player1Username
     * @param player2Username
     * @throws RemoteException
     */
    @Override
    public synchronized void requestStatus(String player1Username, String player2Username, boolean status) throws RemoteException {
        if (status) {
            ClientRemote player1 = players.get(player1Username);
            ClientRemote player2 = players.get(player2Username);

            player1.startGameWindow(player1Username, player2Username, true);
            player2.startGameWindow(player2Username, player1Username, false);

            for (String user : users) {
                if (!user.equals(player1Username)) {
                    players.get(user).removePlayerFromMenu(player1Username);
                }
                if (!user.equals(player2Username)) {
                    players.get(user).removePlayerFromMenu(player2Username);
                }
            }
            users.remove(player1Username);
            users.remove(player2Username);

        } else {
            players.get(player1Username).requestDenied();
        }
    }

    /**
     * to close the game screen at two sides
     *
     * @param player2Username
     * @throws RemoteException
     */
    @Override
    public synchronized void closeGame(String player2Username) throws RemoteException {

        ClientRemote player = players.get(player2Username);
        player.opponentClosed();

    }

    /**
     * to close the game screen at two sides
     *
     * @param player2Username
     * @throws RemoteException
     */
    @Override
    public void closeRoom(String playerName) throws RemoteException {
        for (String user : users) {
            if (!user.equals(playerName)) {
                players.get(user).removePlayerFromMenu(playerName);
            }
        }

    }

    /**
     * to reset the game at two sides
     *
     * @param player2Username
     * @throws RemoteException
     */
    @Override
    public void resetGame(String player2Username) throws RemoteException {
        ClientRemote player = players.get(player2Username);
        player.resetGame();

    }

}
