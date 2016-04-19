/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcit.lms.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.*;
import java.sql.Connection;

/**
 *
 * @author tictoc
 */
public class BookDAO extends BaseDAO{

    public BookDAO(Connection conn) {
        super(conn);
    }
    
    public void addBook(Book book){		
        try{
            save("insert into tbl_book (title, pubId) values (?,?)", new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
            System.out.println("insert book:\n"+se);
        }
    }
	public Integer addBookWithID(Book book){
		try{
           return saveWithID("insert into tbl_book (title, pubId) values (?,?)", new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
		} catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }
		return null;
	}
	
    public void updateBook(Book book){
        try{
            save("update tbl_book set title = ? , pubId =? where bookId = ?", new Object[] {book.getTitle(), book.getPublisher().getPublisherId(), book.getBookId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public void deleteBook(Integer bookId) {		 
        try{
            save("delete from tbl_book where bookId = ?", new Object[] {bookId});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public List<Book> readAllBooks() {
        try{
            return (List<Book>) readAll("select * from tbl_book", null);}
        catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }
        return null;
    }

    public List<Book> readBooksByName(String title){

        try{
            return (List<Book>) readAll("select * from tbl_book where title like ?", new Object[] {title});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }
    public Book readBookFromResultSet(ResultSet rs) throws SQLException{
    	Book b = new Book();
        b.setBookId(rs.getInt("bookId"));
        b.setTitle(rs.getString("title"));
        Publisher p = new Publisher();
        p.setPublisherId(rs.getInt("pubId"));
        b.setPublisher(p);
        return b;
    }

    public Integer getCount(){		
        try{
            return getCount("select count(*) from tbl_book");
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }

    @Override
    public List<Book> extractData(ResultSet rs) {
        List<Book> books = new ArrayList<Book>();
        try{
            AuthorDAO adao = new AuthorDAO(getConnection());		
            while(rs.next()){
                Book b = new Book();
                b.setBookId(rs.getInt("bookId"));
                b.setTitle(rs.getString("title"));
                Publisher p = new Publisher();
                p.setPublisherId(rs.getInt("pubId"));
                b.setPublisher(p);
                b.setAuthors((List<Author>) adao.readFirstLevel("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[] {b.getBookId()}));			
                books.add(b);
            }
            return books;
        }catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }catch(SQLException se){
        }
        return books;
    }
    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) {
        List<Book> books = new ArrayList<Book>();
        try{
            while(rs.next()){
                Book b = new Book();
                b.setBookId(rs.getInt("bookId"));
                b.setTitle(rs.getString("title"));
                Publisher p = new Publisher();
                p.setPublisherId(rs.getInt("pubId"));
                b.setPublisher(p);
                books.add(b);
            }
        }catch(SQLException se){			
        }
        return books;
    }

    
}
