package com.example.DuAn1_SangCtph07783.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.DuAn1_SangCtph07783.R;
import com.example.DuAn1_SangCtph07783.database.SachTruyenSqlite;
import com.example.DuAn1_SangCtph07783.moder.MucLuc;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

public class ActShow extends AppCompatActivity {
    private LinearLayout lnShow, lnClick;
    private TextView tvDoc, titleShow, tvNamesachShow, tvSoMucCt;
    private RelativeLayout MenuTop, MenuBotton;
    private RadioGroup radioGroup;
    private int dau, cuoi, i, u, idName, j;
    public int saveID;
    private String linkAnh;
    private ImageView imgNext, imgSkip, imgBack, imgZoom_in, imgZoom_out;
    private SachTruyenSqlite sachTruyenSqlite;
    private SharedPreferences sharedPreferences, sharedPreferences1;
    MucLuc mucLuc;
    AlertDialog.Builder builder;

    private CallbackManager callbackManager;

    ShareDialog shareDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_show);
        anhXa();
        FacebookSdk.sdkInitialize(ActShow.this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(ActShow.this);
        builder = new AlertDialog.Builder(this);
        shareDialog.registerCallback(callbackManager, callback);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Picasso.with(this).load(linkAnh);


        sharedPreferences = this.getSharedPreferences("mau", MODE_PRIVATE);
        lnShow.setBackgroundColor(sharedPreferences.getInt("mau", 0xffffffff));
        tvDoc.setTextColor(sharedPreferences.getInt("text", 0xff000000));

        sharedPreferences1 = this.getSharedPreferences("size", MODE_PRIVATE);
        tvDoc.setTextSize(sharedPreferences1.getFloat("size", 14));

        sachTruyenSqlite = new SachTruyenSqlite(this);
        mucLuc = new MucLuc();
        setBackground();
        anView();
        getData();
        setNameSachShow();
        nextAndSkip();
        setImageShare();

    }


    private void setImageShare() {
        ShareLinkContent photo = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(linkAnh)).build();

        ShareButton shareButton = (ShareButton) findViewById(R.id.fb_share_button);
        shareButton.setShareContent(photo);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private FacebookCallback<Sharer.Result> callback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onSuccess(Sharer.Result result) {
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void anhXa() {
        lnShow = findViewById(R.id.lnShow);
        MenuBotton = findViewById(R.id.menuBotton);
        MenuTop = findViewById(R.id.MenuTop);
        radioGroup = findViewById(R.id.groupRadio);
        tvDoc = findViewById(R.id.tvDoc);
        titleShow = findViewById(R.id.titleShow);
        tvNamesachShow = findViewById(R.id.tvNamesachShow);
        lnClick = findViewById(R.id.lnClick);
        imgBack = findViewById(R.id.imgBack);
        imgNext = findViewById(R.id.imgNext);
        imgSkip = findViewById(R.id.imgSkip);
        imgZoom_in = findViewById(R.id.imgZoom_in);
        imgZoom_out = findViewById(R.id.imgZoom_out);
        tvSoMucCt = findViewById(R.id.tvSoMucCt);

    }

    private void anView() {
        lnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MenuBotton.getVisibility() != View.VISIBLE && MenuTop.getVisibility() != View.VISIBLE) {
                    MenuBotton.setVisibility(View.VISIBLE);
                    MenuTop.setVisibility(View.VISIBLE);

                } else {
                    MenuBotton.setVisibility(View.GONE);
                    MenuTop.setVisibility(View.GONE);
                }
            }
        });

    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("show");
        titleShow.setText(bundle.getString("title"));
        tvDoc.setText(bundle.getString("text"));
        idName = Integer.parseInt(bundle.getString("idName"));
//        dau = Integer.parseInt(bundle.getString("positionDau"));
        i = Integer.parseInt(bundle.getString("position"));
        tvSoMucCt.setText(bundle.getString("stt"));
        u = i + 1;
//        cuoi = Integer.parseInt(bundle.getString("positionCuoi"));
        mucLuc.setIdCT(i);

        imgNext.setImageResource(R.drawable.icons_next);
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL = "SELECT MIN(Stt),Max(Stt) FROM ChiTiet WHERE IDName = " + idName;

        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    dau = Integer.parseInt(cursor.getString(cursor.getColumnIndex("MIN(Stt)")));
                    cuoi = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Max(Stt)")));
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }
        sqLiteDatabase.close();

    }

    public void zoomLon(View view) {
        float currentSize = tvDoc.getTextSize(); // default size
        float sp = currentSize / getResources().getDisplayMetrics().scaledDensity;
        AlphaAnimation alpha1 = new AlphaAnimation(1F, 1F);
        alpha1.setDuration(0);
        alpha1.setFillAfter(true);
        imgZoom_out.startAnimation(alpha1);
        if (sp >= 20) {
            AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            imgZoom_in.startAnimation(alpha);
        } else {
            float newSize = (float) (sp + 1); // new size is twice bigger than default one
            tvDoc.setTextSize(newSize);
            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putFloat("size", newSize);
            editor.commit();
        }
    }

    public void zoomOut(View view) {
        float currentSize = tvDoc.getTextSize(); // default size
        float sp = currentSize / getResources().getDisplayMetrics().scaledDensity;
        AlphaAnimation alpha1 = new AlphaAnimation(1F, 1F);
        alpha1.setDuration(0);
        alpha1.setFillAfter(true);
        imgZoom_in.startAnimation(alpha1);
        if (sp <= 10) {
            AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            imgZoom_out.startAnimation(alpha);
        } else {
            float newSize = (float) (sp - 1); // new size is twice bigger than default one
            tvDoc.setTextSize(newSize);
            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putFloat("size", newSize);
            editor.commit();
        }
    }

    public void Save(View view) {


        builder.setMessage("Bạn có muốn lưu chương này không ?")
                .setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveID = mucLuc.getIdCT();
                SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getReadableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("IDCT", saveID);
                sqLiteDatabase.update("ThongTin", contentValues, "IDName" + "=?", new String[]{String.valueOf(idName)});
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.setTitle("Lưu chương");
        alert.show();

    }

    private void nextAndSkip() {

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cuoi == Integer.parseInt(tvSoMucCt.getText().toString())) {
                    AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F);
                    alpha.setDuration(0);
                    alpha.setFillAfter(true);
                    imgNext.startAnimation(alpha);
