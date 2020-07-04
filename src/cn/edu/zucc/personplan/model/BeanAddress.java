package cn.edu.zucc.personplan.model;

public class BeanAddress {
    private int address_id;
    private int user_id;
    private String address_city;
    private String address_province;
    private String address_zone;
    private String address_detail;
    private String address_people;

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_province() {
        return address_province;
    }

    public void setAddress_province(String address_province) {
        this.address_province = address_province;
    }

    public String getAddress_zone() {
        return address_zone;
    }

    public void setAddress_zone(String address_zone) {
        this.address_zone = address_zone;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getAddress_people() {
        return address_people;
    }

    public void setAddress_people(String address_people) {
        this.address_people = address_people;
    }

    public String getAddress_tel() {
        return address_tel;
    }

    public void setAddress_tel(String address_tel) {
        this.address_tel = address_tel;
    }

    private String address_tel;

}
