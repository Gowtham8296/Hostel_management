package com.company;

import java.util.ArrayList;
import java.util.List;

public class Room
{
    public int rno;
    List<Student> students;

    public Room(int rno) {
        this.rno = rno;
        this.students = new ArrayList<>();

    }

    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Room{" +
                "rno=" + rno +
                ", students=" + students +
                '}';
    }
}
