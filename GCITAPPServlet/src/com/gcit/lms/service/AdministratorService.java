/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcit.lms.service;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tictoc
 */
public class AdministratorService {
	/////////////////////////////////////Author/////////////////////////////////////////////////////////////
	public void createAuthorWithBookAssociation(Author author, String[] books) throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
        	int authorId= createAuthorWithID(author);
            Book_AuthorsDAO bdao = new Book_AuthorsDAO(conn);
            for(String book:books){
            	Book_Authors bookauthor = new Book_Authors();
            	bookauthor.setAuthorId(authorId);
            	bookauthor.setBookId(Integer.parseInt(book));
            	bdao.addRow(bookauthor);
            }
            conn.commit();
        }catch (Exception e){
            conn.rollback();
        }finally{
            conn.close();
        }		
    }	
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
	public List<Author> getAllAuthors(int pageNo)throws SQLException, ClassNotFoundException{
            ConnectionUtil c = new ConnectionUtil();
            Connection conn = c.getConnection();
            try{
                    AuthorDAO adao = new AuthorDAO(conn);
                    return adao.readAllAuthors(pageNo);
            }catch (Exception e){
                    e.printStackTrace();
                    //conn.rollback();
            }finally{
                    conn.close();
            }
            return null;
	}	
	public Author getAuthorByID(Integer authorId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorsByID(authorId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	public void deleteAuthor(Integer authorId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(authorId);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}		
	}
	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			adao.updateAuthor(author);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}		
	}
	
	public Integer getAuthorCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	public List<Author> getAllAuthorsByName(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorsByName(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	/////////////////////////////////////////Book//////////////////////////////////////////////////////////
	
	
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
	//////////////////////////////////////////Publisher//////////////////////////////////////////////////////
	public void createPublisher(Publisher publisher) throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
            PublisherDAO adao = new PublisherDAO(conn);
            adao.addPublisher(publisher);
            conn.commit();
        }catch (Exception e){
                conn.rollback();
        }finally{
                conn.close();
        }		
    }    
	public List<Publisher> getAllPublishers()throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
            PublisherDAO adao = new PublisherDAO(conn);
            return adao.readAllPublishers();
        }catch (Exception e){
            e.printStackTrace();
            //conn.rollback();
        }finally{
        	conn.close();
        }
        return null;
	}	
	public Publisher getPublisherByID(Integer publisherId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO adao = new PublisherDAO(conn);
			return adao.readPublishersByID(publisherId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	public void deletePublisher(Integer publisherId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO adao = new PublisherDAO(conn);
			adao.deletePublisher(publisherId);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}		
	}
	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO adao = new PublisherDAO(conn);
			adao.updatePublisher(publisher);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}		
	}
	//////////////////////////////////////Genre//////////////////////////////////////////////////////////////
	public void createGenre(Genre genre) throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
            GenreDAO adao = new GenreDAO(conn);
            adao.addGenre(genre);
            conn.commit();
        }catch (Exception e){
                conn.rollback();
        }finally{
                conn.close();
        }		
    }    
	public List<Genre> getAllGenres()throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
            GenreDAO adao = new GenreDAO(conn);
            return adao.readAllGenres();
        }catch (Exception e){
            e.printStackTrace();
            //conn.rollback();
        }finally{
            conn.close();
        }
        return null;
	}	
	public Genre getGenreByID(Integer genreId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			GenreDAO adao = new GenreDAO(conn);
			return adao.readGenresByID(genreId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	public void deleteGenre(Integer genreId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			GenreDAO adao = new GenreDAO(conn);
			adao.deleteGenre(genreId);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}		
	}
	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			GenreDAO adao = new GenreDAO(conn);
			adao.updateGenre(genre);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}		
	}
	///////////////////////////////////////Library Branch//////////////////////////////////////////////////
	public void createLibraryBranch(LibraryBranch librarybranch) throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
            LibraryBranchDAO adao = new LibraryBranchDAO(conn);
            adao.addLibraryBranch(librarybranch);
            conn.commit();
        }catch (Exception e){
                conn.rollback();
        }finally{
                conn.close();
        }		
    }    
	public List<LibraryBranch> getAllLibraryBranches()throws SQLException, ClassNotFoundException{
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try{
            LibraryBranchDAO adao = new LibraryBranchDAO(conn);
            return adao.readAllLibraryBranches();
        }catch (Exception e){
            e.printStackTrace();
            //conn.rollback();
        }finally{
            conn.close();
        }
        return null;
	}	
	public LibraryBranch getLibraryBranchByID(Integer librarybranchId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			return adao.readLibraryBranchByID(librarybranchId);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	public void deleteLibraryBranch(Integer librarybranchId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			adao.deleteLibraryBranch(librarybranchId);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}		
	}
	public void updateLibraryBranch(LibraryBranch librarybranch) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			adao.updateLibraryBranch(librarybranch);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}		
	}
	

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////

}
