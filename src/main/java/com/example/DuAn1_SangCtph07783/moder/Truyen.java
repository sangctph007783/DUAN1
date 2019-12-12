package com.example.DuAn1_SangCtph07783.moder;

public class Truyen {
    public int idName;
    public String  styles;
    public String NameSach;
    public String anh;
    public  int like;

    public Truyen() {
    }

    public Truyen(int idName, String styles, String nameSach, String anh, int like) {
        this.idName = idName;
        this.styles = styles;
        NameSach = nameSach;
        this.anh = anh;
        this.like = like;
    }


    public int getIdName() {
        return idName;
    }

    public void setIdName(int idName) {
        this.idName = idName;
    }

    public String getStyles() {
        return styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public String getNameSach() {
        return NameSach;
    }

    public void setNameSach(String nameSach) {
        NameSach = nameSach;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
