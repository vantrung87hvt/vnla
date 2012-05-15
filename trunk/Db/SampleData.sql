USE vnla
GO

INSERT INTO tblVanban(FK_sCoquanbanhanhID, FK_sDanhmucvanbanID, sTenvanban, sSohieu, dNgaybanhanh, sGhichu)
VALUES(N'QH', N'LUAT', N'LUẬT HÔN NHÂN VÀ GIA ĐÌNH', N'22/2000/QH10', '09/06/2000', N'')

INSERT INTO tblChuong(FK_iVanbanID, iSothutu, sTenchuong)
VALUES(1, 1, N'Chương I - NHỮNG QUY ĐỊNH CHUNG')
INSERT INTO tblChuong(FK_iVanbanID, iSothutu, sTenchuong)
VALUES(1, 10, N'LY HÔN')

INSERT INTO tblDieu(FK_iChuongID, iSothutu, sNoidung)
VALUES(1, 1, N'Nhiệm vụ và phạm vi điều chỉnh của Luật hôn nhân và gia đình')
INSERT INTO tblDieu(FK_iChuongID, iSothutu, sNoidung)
VALUES(1, 2, N'Những nguyên tắc cơ bản của chế độ hôn nhân và gia đình')


INSERT INTO tblKhoan(FK_iDieuID, iSothutu, sNoidung)
VALUES(2, 1, N'Hôn nhân tự nguyện, tiến bộ, một vợ một chồng, vợ chồng bình đẳng.')



