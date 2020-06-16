package com.xjtu.edu.research.model;

import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Qualifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Record {
    private int id;
    private String childName;
    private Integer childAge;
    private String parName;
    private String phone;
    private Date date;
    private String school;
    private String picked;
    private String result1;
    private String result2;
    private String remarks;

    public Record(int id, String childName, Integer childAge, String parName, String phone, Date date, String school, String picked, String result1, String result2, String remarks) {
        this.id = id;
        this.childName = childName;
        this.childAge = childAge;
        this.parName = parName;
        this.phone = phone;
        this.date = date;
        this.school = school;
        this.picked = picked;
        this.result1 = result1;
        this.result2 = result2;
        this.remarks = remarks;
    }

    //必须加空构造函数
    public Record() {

    }

    public int getId() {
        return id;
    }

    public String getChildName() {
        return childName;
    }

    public Integer getChildAge() {
        return childAge;
    }

    public String getParName() {
        return parName;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDate() {
        return date;
    }

    public String getSchool() {
        return school;
    }

    public String getPicked() {
        return picked;
    }

    public String getResult1() {
        return result1;
    }

    public String getResult2() {
        return result2;
    }

    public String getRemarks() {
        return remarks;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setChildAge(Integer childAge) {
        this.childAge = childAge;
    }

    public void setParName(String parName) {
        this.parName = parName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Qualifier
    public void setDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
        }
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setPicked(List<Object> picked) {
        this.picked = JSONArray.toJSONString(picked);
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
