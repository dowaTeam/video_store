package com.dowa.videostore.persistence;

import com.dowa.videostore.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.dowa.videostore.model.Client;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Andres on 26/05/2015.
 */
public class ClientService {

    public void persistClient(Client client) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO clients (name, f_lastname,s_lastname, registration_date, active, " +
                    "debts, address, phone, email)" +
                    " VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1,client.getName());
            pstmt.setString(2,client.getFLastname());
            pstmt.setString(3,client.getSLastname());
            pstmt.setDate(4,  new java.sql.Date(client.getRegistrationDate().getTime()));
            pstmt.setBoolean(5, client.getActive());
            pstmt.setFloat(6,client.getDebts());
            pstmt.setString(7, client.getAddress());
            pstmt.setString(8,client.getPhone());
            pstmt.setString(9,client.getEmail());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public void updateClient(Client client) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("UPDATE clients SET name=?, f_lastname=?," +
                    "s_lastname=?, registration_date=?, active=?, debts=? ,address=? ,phone=? , email=?  WHERE id = ?");

            pstmt.setString(1,client.getName());
            pstmt.setString(2,client.getFLastname());
            pstmt.setString(3,client.getSLastname());
            pstmt.setDate(4, new java.sql.Date(client.getRegistrationDate().getTime()));
            pstmt.setBoolean(5, client.getActive());
            pstmt.setFloat(6, client.getDebts());
            pstmt.setString(7, client.getAddress());
            pstmt.setString(8,client.getPhone());
            pstmt.setString(9, client.getEmail());
            pstmt.setInt(10, client.getId());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public void deleteClient(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("DELETE FROM clients WHERE id =?");
            pstmt.setInt(1,id);
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public List<Client> findAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Client client = new Client();
        List <Client> allClients= new ArrayList<Client>();
        pstmt = conn.prepareStatement("SELECT * FROM clients");
        try {
            rs = pstmt.executeQuery();
            while(rs.next()) {
                client.setId(rs.getInt(1));
                client.setName(rs.getString(2));
                client.setFLastname(rs.getString(3));
                client.setSLastname(rs.getString(4));
                client.setRegistrationDate(rs.getDate(5));
                client.setActive(rs.getBoolean(6));
                client.setDebts(rs.getFloat(7));
                client.setAddress(rs.getString(8));
                client.setPhone(rs.getString(9));
                client.setEmail(rs.getString(10));
                allClients.add(client);
                client = new Client();
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return allClients;
    }

    public Client find(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Client client = new Client();
        pstmt = conn.prepareStatement("SELECT * FROM clients WHERE id = ?");
        try {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                client.setId(rs.getInt(1));
                client.setName(rs.getString(2));
                client.setFLastname(rs.getString(3));
                client.setSLastname(rs.getString(4));
                client.setRegistrationDate(rs.getDate(5));
                client.setActive(rs.getBoolean(6));
                client.setDebts(rs.getFloat(7));
                client.setAddress(rs.getString(8));
                client.setPhone(rs.getString(9));
                client.setEmail(rs.getString(10));
            }
        }catch (SQLException e){
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return client;
    }

    public int count() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalrows = 0;
        pstmt = conn.prepareStatement("SELECT COUNT(*) FROM clients");
        try {
            rs = pstmt.executeQuery();
            if(rs.next()) {
                totalrows = rs.getInt(1);
            }
        }catch(SQLException e){
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return totalrows;
    }

}
