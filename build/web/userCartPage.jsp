f<%-- 
    Document   : userCart
    Created on : Mar 2, 2021, 1:02:05 PM
    Author     : DELL
--%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
        <link type="text/css" rel="stylesheet" href="CSS/userMainPage.css">
        <link type="text/css" rel="stylesheet" href="CSS/all.css">
        <script src="JS/userCartPage.js" defer></script>
        <script src="https://kit.fontawesome.com/10736bd9b2.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
    <body onload="resizeAside()">
        <c:set var="controllerServlet" value="OnlineShop"></c:set>
        <c:set var="a" value="${account}"></c:set>
        <c:set var="newCart" value="${cart}"></c:set>
        <c:set var="bestRatedProductList" value="${bestRatedProductList}"></c:set>
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
                <a href="${controllerServlet}?action=showUserMainPage" class="mainTab productTab" id="productTab">Product</a>
                <span class="mainTab cartTab active" id="accountTab">Cart</span>
                <a href="${controllerServlet}?action=showUserAccount" class="mainTab accountTab" id="accountTab">Account</a>
            </section>
        </nav>
        <aside class="adBar1">AD</aside>
        <main>
            <h4>${msg}</h4>
            <form action="${controllerServlet}?action=createCart&accUserName=${a.accUserName}" method="POST" id="cartForm">
                <table class="cartCard" id="newCartCard">
                    <thead>
                    <th>Name</th>
                    <th>Price</th><th></th>
                    <th>Quantity</th><th></th>
                    <th>Total</th><th></th>
                    </thead>
                    <c:choose>
                        <c:when test="${!(!(empty newCart)&& newCart.detailList.size()!=0)}">
                            <thead>
                            <th colspan="7">Your cart is empty!</th>    
                            </thead>
                            <thead>
                            <th colspan="7" onclick="document.querySelector('.popUp').classList.remove('displayNone'); document.querySelector('#backdrop').classList.remove('displayNone')">
                                <i class='fas fa-cart-plus'></i>
                            </th>    
                            </thead>
                        </c:when>
                        <c:otherwise>
                            <tbody class="cartDetailList" id="cartDetailList">

                                <c:set var="total" value="0"></c:set>
                                <c:forEach var="detail" items="${newCart.detailList}" varStatus="loop">
                                    <c:set var="total" value="${total +detail.productPrice*detail.numOfProduct}"></c:set>
                                    <tr class="detailCard" id="newDetailCard-${detail.product.productId}>">
                                        <td><span class="detailProductname">${detail.product.productName}</span></td>
                                        <td><input type="number" name="detailProductPrice-${loop.index+1}" id="detailProductPrice-${detail.product.productId}" value="${detail.productPrice}" readonly></td><td>X</td>
                                        <td><input type="number" name="detailProductNum-${loop.index+1}" id="detailProductNum-${detail.product.productId}" value="${detail.numOfProduct}" min="1" required onblur="window.location.replace(`${controllerServlet}?action=updateQuantity&productId=${detail.product.productId}&quantity=` + this.value);" onchange="calcDetailTotoal('${detail.product.productId}')"></td>
                                        <td style="text-align: center">=</td>
                                        <td><span class="detailTotal" id="detailTotal-${detail.product.productId}">${detail.productPrice*detail.numOfProduct}</span></td>
                                        <td style="text-align: center">vnd</td>
                                        <td>
                                            <a href="${controllerServlet}?action=removeProduct&productId=${detail.product.productId}"><i class="far fa-minus-square"></i></a>
                                        </td>
                                        <td><input type="hidden" name="detailProductId-${loop.index+1}" id="detailProductId-${detail.product.productId}" value="${detail.product.productId}"></td>
                                    </tr>
                                </c:forEach>
                            </tbody> 
                            <thead>
                            <th colspan="7" onclick="document.querySelector('.popUp').classList.remove('displayNone'); document.querySelector('#backdrop').classList.remove('displayNone')">
                                <i class='fas fa-cart-plus'></i>
                            </th>    
                            </thead>
                            <tbody class="cartOverall" id="cartOverall">
                                <tr>
                                    <td></td><td></td><td></td>
                                    <th style="text-align: right">Total</th><th>=</th>
                                    <th style="text-align: left"> <span class="cartTotal" id="cartTotal">${total}</span></th><th>vnd</th>
                                </tr>

                                <tr>
                                    <fmt:formatDate value="${cart.cartBuyDate}" var="formattedBuyDate" type="date" pattern="yyyy-MM-dd" />
                                    <fmt:formatDate value="${cart.cartShipDate}" var="formattedShipDate" type="date" pattern="yyyy-MM-dd" />
                                    <td style="text-align: right;">Buy date:</td><td colspan="2"><input type="date" name="buyDate" value="${formattedBuyDate}" readonly></td>
                                    <td style="text-align: right;">Delivery date:</td><td colspan="3"><input type="date" name="deliveryDate" min="${formattedShipDate}" value="${formattedShipDate}" required></td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">Address:</td>
                                    <td colspan="5">
                                        <textarea name="cartAddress" class="cartAddress" id="cartAddress" placeholder="Your delivery address" required rows="3" cols="50" required>${a.accAddress}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td><td onclick="return confirmOrder();"><input type="submit" value="Order"></td>
                                </tr>
                            </tbody>
                        </c:otherwise>
                    </c:choose>
                </table>
            </form>
            <section class="popUp bestRatedProduct displayNone">
                <c:forEach var="product" items="${bestRatedProductList}">
                    <section class="productCard" id="productCard-${product.productId}" onclick="popUpProductDetail('${product.productId}">
                        <section class="buyButton">
                            <a href="${controllerServlet}?action=buyProductFromCart&productId=${product.productId}" onclick="event.stopImmediatePropagation();"><i class='fas fa-cart-plus'></i></a>
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
                    </section>
                </c:forEach>
            </section>
            <section class="popUp bestRatedProduct displayNone">
                <c:forEach var="product" items="${bestRatedProductList}">
                    <section class="productCard" id="productCard-${product.productId}" onclick="popUpProductDetail('${product.productId}">
                        <section class="buyButton">
                            <a href="${controllerServlet}?action=buyProductFromCart&productId=${product.productId}" onclick="event.stopImmediatePropagation();"><i class='fas fa-cart-plus'></i></a>
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
