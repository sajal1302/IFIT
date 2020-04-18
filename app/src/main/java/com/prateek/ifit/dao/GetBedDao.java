package com.prateek.ifit.dao;

public class GetBedDao {

    int hospimage;
    String address;
    String rrr;
    String hospname;
    int id;

    public GetBedDao(String s, String haddress, String s1, Integer integer) {
        hospname=s;
        address= haddress;
        rrr= s1;
        hospimage= integer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHospimage() {
        return hospimage;
    }

    public String getHospname() {
        return hospname;
    }

    public void setHospname(String hospname) {
        this.hospname = hospname;
    }

    public void setHospimage(int hospimage) {
        this.hospimage = hospimage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRrr() {
        return rrr;
    }

    public void setRrr(String rrr) {
        this.rrr = rrr;
    }

}
