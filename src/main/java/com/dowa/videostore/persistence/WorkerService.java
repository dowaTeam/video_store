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
            conn.close();
        }
        return allStuds;
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
            conn.close();
        }
        return totalrows;
    }
}
