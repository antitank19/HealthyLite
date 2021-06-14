USE [master]
GO
/****** Object:  Database [SE1502_Assignment_Group09]    Script Date: 3/13/2021 11:15:10 PM ******/
CREATE DATABASE [SE1502_Assignment_Group09]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AssignmentPRJ', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\AssignmentPRJ.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'AssignmentPRJ_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\AssignmentPRJ_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SE1502_Assignment_Group09].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET ARITHABORT OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET RECOVERY FULL 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET  MULTI_USER 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'SE1502_Assignment_Group09', N'ON'
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET QUERY_STORE = OFF
GO
USE [SE1502_Assignment_Group09]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 3/13/2021 11:15:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[accUserName] [nvarchar](50) NOT NULL,
	[accPassword] [nvarchar](50) NULL,
	[accFullName] [nvarchar](50) NULL,
	[accGender] [nvarchar](50) NULL,
	[accDob] [date] NULL,
	[accPhone] [nvarchar](50) NULL,
	[accAddress] [nvarchar](2000) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[accUserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 3/13/2021 11:15:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[cartId] [nvarchar](50) NOT NULL,
	[cartBuyDate] [date] NULL,
	[cartShipDate] [date] NULL,
	[accUserName] [nvarchar](50) NULL,
	[cartAddress] [nvarchar](50) NULL,
 CONSTRAINT [PK_Cart] PRIMARY KEY CLUSTERED 
(
	[cartId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CartDetail]    Script Date: 3/13/2021 11:15:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CartDetail](
	[cartId] [nvarchar](50) NOT NULL,
	[productId] [nvarchar](50) NOT NULL,
	[numOfProduct] [int] NULL,
	[productPrice] [numeric](18, 0) NULL,
 CONSTRAINT [PK_CartDetail] PRIMARY KEY CLUSTERED 
(
	[cartId] ASC,
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 3/13/2021 11:15:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[productId] [nvarchar](50) NOT NULL,
	[productName] [nvarchar](50) NULL,
	[productPrice] [numeric](18, 0) NULL,
	[productDescription] [nvarchar](2000) NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rating]    Script Date: 3/13/2021 11:15:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rating](
	[accUserName] [nvarchar](50) NOT NULL,
	[productId] [nvarchar](50) NOT NULL,
	[numOfStar] [float] NULL,
	[comment] [nvarchar](2000) NULL,
 CONSTRAINT [PK_Rating] PRIMARY KEY CLUSTERED 
(
	[accUserName] ASC,
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([accUserName], [accPassword], [accFullName], [accGender], [accDob], [accPhone], [accAddress]) VALUES (N'cahoi19', N'123456789', N'Minh Khoi', N'Male', CAST(N'2001-01-01' AS Date), N'12334567890', N'3 Cao Lo Q8')
GO
INSERT [dbo].[Account] ([accUserName], [accPassword], [accFullName], [accGender], [accDob], [accPhone], [accAddress]) VALUES (N'cahoi2001', N'123456789', N'Tran Khai Minh Khoi', N'Male', CAST(N'2001-01-01' AS Date), N'0901234567', N'35 Cao Lo')
GO
INSERT [dbo].[Account] ([accUserName], [accPassword], [accFullName], [accGender], [accDob], [accPhone], [accAddress]) VALUES (N'demoAcc                         ', N'123456789', N'de mo acc', N'Other', CAST(N'2001-09-25' AS Date), N'9876543210', N'3 demo ok')
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi19-000001', CAST(N'2001-01-01' AS Date), CAST(N'2001-01-01' AS Date), N'cahoi19', N'335 Cao Thang')
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi19-000002', CAST(N'2001-01-01' AS Date), CAST(N'2001-01-01' AS Date), N'cahoi19', N'1 Tran Hung Dao')
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi2001-000001', CAST(N'2001-01-01' AS Date), CAST(N'2001-01-01' AS Date), N'cahoi2001', N'22 Hai Ba Trung')
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi2001-000002', CAST(N'2021-02-21' AS Date), CAST(N'2021-02-21' AS Date), N'cahoi2001', NULL)
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi2001-000003', CAST(N'2021-02-23' AS Date), CAST(N'2021-02-23' AS Date), N'cahoi2001', NULL)
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi2001-000004', CAST(N'2021-02-23' AS Date), CAST(N'2021-02-23' AS Date), N'cahoi2001', NULL)
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi2001-000005', CAST(N'2021-02-23' AS Date), CAST(N'2021-02-23' AS Date), N'cahoi2001', NULL)
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi2001-000006', CAST(N'2021-02-23' AS Date), CAST(N'2021-02-23' AS Date), N'cahoi2001', NULL)
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi2001-000007', CAST(N'2021-02-23' AS Date), CAST(N'2021-02-23' AS Date), N'cahoi2001', NULL)
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi2001-000008', CAST(N'2021-02-23' AS Date), CAST(N'2021-02-23' AS Date), N'cahoi2001', NULL)
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'cahoi2001-000009', CAST(N'2021-02-23' AS Date), CAST(N'2021-02-23' AS Date), N'cahoi2001', NULL)
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'demoAcc-000001', CAST(N'2001-01-01' AS Date), CAST(N'2001-01-01' AS Date), N'demoAcc', N'35 Nam Ki Khoi Nghia')
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'demoAcc-000002', CAST(N'2001-01-01' AS Date), CAST(N'2001-01-01' AS Date), N'demoAcc', N'44 To Bac')
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'demoAcc-000003', CAST(N'2021-02-21' AS Date), CAST(N'2021-02-21' AS Date), N'demoAcc', NULL)
GO
INSERT [dbo].[Cart] ([cartId], [cartBuyDate], [cartShipDate], [accUserName], [cartAddress]) VALUES (N'demoAcc-000004', CAST(N'2021-03-05' AS Date), CAST(N'2021-03-08' AS Date), N'demoAcc', NULL)
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi19-000001', N'0000002', 50, CAST(60000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi19-000002', N'0000001', 2, CAST(20000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi19-000002', N'0000002', 5, CAST(60000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi19-000002', N'0000003', 3, CAST(8000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi2001-000001', N'0000003', 1000, CAST(8000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi2001-000002', N'0000002', 1, CAST(50000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi2001-000003', N'0000001', 1, CAST(25000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi2001-000004', N'0000001', 1, CAST(25000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi2001-000005', N'0000001', 1, CAST(25000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi2001-000006', N'0000001', 1, CAST(25000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi2001-000007', N'0000001', 1, CAST(25000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi2001-000008', N'0000001', 1, CAST(25000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'cahoi2001-000009', N'0000001', 1, CAST(25000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'demoAcc-000001', N'0000001', 100, CAST(20000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'demoAcc-000001', N'0000002', 10, CAST(60000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'demoAcc-000002', N'0000001', 12, CAST(20000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'demoAcc-000002', N'0000003', 5, CAST(8000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'demoAcc-000003', N'0000001', 100, CAST(25000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'demoAcc-000003', N'0000003', 100, CAST(10000 AS Numeric(18, 0)))
GO
INSERT [dbo].[CartDetail] ([cartId], [productId], [numOfProduct], [productPrice]) VALUES (N'demoAcc-000004', N'0000001', 4, CAST(25000 AS Numeric(18, 0)))
GO
INSERT [dbo].[Product] ([productId], [productName], [productPrice], [productDescription]) VALUES (N'0000001', N'Product 1', CAST(25000 AS Numeric(18, 0)), N'A very good demo for a very good product')
GO
INSERT [dbo].[Product] ([productId], [productName], [productPrice], [productDescription]) VALUES (N'0000002', N'Product 2', CAST(50000 AS Numeric(18, 0)), N'a nice product for health')
GO
INSERT [dbo].[Product] ([productId], [productName], [productPrice], [productDescription]) VALUES (N'0000003', N'Product 3', CAST(10000 AS Numeric(18, 0)), N'A drink to boost your size')
GO
INSERT [dbo].[Product] ([productId], [productName], [productPrice], [productDescription]) VALUES (N'0000004', N'Product 4', CAST(200000 AS Numeric(18, 0)), N'An expensive product')
GO
INSERT [dbo].[Rating] ([accUserName], [productId], [numOfStar], [comment]) VALUES (N'cahoi19', N'0000001', 4, N'kinda good')
GO
INSERT [dbo].[Rating] ([accUserName], [productId], [numOfStar], [comment]) VALUES (N'cahoi19', N'0000002', 3, N'meh')
GO
INSERT [dbo].[Rating] ([accUserName], [productId], [numOfStar], [comment]) VALUES (N'cahoi19', N'0000003', 1, N'absolute trash. Idiot ')
GO
INSERT [dbo].[Rating] ([accUserName], [productId], [numOfStar], [comment]) VALUES (N'cahoi2001', N'0000002', 4, N'Nice job')
GO
INSERT [dbo].[Rating] ([accUserName], [productId], [numOfStar], [comment]) VALUES (N'cahoi2001', N'0000003', 4, N'good idea and intention but fail to perform, have some malfunction. Keep improving the product')
GO
INSERT [dbo].[Rating] ([accUserName], [productId], [numOfStar], [comment]) VALUES (N'demoAcc', N'0000001', 5, N'cool Product')
GO
INSERT [dbo].[Rating] ([accUserName], [productId], [numOfStar], [comment]) VALUES (N'demoAcc', N'0000002', 4, N'good but too expensive')
GO
INSERT [dbo].[Rating] ([accUserName], [productId], [numOfStar], [comment]) VALUES (N'demoAcc', N'0000003', 3, N'average')
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Account] FOREIGN KEY([accUserName])
REFERENCES [dbo].[Account] ([accUserName])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_Cart_Account]
GO
ALTER TABLE [dbo].[CartDetail]  WITH CHECK ADD  CONSTRAINT [FK_CartDetail_Cart] FOREIGN KEY([cartId])
REFERENCES [dbo].[Cart] ([cartId])
GO
ALTER TABLE [dbo].[CartDetail] CHECK CONSTRAINT [FK_CartDetail_Cart]
GO
ALTER TABLE [dbo].[CartDetail]  WITH CHECK ADD  CONSTRAINT [FK_CartDetail_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
GO
ALTER TABLE [dbo].[CartDetail] CHECK CONSTRAINT [FK_CartDetail_Product]
GO
ALTER TABLE [dbo].[Rating]  WITH CHECK ADD  CONSTRAINT [FK_Rating_Account] FOREIGN KEY([accUserName])
REFERENCES [dbo].[Account] ([accUserName])
GO
ALTER TABLE [dbo].[Rating] CHECK CONSTRAINT [FK_Rating_Account]
GO
ALTER TABLE [dbo].[Rating]  WITH CHECK ADD  CONSTRAINT [FK_Rating_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
GO
ALTER TABLE [dbo].[Rating] CHECK CONSTRAINT [FK_Rating_Product]
GO
USE [master]
GO
ALTER DATABASE [SE1502_Assignment_Group09] SET  READ_WRITE 
GO
