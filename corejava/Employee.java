package corejava;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Employee extends Person {

  private static int nextId;
  private int id;  // unassigned int == 0

//  private String name;
  private double salary;
  private LocalDate hireDay;

  // the three instance fields that will hold the data
  // manipulated inside an instance of the Employee class

  // The private keyword makes sure that the only methods that can access these instance
  // fields are the methods of the Employee class itself. No outside method can read or
  // write to these fields.

  /*
   * You could use the public keyword with your instance fields, but it would
   * be a very bad idea. Having public data fields would allow any part of the program
   * to read and modify the instance fields, completely ruining encapsulation.Any
   * method of any class can modify public fields—and, in our experience, some
   * code will take advantage of that access privilege when you least expect it.We
   * strongly recommend to make all your instance fields private
   */

  // static initialization block
  // Static initialization occurs when the class is first loaded. Like instance fields,
  // static fields are 0 , false , or null unless you explicitly set them to another value.
  static {
    Random generator = new Random();
    // set nextId to a random number between 0 and 9999
    nextId = generator.nextInt(10000);
  }

  // object initialization block
  {
    id = nextId;
    nextId++;
  }

  // All methods of this class are tagged as public . The keyword public means that any
  // method in any class can call the method.
  public Employee(String n, double s, int year, int month, int day) {  // constructor
    // This constructor runs when you construct objects of the Employee class—giving the
    // instance fields the initial state you want them to have.
    // A constructor can only be called in conjunction with the new operator.
    super(n);
    salary = s;
    hireDay = LocalDate.of(year, month, day);
  }

  /*
   * • A constructor has the same name as the class.
   * • A class can have more than one constructor.
   * • A constructor can take zero, one, or more parameters.
   * • A constructor has no return value.
   * • A constructor is always called with the new operator.
   *
   * Sometimes, it happens that you want to get and set the value of an instance field.
   * Then you need to supply three items:
   * • A private data field;
   * • A public field accessor method; and
   * • A public field mutator method.
   */

  // three more overloaded constructors
  public Employee(String n, double s) {
    super(n);
    salary = s;
    hireDay = LocalDate.now();
  }

  public Employee(double s) {
    this("Employee #" + nextId, s);
  }

  // the default constructor
  public Employee(){
    super("");
    // name initialized to ""
    // salary not explicitly set --  initialized to 0
    // id initialized in initialization block
  }

  public int getId() {
    return id;
  }

  public void setId() {  // void return nothing
    id = nextId;  // set id to next available id
    nextId++;
  }

  public static int getNextId() {
    return nextId;  // returns static field
  }

//  public String getName() {
//    return name;
//  }

  public double getSalary() {
    return salary;
  }

  public LocalDate getHireDay() {
    return hireDay;
  }

  public String getDescription() {
    return String.format("an employee with a salary of $%.2f", salary);
  }

  public void raiseSalary(double byPercent) {
    double raise = this.salary * byPercent / 100;
    this.salary += raise;
  }

  public boolean equals(Object otherObject) {
    // a quick test to see if the objects are identical
    if (this == otherObject) {
      return true;
    }

    // must return false if the explicit parameter is null
    if (otherObject == null) {
      return false;
    }

    // if the classes don't match, they can't be equal
    if (getClass() != otherObject.getClass()) {
      return false;
    }

    // now we know otherObject is a non-null Employee
    Employee other = (Employee) otherObject;

    // test whether the fields have identical values
    return Objects.equals(this.getName(), other.getName()) && salary == other.salary && Objects.equals(hireDay, other.hireDay);
  }

  public int hashCode() {
    return Objects.hash(this.getName(), salary, hireDay);
  }

  public String toString() {
    return getClass().getName() + "[name = " + this.getName() + ", salary = " + salary + ", hireDay = " + hireDay + "]";
  }

  public static void main(String[] args) {  // unit test

    // fill the staff array with four Employee objects
    Employee[] staff = new Employee[4];

    staff[0] = new Employee("Test Tony Tester", 40000, 1990, 3, 15);
    staff[1] = new Employee("Betty", 60000);
    staff[2] = new Employee(66666);
    staff[3] = new Employee();

    for (Employee e : staff) {
      System.out.println(e.getName() +  " " + e.getSalary() + " " + e.getId() + " " + e.getHireDay());
    }

  }
}
