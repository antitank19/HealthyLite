/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Product implements Serializable {

    private String productId;
    private String productName;
    private String productDescription;
    private long productPrice;
    private float avgNumOfStar;

    public Product() {
    }

    public Product(String productId, String productName, String productDescription, long productPrice, float avgNumOfStar) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.avgNumOfStar = (float) (Math.round(avgNumOfStar*10)/10.0);
    }

    public float getAvgNumOfStar() {
        return avgNumOfStar;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public void setAvgNumOfStar(float avgNumOfStar) {
        this.avgNumOfStar = avgNumOfStar;
    }

    @Override
    public String toString() {
        return productId + ", " + productName + ", " + productPrice + ", " + productDescription + ", " + avgNumOfStar;
    }

    public boolean equals(Product o) {

        return productId.equals(o.productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass()==this.getClass()){
            Product other=(Product)obj;
            return productId.equals(other.productId);
        }
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    

}
