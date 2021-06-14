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
public class CartDetail implements Serializable{
    private Cart cart;
    private Product product;
    private int numOfProduct;
    private long productPrice;

    public CartDetail() {
    }

    public CartDetail(Cart cart, Product product, int numOfProduct, long productPrice) {
        this.cart = cart;
        this.product = product;
        this.numOfProduct = numOfProduct;
        this.productPrice = productPrice;
    }

    

    public Cart getCart() {
        return cart;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumOfProduct() {
        return numOfProduct;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setNumOfProduct(int numOfProduct) {
        this.numOfProduct = numOfProduct;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return product.getProductName() + ", " + numOfProduct + ", " + productPrice;
    }
    
}
