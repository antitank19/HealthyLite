<%-- 
    Document   : adminUsersPage
    Created on : Mar 4, 2021, 9:54:18 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin User Page</title>
        <link type="text/css" rel="stylesheet" href="CSS/all.css">
        <link type="text/css" rel="stylesheet" href="CSS/adminUsersPage.css">
        <script src="https://kit.fontawesome.com/10736bd9b2.js" crossorigin="anonymous"></script>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="JS/adminUsersPage.js" defer></script>
        <style>

            body{
                background-image: url('images/bg.jpg');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }

            header{
                background-image: url('images/bg3.jpg');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
            }   

            footer{
                background-image: url('images/bg3.jpg');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
            }

            .productCard{
                background-color: whitesmoke;  
            }

            footer{
                text-align: center;
                font-size: 1.1rem;
            }

            .footerName{
                font-size: 1.25rem;
                font-weight: bold;
            }

        </style>
    </head>
    <body>
        <c:set var="controllerServlet" value="OnlineShop"></c:set>
        <c:set var="accountList" value="${accountList}"></c:set>
        <c:set var="usersCartListList" value="${usersCartListList}"></c:set>
        <c:set var="usersRatingListList" value="${usersRatingListList}"></c:set>
            <div id="backdrop" class="displayNone" onclick="hideAllPopUp()"></div>
            <header>
                <div class="left-side" >
                    <section class="pageIcon" id="pageIcon">
                        <img style="opacity: 0.5" src="images/ico.png">
                    </section>
                </div>
                <div class="right-side">

                    <section class="shopName" id="shopName">
                        <h1 style="font-size: 3.5rem; float: left">HealthyLite</h1>
                    </section>
                    <section class="greeting" id="greeting">
                        Hello Admin
                    </section>
                    <a href="${controllerServlet}?action=logout">Log out</a>
            </div>
        </header>
        <nav>
            <section class="searchBar" id="searchBar">
                <form action="${controllerServlet}?action=adminSearchUser" method="post">
                    <input type="text" name="searchBar" placeholder="Search for user">
                    <input type="submit" value="Search">
                </form>
            </section>
            <section class="mainNav" id="mainNav">
                <a href="${controllerServlet}?action=showAdminProductsPage" class="mainAdminTab productTab" id="usersTab">Products</a>
                <a href="${controllerServlet}?action=showAdminUsersPage" class="mainAdminTab cartTab" id="usersTab">Users</a>
            </section>
        </nav>
        <main style="display: block; width: auto">
            <h4>${msg}</h4>
            <c:forEach var="a" items="${accountList}" varStatus="loop">
                <section class="accountInfoCard" id="accountInfoCard-${a.accUserName}" onclick="popUpAccountDetail('${a.accUserName}')">
                    <c:set var="action" value="viewAccount" scope="request"></c:set>
                    <c:set var="account" value="${a}" scope="request"></c:set>
                    <jsp:include page="accountForm.jsp"></jsp:include>
                    </section>
                    <section class="accountDetail displayNone popUp" id="accountDetail-${a.accUserName}">

                    <h3>${a.accUserName}'s rating history</h3>
                    <c:if test="${usersRatingListList[loop.index].size()==0}">
                        No rating found yet
                    </c:if>
                    <c:forEach var="rating" items="${usersRatingListList[loop.index]}">
                        <div class="ratingCard">
                            <span class="ratingProductName">${rating.product.productName}</span>
                            <span class="ratingAccountUserName">${rating.account.accUserName}</span><br>
                            <span class="ratingNumOfStar">
                                ${rating.numOfStar} stars
                                <c:set var="roundNumOfStar" value="${5 *(Math.round((rating.numOfStar * 10) / 5)) / 10.0}"></c:set>
                                <c:forEach begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${roundNumOfStar>=1}">
                                            <i class='fas fa-star'></i>
                                        </c:when>
                                        <c:when test="${roundNumOfStar==0.5}">
                                            <i class='fas fa-star-half-alt'></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class='far fa-star'></i>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:set var="roundNumOfStar" value="${roundNumOfStar-1}"></c:set>
                                </c:forEach>           
                            </span><br>
                            <span class="ratingComment">${rating.comment}></span><br>
                        </div>
                    </c:forEach>
                        
                    <h3>${a.accUserName}'s cart history</h3>
                    <c:if test="${usersCartListList[loop.index].size()==0}">
                        No rating found yet
                    </c:if>
                    <c:forEach var="cart" items="${usersCartListList[loop.index]}" varStatus="loop2">
                        <table class="cartCard" id="yourCartCard-${loop2.index}">
                            <thead>
                            <th>Name</th>
                            <th>Price</th><th></th>
                            <th>Number</th>
                            <th>Total</th>
                            </thead>
                            <c:set var="total" value="0"></c:set>
                            <c:forEach var="detail" items="${cart.detailList}" varStatus="loop3">
                                <c:set var="total" value="${total+detail.productPrice*detail.numOfProduct}"></c:set>
                                <tr class="detailCard" id="userDetailCard-${detail.product.productId}">
                                    <td><span class="detailProductname">${detail.product.productName}</span></td>
                                    <td><input type="number" name="detailProductPrice-${loop3.index+1}" value="${detail.productPrice}" readonly></td><td>X</td>
                                    <td><input type="number" name="detailNumOfProduct-${loop3.index+1}" value="${detail.numOfProduct}" min="1" readonly required></td>
                                    <td>=<span class="detailTotal" >${detail.productPrice * detail.numOfProduct}</span></td><td>vnd</td>
                                    <td><input type="hidden" name="detailProductId-${loop3.index+1}" value="${detail.product.productId}"></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td><td></td><td></td>
                                <th style="text-align: right">Total:</th>
                                <th> <span class="cartTotal" id="cartTotal">${total}</span></th><th>vnd</th>
                            </tr>

                            <tr>
                                <td style="text-align: right;">Buy date:</td><td colspan="2"><input type="date" name="buyDate" value="${cart.cartBuyDate}" readonly></td>
                                <td style="text-align: right;">Delivery date:</td><td colspan="2"><input type="date" name="deliveryDate" value="${cart.cartShipDate}" readonly required></td>

                            </tr>
                            <tr>
                                <td style="text-align: right;">Address:</td>
                                <td colspan="5">
                                    <textarea name="cartAddress" class="cartAddress" id="cartAddress" placeholder="Your delivery address" readonly required rows="3" cols="50" >${cart.cartAddress}</textarea>
                                </td>
                            </tr>
                        </table>
                    </c:forEach>
                </section>
            </c:forEach>
        </main>
        <footer valign="bottom">
            <section class="footerName">
                HealthyLite-Assignment PRJ SE1502
            </section>
            <section class="pageIcon" id="pageIcon">
                Trần Khải Minh Khôi-SE150850
            </section>
            <section class="shopName" id="shopName">
                Thủy Võ Anh Hoàng-SE150780
            </section>
        </footer>
    </body>
</html>
