package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.entity.*;

public class Book_AuthorsDAO extends BaseDAO {
	public Book_AuthorsDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addRow(Book_Authors bookauthor){		
        try{        	
            save("insert into tbl_book_authors (bookId, authorId) values (?,?)", new Object[] {bookauthor.getBookId(),bookauthor.getAuthorId()});
        } catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }
    }

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
