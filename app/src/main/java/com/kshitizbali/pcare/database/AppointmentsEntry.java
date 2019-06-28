package com.kshitizbali.pcare.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointments")
public class AppointmentsEntry {

    @PrimaryKey
    private int id;
    private String appointment_date;
    private String appointment_time;
    private String doctor_name;
    private String doctor_rating;
    private String doctor_photo;
    private int doctor_exp;
    private String doctor_res_prog;
    private String doctor_bio;
    private String is_booked;

    @Ignore
    public AppointmentsEntry(String appointment_date,String appointment_time,String doctor_name,String doctor_rating,String doctor_photo,int doctor_exp,
                             String doctor_res_prog,String doctor_bio,String is_booked){
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.doctor_name = doctor_name;
        this.doctor_rating =doctor_rating;
        this.doctor_photo = doctor_photo;
        this.doctor_exp = doctor_exp;
        this.doctor_res_prog = doctor_res_prog;
        this.doctor_bio=doctor_bio;
        this.is_booked=is_booked;
    }

    public AppointmentsEntry(int id,String appointment_date,String appointment_time,String doctor_name,String doctor_rating,String doctor_photo,int doctor_exp,
                             String doctor_res_prog,String doctor_bio,String is_booked){
        this.id = id;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.doctor_name = doctor_name;
        this.doctor_rating =doctor_rating;
        this.doctor_photo = doctor_photo;
        this.doctor_exp = doctor_exp;
        this.doctor_res_prog = doctor_res_prog;
        this.doctor_bio=doctor_bio;
        this.is_booked=is_booked;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_rating() {
        return doctor_rating;
    }

    public void setDoctor_rating(String doctor_rating) {
        this.doctor_rating = doctor_rating;
    }

    public String getDoctor_photo() {
        return doctor_photo;
    }

    public void setDoctor_photo(String doctor_photo) {
        this.doctor_photo = doctor_photo;
    }

    public int getDoctor_exp() {
        return doctor_exp;
    }

    public void setDoctor_exp(int doctor_exp) {
        this.doctor_exp = doctor_exp;
    }

    public String getDoctor_res_prog() {
        return doctor_res_prog;
    }

    public void setDoctor_res_prog(String doctor_res_prog) {
        this.doctor_res_prog = doctor_res_prog;
    }

    public String getDoctor_bio() {
        return doctor_bio;
    }

    public void setDoctor_bio(String doctor_bio) {
        this.doctor_bio = doctor_bio;
    }

    public String getIs_booked() {
        return is_booked;
    }

    public void setIs_booked(String is_booked) {
        this.is_booked = is_booked;
    }
}
