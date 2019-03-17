/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import game.dataBaseManager.DataBaseManager;
import game.interfaces.GenericClient;

/**
 *
 * @author ayman
 */
public class ClientDAO implements GenericClient<Client> {

    private PreparedStatement pst;
    private ResultSet results;

    private Connection con;

    @Override
    public int create(Client element) {
        con = DataBaseManager.getConnection();
        int returnVal = 0;
        try {
            pst = con.prepareStatement("insert into players (name,password,score) values (?,?,0)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            pst.setString(1, element.getName());
            pst.setString(2, element.getPassword());
            returnVal = pst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        DataBaseManager.closeConnection();

        return returnVal;
    }

    @Override
    public ArrayList<Client> retreiveAll() {
        con = DataBaseManager.getConnection();
        boolean exist = false;

        ArrayList<Client> clientList = new ArrayList<Client>();
        try {
            pst = con.prepareStatement("select id, name ,score from players ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            while (rs.next()) {
                clientList.add(new Client(rs.getInt("id"), rs.getString("name"), rs.getInt("score")));
                exist = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        DataBaseManager.closeConnection();

        return clientList;
    }

    @Override
    public boolean login(Client element) {
        con = DataBaseManager.getConnection();
        boolean exist = false;
        try {
            pst = con.prepareStatement("select id, name ,score from players where name =? and password =?");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            pst.setString(1, element.getName());
            pst.setString(2, element.getPassword());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            while (rs.next()) {
                // clientList.add(new Client(rs.getInt("id"), rs.getString("name"), rs.getInt("score")));
                exist = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        DataBaseManager.closeConnection();

        return exist;
    }

    @Override
    public int update(String name) {
        con = DataBaseManager.getConnection();
        int returnVal = 0;
        try {
            pst = con.prepareStatement("update players set score =score+1 where  name=?");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            pst.setString(1,name);
            returnVal = pst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        DataBaseManager.closeConnection();

        return returnVal;
    }

    @Override
    public Client retrieve(String name) {
        Client player = null;
        con = DataBaseManager.getConnection();
        try {
            pst = con.prepareStatement("select id, name ,score from players where name =? ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            pst.setString(1, name);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            while (rs.next()) {
                player = new Client(rs.getInt("id"), rs.getString("name"), rs.getInt("score"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        DataBaseManager.closeConnection();

        return player;
    }

}
