package com.company;

import java.util.UUID;

public class Staff
{
    public UUID id;
    public String name;
    public String sex;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Staff(UUID id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
