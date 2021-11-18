package com.wuzx.springboot.pojo;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component

@ConfigurationProperties(prefix = "randomm")
public class RandomProperties {

    // 随机值
    private String value;

    // 随机整数值
    private int number;


    // 随机long类型数
    private long longNum;

    //随机uuid
    private String uuid;

    // 小于10的随机整数
    private int lessThan10;


    // 范围
    private int rangge;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getLongNum() {
        return longNum;
    }

    public void setLongNum(long longNum) {
        this.longNum = longNum;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getLessThan10() {
        return lessThan10;
    }

    public void setLessThan10(int lessThan10) {
        this.lessThan10 = lessThan10;
    }

    public int getRangge() {
        return rangge;
    }

    public void setRangge(int rangge) {
        this.rangge = rangge;
    }

    @Override
    public String toString() {
        return "RandomProperties{" +
                "value='" + value + '\'' +
                ", number=" + number +
                ", longNum=" + longNum +
                ", uuid='" + uuid + '\'' +
                ", lessThan10=" + lessThan10 +
                ", rangge=" + rangge +
                '}';
    }
}
