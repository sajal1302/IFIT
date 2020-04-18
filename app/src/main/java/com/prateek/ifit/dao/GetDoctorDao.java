package com.prateek.ifit.dao;

public class GetDoctorDao {

    String doctorname;
    String doctordegree;
    String doctorspeciality;
    String doctorSchedule;
    int doctorinmage;

    public GetDoctorDao(String s, Integer integer, String s1, String s2) {

        doctorname= s;
        doctorinmage= integer;
        doctordegree= s1;
        doctorspeciality= s2;
    }

    public int getDoctorinmage() {
        return doctorinmage;
    }

    public void setDoctorinmage(int doctorinmage) {
        this.doctorinmage = doctorinmage;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDoctordegree() {
        return doctordegree;
    }

    public void setDoctordegree(String doctordegree) {
        this.doctordegree = doctordegree;
    }

    public String getDoctorspeciality() {
        return doctorspeciality;
    }

    public void setDoctorspeciality(String doctorspeciality) {
        this.doctorspeciality = doctorspeciality;
    }

    public String getDoctorSchedule() {
        return doctorSchedule;
    }

    public void setDoctorSchedule(String doctorSchedule) {
        this.doctorSchedule = doctorSchedule;
    }
}
