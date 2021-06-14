<%-- 
    Document   : adminProductsPage
    Created on : Mar 4, 2021, 9:54:36 AM
    Author     : DELL
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="CSS/all.css">
        <link type="text/css" rel="stylesheet" href="CSS/adminProductsPage.css">
        <script src="https://kit.fontawesome.com/10736bd9b2.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="JS/adminProductsPage.js" defer></script>
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
        <title>Admin Product Page</title>


    </head>
    <body onload="resizeProductCard()">
        <c:set var="controllerServlet" value="OnlineShop"></c:set>
        <c:set var="allProductList" value="${allProductList}"></c:set>
        <c:set var="productRatingList" value="${productRatingList}"></c:set>
        <c:set var="productCartDetailList" value="${productCartDetailList}"></c:set>
        <c:set var="productNumOfCustomer" value="${productNumOfCustomer}">
        </c:set>
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
                <form action="${controllerServlet}?action=adminSearchProduct" method="post">
                    <input type="text" name="searchBar" placeholder="Search for product">
                    <input type="submit" value="Search">
                </form>
            </section>
            <section class="mainNav" id="mainNav">
                <a href="${controllerServlet}?action=showAdminProductsPage" class="mainAdminTab productTab" id="usersTab">Products</a>
                <a href="${controllerServlet}?action=showAdminUsersPage" class="mainAdminTab cartTab" id="usersTab">Users</a>
            </section>
        </nav>
        <main style="display: block; width: auto">
            
            <button class="addProductButton" onclick="showProductForm('', '', '', '', '${controllerServlet}?action=adminAddProduct')"><h3>Add new product</h3></button>
            <h4>${msg}</h4>
            <section class="subPart allProduct">
                <c:forEach var="product" items="${allProductList}" varStatus="loop">
                    <section class="productCard" id="productCard-${product.productId}">
                        <button style="float: right" onclick="showProductForm('${product.productId}', '${product.productName}', '${product.productPrice}', '${product.productDescription}', '${controllerServlet}?action=adminUpdateProduct'); event.stopImmediatePropagation();">Update product</button>
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
                        <a href="${controllerServlet}?action=showAdminProductInfo&productId=${product.productId}">Show product's detail</a>
                    </section>

                </c:forEach>
            </section>
            <form action="" method="POST" class="productForm popUp displayNone" id="productForm">
                <h4></h4>
                <table>
                    <tr>
                        <td>Product id</td>
                        <td><input type="text" name="productId" id="productId" value="" placeholder="This will be computed automatically" readonly></td>
                    </tr>
                    <tr>
                        <td>Product name</td>
                        <td><input type="text" name="productName" id="productName" placeholder="Enter product name" required></td>
                    </tr>
                    <tr>
                        <td>Product price</td>
                        <td><input type="number" name="productPrice" id="productPrice" min="0" step="500" placeholder="Enter product price" required> vnd</td>
                    </tr>
                    <tr>
                        <td>Product description</td>
                        <td><textarea name="productDescription" id="productDescription" placeholder="Enter product description" required></textarea></td>
                    </tr>
                    <tr>
                        <td><button onclick="hideAllPopUp()">Cancel</button></td>
                        <td><input type="submit" id="submit" value=""></td>
                    </tr>
                </table>
            </form>
        </main>
        <footer>
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
