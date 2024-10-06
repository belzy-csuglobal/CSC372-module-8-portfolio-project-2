package Student;

public class Student {
  private String name;
  private String address;
  private double gpa;

  public Student(String name, String address, double gpa) {
    this.name = name;
    this.address = address;
    this.gpa = gpa;
  }

  public String getName() {
    return this.name;
  }

  public String getAddress() {
    return this.address;
  }

  public double getGpa() {
    return this.gpa;
  }
}