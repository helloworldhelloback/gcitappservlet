/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcit.lms.dao;

import com.gcit.lms.entity.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tictoc
 */
public class GenreDAO extends BaseDAO{

    public GenreDAO(Connection conn) {
        super(conn);
    }
    
    public void addGenre(Genre genre){		
        try{
            save("insert into tbl_genre (name) values (?)", new Object[] {genre.getGenreName()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }
	
    public void updateGenre(Genre genre){
        try{
            save("update tbl_genre set name = ? where genre_Id = ?", new Object[] {genre.getGenreName(), genre.getGenreId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public void deleteGenre(Genre genre) {		
        try{
            save("delete from tbl_genre where genre_Id = ?", new Object[] {genre.getGenreId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public List<Genre> readAllGenre() {
        try{
            return (List<Genre>) readAll("select * from tbl_genre", null);}
        catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }
        return null;
    }


    public Integer getCount(){		
        try{
            return getCount("select count(*) from tbl_genre");
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }

    @Override
    public List<Genre> extractData(ResultSet rs) {

        try{
            List<Genre> genres = new ArrayList<Genre>();		
            while(rs.next()){
                    Genre a = new Genre();
                    a.setGenreId(rs.getInt("genre_Id"));
                    a.setGenreName(rs.getString("name"));
                    genres.add(a);
            }
            return genres;
        }catch(SQLException se){
        }
        return null;
    }

    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
