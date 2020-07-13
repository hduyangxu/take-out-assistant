package cn.edu.zucc.personplan.model;

public class BeanUserDetail {
    private int userId;
    private String userName;
    private int orderCount;
    private float sumConsumption;
    private float sumDiscount;
    public static final String[] tableTitles={"用户id","用户名","总购买单数","总消费","总计优惠"};
    public String getCell(int col){
        if(col==0) return String.valueOf(this.userId);
        else if(col==1) return String.valueOf(this.userName);
        else if(col==2) return String.valueOf(this.orderCount);
        else if(col==3) return String.valueOf(this.sumConsumption);
        else if(col==4) return String.valueOf(this.sumDiscount);
        else return "";
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public float getSumConsumption() {
        return sumConsumption;
    }

    public void setSumConsumption(float sumConsumption) {
        this.sumConsumption = sumConsumption;
    }

    public float getSumDiscount() {
        return sumDiscount;
    }

    public void setSumDiscount(float sumDiscount) {
        this.sumDiscount = sumDiscount;
    }
}