//                    imgNext.setImageResource(R.drawable.icons_nextsub);
                } else {
                    AlphaAnimation alpha = new AlphaAnimation(1F, 1F);
                    alpha.setDuration(0);
                    alpha.setFillAfter(true);
                    imgSkip.startAnimation(alpha);
                    imgNext.startAnimation(alpha);


//                    imgNext.setImageResource(R.drawable.icons_next);
                    SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
                    String SachSQL = "SELECT NoiDung,Stt, NameMuc FROM ChiTiet WHERE IDCT = " + (u++);

                    Cursor cursor = sqLiteDatabase.rawQuery(SachSQL, null);

                    if (cursor != null) {
                        if (cursor.getCount() > 0) {

                            cursor.moveToFirst();
                            while (!cursor.isAfterLast()) {
                                mucLuc.setNoidung(cursor.getString(cursor.getColumnIndex("NoiDung")));
                                mucLuc.setTitle(cursor.getString(cursor.getColumnIndex("NameMuc")));
                                tvSoMucCt.setText(cursor.getString(cursor.getColumnIndex("Stt")));
                                titleShow.setText(mucLuc.getTitle());
                                tvDoc.setText(mucLuc.getNoidung());
                                mucLuc.setIdCT(u - 1);
                                cursor.moveToNext();

                            }
                            cursor.close();
                        }
                    }
                    sqLiteDatabase.close();
                }
            }
//            }
        });

        imgSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dau == Integer.parseInt(tvSoMucCt.getText().toString()) ) {
                    AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F);
                    alpha.setDuration(0);
                    alpha.setFillAfter(true);
                    imgSkip.startAnimation(alpha);
//                    imgSkip.setImageResource(R.drawable.icons_skipsub);
                } else {
                    AlphaAnimation alpha = new AlphaAnimation(1F, 1F);
                    alpha.setDuration(0);
                    alpha.setFillAfter(true);
                    imgSkip.startAnimation(alpha);
                    imgNext.startAnimation(alpha);


//                    imgSkip.setImageResource(R.drawable.icons_skip);
                    j = u--;
                    SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
                    String SachSQL1 = "SELECT NoiDung,Stt, NameMuc FROM ChiTiet WHERE IDCT = " + (j - 2);
                    Cursor cursor = sqLiteDatabase.rawQuery(SachSQL1, null);
                    Log.e("i", String.valueOf(j - 2));
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            while (!cursor.isAfterLast()) {
                                mucLuc.setNoidung(cursor.getString(cursor.getColumnIndex("NoiDung")));
                                mucLuc.setTitle(cursor.getString(cursor.getColumnIndex("NameMuc")));
                                tvSoMucCt.setText(cursor.getString(cursor.getColumnIndex("Stt")));
                                titleShow.setText(mucLuc.getTitle());
                                tvDoc.setText(mucLuc.getNoidung());
                                mucLuc.setIdCT(j - 2);
                                cursor.moveToNext();
                            }
                            cursor.close();
                        }
                    }
                    sqLiteDatabase.close();
                }
            }
        });
    }

    private void setBackground() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int position) {
                switch (position) {
                    case R.id.rdBtnTrang:
                        lnShow.setBackgroundColor(0xffffffff);
                        tvDoc.setTextColor(0xff000000);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("mau", 0xffffffff);
                        editor.putInt("text", 0xff000000);
                        editor.putBoolean("check", true);
                        editor.commit();
                        break;
                    case R.id.rdBtnVang:
                        lnShow.setBackgroundColor(0xffffff00);
                        tvDoc.setTextColor(0xff000000);
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.putInt("mau", 0xffffff00);
                        editor1.putInt("text", 0xff000000);
                        editor1.putBoolean("check", true);
                        editor1.commit();
                        break;
                    case R.id.rdBtnXam:
                        lnShow.setBackgroundColor(0xff000000);
                        tvDoc.setTextColor(0xffffffff);
                        SharedPreferences.Editor editor2 = sharedPreferences.edit();
                        editor2.putInt("mau", 0xff000000);
                        editor2.putInt("text", 0xffffffff);
                        editor2.putBoolean("check", true);
                        editor2.commit();
                        break;

                }
            }
        });
    }

    private void setNameSachShow() {
        SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
        String SachSQL1 = "SELECT NameSach, Anh FROM Name WHERE IDName =" + idName;
        Cursor cursor = sqLiteDatabase.rawQuery(SachSQL1, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    tvNamesachShow.setText(cursor.getString(cursor.getColumnIndex("NameSach")));
                    linkAnh = cursor.getString(cursor.getColumnIndex("Anh"));
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
        sqLiteDatabase.close();
    }
}
