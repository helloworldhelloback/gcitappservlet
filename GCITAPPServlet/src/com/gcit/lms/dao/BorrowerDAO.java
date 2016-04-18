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
public class BorrowerDAO extends BaseDAO{

    public BorrowerDAO(Connection conn) {
        super(conn);
    }
    public void addBorrower(Borrower borrower){		
        try{
            save("insert into tbl_borrower (name, address, phone) values (?,?,?)", new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhone()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }
	
    public void updateBorrower(Borrower borrower){
        try{
            save("update tbl_borrower set name=? , address=?, phone=? where cardNo=?", new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public void deleteBorrower(Integer cardNo) {		
        try{
            save("delete from tbl_borrower where cardNo = ?", new Object[] {cardNo});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public List<Borrower> readAllBorrowers() {
        try{
            return (List<Borrower>) readAll("select * from tbl_borrower", null);}
        catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }
        return null;
    }

    public List<Borrower> readBorrowersByName(String name){

        try{
            return (List<Borrower>) readAll("select * from tbl_borrower where name like ?", new Object[] {name});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }

    public Integer getCount(){		
        try{
            return getCount("select count(*) from tbl_borrower");
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }

    @Override
    public List<Borrower> extractData(ResultSet rs) {

        try{
            List<Borrower> borrowers = new ArrayList<Borrower>();		
            while(rs.next()){
                    Borrower a = new Borrower();
                    a.setName(rs.getString("name"));
                    a.setAddress(rs.getString("address"));
                    a.setPhone(rs.getString("phone"));
                    borrowers.add(a);
            }
            return borrowers;
        }catch(SQLException se){
        }
        return null;
    }

    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
