/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Account;
import DTOs.Product;
import DTOs.Rating;
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
public class RatingDAO {
    //ok
    public ArrayList<Rating> getAllRating() throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Rating";

        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String accUserName = rs.getString("accUserName").trim();//ten collumn
                    AccountDAO accDAO = new AccountDAO();
                    Account acc =accDAO.getAccountByAccUserName(accUserName);
                    
                    String productId = rs.getString("productId").trim();
                    ProductDAO proDAO = new ProductDAO();
                    Product product= proDAO.getProductById(productId);
                    
                    float numOfStar = rs.getFloat("numOfStar");
                    String comment = rs.getString("comment").trim();
                    //tao doi tuong DTO
                    Rating rate =new Rating(acc, product, numOfStar, comment);
                    //add to list
                    ratingList.add(rate);
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
        return ratingList;
    }
    //ok
    public ArrayList<Rating> getAllRatingByAccount(Account acc) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Rating where accUserName=?";

        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, acc.getAccUserName().trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {                    
                    String productId = rs.getString("productId").trim();
                    ProductDAO proDAO = new ProductDAO();
                    Product product= proDAO.getProductById(productId);
                    
                    float numOfStar = rs.getFloat("numOfStar");
                    String comment = rs.getString("comment").trim();
                    //tao doi tuong DTO
                    Rating rate =new Rating(acc, product, numOfStar, comment);
                    //add to list
                    ratingList.add(rate);
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
        return ratingList;
    }
    public ArrayList<Rating> getAllRatingByAccUserName(String accUserName) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Rating where accUserName=?";

        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, accUserName.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    AccountDAO accDAO = new AccountDAO();
                    Account acc =accDAO.getAccountByAccUserName(accUserName);
                    
                    String productId = rs.getString("productId").trim();
                    ProductDAO proDAO = new ProductDAO();
                    Product product= proDAO.getProductById(productId);
                    
                    float numOfStar = rs.getFloat("numOfStar");
                    String comment = rs.getString("comment").trim();
                    //tao doi tuong DTO
                    Rating rate =new Rating(acc, product, numOfStar, comment);
                    //add to list
                    ratingList.add(rate);
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
        return ratingList;
    }
    public ArrayList<Rating> getAllRatingByNumOfStarAndProductId(String productId , float numOfStar) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Rating "
                    +"where productId=? and numOfStar=?";

        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, productId.trim());
                pstm.setFloat(2, numOfStar);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String accUserName=rs.getString("accUserName");
                    AccountDAO accDAO = new AccountDAO();
                    Account acc =accDAO.getAccountByAccUserName(accUserName);
                    
                    ProductDAO proDAO = new ProductDAO();
                    Product product= proDAO.getProductById(productId);
                    
                    String comment = rs.getString("comment").trim();
                    //tao doi tuong DTO
                    Rating rate =new Rating(acc, product, numOfStar, comment);
                    //add to list
                    ratingList.add(rate);
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
        return ratingList;
    }
    //ok
    public ArrayList<Rating> getNumOfRatingByNumOfStarAndProductId(int numOfRating,String productId , float numOfStar) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select top (?) * from Rating "
                    +"where productId=? and numOfStar=?";

        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, numOfRating);
                pstm.setString(2, productId.trim());
                pstm.setFloat(3, numOfStar);
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String accUserName=rs.getString("accUserName");
                    AccountDAO accDAO = new AccountDAO();
                    Account acc =accDAO.getAccountByAccUserName(accUserName);
                    
                    ProductDAO proDAO = new ProductDAO();
                    Product product= proDAO.getProductById(productId);
                    
                    String comment = rs.getString("comment").trim();
                    //tao doi tuong DTO
                    Rating rate =new Rating(acc, product, numOfStar, comment);
                    //add to list
                    ratingList.add(rate);
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
        return ratingList;
    }
    //ok
    public ArrayList<Rating> getAllRatingByProductId(String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Rating where productId=?";

        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, productId.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String accUserName = rs.getString("accUserName").trim();//ten collumn
                    AccountDAO accDAO = new AccountDAO();
                    Account acc =accDAO.getAccountByAccUserName(accUserName);
                    
                    ProductDAO proDAO = new ProductDAO();
                    Product product= proDAO.getProductById(productId);
                    
                    float numOfStar = rs.getFloat("numOfStar");
                    String comment = rs.getString("comment").trim();
                    //tao doi tuong DTO
                    Rating rate =new Rating(acc, product, numOfStar, comment);
                    //add to list
                    ratingList.add(rate);
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
        return ratingList;
    }
    public ArrayList<Rating> getAllRatingByProduct(Product p) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Rating where productId=?";

        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, p.getProductId().trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String accUserName = rs.getString("accUserName").trim();//ten collumn
                    AccountDAO accDAO = new AccountDAO();
                    Account acc =accDAO.getAccountByAccUserName(accUserName);
                    
                    float numOfStar = rs.getFloat("numOfStar");
                    String comment = rs.getString("comment").trim();
                    //tao doi tuong DTO
                    Rating rate =new Rating(acc, p, numOfStar, comment);
                    //add to list
                    ratingList.add(rate);
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
        return ratingList;
    }
    //ok
    public float getAverageRatingByProductId(String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select AVG(numOfStar) as averageNumOfStar "
                    +"from Rating "
                    +"where productId=? "
                    +"Group by productId";

        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);
                pstm.setString(1, productId.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();
                if (rs.next()) {
                    Float averageNumOfStar=rs.getFloat("averageNumOfStar");
                    return averageNumOfStar;
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
        return -1;
    }
//ok
    public Rating getRatingByAccUserNameAndProductId(String accUserName, String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select * from Rating "
                + "where accUserName=? and productId=?";
        try {
            if (con != null) {
                //3. Tao doi tuong truy van
                pstm = con.prepareStatement(sql);//gan tham so
                pstm.setString(1, accUserName.trim());
                pstm.setString(2, productId.trim());
                //4. tao doi tuong kq
                rs = pstm.executeQuery();

                if (rs.next()) {
                    AccountDAO accDAO=new AccountDAO();
                    Account acc=accDAO.getAccountByAccUserName(accUserName);
                    
                    ProductDAO proDAO=new ProductDAO();
                    Product product=proDAO.getProductById(productId);
                    
                    Float numOfStar = rs.getFloat("numOfStar");
                    String comment = rs.getString("comment").trim();
                    //tao doi tuong DTO
                    Rating rating=new Rating(acc, product, numOfStar, comment);

                    return rating;
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
    public boolean createRating(Rating rating) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "insert INTO Rating"
                + "(accUserName, productId, numOfStar, comment) "
                + " VALUES ( ?, ?, ?, ?)";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setString(1, rating.getAccount().getAccUserName().trim());
                pstm.setString(2, rating.getProduct().getProductId().trim());
                pstm.setFloat(3, rating.getNumOfStar());
                pstm.setString(4, rating.getComment().trim());
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
    
    public boolean DeleteRatingByAccUserNameAndProductId(String accUserName, String productId) throws NamingException, SQLException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;

        String sql = "delete from Rating "
                + "where accUserName=? and productId=?";

        try {
            if (con != null) {
                //3. Tao doi tuong truy van.
                pstm = con.prepareStatement(sql);
                pstm.setString(1, accUserName.trim());
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
    public boolean updateRating(Rating rating) throws SQLException, NamingException {
        //1, 2
        Connection con = DBConnect.makeConnection();
        PreparedStatement pstm = null;
        String sql = "update Rating set "
                + "numOfStar=?, comment=? "
                + "where accUserName=? and productId=?";
        try {
            if(con!=null){
                //3
                pstm = con.prepareStatement(sql);
                //gan thong so vao PreparedStatment
                pstm.setFloat(1, rating.getNumOfStar());
                pstm.setString(2, rating.getComment().trim());
                
                pstm.setString(3, rating.getAccount().getAccUserName().trim());                
                pstm.setString(4, rating.getProduct().getProductId().trim());
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
