/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Rating implements Serializable{
    private Account account;
    private Product product;
    private float numOfStar;
    private String comment;

    public Rating() {
    }

    public Rating(Account account, Product product, float numOfStar, String comment) {
        this.account = account;
        this.product = product;
        this.numOfStar = numOfStar;
        this.comment = comment;
    }

    public Account getAccount() {
        return account;
    }

    public Product getProduct() {
        return product;
    }

    public float getNumOfStar() {
        return numOfStar;
    }

    public String getComment() {
        return comment;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setNumOfStar(float numOfStar) {
        this.numOfStar = numOfStar;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
    @Override
    public String toString() {
        return account.getAccUserName() + ", " + product.getProductName() + ", " + numOfStar + " stars, " + comment;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass()==this.getClass()){
            Rating other=(Rating)obj;
            return account.equals(other.account)&&product.equals(other.product);
        }
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
}
