package com.github.xjs.pojos;

import java.util.Date;

public class DetailDTO {
    private Date date;
    private Integer count;

    public DetailDTO(Date date, Integer count) {
        this.date = date;
        this.count = count;
    }

    public DetailDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
