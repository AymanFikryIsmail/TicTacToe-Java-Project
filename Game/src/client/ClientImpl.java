/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import game.LoginFXMLController;
import game.OnlineGameFXMLController;
import game.OnlineRoomFXMLController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ayman
 */
public class ClientImpl extends UnicastRemoteObject implements ClientRemote {

    OnlineRoomFXMLController onlineRoomFXMLController;
    OnlineGameFXMLController onlineGameFXMLController;
    LoginFXMLController loginFXMLController;

    public ClientImpl(OnlineRoomFXMLController onlineRoomFXMLController) throws RemoteException {
        this.onlineRoomFXMLController = onlineRoomFXMLController;
    }

    public ClientImpl(LoginFXMLController loginFXMLController) throws RemoteException {
        this.loginFXMLController = loginFXMLController;
    }

    public void initGame(OnlineGameFXMLController onlineGameFXMLController) {
        this.onlineGameFXMLController = onlineGameFXMLController;

    }

    /**
     * put token in opnent
     *
     * @param loc
     * @throws RemoteException
     */
    @Override
    public void play(int loc) throws RemoteException {
        System.out.print("before loc :" + loc);
        //  onlineGameFXMLController.changeTurn(true);
        onlineGameFXMLController.putTocken(loc);
        System.out.print("after loc :" + loc);

    }

    /**
     * remove name from listview
     *
     * @param user
     *      * @throws RemoteException
     *
     */
    @Override
    public void removePlayerFromMenu(String user) throws RemoteException {
        onlineRoomFXMLController.removeUserFromMenu(user);
    }

    /**
     * remove name from listview
     *
     * @param user
     * @throws RemoteException
     *
     */
    @Override
    public void addPlayerToMenu(String user) throws RemoteException {
        onlineRoomFXMLController.addUserToMenu(user);
    }

    /**
     * check request
     *
     * @param user
     *      * @throws RemoteException
     *
     */
    @Override
    public void checkRequest(String user) throws RemoteException {
        onlineRoomFXMLController.checkRequest(user);
    }

    /**
     * set turn
     *
     * @param turn
     * @throws RemoteException
     *
     */
    @Override
    public void setTurn(boolean turn) throws RemoteException {
        onlineGameFXMLController.changeTurn(turn);
    }

    /**
     * set turn
     *
     * @param player1Username
     * @param player2Username
     * @param turn
     *                      * @throws RemoteException
     *
     */
    @Override
    public void startGameWindow(String player1Username, String player2Username, boolean turn) throws RemoteException {
        onlineRoomFXMLController.openGame(player1Username, player2Username, turn);
    }

    /**
     * set returnUsername
     *
     * @throws RemoteException
     * @return String
     *
     */
    @Override
    public String returnUsername() throws RemoteException {
        return onlineRoomFXMLController.getCurrentUserName();
    }

    /**
     * when close the game
     *
     * @throws RemoteException
     *
     */
    @Override
    public void opponentClosed() throws RemoteException {
        onlineGameFXMLController.terminate();
    }

    /**
     * when deny the request
     *
     * @throws RemoteException
     *
     */
    @Override
    public void requestDenied() throws RemoteException {
        onlineRoomFXMLController.requestDenied();

    }

    /**
     * when reset the game
     *
     * @throws RemoteException
     *
     */
    @Override
    public void resetGame() throws RemoteException {
        onlineGameFXMLController.restart();
    }

    /**
     * return the login status
     *
     * @param isLogged
     * @throws RemoteException
     */
    @Override
    public void returnLoginState(boolean isLogged) throws RemoteException {

        loginFXMLController.isLogin(isLogged);

    }

    /**
     * return the register status
     *
     * @param isRegistered
     * @throws RemoteException
     */
    @Override
    public void returnRegisterState(int isRegistered) throws RemoteException {

        loginFXMLController.isRegistered(isRegistered);

    }
     /**
     * return the update the score  status
     *
     * @param isUpdated
     * @throws RemoteException
     */
    @Override
    public void returnUpdateState(int isUpdated) throws RemoteException {

    }
}
