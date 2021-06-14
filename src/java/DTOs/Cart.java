/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Cart implements Serializable{
    private String cartId;
    private Date cartBuyDate;
    private Date cartShipDate;
    private Account account;
    private String cartAddress;
    private ArrayList<CartDetail> detailList=new ArrayList<CartDetail>();

    public Cart() {
    }

    public Cart(String cartId, Date cartBuyDate, Date cartShipDate, Account account, String cartAddress, ArrayList<CartDetail> detailList) {
        this.cartId = cartId;
        this.cartBuyDate = cartBuyDate;
        this.cartShipDate = cartShipDate;
        this.account = account;
        this.detailList=detailList;
        this.cartAddress=cartAddress;
    }

    public String getCartAddress() {
        return cartAddress;
    }
    
    public ArrayList<CartDetail> getDetailList() {
        return detailList;
    }

    
    public String getCartId() {
        return cartId;
    }

    public Date getCartBuyDate() {
        return cartBuyDate;
    }

    public Date getCartShipDate() {
        return cartShipDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setDetailList(ArrayList<CartDetail> detailList) {
        this.detailList = detailList;
    }

    public void setCartAddress(String cartAddress) {
        this.cartAddress = cartAddress;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public void setCartBuyDate(Date cartBuyDate) {
        this.cartBuyDate = cartBuyDate;
    }

    public void setCartShipDate(Date cartShipDate) {
        this.cartShipDate = cartShipDate;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public String toString() {
        return cartId+ ", " + account.getAccFullName() + ", " + cartBuyDate + ", " + cartShipDate + ", " +cartAddress ;
    }
    
}
