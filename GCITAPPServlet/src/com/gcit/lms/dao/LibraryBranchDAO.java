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
public class LibraryBranchDAO extends BaseDAO{

    public LibraryBranchDAO(Connection conn) {
        super(conn);
    }
    public void addLibraryBranch(LibraryBranch libraryBranch){		
        try{
            save("insert into tbl_library_branch (branchName, branchAddress) values (?,?)", new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }
	
    public void updateLibraryBranch(LibraryBranch libraryBranch){
        try{
            save("update tbl_library_branch set branchName = ? , branchAddress =? where branchId = ?", new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress(), libraryBranch.getBranchId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public void deleteLibraryBranch(LibraryBranch libraryBranch) {		
        try{
            save("delete from tbl_library_branch where branchId = ?", new Object[] {libraryBranch.getBranchId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public List<LibraryBranch> readAllLibraryBranch() {
        try{
            return (List<LibraryBranch>) readAll("select * from tbl_library_branch", null);}
        catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }
        return null;
    }

    public List<LibraryBranch> readLibraryBranchsByName(String branchName){

        try{
            return (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchName like ?", new Object[] {branchName});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }

    public Integer getCount(){		
        try{
            return getCount("select count(*) from tbl_library_branch");
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }

    @Override
    public List<LibraryBranch> extractData(ResultSet rs) {
        try{
            List<LibraryBranch> libraryBranchs = new ArrayList<LibraryBranch>();		
            while(rs.next()){
                    LibraryBranch a = new LibraryBranch();
                    a.setBranchId(rs.getInt("branchId"));
                    a.setBranchName(rs.getString("branchName"));
                    a.setBranchAddress(rs.getString("branchAddress"));
                    libraryBranchs.add(a);
            }
            return libraryBranchs;
        }catch(SQLException se){
        }
        return null;
    }

    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
