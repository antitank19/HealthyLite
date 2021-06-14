<%-- 
    Document   : userAccount
    Created on : Mar 2, 2021, 10:06:26 AM
    Author     : DELL
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Detail</title>
        <link type="text/css" rel="stylesheet" href="CSS/all.css">
        <link type="text/css" rel="stylesheet" href="CSS/userMainPage.css">
        <script src="https://kit.fontawesome.com/10736bd9b2.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="JS/checkAccountForm.js" defer></script>
        <script src="JS/userAccountPage.js" defer></script>
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
        <c:set var="a" value="${account}"></c:set>
        <c:set var="controllerServlet" value="OnlineShop"></c:set>
        <c:set var="userRatingList" value="${userRatingList}"></c:set>
        <c:set var="userCartList" value="${userCartList}"></c:set>

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
            <section class="mainNav" id="mainNav">
                <a href="${controllerServlet}?action=showUserMainPage" class="mainTab productTab" id="productTab">Product</a>
                <a href="${controllerServlet}?action=showUserCart" class="mainTab cartTab" id="cartTab">Cart</a>
                <span class="mainTab accountTab active" id="accountTab">Account</span>
            </section>
            <section class="subNav" id="subNav">
                <span class="subTab accontInfoTab active" id="accountInfoTab">Info</span>
                <span class="subTab accontStatTab" id="accountStatTab">Stat</span>
            </section>
        </nav>
        <aside class="adBar1">Ad</aside>
        <main>
            <h4>${msg}</h4>
            <section class="mainPart" id="accountInfoPage">
                <h2>Your account info</h2>

                <c:set var="action" scope="request" value="viewAccount"></c:set>
                <jsp:include page="accountForm.jsp"></jsp:include>
                    <button onclick="popUpAccountForm()">Update account info</button>
                    <span class="popUp updateAccountForm displayNone">
                        <h2>Update account</h2>
                        <form action="${controllerServlet}?action=updateAccount" method="POST" name="f1">
                        <c:set var="action" scope="request" value="updateAccount"></c:set>
                        <jsp:include page="accountForm.jsp"/>
                        <button onclick="hideAllPopUp()">Cancel</button>
                        <input type="Submit" value="Update your info" onclick="return finalCheck('updateAccount', 'updateAccount-accUserName', 'updateAccount-accPassword', 'updateAccount-confirmPassword');">
                    </form>
                </span>
            </section>
            <section class="mainPart displayNone" id="accountStatPage">
                <h3>Your ratings</h3>
                <c:if test="${userRatingList.size()==0}">
                    No rating yet
                </c:if>
                <c:forEach var="rating" items="${userRatingList}">
                    <div class="ratingCard">
                        <span class="ratingProductName">${rating.product.productName}</span>
                        <span class="ratingAccountUserName">${rating.account.accUserName}</span><br>
                        <span class="ratingNumOfStar">
                            ${rating.numOfStar} stars
                            <c:set var="thisNumOfStar" value="${rating.numOfStar}"></c:set>
                            <c:forEach begin="1" end="5" step="1">
                                <c:choose>
                                    <c:when test="${thisNumOfStar >=1}">
                                        <i class='fas fa-star'></i>
                                    </c:when>
                                    <c:when test="${thisNumOfStar >=1}">
                                        <i class='fas fa-star-half-alt'></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class='far fa-star'></i>
                                    </c:otherwise>
                                </c:choose>
                                <c:set var="thisNumOfStar" value="${thisNumOfStar - 1}"></c:set>
                            </c:forEach>
                        </span><br>
                        <span class="ratingComment">${rating.comment}</span><br>
                        <button class="ratingButton" onclick="showRatingForm('${rating.product.productId}', '${rating.product.productName}', '${rating.numOfStar+0}', '${rating.comment}', '${controllerServlet}?action=updateRating&accUserName=${a.accUserName}')">Update your rating</button>
                    </div>
                </c:forEach>

                <h3>Your carts</h3>
                <c:if test="${userCartList.size()==0}">
                    No cart yet
                </c:if>
                <c:forEach var="cart" items="${userCartList}" varStatus="loop">
                    <table class="cartCard" id="yourCartCard-${loop.index + 1}">
                        <thead>
                        <th>Name</th>
                        <th>Price</th><th></th>
                        <th>Number</th>
                        <th>Total</th>
                        </thead>
                        <c:set var="total" value="0"></c:set>
                        <c:forEach var="detail" items="${cart.detailList}">
                            <c:set var="total" value="${total + detail.productPrice*detail.numOfProduct}"></c:set>
                            <tr class="detailCard" id="userDetailCard-${detail.product.productId}">
                                <td><span class="detailProductname">${detail.product.productName}</span></td>
                                <td><input type="number" name="detailProductPrice-${loop.index + 1}" id="detailProductPrice-${detail.product.productId}-${loop.index + 1}" value="${detail.productPrice}" readonly></td><td>X</td>
                                <td><input type="number" name="detailNumOfProduct-${loop.index + 1}" id="detailNumOfProduct-${detail.product.productId}-${loop.index + 1}" value="${detail.numOfProduct}" min="1" readonly required></td>
                                <td>=<span class="detailTotal" id="detailTotal-${detail.product.productId}-${loop.index + 1}">${detail.productPrice*detail.numOfProduct}</span></td><td>vnd</td>
                                <td><input type="hidden" name="detailProductId-${loop.index + 1}" id="userDetailProductId-${detail.product.productId}-${loop.index + 1}" value="${detail.product.productId}"></td>
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
            <form action="" method="POST" class="ratingForm popUp displayNone" id="ratingForm">
                <input type="hidden" name="ratingProductId" id="ratingProductId" value="">
                <span class="ratingProductName"></span>
                <span class="ratingAccountUserName">${a.accUserName}</span>
                <input type="hidden" name="ratingAccUserName" id="ratingAccUserName" value="${a.accUserName}" readonly />
                <br>
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
                </div><br>
                <div>
                    <br>
                    <textarea name="ratingComment" placeholder="Enter your thought on this product"></textarea><br>
                </div>
                <input type="submit" value="Rate">
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
