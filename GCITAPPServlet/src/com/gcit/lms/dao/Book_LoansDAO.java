package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.gcit.lms.entity.Book_Loan;

public class Book_LoansDAO extends BaseDAO {
	
	public Book_LoansDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	//returns books available in a branch for the user to checkout
	public List<Book_Loan> readBookLoanWithBookAndAuthorInfoByBrancId(Integer branchId){
        
		try{
			/*
            return (List<Book_Loan>) readAll("SELECT * FROM tbl_book_copies\n" +
								"INNER JOIN tbl_book ON\n" +
								"	tbl_book_copies.bookId = tbl_book.bookId\n" +
								"    INNER JOIN tbl_book_authors ON \n" +
								"		tbl_book_authors.bookId = tbl_book.bookId\n" +
								"	INNER JOIN tbl_author ON\n" +
								"		tbl_author.authorId = tbl_book_authors.authorId\n" +
								"	INNER JOIN tbl_library_branch ON\n" +
								"		tbl_book_copies.branchId = tbl_library_branch.branchId	\n" +
								"WHERE  tbl_library_branch.branchId= ? \n" +
								"Group BY tbl_book.title", new Object[] {branchId});
								*/
            return (List<Book_Loan>) readAll("SELECT * FROM tbl_book_copies INNER JOIN tbl_book ON tbl_book_copies.bookId = tbl_book.bookId INNER JOIN tbl_book_authors ON tbl_book_authors.bookId = tbl_book.bookId INNER JOIN tbl_author ON tbl_author.authorId = tbl_book_authors.authorId 	INNER JOIN tbl_library_branch ON		tbl_book_copies.branchId = tbl_library_branch.branchId WHERE  tbl_library_branch.branchId=? Group BY tbl_book.title",  new Object[] {branchId});
        }      catch(Exception ce){    
        	ce.printStackTrace();
        }
        return null;
    }

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		List<Book_Loan> book_Loans = new ArrayList<Book_Loan>();	
        try{
            BookDAO bdao = new BookDAO(getConnection());
            AuthorDAO adao = new AuthorDAO(getConnection());
            Book_CopiesDAO bcdao= new Book_CopiesDAO(getConnection());
            while(rs.next()){
            	Book_Loan b = new Book_Loan();
                b.setBookId(rs.getInt("bookId"));
                b.setBranchId(rs.getInt("branchId"));
               // if(rs.get)
                //b.setCardNo(rs.getInt("cardNo"));
               // b.setDateOut(rs.getDate("dateOut"));
               // b.setDueDate(rs.getDate("dueDate"));
               // b.setDateIn(rs.getDate("dateIn"));
                b.setBook(bdao.readBookFromResultSet(rs));	
                b.setAuthor(adao.readAuthorFromResultSet(rs));
                b.setBookcopies(bcdao.readBookCopiesFromResultSet(rs));
                book_Loans.add(b);
            }
            return book_Loans;
        }catch(SQLException se){
        }
              
		return null;
	}


}
