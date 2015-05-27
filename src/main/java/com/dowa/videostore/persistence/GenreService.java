package com.dowa.videostore.persistence;

import com.dowa.videostore.connection.ConnectionFactory;
import com.dowa.videostore.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres on 27/05/2015.
 */
public class GenreService {
    public void persistGenre(Genre genre) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO genres (name) VALUES (?)");
            pstmt.setString(1,genre.getName());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public void updateGenre(Genre genre) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("UPDATE genres SET name=? WHERE id = ?");

            pstmt.setString(1,genre.getName());
            pstmt.setInt(2,genre.getId());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public void deleteGenre(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("DELETE FROM genres WHERE id =?");
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

    public List<Genre> findAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Genre genre = new Genre();
        List <Genre> allGenres= new ArrayList<Genre>();
        pstmt = conn.prepareStatement("SELECT * FROM genres");
        try {
            rs = pstmt.executeQuery();
            while(rs.next()) {
                genre.setId(rs.getInt(1));
                genre.setName(rs.getString(2));
                allGenres.add(genre);
                genre = new Genre();
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return allGenres;
    }

    public Genre find(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Genre genre = new Genre();
        pstmt = conn.prepareStatement("SELECT * FROM genres WHERE id = ?");
        try {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                genre.setId(rs.getInt(1));
                genre.setName(rs.getString(2));
            }
        }catch (SQLException e){
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return genre;
    }

    public int count() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalrows = 0;
        pstmt = conn.prepareStatement("SELECT COUNT(*) FROM genres");
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
