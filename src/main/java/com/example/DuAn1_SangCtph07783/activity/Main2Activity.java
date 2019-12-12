package com.example.DuAn1_SangCtph07783.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.DuAn1_SangCtph07783.R;
import com.example.DuAn1_SangCtph07783.adapter.MyFragmentAdapter;
import com.example.DuAn1_SangCtph07783.adapter.SearchAdapter;
import com.example.DuAn1_SangCtph07783.database.ListDanhSachDAO;
import com.example.DuAn1_SangCtph07783.database.SachTruyenSqlite;
import com.example.DuAn1_SangCtph07783.fragment.FrmAcount;
import com.example.DuAn1_SangCtph07783.fragment.FrmGioiThieu;
import com.example.DuAn1_SangCtph07783.fragment.FrmTruyen;
import com.example.DuAn1_SangCtph07783.fragment.FrmYeuthich;
import com.example.DuAn1_SangCtph07783.moder.Search;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FrmYeuthich frmYeuthich;
    private SearchAdapter searchAdapter;
    private ArrayList<Search> inputList;
    private AutoCompleteTextView autoCompleteTextView;
    private ListDanhSachDAO listDanhSachDAO;
    SachTruyenSqlite sachTruyenSqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sachTruyenSqlite = new SachTruyenSqlite(this);
        sachTruyenSqlite.createDataBase();

        autoCompleteTextView = findViewById(R.id.edtSearch);
        listDanhSachDAO = new ListDanhSachDAO(this);

        inputList = listDanhSachDAO.getALL();

        searchAdapter = new SearchAdapter(this, R.layout.auto_item, inputList);
        autoCompleteTextView.setThreshold(0);
        autoCompleteTextView.setAdapter(searchAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = String.valueOf(autoCompleteTextView.getText()) ;
                Log.d("name", name);

                SQLiteDatabase sqLiteDatabase = sachTruyenSqlite.getWritableDatabase();
                String SachSQL = "SELECT IDName FROM Name Where NameSach ="+ "\"" + name + "\"";

                Cursor cursor = sqLiteDatabase.rawQuery(SachSQL, null);

                if (cursor != null) {
                    if (cursor.getCount() > 0) {

                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            String idName = cursor.getString(cursor.getColumnIndex("IDName"));
                            Intent intent = new Intent(Main2Activity.this, ActInformation.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("i", idName);
                            intent.putExtra("TT", bundle);
                            startActivity(intent);

                            cursor.moveToNext();

                        }
                        cursor.close();
                    }

                }
            }
        });


        tabLayout = findViewById(R.id.tabHome);
        viewPager = findViewById(R.id.viewPagerHome);
        final MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());

        adapter.addFragment(new FrmTruyen(), "Truyện");
        adapter.addFragment(new FrmAcount(), "Account");
        adapter.addFragment(new FrmYeuthich(), "Yêu thích");

        adapter.addFragment(new FrmGioiThieu(), "Giới Thiệu");

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {

                    FrmYeuthich.reload();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }



}

