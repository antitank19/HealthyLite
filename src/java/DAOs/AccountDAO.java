/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Account;
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
public class AccountDAO {
//ok
    public ArrayList<Account> getAllAccount() throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Account";

        ArrayList<Account> accList = new ArrayList<Account>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String accUserName = rs.getString("accUserName").trim();//ten collumn
                    String accPassWord = rs.getString("accPassword").trim();
                    String accFullName = rs.getString("accFullName").trim();
                    Date accDob = rs.getDate("accDob");
                    String accGender = rs.getString("accGender").trim();
                    String accAddress = rs.getString("accAddress").trim();
                    String accPhone = rs.getString("accPhone").trim();
                    //tao doi tuong DTO
                    Account acc = new Account(accUserName, accPassWord, accFullName, accDob, accAddress, accPhone, accGender);
                    //add to list
                    accList.add(acc);
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
        return accList;
    }
//ok
    public Account getAccountByAccUserName(String accUserName) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Account where accUserName=?";
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                pstm.setString(1, accUserName);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String accPassWord = rs.getString("accPassword").trim();
                    String accFullName = rs.getString("accFullName").trim();
                    Date accDob = rs.getDate("accDob");
                    String accGender = rs.getString("accGender").trim();
                    String accAddress = rs.getString("accAddress").trim();
                    String accPhone = rs.getString("accPhone").trim();
                    //tao doi tuong DTO
                    Account acc = new Account(accUserName, accPassWord, accFullName, accDob, accAddress, accPhone, accGender);

                    return acc;
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
    public boolean checkPassword(String accUserName, String checkPassword){
        try {
            Account acc=getAccountByAccUserName(accUserName);
            if (acc==null) {
                return false;
            }
            if (acc.getAccPassword().equals(checkPassword))
                return true;
        } catch (NamingException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //ok
    public boolean createAccount(Account acc) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "insert INTO Account"
                + "(accUserName, accPassword, accFullName, accGender,  accDob, accPhone, accAddress) "
                + " VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setString(1, acc.getAccUserName());
                pstm.setString(2, acc.getAccPassword());
                pstm.setString(3, acc.getAccFullName());
                pstm.setString(4, acc.getAccGender());
                pstm.setDate(5, acc.getAccDob());
                pstm.setString(6, (acc.getAccPhone().isEmpty()?"Not provided":acc.getAccPhone()));
                pstm.setString(7, (acc.getAccAddress().isEmpty()?"Not provided":acc.getAccAddress()));
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
    public boolean DeleteAccountByUserName(String accUserName) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;

        String sql = "delete from Account where accUserName=?";

        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, accUserName);
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
    public boolean updateAccount(Account acc) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "update Account set "
                + "accPassword=?, accFullName=?, accGender=?,  accDob=?, accPhone=?, accAddress=? "
                + "where accUserName=?";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setString(1, acc.getAccPassword());
                pstm.setString(2, acc.getAccFullName());
                pstm.setString(3, acc.getAccGender());
                pstm.setDate(4, acc.getAccDob());
                pstm.setString(5, acc.getAccPhone());
                pstm.setString(6, acc.getAccAddress());
                
                pstm.setString(7, acc.getAccUserName());
                
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
    public ArrayList<Account> getAllAccountBuyProduct(String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql ="select distinct a.accUserName\n" +
                    "from Account a, Cart c, CartDetail d, Product p\n" +
                    "where d.productId=?\n" +
                    "	and d.cartId=c.cartId\n" +
                    "	and c.accUserName=a.accUserName;";

        ArrayList<Account> accList = new ArrayList<Account>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, productId);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String accUserName = rs.getString("accUserName").trim();//ten collumn
                    Account acc = getAccountByAccUserName(accUserName);
                    //add to list
                    accList.add(acc);
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
        return accList;
    }
    public Long getMoneySpentByAccountOnProduct(String accUserName, String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql ="select d.productId, SUM(d.productPrice*d.numOfProduct)as money\n" +
                    "from Cart c, CartDetail d\n" +
                    "where d.productId=?\n" +
                    "	and d.cartId=c.cartId\n" +
                    "	and c.accUserName=?\n" +
                    "Group by c.accUserName, d.productId;";
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, productId);
                pstm.setString(2, accUserName);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                if(rs.next()) {
                    Long money = rs.getLong("money");
                    return money;
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
    public Long getMoneySpentByAccount(String accUserName) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql ="select  SUM(d.productPrice*d.numOfProduct)as money\n" +
                    "from Cart c, CartDetail d\n" +
                    "where d.cartId=c.cartId\n" +
                    "	and c.accUserName=?\n" +
                    "Group by c.accUserName;";
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, accUserName);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                if(rs.next()) {
                    Long money = rs.getLong("money");
                    return money;
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
    public int getNumberOfProductBoughtByAccount(String accUserName, String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql ="select d.productId, SUM(d.numOfProduct)as numOfProduct\n" +
                    "from Cart c, CartDetail d\n" +
                    "where d.productId=?\n" +
                    "	and d.cartId=c.cartId\n" +
                    "	and c.accUserName=?\n" +
                    "Group by c.accUserName, d.productId;";
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, productId);
                pstm.setString(2, accUserName);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                if(rs.next()) {
                    int numOfProduct = rs.getInt("numOfProduct");
                    return numOfProduct;
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
        return 0;
    }
}
