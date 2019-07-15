package com.hsc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName: Travel
 * @Author: 93799
 * @Description: ${description}
 * @Date: 2019/7/4 15:14
 */

@Entity
public class Travel {
    @Id
    @GeneratedValue
    private Integer id;
    private String addressname;
    private String img;
    private String text;
    private Integer userid;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }

    public String getImg() {
        return img;
    }

    public String getText() {
        return text;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
