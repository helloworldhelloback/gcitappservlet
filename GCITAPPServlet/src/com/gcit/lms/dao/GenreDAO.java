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
            save("insert into tbl_genre (genre_name) values (?)", new Object[] {genre.getGenreName()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }
	
    public void updateGenre(Genre genre){
        try{
            save("update tbl_genre set genre_name = ? where genre_id = ?", new Object[] {genre.getGenreName(), genre.getGenreId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public void deleteGenre(Integer genreId) {		
        try{
            save("delete from tbl_genre where genre_id = ?", new Object[] {genreId});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public List<Genre> readAllGenres() {
        try{
        	System.out.println("inside genre dao");
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
    public List<Genre> readGenreByName(String genreName){

        try{
            return (List<Genre>) readAll("select * from tbl_genre where genre_name like ?", new Object[] {genreName});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }
    public Genre readGenresByID(Integer genreId) throws ClassNotFoundException, SQLException{
		List<Genre> genres = (List<Genre>) readAll("select * from tbl_genre where genre_id=?", new Object[] {genreId});
		if(genres!=null && genres.size() >0){
			return genres.get(0);
		}
		return null;
	}
    @Override
    public List<Genre> extractData(ResultSet rs) {

        try{
            List<Genre> genres = new ArrayList<Genre>();		
            while(rs.next()){
                    Genre a = new Genre();
                    a.setGenreId(rs.getInt("genre_id"));
                    a.setGenreName(rs.getString("genre_name"));
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
