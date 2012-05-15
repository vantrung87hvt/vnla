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
	PK_sDanhmucvanbanID NVarchar(15) Primary key,
	sTendanhmuc Nvarchar(150) not null
) 
go

--Bảng Cơ quan
Create table [tblCoquan]
(
	PK_sCoquanID NVarchar(15) Primary key,
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
	PK_iVanbanID Bigint Identity Primary key,
	FK_sCoquanbanhanhID NVarchar(15) NOT NULL References tblCoquan(PK_sCoquanID),
	--FK_iNguoikyID Smallint NOT NULL References tblNguoiky(PK_iNguoikyID),
	FK_sDanhmucvanbanID NVarchar(15) NOT NULL References tblDanhmucvanban(PK_sDanhmucvanbanID),
	sTenvanban Nvarchar(300) NOT NULL,
	sSohieu NVarchar(50) NULL,
	dNgaybanhanh Datetime NULL Default GETDATE(),
	dNgaycohieuluc Datetime NULL,
	dNgayhethan Datetime NULL,
	iTrangthai Smallint NULL Default 1,
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
	iSothutu Smallint NULL,
	sNoidung Nvarchar(MAX) NOT NULL
)

--Bảng khoản
Create table tblKhoan
(
	PK_iKhoanID Bigint Identity Primary key,
	FK_iDieuID Bigint NOT NULL References tblDieu(PK_iDieuID),
	iSothutu Smallint NULL,
	sNoidung Nvarchar(MAX) NOT NULL
)

--Bảng điểm
Create table tblDiem
(
	PK_iDiemID Bigint Identity Primary key,
	FK_iKhoanID Bigint NOT NULL References tblKhoan(PK_iKhoanID),
	iSothutu Smallint NULL,
	sNoidung Nvarchar(MAX) NOT NULL
)

--Bảng mục
Create table tblMuc
(
	PK_iMucID Bigint Identity Primary key,
	FK_iKhoanID Bigint NOT NULL References tblKhoan(PK_iKhoanID),
	iSothutu Smallint NULL,
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
--drop table tblTuloai
create table tblTuloai
(
	PK_iTuloaiID Bigint Identity Primary key,
	sNhan NVarchar(5),
	sTu NVarchar(100),
	iSoluong int,
	FK_iDieuID Bigint Default 0,
	FK_iKhoanID Bigint Default 0,
	FK_iDiemID Bigint Default 0
)