package com.example.DuAn1_SangCtph07783.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.DuAn1_SangCtph07783.moder.Search;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<String> implements Filterable {

    private ArrayList<Search> inputList;
    private ArrayList<Search> autoCompleteList;

    public SearchAdapter(Context context, int textViewResourceId,ArrayList<Search> inputList) {
        super(context, textViewResourceId);
        this.inputList = inputList;
    }

    @Override
    public int getCount() {
        return autoCompleteList == null ? 0 : autoCompleteList.size();
    }
    @Override
    public String getItem(int position) {
        return autoCompleteList.get(position).getNameSearch();
}
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    autoCompleteList = new ArrayList<>();
                    for (int i = 0; i < inputList.size(); i++) {
                        if (inputList.get(i).getNameSearch().toLowerCase().contains(constraint.toString().toLowerCase()))
                           {
                            autoCompleteList.add(inputList.get(i));
                        }
                    }

                    filterResults.values = autoCompleteList;
                    filterResults.count = autoCompleteList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
