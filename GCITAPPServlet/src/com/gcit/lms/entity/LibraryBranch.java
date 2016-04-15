/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcit.lms.entity;
import java.util.List;

/**
 *
 * @author tictoc
 */
public class LibraryBranch {
    private int branchId;
    private String branchName;
    private String branchAddress;
    private List<Book> books;

    /**
     * @return the branchId
     */
    public int getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the branchAddress
     */
    public String getBranchAddress() {
        return branchAddress;
    }

    /**
     * @param branchAddress the branchAddress to set
     */
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    /**
     * @return the books
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
}
