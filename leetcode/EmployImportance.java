package leetcode;

import java.util.List;

class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};



public class EmployImportance {
    public int getImportance(List<Employee> employees, int id) {

        int res = 0;

        if (employees.size() == 0) {
            return res;
        }

        for (Employee employee : employees) {
            if (employee.id == id) {
                res += employee.importance;
                for (int i : employee.subordinates) {
                    res += getImportance(employees, i);
                }
            }
        }

        return res;

    }
}
