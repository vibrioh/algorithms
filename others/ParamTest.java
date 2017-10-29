package corejava;

/**
 * Created by vibri on 7/15/2017.
 * This program demonstrates parameter passing in Java.
 */
public class ParamTest {

  public static void main(String[] args) {

    /*
     * Test 1: Methods can't modify numeric parameters
     */
    System.out.println("Testing tripleValue:");
    double percent = 10;
    System.out.println("Before: percent = " + percent);
    tripleValue(percent);
    System.out.println("After: percent = " + percent);

    /*
     * Test 2: Methods can change the state of object parameters
     */
    System.out.println("\nTesting tripleSalary:");
    Employee harry = new Employee("Harry", 5000, 1988, 8, 18);
    System.out.println("Before: salary = " + harry.getSalary());
    tripleSalary(harry);
    System.out.println("After: salary = " + harry.getSalary());

    /*
     * Test 3: Methods can't attach new objects to object parameters
     */
    System.out.println("\nTesting swap:");
    Employee a = new Employee("Alice", 70000, 1999, 9, 19);
    Employee b = new Employee("Bob", 60000, 1966, 6, 16);
    System.out.println("Before: a = " + a.getName());
    System.out.println("Before: b = " + b.getName());
    swap(a, b);
    System.out.println("After: a = " + a.getName());
    System.out.println("After: b = " + b.getName());
  }

  public static void tripleValue(double x) {  // doesn't work
    x = 3 * x;
    System.out.println("End of method: x = " + x);
  }

  public static void tripleSalary(Employee x) {
    x.raiseSalary(200);
    System.out.println("End of method: salary = " + x.getSalary());
  }

  public static void swap(Employee x, Employee y) {
    Employee temp = x;
    x = y;
    y = temp;
    System.out.println("End of method: x = " + x.getName());
    System.out.println("End of method: y = " + y.getName());
  }
}
