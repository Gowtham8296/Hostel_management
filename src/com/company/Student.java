package com.company;

import java.util.UUID;

public class Student
{
    public UUID id;
    public String name;
    public String stream;
    public String address;
    public Room room;

    public Student(UUID id, String name, String stream, String address, Room room) {
        this.id = id;
        this.name = name;
        this.stream = stream;
        this.address = address;
        this.room = room;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStream() {
        return stream;
    }

    public String getAddress() {
        return address;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stream='" + stream + '\'' +
                ", address='" + address + '\'' +
                ", room=" + room +
                '}';
    }
}
