package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanRider {
    private int rider_id;
    private String rider_name;
    private Date rider_joinDate;
    private String rider_identification;
    public static final String[] tableTitles={"骑手编号","骑手名","注册时间","等级"};

    public String getCell(int col){
        if(col==0) return String.valueOf(this.rider_id);
        else if(col==1) return this.rider_name;
        else if(col==2) return String.valueOf(this.rider_joinDate);
        else if(col==3) return this.rider_identification;
        else return "";
    }

    public int getRider_id() {
        return rider_id;
    }

    public void setRider_id(int rider_id) {
        this.rider_id = rider_id;
    }

    public String getRider_name() {
        return rider_name;
    }

    public void setRider_name(String rider_name) {
        this.rider_name = rider_name;
    }

    public Date getRider_joinDate() {
        return rider_joinDate;
    }

    public void setRider_joinDate(Date rider_joinDate) {
        this.rider_joinDate = rider_joinDate;
    }

    public String getRider_identification() {
        return rider_identification;
    }

    public void setRider_identification(String rider_identification) {
        this.rider_identification = rider_identification;
    }
}
