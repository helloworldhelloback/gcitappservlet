package com.gcit.lms.entity;

import java.util.List;

public class Book_Loan {

	private int bookId;
	private int branchId;
	private int cardNo;
	private java.sql.Date dateOut;
    private java.sql.Date dueDate;
    private java.sql.Date dateIn;
    private Book book;
    private Author author;
    private Book_Copies bookcopies;
    

	
	public Book_Copies getBookcopies() {
		return bookcopies;
	}
	public void setBookcopies(Book_Copies bookcopies) {
		this.bookcopies = bookcopies;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public java.sql.Date getDateOut() {
		return dateOut;
	}
	public void setDateOut(java.sql.Date dateOut) {
		this.dateOut = dateOut;
	}
	public java.sql.Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(java.sql.Date dueDate) {
		this.dueDate = dueDate;
	}
	public java.sql.Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(java.sql.Date dateIn) {
		this.dateIn = dateIn;
	}
}
