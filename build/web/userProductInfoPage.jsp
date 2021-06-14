<%-- 
    Document   : userShowProductInfo
    Created on : Mar 15, 2021, 7:54:31 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="CSS/all.css">
        <link type="text/css" rel="stylesheet" href="CSS/userMainPage.css">
        <script src="https://kit.fontawesome.com/10736bd9b2.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="JS/userMainPage.js" defer></script>
        <title>Product Detail</title>
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
    <c:set var="controllerServlet" value="OnlineShop"></c:set>
    <c:set var="a" value="${account}"></c:set>
    <c:set var="userRatingList" value="${userRatingList}"></c:set>
    <c:set var="yourBoughtProductIdList" value="${yourBoughtProductIdList}"></c:set>
    <c:set var="product" value="${product}"></c:set>
    <c:set var="numOfCustomer" value="${numOfCustomer}"></c:set>
    <c:set var="ratingList" value="${ratingList}"></c:set>
        <body onload="resizeAside()" onresize="resizeAside()">
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
                <a href="${controllerServlet}?action=showUserMainPage" class="mainTab productTab active" id="productTab">Products</a>
                <a href="${controllerServlet}?action=showUserCart" class="mainTab cartTab" id="cartTab">Cart</a>
                <a href="${controllerServlet}?action=showUserAccount" class="mainTab accountTab" id="accountTab">Account</a>
            </section>
        </nav>
        <aside class="adBar1">Ad</aside>
        <main>
            <h4>${msg}</h4>
            <div id="backdrop" class="displayNone" onclick="hideAllPopUp"></div>
            <section class="productDetail" id="productDetail-${product.productId}">
                <section class="productCard productInfo">
                    <section class="buyButton" onclick="addToCart('${product.productId}', '${product.productName}', ${product.productPrice})">
                        <a target="_blank" href="${controllerServlet}?action=buyProduct&productId=${product.productId}"><i class='fas fa-cart-plus'></i></a>
                    </section>
                    <section class="productName">${product.productName}</section>
                    <section class="productPrice">${product.productPrice} vnd</section>
                    <section class="productDescription">${product.productDescription}</section> 
                        <c:if test="${yourBoughtProductIdList.contains(product.productId)}">
                            <c:set var="alreadyRate" value="${false}"></c:set>
                            <c:forEach var="rating" items="${userRatingList}">
                                <c:if test="${product.productId==rating.product.productId}">
                                    <c:set var="alreadyRate" value="${true}"></c:set>
                                    <c:set var="yourRating" value="${rating}"></c:set>
                                <button class="ratingButton" onclick="showRatingForm('${rating.product.productId}', '${rating.product.productName}', '${rating.numOfStar}', '${rating.comment}', '${controllerServlet}?action=updateRating&accUserName=${a.accUserName}')">Update your rating</button>
                            </c:if>
                        </c:forEach>
                        <c:if test="${!alreadyRate}">
                            <button class="ratingButton" onclick="showRatingForm('${product.productId}', '${product.productName}', '5', '', '${controllerServlet}?action=createRating&accUserName=${a.accUserName}')">Leave a comment</button>
                        </c:if>        
                    </c:if>
                </section>
                <section class="productStat" >
                    <c:set var="totalNum" value="0"></c:set>
                    <c:forEach var="cartDetail" items="${productCartDetailList[loop.index]}">
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
                        <span>${numOfCustomer} customers have bought this product
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
                    <c:if test="${!(empty yourRating)}">
                        <h3>Your rating</h3>
                        <div class="ratingCard">
                            <span class="ratingProductName">${yourRating.product.productName}</span>
                            <span class="ratingAccountUserName">${yourRating.account.accUserName}</span><br>
                            <span class="ratingNumOfStar">
                                ${yourRating.numOfStar} stars 
                                <c:set var="roundNumOfStar" value="${yourRating.numOfStar}"></c:set>
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
                            <span class="ratingComment">${yourRating.comment}</span><br>
                            <button><span class="ratingButton" onclick="showRatingForm('${yourRating.product.productId}', '${yourRating.product.productName}', '${yourRating.numOfStar}', '${yourRating.comment}', '${controllerServlet}?action=updateRating&accUserName=${a.accUserName}')">Update your rating</span></button>
                        </c:if>
                    </div>
                    <h3>${product.productName}'s rating history</h3>
                    <c:if test="${ratingList.size()==0}">
                        No rating found yet
                    </c:if>
                    <c:forEach var="rating" items="${ratingList}">
                        <c:if test="${!(!(empty yourRating)&&rating.account.accUserName==yourRating.account.accUserName)}">
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
                        </c:if>
                    </c:forEach>
                </section>
            </section>
            <form action="" method="POST" class="ratingForm popUp displayNone" id="ratingForm">
                <input type="hidden" name="ratingProductId" id="ratingProductId" value="">
                <input type="hidden" name="ratingAccUserName" id="ratingAccUserName" value="${a.accUserName}" readonly />
                <table>
                    <tr>
                        <td>
                            <span class="ratingProductName"></span>
                        </td>
                        <td>
                            <span class="ratingAccountUserName">${a.accUserName}</span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="rate">
                                <input type="radio" id="star5" name="ratingNumOfStar" value="5" />
                                <label for="star5" title="text">5 stars</label>
                                <input type="radio" id="star4" name="ratingNumOfStar" value="4" />
                                <label for="star4" title="text">4 stars</label>
                                <input type="radio" id="star3" name="ratingNumOfStar" value="3" />
                                <label for="star3" title="text">3 stars</label>
                                <input type="radio" id="star2" name="ratingNumOfStar" value="2" />
                                <label for="star2" title="text">2 stars</label>
                                <input type="radio" id="star1" name="ratingNumOfStar" value="1" />
                                <label for="star1" title="text">1 star</label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div>
                                <textarea name="ratingComment" placeholder="Enter your thought on this product"></textarea><br>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Rate">
                        </td>
                    </tr>
                </table>
            </form> 
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
