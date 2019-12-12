package com.example.DuAn1_SangCtph07783.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DuAn1_SangCtph07783.R;
import com.example.DuAn1_SangCtph07783.database.SachTruyenSqlite;
import com.example.DuAn1_SangCtph07783.moder.MucLuc;
import com.example.DuAn1_SangCtph07783.moder.ThongTin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActInformation extends AppCompatActivity {

    private TextView tvNameTT, tvTacGiaTT, tvNXBTT, tvSoMucTT, tvNguonTT, tvNamesachShow;
    private List<MucLuc> mucLucList;
    private ImageView imageView, imgBack;
    private SachTruyenSqlite sachTruyenSqlite;
    int id, idsave;

    String noiDung, soTT, nameMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_information2);
        imageView = findViewById(R.id.imgTTAvata);
        tvNameTT = findViewById(R.id.tvNameTT);
        tvTacGiaTT = findViewById(R.id.tvTacGiaTT);
        tvNXBTT = findViewById(R.id.tvNXBTT);
        tvSoMucTT = findViewById(R.id.tvSoMucTT);
        tvNguonTT = findViewById(R.id.tvNguonTT);
        tvNamesachShow = findViewById(R.id.tvNamesachShow);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("TT");
        id = Integer.parseInt(bundle.getString("i"));

        mucLucList = new ArrayList<>();

        sachTruyenSqlite = new SachTruyenSqlite(this);
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL = "SELECT * FROM ChiTiet WHERE IDName = " + id;

        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    MucLuc mucLuc = new MucLuc();
                    mucLuc.setIdCT(Integer.parseInt(cursor.getString(cursor.getColumnIndex("IDCT"))));
                    mucLuc.setNamemuc(cursor.getString(cursor.getColumnIndex("Muc")));
                    mucLuc.setNoidung(cursor.getString(cursor.getColumnIndex("NoiDung")));
                    mucLuc.setTitle(cursor.getString(cursor.getColumnIndex("NameMuc")));
                    mucLuc.setIdNameCT(Integer.parseInt(cursor.getString(cursor.getColumnIndex("IDName"))));
                    mucLuc.setStt(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Stt"))));
                    mucLucList.add(mucLuc);
                    cursor.moveToNext();
                }
                cursor.close();
            }


        }
        String thongtinSQL = "SELECT * FROM ThongTin WHERE IDName = " + id;

        Cursor cursor1 = sqLiteDatabase.rawQuery(thongtinSQL, null);


        if (cursor1 != null) {
            if (cursor1.getCount() > 0) {

                cursor1.moveToFirst();
                while (!cursor1.isAfterLast()) {
                    ThongTin thongTin = new ThongTin();
                    thongTin.setId(Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("IDTT"))));
                    thongTin.setTenSach(cursor1.getString(cursor1.getColumnIndex("Name")));
                    thongTin.setTacGia(cursor1.getString(cursor1.getColumnIndex("TacGia")));
                    thongTin.setNhaXuatBan(cursor1.getString(cursor1.getColumnIndex("NXB")));
                    thongTin.setSoMuc(Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("SoMuc"))));
                    thongTin.setNguon(cursor1.getString(cursor1.getColumnIndex("Nguon")));
                    thongTin.setIdNameTT(Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("IDName"))));
                    thongTin.setLinkAnh(cursor1.getString(cursor1.getColumnIndex("Anh")));


                    tvNameTT.setText(thongTin.getTenSach());
                    tvTacGiaTT.setText(thongTin.getTacGia());
                    tvNXBTT.setText(thongTin.getNhaXuatBan());
                    tvSoMucTT.setText(String.valueOf(thongTin.getSoMuc()));
                    tvNguonTT.setText(thongTin.getNguon());
                    tvNamesachShow.setText(thongTin.getTenSach());
                    Picasso.with(this).load(thongTin.getLinkAnh()).into(imageView);

                    Intent intent1 = new Intent(this, ActShow.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("nameSach", thongTin.getTenSach());
                    intent1.putExtra("Name", bundle1);

                    cursor1.moveToNext();

                }
                cursor1.close();
            }


        }


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.ryMucLuc);
        MucLucAdapter mucLucAdapter = new MucLucAdapter(this, mucLucList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mucLucAdapter);

    }


    public void DocTiep(View view) {
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL = "SELECT IDCT FROM ThongTin WHERE IDName =" + id;

        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    idsave = (Integer.parseInt(cursor.getString(cursor.getColumnIndex("IDCT"))));
                    Log.e("IDCT", String.valueOf(idsave));
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }


        if (idsave == 1) {
            Toast.makeText(ActInformation.this, "Bạn chưa đoạc cuốn sách này lần nào", Toast.LENGTH_SHORT).show();
        } else {
            String Save = "SELECT * FROM ChiTiet WHERE IDCT = " + idsave;

            Cursor cursor1 = sqLiteDatabase.rawQuery(Save, null);

            if (cursor1 != null) {
                if (cursor1.getCount() > 0) {

                    cursor1.moveToFirst();
                    while (!cursor1.isAfterLast()) {

                        String idct = cursor1.getString(cursor1.getColumnIndex("IDCT"));
                       String Noidung = cursor1.getString(cursor1.getColumnIndex("NoiDung"));
                       String title  = cursor1.getString(cursor1.getColumnIndex("NameMuc"));
                       String idName = cursor1.getString(cursor1.getColumnIndex("IDName"));
                        String stt = cursor1.getString(cursor1.getColumnIndex("Stt"));


                        Intent intent = new Intent(ActInformation.this, ActShow.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("position", idct);
                        bundle.putString("stt", stt);
                        bundle.putString("idName", idName);
                        bundle.putString("text", Noidung);
                        bundle.putString("title", title);
                        intent.putExtra("show", bundle);
                        startActivity(intent);
                        cursor1.moveToNext();
                    }
                    cursor1.close();
                }
            }
        }


    }
}
