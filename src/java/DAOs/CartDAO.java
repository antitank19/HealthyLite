/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Account;
import DTOs.Cart;
import DTOs.CartDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import ultilities.DBConnect;

/**
 *
 * @author DELL
 */
public class CartDAO {
    //ok
    public ArrayList<Cart> getAllCart() throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Cart";

        ArrayList<Cart> cartList = new ArrayList<Cart>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String cartId = rs.getString("cartId").trim();//ten collumn
                    Date cartBuyDate = rs.getDate("cartBuyDate");
                    Date cartShipDate = rs.getDate("cartShipDate");
                    String accUserName = rs.getString("accUserName").trim();
                    String cartAddress=rs.getString("cartAddress");
                    
                    AccountDAO accDAO=new AccountDAO();
                    Account acc=accDAO.getAccountByAccUserName(accUserName);
                    //tao doi tuong DTO
                    CartDetailDAO detailDAO= new CartDetailDAO();
                    Cart cart=new Cart(cartId, cartBuyDate, cartShipDate, acc,cartAddress, null);
                    ArrayList<CartDetail> detailList=detailDAO.getAllDetailByCart(cart);
                    cart.setDetailList(detailList);
                    //add to list
                    cartList.add(cart);
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
        return cartList;
    }
    //ok
    public ArrayList<Cart> getAllCartByAccUserName(String accUserName) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Cart where accUserName=?";

        ArrayList<Cart> cartList = new ArrayList<Cart>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, accUserName.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String cartId = rs.getString("cartId").trim();//ten collumn
                    Date cartBuyDate = rs.getDate("cartBuyDate");
                    Date cartShipDate = rs.getDate("cartShipDate");
                    String cartAddress=rs.getString("cartAddress");
                    
                    AccountDAO accDAO=new AccountDAO();
                    Account acc=accDAO.getAccountByAccUserName(accUserName);
                    //tao doi tuong DTO
                    CartDetailDAO detailDAO= new CartDetailDAO();
                    Cart cart=new Cart(cartId, cartBuyDate, cartShipDate, acc,cartAddress, null);
                    ArrayList<CartDetail> detailList=detailDAO.getAllDetailByCart(cart);
                    cart.setDetailList(detailList);
                    //add to list
                    cartList.add(cart);
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
        return cartList;
    }
    public ArrayList<Cart> getAllCartByAccount(Account a) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Cart where accUserName=?";

        ArrayList<Cart> cartList = new ArrayList<Cart>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, a.getAccUserName().trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String cartId = rs.getString("cartId").trim();//ten collumn
                    Date cartBuyDate = rs.getDate("cartBuyDate");
                    Date cartShipDate = rs.getDate("cartShipDate");
                    String cartAddress=rs.getString("cartAddress");
                    
                    //tao doi tuong DTO
                    CartDetailDAO detailDAO= new CartDetailDAO();
                    Cart cart=new Cart(cartId, cartBuyDate, cartShipDate, a,cartAddress, new ArrayList<CartDetail>());
                    ArrayList<CartDetail> detailList=detailDAO.getAllDetailByCart(cart);
                    cart.setDetailList(detailList);
                    //add to list
                    cartList.add(cart);
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
        return cartList;
    }
    public Cart getLatestCartByAccount(Account a) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select top 1 * from Cart where accUserName=? order by cartId desc";
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, a.getAccUserName().trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                if (rs.next()) {
                    String cartId = rs.getString("cartId").trim();//ten collumn
                    Date cartBuyDate = rs.getDate("cartBuyDate");
                    Date cartShipDate = rs.getDate("cartShipDate");
                    String cartAddress=rs.getString("cartAddress");
                    
                    //tao doi tuong DTO
                    CartDetailDAO detailDAO= new CartDetailDAO();
                    Cart cart=new Cart(cartId, cartBuyDate, cartShipDate, a,cartAddress, new ArrayList<CartDetail>());
                    ArrayList<CartDetail> detailList=detailDAO.getAllDetailByCart(cart);
                    cart.setDetailList(detailList);
                    
                    return cart;
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
    public Cart getCartById(String cartId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Cart where cartId=?";
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                pstm.setString(1, cartId.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                if (rs.next()) {
                    Date cartBuyDate = rs.getDate("cartBuyDate");
                    Date cartShipDate = rs.getDate("cartShipDate");
                    String accUserName = rs.getString("accUserName").trim();
                    String cartAddress=rs.getString("cartAddress");
                    
                    AccountDAO accDAO=new AccountDAO();
                    Account acc=accDAO.getAccountByAccUserName(accUserName);
                    //tao doi tuong DTO
                    CartDetailDAO detailDAO= new CartDetailDAO();
                    Cart cart=new Cart(cartId, cartBuyDate, cartShipDate, acc,cartAddress, new ArrayList<CartDetail>());
                    ArrayList<CartDetail> detailList=detailDAO.getAllDetailByCart(cart);
                    cart.setDetailList(detailList);

                    return cart;
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
    //maybe ok, list not tested yet
    public boolean createCart(Cart cart) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "insert INTO Cart"
                + "(cartId, cartBuyDate, cartShipDate, accUserName, cartAddress) "
                + " VALUES ( ?, ?, ?, ?, ?)";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setString(1, cart.getCartId().trim());
                pstm.setDate(2, cart.getCartBuyDate());
                pstm.setDate(3, cart.getCartShipDate());
                pstm.setString(4, cart.getAccount().getAccUserName().trim());
                pstm.setString(5, cart.getCartAddress());
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
    
    public boolean DeleteCartById(String cartId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;

        String sql = "delete from Cart where cartId=?";

        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, cartId.trim());
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
    public boolean updateCart(Cart cart) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "update Cart set "
                + "cartBuyDate=?, cartShipDate=?, accUserName=?"
                + " where cartId=?";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setDate(1, cart.getCartBuyDate());
                pstm.setDate(2, cart.getCartShipDate());
                pstm.setString(3, cart.getAccount().getAccUserName().trim());
                
                pstm.setString(4, cart.getCartId().trim());
                
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
