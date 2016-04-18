/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.Book_CopiesDAO;
import com.gcit.lms.entity.*;

/**
 *
 * @author tictoc
 */
public class LibrarianService {
	
	
	public List<Book_Copies> getAllBooksCopiesByBranchId(Integer branchId)throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
            Book_CopiesDAO bdao = new Book_CopiesDAO(conn);
            return bdao.readBookTitleAndCopiesByBranchID(branchId);
        }catch (Exception e){
                e.printStackTrace();
                //conn.rollback();
        }finally{
                conn.close();
        }
        return null;
	}
	public Book_Copies getBookCopyByID(Integer bookId, Integer branchId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			Book_CopiesDAO adao = new Book_CopiesDAO(conn);
			return adao.readBookCopyById(bookId, branchId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
    
}
