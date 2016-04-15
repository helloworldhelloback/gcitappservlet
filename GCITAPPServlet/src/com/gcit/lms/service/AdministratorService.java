/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcit.lms.service;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tictoc
 */
public class AdministratorService {
    public void createAuthor(Author author) throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
            AuthorDAO adao = new AuthorDAO(conn);
            adao.addAuthor(author);
            conn.commit();
        }catch (Exception e){
                conn.rollback();
        }finally{
                conn.close();
        }		
    }
	
    public Integer createAuthorWithID(Author author)throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        Integer authorId = null;
        try{
                AuthorDAO adao = new AuthorDAO(conn);
                authorId = adao.addAuthorWithID(author);
                conn.commit();
        }catch (Exception e){
                conn.rollback();
        }finally{
                conn.close();
        }
        return authorId;
    }
	
	public List<Author> getAllAuthors()throws SQLException, ClassNotFoundException{
            ConnectionUtil c = new ConnectionUtil();
            Connection conn = c.getConnection();
            try{
                    AuthorDAO adao = new AuthorDAO(conn);
                    return adao.readAllAuthors();
            }catch (Exception e){
                    e.printStackTrace();
                    //conn.rollback();
            }finally{
                    conn.close();
            }
            return null;
	}
	
	public List<Book> getAllBooks()throws SQLException, ClassNotFoundException{
            ConnectionUtil c = new ConnectionUtil();
            Connection conn = c.getConnection();
            try{
                BookDAO bdao = new BookDAO(conn);
                return bdao.readAllBooks();
            }catch (Exception e){
                    e.printStackTrace();
                    //conn.rollback();
            }finally{
                    conn.close();
            }
            return null;
	}
}
