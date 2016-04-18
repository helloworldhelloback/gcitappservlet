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
            save("insert into tbl_library_branch (branchName, branchAddress) values (?,?)", new Object[] {libraryBranch.getLibrarybranchName(), libraryBranch.getLibrarybranchAddress()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }
	
    public void updateLibraryBranch(LibraryBranch libraryBranch){
        try{
            save("update tbl_library_branch set branchName = ? , branchAddress =? where branchId = ?", new Object[] {libraryBranch.getLibrarybranchName(), libraryBranch.getLibrarybranchAddress(), libraryBranch.getLibrarybranchId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public void deleteLibraryBranch(Integer branchId) {		
        try{
            save("delete from tbl_library_branch where branchId = ?", new Object[] {branchId});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public List<LibraryBranch> readAllLibraryBranches() {
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
    public LibraryBranch readLibraryBranchByID(Integer branchId) throws ClassNotFoundException, SQLException{
		List<LibraryBranch> librarybranches = (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchId = ?", new Object[] {branchId});
		if(librarybranches!=null && librarybranches.size() >0){
			return librarybranches.get(0);
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
                    a.setLibrarybranchId(rs.getInt("branchId"));
                    a.setLibrarybranchName(rs.getString("branchName"));
                    a.setLibrarybranchAddress(rs.getString("branchAddress"));
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
