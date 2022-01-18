package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Main
{
    private static final Hostel bcm = new Hostel();

    public void addStudent(int roomNumber, UUID id, String name, String stream, String address) {
        Room room = findRoomById(roomNumber);
        if(room != null) {
            Student student = new Student(id,name,stream,address,room);
            room.getStudents().add(student);
        }
        else
            System.out.println("This " + roomNumber + " is not Found!..");
    }

    public static List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        for (Room room : bcm.getRooms()) {
            for (Student student : room.getStudents()) {
                students.add(student);
            }
        }
        return students;
    }

    public void updateRoomNumberOfStudent(int oldRoomNumber,UUID studentId, int newRoomNumber){
        // step 1: find room by room number
        Room oldRoom = findRoomById(oldRoomNumber);

        // step 2: find student by that studentId in that room
        Student toBeDeleted  = null;

        for(Student student : oldRoom.getStudents()){
            if(studentId.equals(student.getId())){
                toBeDeleted = student;
                break;
            }
        }
        // step 3: remove student from that room
        oldRoom.getStudents().remove(toBeDeleted);
        // step 4: find room by the new room id
        Room newRoom =  findRoomById(newRoomNumber);
        // step 5: update student to have new room object
        toBeDeleted.setRoom(newRoom);
        newRoom.getStudents().add(toBeDeleted);
    }

    public void deleteStudentByRoomNumberAndStudentId(int roomNumber, UUID studentId){
        Room oldRoom = findRoomById(roomNumber);
        Student studentToDelete = null;
        for(Student student : oldRoom.getStudents()){
            if(student.getId().equals(studentId)){
                studentToDelete = student;
                break;
            }
         }
        if(studentToDelete != null)
            oldRoom.getStudents().remove(studentToDelete);
     }

    public void deleteRoomByRoomNumber(int roomNumber){
        Room deleteRoom = findRoomById(roomNumber);

        for(Student student : deleteRoom.getStudents()){
            student.setRoom(null);
        }
        bcm.getRooms().remove(deleteRoom);
    }

    public void addRoom(int roomNumber){
        Room newRoom = new Room(roomNumber);
        bcm.getRooms().add(newRoom);
    }

    public void updateRoomNumber(int oldRoomNumber,int newRoomNumber){
        // step 1: find room with old room number
        Room oldRoom = findRoomById(oldRoomNumber);
        // step 2: find room with new room number
        Room newRoom = findRoomById(newRoomNumber);
        // step 3: find all students with old room number and update their room to new room
        for(Student student : oldRoom.getStudents()){
            student.setRoom(newRoom);
            newRoom.getStudents().add(student);
        }
        // step 4: delete students from old room
        oldRoom.getStudents().clear();
    }






    public void updateStudentByRoomIdAndStudentId(int roomNumber,UUID studentId,String newName){
        // step 1 : find the room with the given roomNumber
        Room room = findRoomById(roomNumber);

        // step 2 : find student with given id from the found room
        for(Student student : room.getStudents()){
            if(student.getId().equals(studentId)){
                student.setName(newName);
            }
        }
    }

    private Room findRoomById(int roomNumber)
    {

        for(Room room:bcm.getRooms())
        {
            if(Objects.equals(room.getRno(),roomNumber))
            {
                return room;
            }

        }
        return null;
    }
    public static void main(String[] args)
    {
        Room r = new Room(25);
        Student s = new Student(UUID.randomUUID(),"Gowtham","computer science","Hassan",r);
        r.getStudents().add(s);
        bcm.getRooms().add(r);
        System.out.println(getAllStudents());
    }
}
