package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.*;


public class AuthorDAO extends BaseDAO{
	
	public AuthorDAO(Connection conn){
		super(conn);
	}

    public void addAuthor(Author author){		
        try{
            save("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
        } catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }
    }
	
	public Integer addAuthorWithID(Author author){
        try{
            return saveWithID("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	} catch(ClassNotFoundException ce){
        }catch(SQLException se){
        }
            return null;
	}

    public void updateAuthor(Author author){		
        try{
            save("update tbl_author set authorName = ? where authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
        } catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }
    }

    public void deleteAuthor(Integer authorId){		
        try{
        	save("delete from tbl_author where authorId = ?",new Object[]{authorId});
        } catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }                
    }
    

    public List<Author> readAllAuthors() {
        try{
            return (List<Author>) readAll("select * from tbl_author", null);
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }

    public List<Author> readAuthorsByName(String name){
        try{
            return (List<Author>) readAll("select * from tbl_author where authorName like ?", new Object[] {name});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }

    public Integer getCount(){		
        try{
            return getCount("select count(*) from tbl_author");
        }catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }
    public Author readAuthorsByID(Integer authorId) throws ClassNotFoundException, SQLException{
		List<Author> authors = (List<Author>) readAll("select * from tbl_author where authorId = ?", new Object[] {authorId});
		if(authors!=null && authors.size() >0){
			return authors.get(0);
		}
		return null;
	}
    public Author readAuthorFromResultSet(ResultSet rs) throws SQLException{
    	Author a = new Author();
        a.setAuthorId(rs.getInt("authorId"));
        a.setAuthorName(rs.getString("authorName"));
        return a;
    }

    @Override
    public List<Author> extractData(ResultSet rs) {		
        List<Author> authors = new ArrayList<Author>();	
        try{
            BookDAO bdao = new BookDAO(getConnection());
            while(rs.next()){
                Author a = new Author();
                a.setAuthorId(rs.getInt("authorId"));
                a.setAuthorName(rs.getString("authorName"));
                a.setBooks((List<Book>) bdao.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)", new Object[] {a.getAuthorId()}));							
                authors.add(a);
            }
            return authors;
        }catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return authors;            
    }
	
    @Override
    public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException {
        List<Author> authors = new ArrayList<Author>();
        while(rs.next()){
            Author a = new Author();
            a.setAuthorId(rs.getInt("authorId"));
            a.setAuthorName(rs.getString("authorName"));
            authors.add(a);
        }
        return authors;
    }

}
