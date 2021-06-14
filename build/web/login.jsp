<%-- 
    Document   : login
    Created on : Jan 31, 2021, 9:17:38 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="CSS/all.css">
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
        <title>Login Page</title>
    </head>
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
            </div>
        </header>
        <main style="min-height: 72vh;display: block; width: auto">
            <h2>${msg}</h2>
            <section>
                <form action="OnlineShop?action=login" method="POST">
                    <input type="text" name="accUserName" id="accUserName" placeholder="Enter your username"><br>
                    <input type="password" name="accPassword" id="accPassword" placeholder="Enter your password"><br>
                    <input type="Submit" value="Login">
                </form>
            </section>
            <section>
                <a href="OnlineShop?action=showCreateUserAccount">Create new account</a>
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
