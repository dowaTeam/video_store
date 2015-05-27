package com.dowa.videostore.persistence;

import com.dowa.videostore.connection.ConnectionFactory;
import com.dowa.videostore.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres on 27/05/2015.
 */
public class MovieService {
    public void persistMovie(Movie movie) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            movie.setImagePath(movie.getFile().getFileName());
            pstmt = conn.prepareStatement("INSERT INTO movies (name, id_genre, synopsis, length, registration_date, disp_dvd, " +
                    "disp_bray, image_path)" +
                    " VALUES (?,?,?,?,?,?,?,?)");
            pstmt.setString(1,movie.getName());
            pstmt.setInt(2, movie.getIdGenre());
            pstmt.setString(3,movie.getSynopsis());
            pstmt.setInt(4, movie.getLength());
            pstmt.setDate(5,  new java.sql.Date(movie.getRegistrationDate().getTime()));
            pstmt.setInt(6, movie.getDispDvd());
            pstmt.setInt(7, movie.getDispBray());
            pstmt.setString(8,movie.getImagePath());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public void updateMovie(Movie movie) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("UPDATE movies SET name=?, id_genre=?," +
            "synopsis=?, length=?, registration_date=?, disp_dvd=?, disp_bray=? ,image_path=? WHERE id = ?");

            pstmt.setString(1,movie.getName());
            pstmt.setInt(2,movie.getIdGenre());
            pstmt.setString(3,movie.getSynopsis());
            pstmt.setInt(4, movie.getLength());
            pstmt.setDate(5,  new java.sql.Date(movie.getRegistrationDate().getTime()));
            pstmt.setInt(6, movie.getDispDvd());
            pstmt.setInt(7, movie.getDispBray());
            pstmt.setString(8,movie.getImagePath());
            pstmt.setInt(9,movie.getId());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public void deleteMovie(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("DELETE FROM movies WHERE id =?");
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

    public List<Movie> findAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Movie movie = new Movie();
        List <Movie> allMovies= new ArrayList<Movie>();
        pstmt = conn.prepareStatement("SELECT * FROM movies");
        try {
            rs = pstmt.executeQuery();
            while(rs.next()) {
                movie.setId(rs.getInt(1));
                movie.setName(rs.getString(2));
                movie.setIdGenre(rs.getInt(3));
                movie.setSynopsis(rs.getString(4));
                movie.setLength(rs.getInt(5));
                movie.setRegistrationDate(rs.getDate(6));
                movie.setDispDvd(rs.getInt(7));
                movie.setDispBray(rs.getInt(8));
                movie.setImagePath(rs.getString(9));
                movie.setGenreName(getGenreName(movie.getIdGenre()));
                System.out.println(movie.getName());
                allMovies.add(movie);
                movie = new Movie();
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return allMovies;
    }

    public Movie find(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Movie movie = new Movie();
        pstmt = conn.prepareStatement("SELECT * FROM movies WHERE id = ?");
        try {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                movie.setId(rs.getInt(1));
                movie.setName(rs.getString(2));
                movie.setIdGenre(rs.getInt(3));
                movie.setSynopsis(rs.getString(4));
                movie.setLength(rs.getInt(5));
                movie.setRegistrationDate(rs.getDate(6));
                movie.setDispDvd(rs.getInt(7));
                movie.setDispBray(rs.getInt(8));
                movie.setImagePath(rs.getString(9));
                movie.setGenreName(getGenreName(movie.getIdGenre()));
            }
        }catch (SQLException e){
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return movie;
    }

    public int count() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalrows = 0;
        pstmt = conn.prepareStatement("SELECT COUNT(*) FROM movies");
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

    public String getGenreName(int id){
        try {
            String name = new GenreService().find(id).getName();
            return name;
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            return "";
        }
    }
}
