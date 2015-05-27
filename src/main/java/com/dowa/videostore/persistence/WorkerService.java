package com.dowa.videostore.persistence;

/**
 * Created by Andres on 26/05/2015.
 */

import com.dowa.videostore.connection.ConnectionFactory;
import com.dowa.videostore.model.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerService {

    public void persistWorker(Worker worker) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO workers (name, f_lastname,s_lastname,is_admin,address ,phone, email, password)" +
                    " VALUES (?,?,?,?,?,?,?,?)");
            pstmt.setString(1,worker.getName());
            pstmt.setString(2,worker.getFLastname());
            pstmt.setString(3,worker.getSLastname());
            pstmt.setBoolean(4,worker.getIsAdmin());
            pstmt.setString(5,worker.getAddress());
            pstmt.setString(6,worker.getPhone());
            pstmt.setString(7,worker.getEmail());
            pstmt.setString(8, worker.getPassword());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public void updateWorker(Worker worker) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("UPDATE workers SET name=?, f_lastname=?," +
                    "s_lastname=?, is_admin=? ,address=? ,phone=? , email=? , password=? WHERE id = ?");

            pstmt.setString(1,worker.getName());
            pstmt.setString(2,worker.getFLastname());
            pstmt.setString(3,worker.getSLastname());
            pstmt.setBoolean(4,worker.getIsAdmin());
            pstmt.setString(5,worker.getAddress());
            pstmt.setString(6,worker.getPhone());
            pstmt.setString(7,worker.getEmail());
            pstmt.setString(8, worker.getPassword());
            pstmt.setInt(9,worker.getId());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public void deleteWorker(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("DELETE FROM workers WHERE id =?");
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

    public List<Worker> findAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Worker worker = new Worker();
        List <Worker> allStuds= new ArrayList<Worker>();
        pstmt = conn.prepareStatement("SELECT * FROM workers");
        try {
            rs = pstmt.executeQuery();
            while(rs.next()) {
                worker.setId(rs.getInt(1));
                worker.setName(rs.getString(2));
                worker.setFLastname(rs.getString(3));
                worker.setSLastname(rs.getString(4));
                worker.setIsAdmin(rs.getBoolean(5));
                worker.setAddress(rs.getString(6));
                worker.setPhone(rs.getString(7));
                worker.setEmail(rs.getString(8));
                allStuds.add(worker);
                worker = new Worker();
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return allStuds;
    }

    public Worker find(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Worker worker = new Worker();
        pstmt = conn.prepareStatement("SELECT * FROM workers WHERE id = ?");
        try {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                worker.setId(rs.getInt(1));
                worker.setName(rs.getString(2));
                worker.setFLastname(rs.getString(3));
                worker.setSLastname(rs.getString(4));
                worker.setIsAdmin(rs.getBoolean(5));
                worker.setAddress(rs.getString(6));
                worker.setPhone(rs.getString(7));
                worker.setEmail(rs.getString(8));
            }
        }catch (SQLException e){
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return worker;
    }

    public int count() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalrows = 0;
        pstmt = conn.prepareStatement("SELECT COUNT(*) FROM workers");
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
