package com.company;

import java.sql.SQLOutput;
import java.util.*;

public class Main
{
    private static final Hostel bcm = new Hostel();

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

        System.out.println(oldRoom);

        // step 2: find student by that studentId in that room
        Student toBeDeleted  = null;

        for(Student student : oldRoom.getStudents()){
            System.out.println(student);
            if(studentId.toString().equals(student.getId().toString())){
                System.out.println("found id setting student");
                toBeDeleted = student;
                break;
            }
        }
        // step 3: remove student from that room
        Student finalToBeDeleted = toBeDeleted;
        oldRoom.getStudents().removeIf(student -> student.getId().toString().equals(finalToBeDeleted.getId().toString()));
        System.out.println(oldRoom.getStudents());
        // step 4: find room by the new room id
        Room newRoom = findRoomById(newRoomNumber);
        System.out.println("newRoom = " + newRoom);
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
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);

        try {
            boolean stop = false;
            while (!stop) {
                printMenu();
                System.out.println("Enter Your Choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 :
                        System.out.println("Enter the Student Name: ");
                        String name = scanner.next();
                        System.out.println("Enter the Student Address: ");
                        String address = scanner.next();
                        System.out.println("Enter the Student Stream: ");
                        String stream = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter the Room Number: ");
                        int roomNumber = scanner.nextInt();
                        System.out.println("Enter the Student Academic Year: ");
                        int year = scanner.nextInt();
                        main.addStudent(roomNumber, UUID.randomUUID(), name, stream, address );
                        System.out.println("Student  " + name + " has been added..");
                        break;



                    case 2 :
                        System.out.println(getAllStudents());
                    break;
                    case 3 :
                        System.out.println("Enter the Old Room Number: ");
                        int oldRoomNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter the new Room Number: ");
                        int newRoomNumber = scanner.nextInt();
                        main.updateRoomNumber(oldRoomNumber, newRoomNumber);
                        break;

                    case 4 :
                        System.out.println("Enter the  Room Number: ");
                        int roomNumber1 = scanner.nextInt();
                        System.out.println("Enter the  Student Id: ");
                        String studentId = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter the Student New name: ");
                        String newName = scanner.next();
                        main.updateStudentByRoomIdAndStudentId(roomNumber1, UUID.fromString(studentId), newName);
                    break;
                    case 5 :
                        System.out.println("Enter the  Old Room Number: ");
                        int oldRoomNumber1 = scanner.nextInt();
                        System.out.println("Enter the  Student Id: ");
                        String studentId1 = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter the  New Room Number: ");
                        int newRoomNumber1 = scanner.nextInt();
                        main.updateRoomNumberOfStudent(oldRoomNumber1, UUID.fromString(studentId1), newRoomNumber1);
                    break;
                    case 6 :
                        System.out.println("Enter the  Room Number: ");
                        int roomNumber2 = scanner.nextInt();
                        main.addRoom(roomNumber2);
                        break;

                    case 7 :
                        System.out.println("Enter the  Old Room Number: ");
                        int oldRoomNumber2 = scanner.nextInt();
                        System.out.println("Enter the  New Room Number: ");
                        int newRoomNumber2 = scanner.nextInt();
                        main.updateRoomNumber(oldRoomNumber2, newRoomNumber2);
                    break;
                    case 8 :
                            System.out.println("Enter the  Room Number: ");
                                int roomNumber3 = scanner.nextInt();
                              System.out.println("Enter the  Student Id: ");
                            String  studentId3 = scanner.next();
                            System.out.println("Enter the  Student Name: ");
                            String  name3 = scanner.next();
                            main.updateStudentByRoomIdAndStudentId(roomNumber3, UUID.fromString(studentId3), name3);
                         break;
                    case 9 :
                        System.out.println("Enter the  Room Number: ");
                        int roomNumber4 = scanner.nextInt();
                        main.findRoomById(roomNumber4);
                        break;

                    default:
                        System.out.println("Thank you for using expense tracker OOPS Application");
                        stop = true;
                        break;
                    }
                }

        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    private static void printMenu() {
        System.out.println("1) Add Students [+]\n" +
                "2) List all Students[+]\n" +
                "3) Update room number [+]\n" +
                "4) update Student By RoomId And StudentId [+]\n" +
                "5) update Room Number Of Student [+]\n" +
                "6) add room [+]\n" +
                "7) update Room Number [-]\n" +
                "8) update Student By Room Id And Student Id [+]\n" +
                "9) Enter the room id to find Room By Id [+]");
        System.out.println("10) exit");
    }


}
