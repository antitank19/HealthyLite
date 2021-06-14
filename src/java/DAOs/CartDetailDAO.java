/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Cart;
import DTOs.CartDetail;
import DTOs.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import ultilities.DBConnect;

/**
 *
 * @author DELL
 */
public class CartDetailDAO {
    //ok
    public ArrayList<CartDetail> getAllDetail() throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from CartDetail";

        ArrayList<CartDetail> detailList = new ArrayList<CartDetail>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String cartId = rs.getString("cartId").trim();//ten collumn
                    CartDAO cartDAO=new CartDAO();
                    Cart cart =cartDAO.getCartById(cartId);
                    
                    String productId = rs.getString("productId").trim();
                    ProductDAO proDAO=new ProductDAO();
                    Product product=proDAO.getProductById(productId);
                    
                    int numOfProduct = rs.getInt("numOfProduct");
                    long productPrice = rs.getLong("productPrice");
                    //tao doi tuong DTO
                    CartDetail detail=new CartDetail(cart, product, numOfProduct, productPrice);
                    //add to list
                    detailList.add(detail);
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
        return detailList;
    }
//ok
    public ArrayList<CartDetail> getAllDetailByCartId(String cartId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from CartDetail where cartId=?";
        
        ArrayList<CartDetail> detailList = new ArrayList<CartDetail>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                pstm.setString(1, cartId.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                while (rs.next()) {
                    CartDAO cartDAO=new CartDAO();
                    Cart cart =cartDAO.getCartById(cartId);
                    
                    String productId = rs.getString("productId").trim();
                    ProductDAO proDAO=new ProductDAO();
                    Product product=proDAO.getProductById(productId);
                    
                    int numOfProduct = rs.getInt("numOfProduct");
                    long productPrice = rs.getLong("productPrice");
                    //tao doi tuong DTO
                    CartDetail detail=new CartDetail(cart, product, numOfProduct, productPrice);
                    //add to list
                    detailList.add(detail);
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
        return detailList;
    }
    public ArrayList<CartDetail> getAllDetailByCart(Cart cart) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from CartDetail where cartId=?";
        
        ArrayList<CartDetail> detailList = new ArrayList<CartDetail>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                pstm.setString(1, cart.getCartId().trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                while (rs.next()) {                    
                    String productId = rs.getString("productId").trim();
                    ProductDAO proDAO=new ProductDAO();
                    Product product=proDAO.getProductById(productId);
                    
                    int numOfProduct = rs.getInt("numOfProduct");
                    long productPrice = rs.getLong("productPrice");
                    //tao doi tuong DTO
                    CartDetail detail=new CartDetail(cart, product, numOfProduct, productPrice);
                    //add to list
                    detailList.add(detail);
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
        return detailList;
    }
    //ok
    public ArrayList<CartDetail> getAllDetailByProductId(String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from CartDetail where productId=?";
        
        ArrayList<CartDetail> detailList = new ArrayList<CartDetail>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                pstm.setString(1, productId.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String cartId = rs.getString("cartId").trim();
                    CartDAO cartDAO=new CartDAO();
                    Cart cart =cartDAO.getCartById(cartId);
                    
                    ProductDAO proDAO=new ProductDAO();
                    Product product=proDAO.getProductById(productId);
                    
                    int numOfProduct = rs.getInt("numOfProduct");
                    long productPrice = rs.getLong("productPrice");
                    //tao doi tuong DTO
                    CartDetail detail=new CartDetail(cart, product, numOfProduct, productPrice);
                    //add to list
                    detailList.add(detail);
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
        return detailList;
    }
    public ArrayList<CartDetail> getAllDetailByProduct(Product p) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from CartDetail where productId=?";
        
        ArrayList<CartDetail> detailList = new ArrayList<CartDetail>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                pstm.setString(1, p.getProductId().trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String cartId = rs.getString("cartId").trim();
                    CartDAO cartDAO=new CartDAO();
                    Cart cart =cartDAO.getCartById(cartId);
                    
                    int numOfProduct = rs.getInt("numOfProduct");
                    long productPrice = rs.getLong("productPrice");
                    //tao doi tuong DTO
                    CartDetail detail=new CartDetail(cart, p, numOfProduct, productPrice);
                    //add to list
                    detailList.add(detail);
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
        return detailList;
    }
    //ok
    public CartDetail getAllDetailByCartIdAndProductId(String cartId, String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from CartDetail "
                + "where cartId=? and productId=?";
        
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                pstm.setString(1, cartId.trim());
                pstm.setString(2, productId.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                if (rs.next()) {
                    CartDAO cartDAO=new CartDAO();
                    Cart cart =cartDAO.getCartById(cartId);
                    
                    ProductDAO proDAO=new ProductDAO();
                    Product product=proDAO.getProductById(productId);
                    
                    int numOfProduct = rs.getInt("numOfProduct");
                    long productPrice = rs.getInt("productPrice");
                    //tao doi tuong DTO
                    CartDetail detail=new CartDetail(cart, product, numOfProduct, productPrice);
                    //add to list
                    return detail;
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
    public boolean createDetail(CartDetail detail) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "insert INTO CartDetail"
                + "(cartId, productId, numOfProduct, productPrice) "
                + " VALUES ( ?, ?, ?, ?)";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setString(1, detail.getCart().getCartId().trim());
                pstm.setString(2, detail.getProduct().getProductId().trim());
                pstm.setInt(3, detail.getNumOfProduct());
                pstm.setLong(4, detail.getProductPrice());
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
    
    public boolean DeleteDetailByCartIdAndProductId(String cartId, String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;

        String sql = "delete from CartDetail "
                + "where cartId=? and productId=?";

        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, cartId.trim());
                pstm.setString(2, productId.trim());
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
    public boolean updateCartDetail(CartDetail detail) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "update CartDetail set "
                + "numOfProduct=?, productPrice=?"
                + " where cartId=? and productId=?";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setInt(1, detail.getNumOfProduct());
                pstm.setLong(2, detail.getProductPrice());
                
                pstm.setString(3, detail.getCart().getCartId().trim());
                pstm.setString(4, detail.getProduct().getProductId().trim());
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
