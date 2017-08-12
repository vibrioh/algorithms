package corejava;

/**
 * Created by vibri on 7/15/2017.
 * This program tests the Employee class.
 * Static nested classes and normal classes are almost the same in functionality,
 * it's just different methods to group things. However when using static nested classes,
 * you cannot put definitions of them in separated files, which will lead to a single file
 * containing a lot of class definitions.
 */
public class EmployeeTest {

  public static void main(String[] args) {

    // fill the staff array with three Employee objects
    Employee[] staff = new Employee[3];

    staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
    staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
    staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

    // raise everyone's salary by 5%
    for (Employee e : staff) {
      e.raiseSalary(5);
    }

    for (Employee e : staff) {
      System.out.println("name=" + e.getName() + ", salary=" + e.getSalary() + ", hireDay=" + e.getHireDay());
    }

    int n = Employee.getNextId();  // calls static method
    System.out.println("Next available id = " + n);
  }

//  private static class Employee {
//
//    private String name;
//    private double salary;
//    private LocalDate hireDay;
//
//    public Employee(String n, double s, int year, int month, int day) {
//      name = n;
//      salary = s;
//      hireDay = LocalDate.of(year, month, day);
//    }
//
//    public String getName() {
//      return name;
//    }
//
//    public double getSalary() {
//      return salary;
//    }
//
//    public LocalDate getHireDay() {
//      return hireDay;
//    }
//
//    public void raiseSalary(double byPercent) {
//      double raise = salary * byPercent / 100;
//      salary += raise;
//    }
//  }

}

