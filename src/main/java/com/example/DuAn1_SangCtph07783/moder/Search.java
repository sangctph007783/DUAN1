package com.example.DuAn1_SangCtph07783.moder;

public class Search {
    private int id;
    public String nameSearch;


    public Search(int id, String nameSearch) {
        this.id = id;
        this.nameSearch = nameSearch;
    }

    public String getIdAndName(){
        return id + nameSearch;
    }
    public Search() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }
}


