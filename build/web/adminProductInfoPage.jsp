<%-- 
    Document   : adminProductInfoPage
    Created on : Mar 16, 2021, 9:09:46 AM
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
        <script src="JS/adminProductsPage.js" defer></script>
        <title>Product Detail For Admin</title>
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
    <c:set var="controllerServlet" value="OnlineShop"></c:set>
    <c:set var="productList" value="${product}"></c:set>
    <c:set var="ratingList" value="${ratingList}"></c:set>
    <c:set var="detailList" value="${detailList}"></c:set>
    <c:set var="numOfCustomer" value="${numOfCustomer}"></c:set>
        <body>
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
        <div id="backdrop" class="displayNone" onclick="hideAllPopUp()"></div>
        <main style="display: block; width: auto">
            <h4>${msg}</h4>
            <section class="productDetail" id="productDetail-${product.productId}">
                <section class="productInfo prod">
                    <section class="productName">${product.productName}</section>
                    <section class="productPrice">${product.productPrice} vnd</section>
                    <section class="productDescription">${product.productDescription}</section> 
                    <br>
                </section>
                <section class="productStat" >

                    <c:set var="totalMoney" value="0"></c:set>
                    <c:set var="totalNum" value="0"></c:set>
                    <c:forEach var="cartDetail" items="${detailList}">
                        <c:set var="totalMoney" value="${totalMoney+cartDetail.productPrice*cartDetail.numOfProduct}"></c:set>
                        <c:set var="totalNum" value="${totalNum+cartDetail.numOfProduct}"></c:set>
                    </c:forEach>
                    <c:set var="numOf5" value="0"></c:set>
                    <c:set var="numOf4" value="0"></c:set>
                    <c:set var="numOf3" value="0"></c:set>
                    <c:set var="numOf2" value="0"></c:set>
                    <c:set var="numOf1" value="0"></c:set>

                    <c:forEach var="rating" items="${ratingList}">
                        <c:if test="${rating.product.productId==product.productId}">
                            <c:choose>
                                <c:when test="${rating.numOfStar==5}">
                                    <c:set var="numOf5" value="${numOf5+1}"></c:set>
                                </c:when>
                                <c:when test="${rating.numOfStar==4}">
                                    <c:set var="numOf4" value="${numOf4+1}"></c:set>
                                </c:when>
                                <c:when test="${rating.numOfStar==3}">
                                    <c:set var="numOf3" value="${numOf3+1}"></c:set>
                                </c:when>
                                <c:when test="${rating.numOfStar==2}">
                                    <c:set var="numOf2" value="${numOf2+1}"></c:set>
                                </c:when>
                                <c:when test="${rating.numOfStar==1}">
                                    <c:set var="numOf1" value="${numOf1+1}"></c:set>
                                </c:when>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                    <span class="overall">
                        <span>${numOfCustomer} customers have spent ${totalMoney} vnd buying ${totalNum} products
                            <br>${ratingList.size()} customers rated ${product.avgNumOfStar} stars on average
                        </span>
                    </span>
                    <div class="row">
                        <div class="side">
                            <div>5 star</div>
                        </div>
                        <div class="middle">
                            <div class="bar-container">
                                <div class="bar-5" style="width: ${ratingList.size()==0?0:numOf5*100/ratingList.size()}%"></div>
                            </div>
                        </div>
                        <div class="side right">
                            <div>${numOf5}</div>
                        </div>
                        <div class="side">
                            <div>4 star</div>
                        </div>
                        <div class="middle">
                            <div class="bar-container">
                                <div class="bar-4" style="width: ${ratingList.size()==0?0:numOf4*100/ratingList.size()}%"></div>
                            </div>
                        </div>
                        <div class="side right">
                            <div>${numOf4}</div>
                        </div>
                        <div class="side">
                            <div>3 star</div>
                        </div>
                        <div class="middle">
                            <div class="bar-container">
                                <div class="bar-3" style="width: ${ratingList.size()==0?0:numOf3*100/ratingList.size()}%"></div>
                            </div>
                        </div>
                        <div class="side right">
                            <div>${numOf3}</div>
                        </div>
                        <div class="side">
                            <div>2 star</div>
                        </div>
                        <div class="middle">
                            <div class="bar-container">
                                <div class="bar-2" style="width: ${ratingList.size()==0?0:numOf2*100/ratingList.size()}%"></div>
                            </div>
                        </div>
                        <div class="side right">
                            <div>${numOf2}</div>
                        </div>
                        <div class="side">
                            <div>1 star</div>
                        </div>
                        <div class="middle">
                            <div class="bar-container">
                                <div class="bar-1" style="width: ${ratingList.size()==0?0:numOf1*100/ratingList.size()}%"></div>
                            </div>
                        </div>
                        <div class="side right">
                            <div>${numOf1}</div>
                        </div>
                    </div>
                    <h3>${product.productName}'s rating history</h3>
                    <c:if test="${ratingList.size()==0}">
                        No rating found yet
                    </c:if>
                    <c:forEach var="rating" items="${ratingList}">
                        <div class="ratingCard">
                            <span class="ratingProductName">${rating.product.productName}</span>
                            <span class="ratingAccountUserName">${rating.account.accUserName}</span><br>
                            <span class="ratingNumOfStar">
                                ${rating.numOfStar} stars
                                <c:set var="roundNumOfStar" value="${rating.numOfStar}"></c:set>
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
                            <span class="ratingComment">${rating.comment}</span><br>
                        </div>
                    </c:forEach>
                </section>
            </section>
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
