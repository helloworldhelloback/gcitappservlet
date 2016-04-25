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

    public void addAuthor(Author author) throws ClassNotFoundException, SQLException{	        
        save("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});   
    }
	
	public Integer addAuthorWithID(Author author)throws ClassNotFoundException, SQLException{
        return saveWithID("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	
        //return null;
	}

    public void updateAuthor(Author author)throws ClassNotFoundException, SQLException{		      
        save("update tbl_author set authorName = ? where authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});   
    }

    public void deleteAuthor(Integer authorId) throws ClassNotFoundException, SQLException{	
    	save("delete from tbl_author where authorId = ?",new Object[]{authorId});                     
    }
    

    public List<Author> readAllAuthors(int pageNo) throws ClassNotFoundException, SQLException{
    	setPageNo(pageNo);
        return (List<Author>) readAll("select * from tbl_author", null);                        
       // return null;
    }

    public List<Author> readAuthorsByName(String name) throws ClassNotFoundException, SQLException{

        return (List<Author>) readAll("select * from tbl_author where authorName like ?", new Object[] {name});
      
        //return null;
    }

    public Integer getCount() throws ClassNotFoundException, SQLException{		

        return getCount("select count(*) from tbl_author");
       
       // return null;
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
    public List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {		
        List<Author> authors = new ArrayList<Author>();	        
        BookDAO bdao = new BookDAO(getConnection());
        while(rs.next()){
            Author a = new Author();
            a.setAuthorId(rs.getInt("authorId"));
            a.setAuthorName(rs.getString("authorName"));
            a.setBooks((List<Book>) bdao.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)", new Object[] {a.getAuthorId()}));							
            authors.add(a);
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
