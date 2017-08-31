package com.mr.model;

import lombok.Data;

@Data
public class Billing {
    private Long id;
    private Double amount;
    private String desc;
    private User user;

    public Billing(Long id, Double amount, String desc, User user) {
        this.id = id;
        this.amount = amount;
        this.desc = desc;
        this.user = user;
    }

    public Billing() {
        this.id = 0L;
        this.amount = 0D;
        this.desc = "";
    }
}
