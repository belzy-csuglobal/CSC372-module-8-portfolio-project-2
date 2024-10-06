import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

import Student.Student;
import Student.StudentComparator;

public class Main {
  public static Scanner scanner = new Scanner(System.in);
  public static List<Student> students = new LinkedList<Student>();
  public static boolean quit = false;

  public static void main(String[] args) {
    seedData();
    prompt();
  }

  public static void clear() {
    for (int i = 0; i < 50; i++) {
      System.out.println("\n");
    }
  }

  public static void prompt() {
    while(!quit) {
      // Clear the console output.
      clear();

      System.out.println("Students (CSV)\n--------------\n\nname, address, gpa");

      // Print students data to console in CSV format.
      for (int i = 0; i < students.size(); i++) {
        System.out.println(String.format("%s, %s, %.1f",
          students.get(i).getName(),
          students.get(i).getAddress(),
          students.get(i).getGpa()));
      }

      // Display input prompt
      System.out.println("\n(A) Add Student (E) Export Data (Q) Quit");
      String input = scanner.nextLine().toLowerCase();

      // Handle user input
      try {
        switch (input) {
          case "a":
            addStudent();
            break;
          case "e":
            // Sort students in ascending order and export to text file.
            Collections.sort(students, new StudentComparator());
            exportData();
            break;
          case "q":
            quit = true;
            break;
          default:
            continue;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    scanner.close();
  }

  public static void addStudent() throws Exception {
    String name;
    String address;
    double gpa;

    // Clear the console output.
    clear();

    // Get new student name
    System.out.println("New Student");
    System.out.print("- Name: ");
    name = scanner.nextLine();

    // Get new student address
    System.out.print("- Address: ");
    address = scanner.nextLine();


    // Get new student gpa
    System.out.print("- GPA: ");
    gpa = scanner.nextDouble();

    // Validate gpa.
    if (Double.isNaN(gpa))
      throw new Exception("GPA must be a double value");

    // Add new student to list
    students.add(new Student(name, address, gpa));
  }

  public static void exportData() {
    try {
      File file = new File("students.txt");
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));

      // Write all students data to text file in CSV format
      writer.write("name, address, gpa\n");
      for (Student student : students) {
        writer.write(String.format("%s, %s, %.2f\n",
          student.getName(), student.getAddress(), student.getGpa()));
      }

      writer.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void seedData() {
    double[] gpas = { 
      3.2, 4.0, 2.7, 2.5, 3.6, 1.9, 2.1, 3.8, 4.0, 3.5 };
    String[] names = {
      "Isaac", "Eva", "Frank", "David", "Alice", 
      "Jack", "Charlie", "Bob", "Helen", "Grace" };
    String[] addresses = {
      "1234 Maple Street, Imaginary City, IL 56789",
      "5678 Elm Avenue, Fictional Town, CA 12345",
      "9876 Pine Lane, Make-Believe Village, TX 67890",
      "4321 Birch Road, Fantasy Heights, NY 54321",
      "8765 Cedar Court, Dreamland Springs, FL 34567",
      "2109 Oak Circle, Wonderland Junction, AZ 87654",
      "6543 Redwood Boulevard, Enchanted Grove, WA 21098",
      "1098 Spruce Drive, Mythical Meadows, CO 76543",
      "3456 Willow Street, Magic Haven, NC 89012",
      "7890 Juniper Lane, Mystic Valley, GA 45678",
    };

    // Create initial list of students
    for (int i = 0; i < 10; i++) {
      students.add(new Student(names[i], addresses[i], gpas[i]));
    }
  }
}