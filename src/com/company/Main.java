package com.company;

import java.util.*;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {

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
                        HostelManagement.addStudent(roomNumber, UUID.randomUUID(), name, stream, address );
                        System.out.println("Student  " + name + " has been added..");
                        break;
                    case 2 :
                        System.out.println(HostelManagement.getAllStudents());
                        break;
                    case 3 :
                        System.out.println("Enter the Old Room Number: ");
                        int oldRoomNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter the new Room Number: ");
                        int newRoomNumber = scanner.nextInt();
                        HostelManagement.updateRoomNumber(oldRoomNumber, newRoomNumber);
                        break;
                   case 4 :
                        System.out.println("Enter the  Room Number: ");
                        int roomNumber1 = scanner.nextInt();
                        System.out.println("Enter the  Student Id: ");
                        String studentId = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter the Student New name: ");
                        String newName = scanner.next();
                        HostelManagement.updateStudentByRoomIdAndStudentId(roomNumber1, UUID.fromString(studentId), newName);
                    break;
                    case 5 :
                        System.out.println("Enter the  Old Room Number: ");
                        int oldRoomNumber1 = scanner.nextInt();
                        System.out.println("Enter the  Student Id: ");
                        String studentId1 = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter the  New Room Number: ");
                        int newRoomNumber1 = scanner.nextInt();
                        HostelManagement.updateRoomNumberOfStudent(oldRoomNumber1, UUID.fromString(studentId1), newRoomNumber1);
                    break;
                    case 6 :
                        System.out.println("Enter the  Room Number: ");
                        int roomNumber2 = scanner.nextInt();
                        HostelManagement.addRoom(roomNumber2);
                        break;
                    case 7 :
                        System.out.println("Enter the  Old Room Number: ");
                        int oldRoomNumber2 = scanner.nextInt();
                        System.out.println("Enter the  New Room Number: ");
                        int newRoomNumber2 = scanner.nextInt();
                        HostelManagement.updateRoomNumber(oldRoomNumber2, newRoomNumber2);
                        break;
                    case 8 :
                        System.out.println("Enter the  Room Number: ");
                        int roomNumber3 = scanner.nextInt();
                        System.out.println("Enter the  Student Id: ");
                        String  studentId3 = scanner.next();
                        System.out.println("Enter the  Student Name: ");
                        String  name3 = scanner.next();
                        HostelManagement.updateStudentByRoomIdAndStudentId(roomNumber3, UUID.fromString(studentId3), name3);
                        break;
                    case 9 :
                        System.out.println("Enter the  Room Number: ");
                        int roomNumber4 = scanner.nextInt();
                        HostelManagement.findRoomById(roomNumber4);
                        break;
                    default:
                        System.out.println("Thank you for using Hostel Management OOPS Application");
                        stop = true;
                    }
                }

        } catch (InputMismatchException err) {
            System.out.println("Please enter a correct choice from the menu : " + err.getMessage());
        } catch (Exception err) {
            System.out.println("An error occured : " + err.getMessage());
        } finally {
            scanner.close();
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
