/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Cart;
import DTOs.CartDetail;
import DTOs.Product;
import DTOs.Rating;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.naming.NamingException;
import ultilities.DBConnect;

/**
 *
 * @author DELL
 */
public class ProductDAO {
    //ok
    public ArrayList<Product> getAllProduct() throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql ="select p.*, AVG(r.numOfStar)as avgNumOfStar\n" +
                    "from Product p left join Rating r\n" +
                    "on p.productId=r.productId\n" +
                    "group by p.productId, p.productName, p.productPrice, p.productDescription";

        ArrayList<Product> productList = new ArrayList<Product>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String productId = rs.getString("productid").trim();//ten collumn
                    String productName = rs.getString("productName").trim();
                    long productPrice = rs.getLong("productPrice");
                    String productDescription = rs.getString("productDescription").trim();
                    float avgNumOfStar=new BigDecimal(rs.getFloat("avgNumOfStar")).setScale(1, RoundingMode.HALF_UP).floatValue();
                    //tao doi tuong DTO
                    Product p=new Product(productId, productName, productDescription, productPrice, avgNumOfStar);
                    //add to list
                    productList.add(p);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return productList;
    }
    public ArrayList<Product> getNumOfBestRatedProduct(int numOfProduct) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql ="select top (?) p.*, AVG(r.numOfStar)as avgNumOfStar\n" +
                    "from Product p left join Rating r\n" +
                    "on p.productId=r.productId\n" +
                    "group by p.productId, p.productName, p.productPrice, p.productDescription\n"+
                    "order by avgNumOfStar desc";

        ArrayList<Product> productList = new ArrayList<Product>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, numOfProduct);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String productId = rs.getString("productid").trim();//ten collumn
                    String productName = rs.getString("productName").trim();
                    long productPrice = rs.getLong("productPrice");
                    String productDescription = rs.getString("productDescription").trim();
                    float avgNumOfStar=new BigDecimal(rs.getFloat("avgNumOfStar")).setScale(1, RoundingMode.HALF_UP).floatValue();
                    //tao doi tuong DTO
                    Product p=new Product(productId, productName, productDescription, productPrice, avgNumOfStar);
                    //add to list
                    productList.add(p);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return productList;
    }
//ok
    public Product getProductById(String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql ="select p.*, AVG(r.numOfStar)as avgNumOfStar\n" +
                    "from Product p left join Rating r on p.productId=r.productId\n" +
                    "where  p.productId=?\n" +
                    "group by p.productId, p.productName, p.productPrice, p.productDescription";
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                pstm.setString(1, productId.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String productName = rs.getString("productName").trim();
                    long productPrice = rs.getLong("productPrice");
                    String productDescription = rs.getString("productDescription").trim();
                    float avgNumOfStar=new BigDecimal(rs.getFloat("avgNumOfStar")).floatValue();
                    //tao doi tuong DTO
                    Product p=new Product(productId, productName, productDescription, productPrice, avgNumOfStar);

                    return p;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    public Product getLatestProduct() throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql ="select top 1 p.*, AVG(r.numOfStar)as avgNumOfStar\n" +
                    "from Product p left join Rating r\n" +
                    "on p.productId=r.productId \n" +
                    "group by p.productId, p.productName, p.productPrice, p.productDescription\n"+
                    "order by p.productId desc";
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String productId = rs.getString("productId").trim();
                    String productName = rs.getString("productName").trim();
                    long productPrice = rs.getLong("productPrice");
                    String productDescription = rs.getString("productDescription").trim();
                    float avgNumOfStar=new BigDecimal(rs.getFloat("avgNumOfStar")).floatValue();
                    //tao doi tuong DTO
                    Product p=new Product(productId, productName, productDescription, productPrice, avgNumOfStar);

                    return p;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    //ok
    public boolean createProduct(Product pro) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "insert INTO Product"
                + "(productId, productName, productPrice, productDescription) "
                + " VALUES ( ?, ?, ?, ?)";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setString(1, pro.getProductId().trim());
                pstm.setString(2, pro.getProductName().trim());
                pstm.setLong(3, pro.getProductPrice());
                pstm.setString(4, pro.getProductDescription().trim());
                //thuc thi cau truy van
                pstm.executeUpdate();
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally{
            //5
            if(pstm!=null)
                pstm.close();
            if(con!=null)
                con.close();
        }
        return false;
    }
    //ok
    public boolean DeleteProductById(String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;

        String sql = "delete from Product where productId=?";

        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, productId.trim());
                //4. thuc hien truy van
                
                pstm.executeUpdate();
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            if(pstm!=null)
                pstm.close();
            if(con!=null)
                con.close();
        }
        return false;
    }
    //ok
    public boolean updateProduct(Product pro) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "update Product set "
                + "productName=?, productPrice=?, productDescription=? "
                + "where productId=?";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setString(1, pro.getProductName().trim());
                pstm.setLong(2, pro.getProductPrice());
                pstm.setString(3, pro.getProductDescription().trim());
                
                pstm.setString(4, pro.getProductId().trim());
                //thuc thi cau truy van
                pstm.executeUpdate();
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally{
            //5
            if(pstm!=null)
                pstm.close();
            if(con!=null)
                con.close();
        }
        return false;
    }
}
