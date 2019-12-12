package com.example.DuAn1_SangCtph07783.moder;

public class ThongTin {
    public int id ;
    public String linkAnh;
    public String tenSach;
    public String tacGia;
    public String nhaXuatBan;
    public int soMuc;
    public String nguon;
    public int idNameTT;
    public int idCT;

    public ThongTin() {
    }

    public ThongTin(int id, String linkAnh, String tenSach, String tacGia, String nhaXuatBan, int soMuc, String nguon, int idNameTT, int idCT ) {
        this.id = id;
        this.linkAnh = linkAnh;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.soMuc = soMuc;
        this.nguon = nguon;
        this.idNameTT = idNameTT;
        this.idCT = idCT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getSoMuc() {
        return soMuc;
    }

    public void setSoMuc(int soMuc) {
        this.soMuc = soMuc;
    }

    public String getNguon() {
        return nguon;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public int getIdNameTT() {
        return idNameTT;
    }

    public void setIdNameTT(int idNameTT) {
        this.idNameTT = idNameTT;
    }

    public int getIdCT() {
        return idCT;
    }

    public void setIdCT(int idCT) {
        this.idCT = idCT;
    }
}
