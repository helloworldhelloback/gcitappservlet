package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.*;

public class Book_CopiesDAO extends BaseDAO{

	public Book_CopiesDAO(Connection conn){
		super(conn);
	}
	
	public Book_Copies readBookCopyById(Integer bookId, Integer branchId) throws ClassNotFoundException, SQLException{
		List<Book_Copies> bookcopies = (List<Book_Copies>) readAll("SELECT * FROM tbl_book_copies INNER JOIN tbl_book ON tbl_book.bookId = tbl_book_copies.bookId Where tbl_book_copies.bookId=? and tbl_book_copies.branchId=?", new Object[] {bookId, branchId});
		if(bookcopies!=null && bookcopies.size() >0){
			return bookcopies.get(0);
		}
		return null;
	}
	public List<Book_Copies> readBookTitleAndCopiesByBranchID(Integer branchId) throws ClassNotFoundException, SQLException{
		Object obj = new Object();
		Book_Copies copy = new Book_Copies();
		List<Book_Copies> bookcopies = (List<Book_Copies>) readAll("SELECT * FROM tbl_book_copies INNER JOIN tbl_book ON tbl_book.bookId = tbl_book_copies.bookId Where tbl_book_copies.branchId=?", new Object[] {branchId});
		if(bookcopies!=null && bookcopies.size() >0){
			return bookcopies;
		}
		return null;
	}
	public void updateBookCopyNum(Book_Copies bookcopies){
        try{
            save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId=?", new Object[] {bookcopies.getNoOfCopies(), bookcopies.getBookId(), bookcopies.getBranchId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<Book_Copies> extractData(ResultSet rs) {
        try{
            List<Book_Copies> bookcopies = new ArrayList<Book_Copies>();		
            while(rs.next()){
            		Book_Copies bc = new Book_Copies();
            		bc.setBookId(rs.getInt("bookId"));
            		bc.setBranchId(rs.getInt("branchId"));
            		bc.setNoOfCopies(rs.getInt("noOfCopies"));
            		bc.setTitle(rs.getString("title"));
                    bookcopies.add(bc);
            }
            return bookcopies;
        }catch(SQLException se){
        }
        return null;
    }
}
