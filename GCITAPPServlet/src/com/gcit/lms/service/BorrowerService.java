/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

/**
 *
 * @author tictoc
 */
public class BorrowerService {
	public List<Book_Loan> getAllBookCopiesByBranchId(Integer branchId)throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
            Book_LoansDAO bldao = new Book_LoansDAO(conn);
            return bldao.readBookLoanWithBookAndAuthorInfoByBrancId(branchId);
        }catch (Exception e){
                e.printStackTrace();
                //conn.rollback();
        }finally{
                conn.close();
        }
        return null;
	}
}
