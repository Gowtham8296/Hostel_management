package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author chethan on 19-01-2022 08:00 AM
 **/
public class HostelManagement {

    private static final Hostel bcm = new Hostel();

    public HostelManagement() {

    }

    static {
        Room fiftyFour = new Room(54);
        Room fiftyFive = new Room(55);
        bcm.getRooms().add(fiftyFour);
        bcm.getRooms().add(fiftyFive);
        Student student = new Student(UUID.randomUUID(), "Chethan", "cs", "Bangalore", fiftyFive);
        Student student1 = new Student(UUID.randomUUID(), "Gowtham", "Is", "Hassan", fiftyFive);
        fiftyFive.getStudents().add(student);
        fiftyFive.getStudents().add(student1);
    }

    public static void addStudent(int roomNumber, UUID id, String name, String stream, String address) {
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
        bcm.getRooms().forEach(room -> students.addAll(room.getStudents()));
        return students;
    }

    public static void deleteStudentByRoomNumberAndStudentId(int roomNumber, UUID studentId){
        Room oldRoom = findRoomById(roomNumber);
        assert oldRoom != null : "Room #" + roomNumber + " not found";
        oldRoom.getStudents().removeIf(student -> student.getId().toString().equals(studentId.toString()));
    }

    public static void deleteRoomByRoomNumber(int roomNumber){
        Room deleteRoom = findRoomById(roomNumber);
        assert deleteRoom != null : "room #" + roomNumber + " not found";
        deleteRoom.getStudents().forEach(student -> student.setRoom(null));
        bcm.getRooms().remove(deleteRoom);
    }

    private static void addRoom(Room room){
        bcm.getRooms().add(room);
    }

    public static void addRoom(int roomNumber){
        addRoom(new Room(roomNumber));
    }

    public static void updateRoomNumber(int oldRoomNumber,int newRoomNumber){
        Room oldRoom = findRoomById(oldRoomNumber);
        Room newRoom = findRoomById(newRoomNumber);
        assert oldRoom != null : "room #" + oldRoomNumber + " not found";
        oldRoom.getStudents().forEach(student -> student.setRoom(newRoom));
        assert newRoom != null : "room #" + newRoomNumber + " not found";
        newRoom.getStudents().addAll(oldRoom.getStudents());
        oldRoom.getStudents().clear();
    }

    public static void updateStudentByRoomIdAndStudentId(int roomNumber,UUID studentId,String newName) throws Exception {
        Room room = findRoomById(roomNumber);
        assert room != null : "room #" + roomNumber + " not found";
        room.getStudents()
                .stream()
                .filter(student -> student.getId().toString().equals(studentId.toString()))
                .findFirst()
                .orElseThrow(() -> new Exception("student with that id not found"))
                .setName(newName);
    }

    public static Room findRoomById(int roomNumber) {
        return bcm.getRooms().stream().filter(room -> Objects.equals(roomNumber,room.getRno())).findFirst().orElse(null);
    }

    public static void updateRoomNumberOfStudent(int oldRoomNumber,UUID studentId, int newRoomNumber){
        Room oldRoom = findRoomById(oldRoomNumber);

        Student toBeDeleted  = oldRoom
                .getStudents()
                .stream()
                .filter(student -> student.getId().toString().equals(studentId.toString())).findFirst()
                .orElse(null);

        assert toBeDeleted != null : "student #" + studentId.toString() + " not found";

        oldRoom.getStudents().remove(toBeDeleted);
        Room newRoom = findRoomById(newRoomNumber);
        toBeDeleted.setRoom(newRoom);
        newRoom.getStudents().add(toBeDeleted);
    }
}
