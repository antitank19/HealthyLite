<%-- 
    Document   : mainPage
    Created on : Mar 2, 2021, 9:50:43 AM
    Author     : DELL
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page-Product List</title>
        <link type="text/css" rel="stylesheet" href="CSS/all.css">
        <link type="text/css" rel="stylesheet" href="CSS/userMainPage.css">
        <script src="https://kit.fontawesome.com/10736bd9b2.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="JS/userMainPage.js" defer></script>
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

            main{
                background-color: rgba(180, 180, 180, 0.9);
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
    <body onload="resizeAside()" onresize="resizeAside()">
        <c:set var="controllerServlet" value="OnlineShop"></c:set>
        <c:set var="a" value="${account}"></c:set>
        <c:set var="userRatingList" value="${userRatingList}"></c:set>
        <c:set var="yourBoughtProductIdList" value="${yourBoughtProductIdList}"></c:set>
        <c:set var="bestRatedProductList" value="${bestRatedProductList}"></c:set>
        <c:set var="bestRatedProductNumOfCustomer" value="${bestRatedProductNumOfCustomer}"></c:set>
        <c:set var="bestRatedProductRatingList" value="${bestRatedProductRatingList}"></c:set>
            <div id="backdrop" class="displayNone" onclick="hideAllPopUp"></div>
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
                        Hello ${a.accUserName}
                </section>
                <a href="${controllerServlet}?action=logout">Log out</a>
            </div> 
        </header>

        <nav>
            <section class="searchBar" id="searchBar">
                <form action="${controllerServlet}?action=userSearchProduct" method="post">
                    <input type="search" name="searchBar" placeholder="Search for product">
                    <input type="submit" value="Search">
                </form>
            </section>
            <section class="mainNav" id="mainNav">
                <a href="${controllerServlet}?action=showUser" class="mainTab cartTab active" id="productTab">Product</a>
                <a href="${controllerServlet}?action=showUserCart" class="mainTab cartTab" id="cartTab">Cart</a>
                <a href="${controllerServlet}?action=showUserAccount" class="mainTab accountTab" id="accountTab">Account</a>
            </section>

        </nav>
        <aside class="adBar1">
                Ads
        </aside>
        <main>
            <h4>${msg}</h4>
            <section class="mainPart" id="productPage">
                <c:forEach var="product" items="${bestRatedProductList}" varStatus="loop">
                    <section class="productCard" id="productCard-${product.productId}">
                        <section class="buyButton" onclick="addToCart('${product.productId}', '${product.productName}', ${product.productPrice})">
                            <a target="_blank" href="${controllerServlet}?action=buyProduct&productId=${product.productId}" onclick="event.stopImmediatePropagation();"><i class='fas fa-cart-plus'></i></a>
                        </section>
                        <section class="productName">${product.productName}</section>
                        <section class="productPrice">${product.productPrice} vnd</section>                        
                        <section class="productRating">
                            ${product.avgNumOfStar} stars
                            <c:set var="roundNumOfStar" value="${5 *(Math.round((product.avgNumOfStar * 10) / 5)) / 10.0}"></c:set>
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
                        </section>
                        <section class="productDescription">${product.productDescription}</section>
                        <a href="${controllerServet}?action=showUserProductInfo&productId=${product.productId}">Show product's detail</a>
                    </section>
                </c:forEach>
            </section>
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
