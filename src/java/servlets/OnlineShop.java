/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAOs.AccountDAO;
import DAOs.CartDAO;
import DAOs.CartDetailDAO;
import DAOs.ProductDAO;
import DAOs.RatingDAO;
import DTOs.Account;
import DTOs.Cart;
import DTOs.CartDetail;
import DTOs.Product;
import DTOs.Rating;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class OnlineShop extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String controllerServlet = "OnlineShop";
        String userMainPage = "userMainPage.jsp";
        String userProductInfoPage = "userProductInfoPage.jsp";
        String userAccountPage = "userAccountPage.jsp";
        String userCartPage = "userCartPage.jsp";
        String loginPage = "login.jsp";
        String accountForm = "accountForm.jsp";
        String createUserAccount = "createUserAccount.jsp";
        String adminUsersPage = "adminUsersPage.jsp";
        String adminProductsPage = "adminProductsPage.jsp";
        String adminProductInfoPage = "adminProductInfoPage.jsp";

        ServletContext sContext = getServletContext();
        String adminUsername = sContext.getInitParameter("adminUsername");
        String adminPassword = sContext.getInitParameter("adminPassword");

        String action = request.getParameter("action");
        System.err.println(action);
//        System.err.println(request.getHeader(HttpHeaders.ORIGIN));
        try {

            if (action == null || action.equals("")) {//
                request.setAttribute("msg", "Welcome to shop name");
                RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                rd.forward(request, response);
            } else {
                switch (action) {
                    //attempt to login
                    case "login": {
                        String accUserName = request.getParameter("accUserName");
                        String accPassWord = request.getParameter("accPassword");
                        if (accUserName.equals(adminUsername) && accPassWord.equals(adminPassword)) {
                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showAdminProductsPage");
                            HttpSession session = request.getSession();
                            session.setAttribute("isAdmin", true);
                            rd.forward(request, response);
                        } else {
                            AccountDAO aDAO = new AccountDAO();
                            Account a = aDAO.getAccountByAccUserName(accUserName);
                            if (a == null) {
                                //no username
                                request.setAttribute("msg", "The username you entered doesn't match any of the accounts");
                                RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                                rd.forward(request, response);
                            }
                            if (!a.getAccPassword().equals(accPassWord)) {
                                //wrong password
                                request.setAttribute("msg", "The password you have entered is incorrect");
                                RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                                rd.forward(request, response);
                            } else {
                                //correct username and password
                                HttpSession session = request.getSession();
                                session.setAttribute("account", a);
                                System.err.println(session.getAttribute("account") == null);
                                RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showUserMainPage");
                                rd.forward(request, response);
                            }
                        }
                        break;
                    }
                    //Go to createUserAccount
                    case "showCreateUserAccount": {//
                        request.setAttribute("action", action);
                        request.setAttribute("msg", "Create your new account");
                        Account a = new Account();
                        request.setAttribute("account", a);
                        RequestDispatcher rd = request.getRequestDispatcher(createUserAccount);
                        rd.forward(request, response);
                        break;
                    }
                    //Create new account
                    case "createAccount": {//
                        String accFullName = request.getParameter("accFullName");
                        String accUserName = request.getParameter("accUserName");
                        String accPassword = request.getParameter("accPassword");
                        String accAddress = request.getParameter("accAddress");
                        String accGender = request.getParameter("accGender");
                        String accPhone = request.getParameter("accPhone");
                        Date accDob = Date.valueOf(request.getParameter("accDob"));
                        if (accUserName.equals("admin")) {
                            request.setAttribute("action", "showCreateUserAccount");
                            request.setAttribute("msg", "Username is taken. Please choose another username");
                            Account a = new Account();
                            request.setAttribute("account", a);
                            RequestDispatcher rd = request.getRequestDispatcher(createUserAccount);
                            rd.forward(request, response);
                        } else {
                            Account newacc = new Account(accUserName, accPassword, accFullName, accDob, accAddress, accPhone, accGender);
                            AccountDAO aDAO = new AccountDAO();
                            boolean succcess = aDAO.createAccount(newacc);
                            if (succcess) {
                                request.setAttribute("msg", "Create account successfully. Now you can login and begin shopping");
                                RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                                rd.forward(request, response);
                            } else {
                                request.setAttribute("action", "showCreateUserAccount");
                                request.setAttribute("msg", "Username is taken. Please choose another username");
                                Account a = new Account();
                                request.setAttribute("account", a);
                                RequestDispatcher rd = request.getRequestDispatcher(createUserAccount);
                                rd.forward(request, response);
                            }
                        }
                        break;
                    }
                    //go to different page
                    case "showUserMainPage": {//
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        }
                        CartDAO cDAO = new CartDAO();
                        RatingDAO rDAO = new RatingDAO();
                        CartDetailDAO dDAO = new CartDetailDAO();
                        Account a = (Account) session.getAttribute("account");
                        ProductDAO pDAO = new ProductDAO();
                        ArrayList<Product> bestRatedProductList = pDAO.getNumOfBestRatedProduct(15);
                        request.setAttribute("bestRatedProductList", bestRatedProductList);

                        ArrayList<Rating> userRatingList = rDAO.getAllRatingByAccount(a);
                        request.setAttribute("userRatingList", userRatingList);
                        ArrayList<Cart> userCartList = cDAO.getAllCartByAccount(a);
                        ArrayList<String> yourBoughtProductIdList = new ArrayList<String>();
                        for (Cart cart : userCartList) {
                            for (CartDetail cartDetail : cart.getDetailList()) {
                                if (!yourBoughtProductIdList.contains(cartDetail.getProduct().getProductId())) {
                                    yourBoughtProductIdList.add(cartDetail.getProduct().getProductId());
                                }
                            }
                        }
                        request.setAttribute("yourBoughtProductIdList", yourBoughtProductIdList);
                        RequestDispatcher rd = request.getRequestDispatcher(userMainPage);
                        rd.forward(request, response);
                        break;
                    }
                    case "showUserProductInfo": {//
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else if (request.getParameter("productId") == null) {
                            request.setAttribute("msg", "Product doesn't exist");
                            request.getRequestDispatcher(userMainPage);
                        } else {
                            ProductDAO pDAO = new ProductDAO();
                            Product product = pDAO.getProductById(request.getParameter("productId"));
                            if (product == null) {
                                request.setAttribute("msg", "Product doesn't exist");
                                request.getRequestDispatcher(userMainPage);
                            } else {
                                Account a = (Account) session.getAttribute("account");
                                RatingDAO rDAO = new RatingDAO();
                                CartDAO cDAO=new CartDAO();
                                CartDetailDAO dDAO = new CartDetailDAO();

                                ArrayList<Rating> userRatingList = rDAO.getAllRatingByAccount(a);
                                request.setAttribute("userRatingList", userRatingList);

                                ArrayList<Cart> userCartList=cDAO.getAllCartByAccount(a);
                                ArrayList<String> yourBoughtProductIdList = new ArrayList<String>();
                                for (Cart cart : userCartList) {
                                    for (CartDetail cartDetail : cart.getDetailList()) {
                                        if (!yourBoughtProductIdList.contains(cartDetail.getProduct().getProductId())) {
                                            yourBoughtProductIdList.add(cartDetail.getProduct().getProductId());
                                        }
                                    }
                                }
                                request.setAttribute("yourBoughtProductIdList", yourBoughtProductIdList);

                                ArrayList<Rating> ratingList = rDAO.getAllRatingByProduct(product);
                                ArrayList<String> customerList = new ArrayList<String>();
                                ArrayList<CartDetail> detailList = dDAO.getAllDetailByProduct(product);
                                for (CartDetail cartDetail : detailList) {
                                    if (!customerList.contains(cartDetail.getCart().getAccount().getAccUserName())) {
                                        customerList.add(cartDetail.getCart().getAccount().getAccUserName());
                                    }
                                }
                                int numOfCustomer = customerList.size();

                                request.setAttribute("product", product);
                                request.setAttribute("ratingList", ratingList);
                                request.setAttribute("numOfCustomer", numOfCustomer);
                                request.getRequestDispatcher(userProductInfoPage).forward(request, response);
                            }
                        }
                        break;
                    }
                    case "showUserAccount": {//
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        }
                        Account a = (Account) session.getAttribute("account");
                        request.setAttribute("account", a);
                        CartDAO cDAO = new CartDAO();
                        ArrayList<Cart> userCartList = cDAO.getAllCartByAccount(a);
                        request.setAttribute("userCartList", userCartList);
                        RatingDAO rDAO = new RatingDAO();
                        ArrayList<Rating> userRatingList = rDAO.getAllRatingByAccount(a);
                        request.setAttribute("userRatingList", userRatingList);

                        RequestDispatcher rd = request.getRequestDispatcher(userAccountPage);
                        rd.forward(request, response);
                        break;
                    }
                    case "showUserCart": {
                        HttpSession session = request.getSession();
                        ProductDAO pDAO = new ProductDAO();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            Cart newCart = new Cart();
                            newCart.setDetailList(new ArrayList<CartDetail>());
                            if (session.getAttribute("cart") != null) {
                                newCart = (Cart) session.getAttribute("cart");
                            }
                            newCart.setCartBuyDate(new java.sql.Date(System.currentTimeMillis()));
                            newCart.setCartShipDate(new java.sql.Date(System.currentTimeMillis() + 86400000 * 3));
                            session.setAttribute("cart", newCart);
                            ArrayList<Product> bestRatedProductList = pDAO.getNumOfBestRatedProduct(15);
                            request.setAttribute("bestRatedProductList", bestRatedProductList);
                            RequestDispatcher rd = request.getRequestDispatcher(userCartPage);
                            rd.forward(request, response);
                        }
                        break;
                    }
                    case "showAdminUsersPage": {
                        HttpSession session = request.getSession();
                        if (!(session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin"))) {
                            request.setAttribute("msg", "You need to login as an admin first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            AccountDAO aDAO = new AccountDAO();
                            CartDAO cDAO = new CartDAO();
                            RatingDAO rDAO = new RatingDAO();
                            ArrayList<Account> accountList = aDAO.getAllAccount();
                            ArrayList<ArrayList<Cart>> usersCartListList = new ArrayList<ArrayList<Cart>>();
                            ArrayList<ArrayList<Rating>> usersRatingListList = new ArrayList<ArrayList<Rating>>();

                            for (Account a : accountList) {
                                usersCartListList.add(new ArrayList<Cart>());
                                usersRatingListList.add(new ArrayList<Rating>());
                            }
                            for (Cart cart : cDAO.getAllCart()) {
                                int pos = accountList.indexOf(cart.getAccount());
                                System.err.println(pos);
                                if (pos >= 0) {
                                    usersCartListList.get(pos).add(cart);
                                }
                            }
                            for (Rating rating : rDAO.getAllRating()) {
                                int pos = accountList.indexOf(rating.getAccount());
                                System.err.println(pos);
                                if (pos >= 0) {
                                    usersRatingListList.get(pos).add(rating);
                                }
                            }
                            request.setAttribute("accountList", accountList);
                            request.setAttribute("usersCartListList", usersCartListList);
                            request.setAttribute("usersRatingListList", usersRatingListList);
                            RequestDispatcher rd = request.getRequestDispatcher(adminUsersPage);
                            rd.forward(request, response);
                        }
                        break;
                    }

                    case "showAdminProductsPage": {//
                        HttpSession session = request.getSession();;
                        if (!(session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin"))) {
                            request.setAttribute("msg", "You need to login as an admin first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            ProductDAO pDAO = new ProductDAO();
                            ArrayList<Product> allProductList = pDAO.getAllProduct();
                            request.setAttribute("allProductList", allProductList);
                            RatingDAO rDAO = new RatingDAO();
                            ArrayList<Rating> allRatingList = rDAO.getAllRating();
                            request.setAttribute("allRatingList", allRatingList);
                            

                            RequestDispatcher rd = request.getRequestDispatcher(adminProductsPage);
                            rd.forward(request, response);
                        }
                        break;
                    }
                    case "showAdminProductInfo": {
                        HttpSession session = request.getSession();
                        System.err.println("ok here");
                        if (!(session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin"))) {
                            request.setAttribute("msg", "You need to login as an admin first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else if (request.getParameter("productId") == null) {
                            request.setAttribute("msg", "Product doesn't exist");
                            request.getRequestDispatcher(userMainPage);
                        } else {
                            ProductDAO pDAO = new ProductDAO();
                            Product product = pDAO.getProductById(request.getParameter("productId"));
                            if (product == null) {
                                request.setAttribute("msg", "Product doesn't exist");
                                request.getRequestDispatcher(userMainPage);
                            } else {
                                RatingDAO rDAO = new RatingDAO();
                                CartDAO cDAO=new CartDAO();
                                CartDetailDAO dDAO = new CartDetailDAO();

                                ArrayList<Rating> ratingList = rDAO.getAllRatingByProduct(product);
                                ArrayList<String> customerList = new ArrayList<String>();
                                ArrayList<CartDetail> detailList = dDAO.getAllDetailByProduct(product);
                                for (CartDetail cartDetail : detailList) {
                                    if (!customerList.contains(cartDetail.getCart().getAccount().getAccUserName())) {
                                        customerList.add(cartDetail.getCart().getAccount().getAccUserName());
                                    }
                                }
                                int numOfCustomer = customerList.size();
                                request.setAttribute("product", product);
                                request.setAttribute("ratingList", ratingList);
                                request.setAttribute("numOfCustomer", numOfCustomer);
                                request.setAttribute("detailList", detailList);
                                request.getRequestDispatcher(adminProductInfoPage).forward(request, response);
                            }
                        }
                        break;
                    }
                    //user info things
                    //update user info
                    case "updateAccount": {
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            AccountDAO aDAO = new AccountDAO();
                            String accFullName = request.getParameter("accFullName");
                            String accUserName = request.getParameter("accUserName");
                            String accPassword = aDAO.getAccountByAccUserName(accUserName).getAccPassword();
                            if (!(request.getParameter("changePassword") == null && request.getParameter("changePassword").equalsIgnoreCase("Yes"))) {
                                accPassword = request.getParameter("accPassword");
                            }
                            String accAddress = request.getParameter("accAddress");
                            String accGender = request.getParameter("accGender");
                            String accPhone = request.getParameter("accPhone");
                            Date accDob = Date.valueOf(request.getParameter("accDob"));
                            Account newacc = new Account(accUserName, accPassword, accFullName, accDob, accAddress, accPhone, accGender);
                            
                            if(aDAO.updateAccount(newacc))
                                request.setAttribute("msg", "Update successfully");

                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showUserAccount");
                            rd.forward(request, response);
                        }
                        break;
                    }
                    //cart things
                    //user odered, create new cart 
                    case "createCart": {
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            Account a = (Account)session.getAttribute("account");
                            CartDAO cDAO = new CartDAO();
                            Cart lastCart = cDAO.getLatestCartByAccount(a);

                            String cartId;
                            if (lastCart!=null)
                                cartId = a.getAccUserName() + "-" + (String.format("%06d", Integer.parseInt(lastCart.getCartId().split("-")[1]) + 1));
                            else
                                cartId=a.getAccUserName() + "-000001";
                            System.err.println("new cart Id " + cartId);

                            java.sql.Date buyDate = Date.valueOf(request.getParameter("buyDate"));
                            java.sql.Date deliveryDate = Date.valueOf(request.getParameter("deliveryDate"));
                            System.err.println("buy date " + buyDate + " de day " + deliveryDate);

                            String cartAddress = request.getParameter("cartAddress");
                            System.err.println("cart add " + cartAddress);
                            Cart newCart = new Cart(cartId, buyDate, deliveryDate, a, cartAddress, new ArrayList<>());
                            if(cDAO.createCart(newCart))
                                request.setAttribute("msg", "Order successfully");

                            int numOfDetai = 0;
                            CartDetailDAO dDAO = new CartDetailDAO();
                            ProductDAO pDAO = new ProductDAO();
                            while (request.getParameter("detailProductId-" + (numOfDetai + 1)) != null) {
                                numOfDetai++;
                                String detailProductId = request.getParameter("detailProductId-" + numOfDetai);
                                Product p = pDAO.getProductById(detailProductId);
                                long detailProductPrice = Long.parseLong(request.getParameter("detailProductPrice-" + numOfDetai));
                                int detailNumOfProduct = Integer.parseInt(request.getParameter("detailProductNum-" + numOfDetai));
                                CartDetail newDetail = new CartDetail(newCart, p, detailNumOfProduct, detailProductPrice);
                                newCart.getDetailList().add(newDetail);
                                System.err.println("detail " + newDetail);
                                dDAO.createDetail(newDetail);
                            }
                            session.setAttribute("cart", new Cart());
                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showUserMainPage");
                            rd.forward(request, response);
                        }
                        break;
                    }
                    case "buyProduct": {
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            ProductDAO pDAO = new ProductDAO();

                            String productId = request.getParameter("productId");
                            Product newPro = pDAO.getProductById(productId);
                            
                            PrintWriter out = response.getWriter();
                            out.print("<body OnLoad=\"javascript:parent.window.close()\"></body>");

                            if (session.getAttribute("cart") == null) {
                                Cart newCart = new Cart();
                                newCart.setDetailList(new ArrayList<CartDetail>());
                                newCart.getDetailList().add(new CartDetail(newCart, newPro, 1, newPro.getProductPrice()));
                                session.setAttribute("cart", newCart);
                            } else {
                                Cart newCart = (Cart) session.getAttribute("cart");
                                int index = -1;
                                for (int i = 0; i < newCart.getDetailList().size(); i++) {
                                    if (newCart.getDetailList().get(i).getProduct().getProductId().equals(productId)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index == -1) {
                                    newCart.getDetailList().add(new CartDetail(newCart, newPro, 1, newPro.getProductPrice()));
                                } else {
                                    int quanity = newCart.getDetailList().get(index).getNumOfProduct();
                                    newCart.getDetailList().get(index).setNumOfProduct(quanity + 1);
                                }
                                session.setAttribute("cart", newCart);
                            }
                            
                            //response.sendRedirect(controllerServlet + "?action=showUserCart");
                        }
                        break;
                    }
                    case "buyProductFromCart": {
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            ProductDAO pDAO = new ProductDAO();

                            String productId = request.getParameter("productId");
                            Product newPro = pDAO.getProductById(productId);

                            if (session.getAttribute("cart") == null) {
                                Cart newCart = new Cart();
                                newCart.setDetailList(new ArrayList<CartDetail>());
                                newCart.getDetailList().add(new CartDetail(newCart, newPro, 1, newPro.getProductPrice()));
                                session.setAttribute("cart", newCart);
                            } else {
                                Cart newCart = (Cart) session.getAttribute("cart");
                                int index = -1;
                                for (int i = 0; i < newCart.getDetailList().size(); i++) {
                                    if (newCart.getDetailList().get(i).getProduct().getProductId().equals(productId)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index == -1) {
                                    newCart.getDetailList().add(new CartDetail(newCart, newPro, 1, newPro.getProductPrice()));
                                } else {
                                    int quanity = newCart.getDetailList().get(index).getNumOfProduct();
                                    newCart.getDetailList().get(index).setNumOfProduct(quanity + 1);
                                }
                                session.setAttribute("cart", newCart);
                            }
                            response.sendRedirect(controllerServlet + "?action=showUserCart");
                        }
                        break;
                    }
                    case "removeProduct": {
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            Cart newCart = (Cart) session.getAttribute("cart");
                            String productId = request.getParameter("productId");
                            int index = -1;
                            for (int i = 0; i < newCart.getDetailList().size(); i++) {
                                if (newCart.getDetailList().get(i).getProduct().getProductId().equals(productId)) {
                                    index = i;
                                    break;
                                }
                            }
                            if (index >= 0) {
                                newCart.getDetailList().remove(index);
                            }
                            response.sendRedirect(controllerServlet + "?action=showUserCart");
                        }
                        break;
                    }
                    case "updateQuantity": {
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            Cart newCart = (Cart) session.getAttribute("cart");
                            String productId = request.getParameter("productId");
                            int quantity = Integer.parseInt(request.getParameter("quantity"));
                            int index = -1;
                            for (int i = 0; i < newCart.getDetailList().size(); i++) {
                                if (newCart.getDetailList().get(i).getProduct().getProductId().equals(productId)) {
                                    index = i;
                                    break;
                                }
                            }
                            if (index >= 0) {
                                newCart.getDetailList().get(index).setNumOfProduct(quantity);
                            }
                            response.sendRedirect(controllerServlet + "?action=showUserCart");
                        }
                        break;
                    }
                    //rating things
                    //create new rating
                    case "createRating": {
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            Account a =(Account)session.getAttribute("account");

                            String productId = request.getParameter("ratingProductId");
                            ProductDAO pDAO = new ProductDAO();
                            Product p = pDAO.getProductById(productId);

                            String ratingComment = request.getParameter("ratingComment");
                            int ratingNumOfStar = Integer.parseInt(request.getParameter("ratingNumOfStar"));

                            Rating newRating = new Rating(a, p, ratingNumOfStar, ratingComment);
                            System.err.println(newRating);

                            RatingDAO rDAO = new RatingDAO();
                            if(rDAO.createRating(newRating))
                                request.setAttribute("msg", "Rate successfully");

                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showUserMainPage");
                            rd.forward(request, response);
                        }
                        break;
                    }
                    //update rating
                    case "updateRating": {
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            System.err.println("nice");
                            String accUserName = request.getParameter("accUserName");
                            AccountDAO aDAO = new AccountDAO();
                            Account a = aDAO.getAccountByAccUserName(accUserName);

                            String productId = request.getParameter("ratingProductId");
                            ProductDAO pDAO = new ProductDAO();
                            Product p = pDAO.getProductById(productId);

                            String ratingComment = request.getParameter("ratingComment");
                            int ratingNumOfStar = Integer.parseInt(request.getParameter("ratingNumOfStar"));

                            Rating newRating = new Rating(a, p, ratingNumOfStar, ratingComment);
                            System.err.println(newRating);

                            RatingDAO rDAO = new RatingDAO();
                            if(rDAO.updateRating(newRating))
                                request.setAttribute("msg", "Update your rating successfully");

                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=login&accUserName=" + accUserName + "&accPassword=" + a.getAccPassword());
                            rd.forward(request, response);
                        }
                        break;
                    }
                    //log out
                    case "logout": {
                        HttpSession session = request.getSession();
                        session.invalidate();
                        request.setAttribute("msg", "You logged out");
                        RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=");
                        rd.forward(request, response);
                        break;
                    }
                    //admin stuff
                    case "adminAddProduct": {
                        HttpSession session = request.getSession();
                        if (!(session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin"))) {
                            request.setAttribute("msg", "You need to login as an admin first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            ProductDAO pDAO = new ProductDAO();
                            Product latestProduct = pDAO.getLatestProduct();
                            String newProdductId = String.format("%07d", Integer.parseInt(latestProduct.getProductId()) + 1);
                            String newProductName = request.getParameter("productName");
                            long newProductPrice = Long.parseLong(request.getParameter("productPrice"));
                            String newProductDescription = request.getParameter("productDescription");
                            Product newProduct = new Product(newProdductId, newProductName, newProductDescription, newProductPrice, 5);

                            if(pDAO.createProduct(newProduct))
                                request.setAttribute("msg", "Add successfully");
                            request.setAttribute("msg", "Add unsuccessfully");                            

                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showAdminProductsPage");
                            rd.forward(request, response);
                        }
                        break;
                    }
                    case "adminUpdateProduct": {
                        HttpSession session = request.getSession();
                        if (!(session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin"))) {
                            request.setAttribute("msg", "You need to login as an admin first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else {
                            ProductDAO pDAO = new ProductDAO();
                            String newProdductId = request.getParameter("productId");
                            String newProductName = request.getParameter("productName");
                            long newProductPrice = Long.parseLong(request.getParameter("productPrice"));
                            String newProductDescription = request.getParameter("productDescription");
                            Product newProduct = new Product(newProdductId, newProductName, newProductDescription, newProductPrice, 5);

                            if(pDAO.updateProduct(newProduct))
                                request.setAttribute("msg", "Update successfully");

                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showAdminProductsPage");
                            rd.forward(request, response);
                        }
                        break;
                    }
                    case "userSearchProduct": {
                        System.err.println("here");
                        HttpSession session = request.getSession();
                        if (session.getAttribute("account") == null) {
                            System.err.println("empty account");
                            request.setAttribute("msg", "You need to login first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else if (request.getParameter("searchBar") == null) {
                            System.err.println("Empty searchBar");
                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showUserMainPage");
                            rd.forward(request, response);
                        } else {
                            String searchString = request.getParameter("searchBar");
                            CartDAO cDAO = new CartDAO();
                            RatingDAO rDAO = new RatingDAO();
                            CartDetailDAO dDAO = new CartDetailDAO();
                            Account a = (Account) session.getAttribute("account");
                            ProductDAO pDAO = new ProductDAO();
                            ArrayList<Product> allProducts = pDAO.getAllProduct();
                            ArrayList<Product> searchProduct = new ArrayList<Product>();
                            for (Product product : allProducts) {
                                for (String string : searchString.split(" ")) {
                                    if (!string.trim().isEmpty()) {
                                        if (product.getProductId().equalsIgnoreCase(string) || product.getProductName().toLowerCase().contains(string.toLowerCase()) || product.getProductDescription().toLowerCase().contains(string.toLowerCase())) {
                                            searchProduct.add(product);
                                        }
                                    }
                                }
                            }

                            ArrayList<ArrayList<Rating>> bestRatedProductRatingList = new ArrayList<ArrayList<Rating>>();
                            ArrayList<ArrayList<CartDetail>> bestRatedProductCartDetailList = new ArrayList<ArrayList<CartDetail>>();
                            ArrayList bestRatedProductNumOfCustomer = new ArrayList();
                            for (Product product : searchProduct) {
                                bestRatedProductRatingList.add(rDAO.getAllRatingByProduct(product));
                                ArrayList<String> customerList = new ArrayList<String>();
                                ArrayList<CartDetail> detailList = dDAO.getAllDetailByProduct(product);
                                bestRatedProductCartDetailList.add(detailList);
                                for (CartDetail cartDetail : detailList) {
                                    if (!customerList.contains(cartDetail.getCart().getAccount().getAccUserName())) {
                                        customerList.add(cartDetail.getCart().getAccount().getAccUserName());
                                    }
                                }
                                bestRatedProductNumOfCustomer.add(customerList.size());
                            }
                            request.setAttribute("msg", "Found "+searchProduct.size()+" products");
                            request.setAttribute("bestRatedProductList", searchProduct);
                            request.setAttribute("bestRatedProductRatingList", bestRatedProductRatingList);
                            request.setAttribute("bestRatedProductCartDetailList", bestRatedProductCartDetailList);
                            request.setAttribute("bestRatedProductNumOfCustomer", bestRatedProductNumOfCustomer);

                            ArrayList<Rating> userRatingList = rDAO.getAllRatingByAccount(a);
                            request.setAttribute("userRatingList", userRatingList);
                            ArrayList<Cart> userCartList = cDAO.getAllCartByAccount(a);
                            ArrayList<String> yourBoughtProductIdList = new ArrayList<String>();
                            for (Cart cart : userCartList) {
                                for (CartDetail cartDetail : cart.getDetailList()) {
                                    if (!yourBoughtProductIdList.contains(cartDetail.getProduct().getProductId())) {
                                        yourBoughtProductIdList.add(cartDetail.getProduct().getProductId());
                                    }
                                }
                            }
                            request.setAttribute("yourBoughtProductIdList", yourBoughtProductIdList);
                            RequestDispatcher rd = request.getRequestDispatcher(userMainPage);
                            rd.forward(request, response);
                        }
                        break;
                    }
                    case "adminSearchProduct": {
                        System.err.println("here");
                        HttpSession session = request.getSession();
                        if (!(session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin"))) {
                            request.setAttribute("msg", "You need to login as an admin first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            rd.forward(request, response);
                        } else if (request.getParameter("searchBar") == null) {
                            System.err.println("Empty searchBar");
                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showAdminProductsPage");
                            rd.forward(request, response);
                        } else {
                            String searchString = request.getParameter("searchBar");
                            ProductDAO pDAO = new ProductDAO();
                            CartDetailDAO dDAO = new CartDetailDAO();
                            ArrayList<Product> allProductList = pDAO.getAllProduct();
                            ArrayList<Product> searchProduct = new ArrayList<Product>();
                            for (Product product : allProductList) {
                                for (String string : searchString.split(" ")) {
                                    if (!string.trim().isEmpty()) {
                                        if (product.getProductId().equalsIgnoreCase(string) || product.getProductName().toLowerCase().contains(string.toLowerCase()) || product.getProductDescription().toLowerCase().contains(string.toLowerCase())) {
                                            searchProduct.add(product);
                                        }
                                    }
                                }
                            }
                            RatingDAO rDAO = new RatingDAO();
                            ArrayList<Rating> allRatingList = rDAO.getAllRating();
                            request.setAttribute("allRatingList", allRatingList);
                            ArrayList<ArrayList<Rating>> productRatingList = new ArrayList<ArrayList<Rating>>();
                            ArrayList productNumOfCustomer = new ArrayList();
                            ArrayList<ArrayList<CartDetail>> productCartDetailList = new ArrayList<ArrayList<CartDetail>>();
                            for (Product product : searchProduct) {
                                productRatingList.add(new ArrayList<Rating>());
                                productCartDetailList.add(new ArrayList<CartDetail>());
                            }
                            for (Rating rating : rDAO.getAllRating()) {
                                int pos = searchProduct.indexOf(rating.getProduct());
                                System.err.println(pos);
                                if (pos >= 0) {
                                    productRatingList.get(pos).add(rating);
                                }
                            }
                            for (CartDetail detail : dDAO.getAllDetail()) {
                                int pos = searchProduct.indexOf(detail.getProduct());
                                System.err.println(pos);
                                if (pos >= 0) {
                                    productCartDetailList.get(pos).add(detail);
                                }
                            }
                            for (ArrayList<CartDetail> arrayList : productCartDetailList) {
                                ArrayList<String> customerList = new ArrayList<String>();
                                for (CartDetail cartDetail : arrayList) {
                                    if (!customerList.contains(cartDetail.getCart().getAccount().getAccUserName())) {
                                        customerList.add(cartDetail.getCart().getAccount().getAccUserName());
                                    }
                                }
                                productNumOfCustomer.add(customerList.size());
                            }
                            request.setAttribute("msg", "Found "+searchProduct.size()+" products");
                            request.setAttribute("allProductList", searchProduct);
                            request.setAttribute("productRatingList", productRatingList);
                            request.setAttribute("productNumOfCustomer", productNumOfCustomer);
                            request.setAttribute("productCartDetailList", productCartDetailList);

                            RequestDispatcher rd = request.getRequestDispatcher(adminProductsPage);
                            rd.forward(request, response);
                        }
                        break;
                    }
                    case "adminSearchUser": {
                        System.err.println("here");
                        HttpSession session = request.getSession();
                        if (!(session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin"))) {
                            request.setAttribute("msg", "You need to login as an admin first");
                            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                            System.err.println("empty sesion");
                            rd.forward(request, response);
                        } else if (request.getParameter("searchBar") == null) {
                            System.err.println("Empty searchBar");
                            RequestDispatcher rd = request.getRequestDispatcher(controllerServlet + "?action=showAdminUsersPage");
                            rd.forward(request, response);
                        } else {
                            System.err.println("enter");
                            String searchString = request.getParameter("searchBar");
                            AccountDAO aDAO = new AccountDAO();
                            CartDAO cDAO = new CartDAO();
                            RatingDAO rDAO = new RatingDAO();
                            ArrayList<Account> accountList = aDAO.getAllAccount();
                            ArrayList<ArrayList<Cart>> usersCartListList = new ArrayList<ArrayList<Cart>>();
                            ArrayList<ArrayList<Rating>> usersRatingListList = new ArrayList<ArrayList<Rating>>();
                            ArrayList<Account> searchAccountList = new ArrayList<Account>();
                            for (Account account : accountList) {
                                for (String string : searchString.split(" ")) {
                                    if (!string.trim().isEmpty()) {
                                        if (account.getAccUserName().equalsIgnoreCase(string) || account.getAccFullName().toLowerCase().contains(string.toLowerCase())) {
                                            searchAccountList.add(account);
                                        }
                                    }
                                }
                            }
                            for (Account a : searchAccountList) {
                                usersCartListList.add(new ArrayList<Cart>());
                                usersRatingListList.add(new ArrayList<Rating>());
                            }
                            for (Cart cart : cDAO.getAllCart()) {
                                int pos = searchAccountList.indexOf(cart.getAccount());
                                System.err.println(pos);
                                if (pos >= 0) {
                                    usersCartListList.get(pos).add(cart);
                                }
                            }
                            for (Rating rating : rDAO.getAllRating()) {
                                int pos = searchAccountList.indexOf(rating.getAccount());
                                System.err.println(pos);
                                if (pos >= 0) {
                                    usersRatingListList.get(pos).add(rating);
                                }
                            }
                            request.setAttribute("msg", "Found "+searchAccountList.size()+" user");
                            request.setAttribute("accountList", searchAccountList);
                            request.setAttribute("usersCartListList", usersCartListList);
                            request.setAttribute("usersRatingListList", usersRatingListList);
                            RequestDispatcher rd = request.getRequestDispatcher(adminUsersPage);
                            rd.forward(request, response);
                        }
                        break;
                    }
                }
                //search fuction
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
