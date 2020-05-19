package com.revenant.shipper.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonData implements Serializable {

    private String name;
    private String letters;
    private String tel;
    private String id;
    private String photourl;
    private boolean isselect=false;
}
