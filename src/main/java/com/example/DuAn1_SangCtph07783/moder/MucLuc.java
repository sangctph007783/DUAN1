package com.example.DuAn1_SangCtph07783.moder;

public class MucLuc {
    public int stt;
    public int idCT;
    public String namemuc;
    public String noidung;
    public int idNameCT;
    public String title;

    public MucLuc(int stt, int idCT, String namemuc, String noidung, int idNameCT, String title) {
        this.stt = stt;
        this.idCT = idCT;
        this.namemuc = namemuc;
        this.noidung = noidung;
        this.idNameCT = idNameCT;
        this.title = title;
    }

    public MucLuc() {
    }

    public String getNamemuc() {
        return namemuc;
    }

    public void setNamemuc(String namemuc) {
        this.namemuc = namemuc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdCT() {
        return idCT;
    }

    public void setIdCT(int idCT) {
        this.idCT = idCT;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getIdNameCT() {
        return idNameCT;
    }

    public void setIdNameCT(int idNameCT) {
        this.idNameCT = idNameCT;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
}
