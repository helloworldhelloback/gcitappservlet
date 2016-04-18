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
public class PublisherDAO extends BaseDAO{

    public PublisherDAO(Connection conn) {
        super(conn);
    }
    
    public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{		
       // try{
            save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
        //}      catch(Exception ce){            
        //}
    }
	
    public void updatePublisher(Publisher publisher){
        try{
            save("update tbl_publisher set publisherName=?, publisherAddress=?, publisherPhone=? where publisherId = ?", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public void deletePublisher(Integer publisherId) {		
        try{
            save("delete from tbl_publisher where publisherId = ?", new Object[] {publisherId});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
    }

    public List<Publisher> readAllPublishers() {
        try{
            return (List<Publisher>) readAll("select * from tbl_publisher", null);}
        catch(ClassNotFoundException ce){

        }catch(SQLException se){
        }
        return null;
    }

    public List<Publisher> readPublishersByName(String publisherName){

        try{
            return (List<Publisher>) readAll("select * from tbl_publisher where publisherName like ?", new Object[] {publisherName});
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }
    public Publisher readPublishersByID(Integer publisherId) throws ClassNotFoundException, SQLException{
		List<Publisher> publishers = (List<Publisher>) readAll("select * from tbl_publisher where publisherId=?", new Object[] {publisherId});
		if(publishers!=null && publishers.size() >0){
			return publishers.get(0);
		}
		return null;
	}

    public Integer getCount(){		
        try{
            return getCount("select count(*) from tbl_publisher");
        }      catch(ClassNotFoundException ce){            
        }catch(SQLException se){
        }
        return null;
    }

    @Override
    public List<Publisher> extractData(ResultSet rs) {

        try{
            List<Publisher> publishers = new ArrayList<Publisher>();		
            while(rs.next()){
                    Publisher a = new Publisher();
                    a.setPublisherId(rs.getInt("publisherId"));
                    a.setPublisherName(rs.getString("publisherName"));
                    a.setPublisherAddress(rs.getString("publisherAddress"));
                    a.setPublisherPhone(rs.getString("publisherPhone"));
                    publishers.add(a);
            }
            return publishers;
        }catch(SQLException se){
        }
        return null;
    }

    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
