/*
Created		12/04/2012
Modified	
Project		VNLA	
Author		liemqv@gmail.com
Version		1.0
Database	MS SQL 2005 
*/

USE master
GO

IF  EXISTS (
	SELECT name 
		FROM sys.databases 
		WHERE name = N'vnla'
)
DROP DATABASE [vnla]
GO

CREATE DATABASE [vnla]
GO

use [vnla]
go

--Bảng Danh mục văn bản
Create table [tblDanhmucvanban]
(
	PK_iDanhmucvanbanID Smallint Identity Primary key,
	sTendanhmuc Nvarchar(150) not null,
	FK_iDanhmucchaID Smallint NOT NULL References tblDanhmucvanban(PK_iDanhmucvanbanID)
) 
go

--Bảng Cơ quan
Create table [tblCoquan]
(
	PK_iCoquanID Smallint Identity Primary key,
	sTencoquan Nvarchar(150) not null
) 
go

--Bảng Người ký
Create table [tblNguoiky]
(
	PK_iNguoikyID Smallint Identity Primary key,
	sHotennguoiky Nvarchar(50) not null,
	sChucvu Nvarchar(50)
) 
go

--Bảng Văn bản
Create table [tblVanban]
(
	PK_iVanbanID Bigint Primary key,
	FK_iCoquanbanhanhID Smallint NOT NULL References tblCoquan(PK_iCoquanID),
	FK_iNguoikyID Smallint NOT NULL References tblNguoiky(PK_iNguoikyID),
	FK_iDanhmucvanbanID Smallint NOT NULL References tblDanhmucvanban(PK_iDanhmucvanbanID),
	sTenvanban Nvarchar(300) NOT NULL,
	sSohieu Varchar(50),
	dNgaybanhanh Datetime NOT NULL Default GETDATE(),
	dNgaycohieuluc Datetime NOT NULL,
	dNgayhethan Datetime NULL,
	iTrangthai Smallint NOT NULL Default 1,
	sGhichu Nvarchar(500)
) 
go

--Bảng Chương
Create table [tblChuong]
(
	PK_iChuongID Bigint Identity Primary key,
	FK_iVanbanID Bigint NOT NULL References tblVanban(PK_iVanbanID),
	iSothutu Smallint NOT NULL,
	sTenchuong Nvarchar(200) NOT NULL
)

--Bảng Điều
Create table [tblDieu]
(
	PK_iDieuID Bigint Identity Primary key,
	FK_iChuongID Bigint NOT NULL References tblChuong(PK_iChuongID),
	FK_iVanbanID Bigint NOT NULL References tblVanban(PK_iVanbanID),
	iSothutu Smallint NOT NULL,
	sTendieu Nvarchar(200) NOT NULL,
	sNoidung Nvarchar(MAX) NOT NULL
)

--Bảng Văn bản liên quan
Create table [tblVanbanlienquan]
(
	FK_iVanbanchinhID Bigint NOT NULL References tblVanban(PK_iVanbanID),
	FK_iVanbanlienquanID Bigint NOT NULL References tblVanban(PK_iVanbanID),
	Primary key(FK_iVanbanchinhID, FK_iVanbanlienquanID)
)

--Bảng Người dùng
Create table [tblNguoidung]
(
	PK_iNguoidungID Smallint Primary key,
	sTendangnhap Varchar(45) NOT NULL,
	sMatkhau Varchar(45),
	sEmail Varchar(50),
	sHoten Nvarchar(50),
	iTrangthai Smallint NOT NULL Default(1)
)