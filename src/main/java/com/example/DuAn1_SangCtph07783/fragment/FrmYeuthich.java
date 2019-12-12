package com.example.DuAn1_SangCtph07783.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DuAn1_SangCtph07783.R;
import com.example.DuAn1_SangCtph07783.adapter.YeuThichAdapter;
import com.example.DuAn1_SangCtph07783.database.ListDanhSachDAO;
import com.example.DuAn1_SangCtph07783.moder.YeuThich;

import java.util.List;


public class FrmYeuthich extends Fragment {
    public static List<YeuThich> yeuThichList;
    public static ListDanhSachDAO listDanhSachDAO;
    public static YeuThichAdapter yeuThichAdapter;
    public FrmYeuthich() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_yeuthich, container, false);

        listDanhSachDAO = new ListDanhSachDAO(getContext());
//        yeuThichList.clear();
        yeuThichList = listDanhSachDAO.getAllYeuThich();
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 3);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ryYeuThich);
        yeuThichAdapter = new YeuThichAdapter(getContext(), yeuThichList);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(yeuThichAdapter);
        return view;
    }

    public static void reload(){
        yeuThichList.clear();
        yeuThichList.addAll(listDanhSachDAO.getAllYeuThich());
        yeuThichAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Rwsume","1");
    }
}
