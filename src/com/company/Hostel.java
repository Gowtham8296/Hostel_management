package com.company;

import java.util.ArrayList;
import java.util.List;

public class Hostel
{
    List<Room> rooms;
    List<Staff> staffs;

    public Hostel() {
        this.rooms = new ArrayList<>();
        this.staffs = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "rooms=" + rooms +
                ", staffs=" + staffs +
                '}';
    }
}
