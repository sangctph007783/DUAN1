package com.example.DuAn1_SangCtph07783.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.DuAn1_SangCtph07783.moder.Search;
import com.example.DuAn1_SangCtph07783.moder.Truyen;
import com.example.DuAn1_SangCtph07783.moder.TruyenCuoi;
import com.example.DuAn1_SangCtph07783.moder.TruyenKiemHiep;
import com.example.DuAn1_SangCtph07783.moder.TruyenVietNam;
import com.example.DuAn1_SangCtph07783.moder.YeuThich;

import java.util.ArrayList;
import java.util.List;

public class ListDanhSachDAO {

    private SachTruyenSqlite sachTruyenSqlite;

    public ListDanhSachDAO(Context context) {
        this.sachTruyenSqlite = new SachTruyenSqlite(context);

    }

    private String LIST_TABLE = "Name";
    public String ID_NAME = "IDName";
    public String NAME = "NameSach";
    public String STYLES = "Styles";
    public String ANH = "Anh";





    public ArrayList<Search> getALL() {
        ArrayList<Search> searchList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL = "SELECT * FROM Name ";

        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL,null);

        if(cursor !=null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Search search = new Search();

                    search.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_NAME))));
                    search.setNameSearch(cursor.getString(cursor.getColumnIndex(NAME)));


                    searchList.add(search);
                    cursor.moveToNext();

                }
                cursor.close();
            }

        }
        return  searchList;
    }



    public List<YeuThich> getAllYeuThich() {
        List<YeuThich> yeuThichList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL = "SELECT * FROM Name WHERE YeuThich = 1 ";

        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL,null);

        if(cursor !=null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    YeuThich yeuThich = new YeuThich();

                    yeuThich.setIdName(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_NAME))));
                    yeuThich.setNameSach(cursor.getString(cursor.getColumnIndex(NAME)));
                    yeuThich.setAnh(cursor.getString(cursor.getColumnIndex(ANH)));
                    yeuThich.setStyles(cursor.getString(cursor.getColumnIndex(STYLES)));

                    yeuThichList.add(yeuThich);
                    cursor.moveToNext();

                }
                cursor.close();
            }

        }
        return  yeuThichList;
    }







    public List<Truyen> getALLTruyen(){
        List<Truyen> truyenList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL = "SELECT * FROM Name WHERE Styles = \"Truyện tổng hợp\" ";

        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL,null);

        if(cursor !=null){
            if (cursor.getCount() >0){

                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    Truyen truyen = new Truyen();

                    truyen.setIdName(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_NAME))));
                    truyen.setNameSach(cursor.getString(cursor.getColumnIndex(NAME)));
                    truyen.setAnh(cursor.getString(cursor.getColumnIndex(ANH)));
                    truyen.setStyles(cursor.getString(cursor.getColumnIndex(STYLES)));
                    truyen.setLike(Integer.parseInt(cursor.getString(cursor.getColumnIndex("YeuThich"))));

                    truyenList.add(truyen);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }
        return truyenList;
    }


    public List<TruyenCuoi> getALLTruyencuoi(){
        List<TruyenCuoi> truyenCuoiList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL = "SELECT * FROM Name WHERE Styles = \"Truyện cười\" ";

        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL,null);

        if(cursor !=null){
            if (cursor.getCount() >0){

                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    TruyenCuoi truyenCuoi = new TruyenCuoi();

                    truyenCuoi.setIdName(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_NAME))));
                    truyenCuoi.setNameSach(cursor.getString(cursor.getColumnIndex(NAME)));
                    truyenCuoi.setAnh(cursor.getString(cursor.getColumnIndex(ANH)));
                    truyenCuoi.setStyles(cursor.getString(cursor.getColumnIndex(STYLES)));
                    truyenCuoi.setLike(Integer.parseInt(cursor.getString(cursor.getColumnIndex("YeuThich"))));

                    truyenCuoiList.add(truyenCuoi);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }
        return truyenCuoiList;
    }


    public List<TruyenKiemHiep> getALLTruyenKiemHiep(){
        List<TruyenKiemHiep> truyenKiemHiepList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL = "SELECT * FROM Name WHERE Styles = \"Truyện kiếm hiệp\" ";

        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL,null);

        if(cursor !=null){
            if (cursor.getCount() >0){

                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    TruyenKiemHiep truyenKiemHiep = new TruyenKiemHiep();

                    truyenKiemHiep.setIdName(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_NAME))));
                    truyenKiemHiep.setNameSach(cursor.getString(cursor.getColumnIndex(NAME)));
                    truyenKiemHiep.setAnh(cursor.getString(cursor.getColumnIndex(ANH)));
                    truyenKiemHiep.setStyles(cursor.getString(cursor.getColumnIndex(STYLES)));
                    truyenKiemHiep.setLike(Integer.parseInt(cursor.getString(cursor.getColumnIndex("YeuThich"))));

                    truyenKiemHiepList.add(truyenKiemHiep);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }
        return truyenKiemHiepList;
    }

    public List<TruyenVietNam> getALLTruyenVietNam(){
        List<TruyenVietNam> truyenVietNamList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL = "SELECT * FROM Name WHERE Styles = \"Truyện Việt Nam\" ";

        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL,null);

        if(cursor !=null){
            if (cursor.getCount() >0){

                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    TruyenVietNam truyenVietNam = new TruyenVietNam();
                    truyenVietNam.setIdName(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_NAME))));
                    truyenVietNam.setNameSach(cursor.getString(cursor.getColumnIndex(NAME)));
                    truyenVietNam.setAnh(cursor.getString(cursor.getColumnIndex(ANH)));
                    truyenVietNam.setStyles(cursor.getString(cursor.getColumnIndex(STYLES)));
                    truyenVietNam.setLike(Integer.parseInt(cursor.getString(cursor.getColumnIndex("YeuThich"))));

                    truyenVietNamList.add(truyenVietNam);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }
        return truyenVietNamList;
    }
}

