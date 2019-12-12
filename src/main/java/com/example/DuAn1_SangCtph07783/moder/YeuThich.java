package com.example.DuAn1_SangCtph07783.moder;

public class YeuThich {
    public int idName;
    public String  styles;
    public String NameSach;
    public String anh;

    public YeuThich() {
    }

    public YeuThich(int idName, String styles, String nameSach, String anh) {
        this.idName = idName;
        this.styles = styles;
        NameSach = nameSach;
        this.anh = anh;
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
}
