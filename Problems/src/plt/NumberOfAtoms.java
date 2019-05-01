package plt;

import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class NumberOfAtoms {
    public String countOfAtoms(String formula) {

        TreeMap<String, Integer> map = new TreeMap<>();
        Stack<TreeMap> stack = new Stack<>();

        int i = 0, len = formula.length();
        while (i < len) {
            System.out.println("TreeMap: " + map);
            System.out.println("Stack: " + stack + "\n");
            // meet (, push in whatever now in map; meet ) , update curr map with val and stack up map (if not empty)
            if (formula.charAt(i) == '(') {
                // map放进去
                stack.push(map);
                map = new TreeMap<>();
                i++;
            } else if (formula.charAt(i) == ')') {
                // map吐出来
                int val = 0;
                i++;
                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    val = val * 10 + formula.charAt(i++) - '0';
                }
                val = val == 0 ? 1 : val;

                if (!stack.isEmpty()) {
                    TreeMap<String, Integer> temp = map;
                    map = stack.pop();
                    for (String atom : temp.keySet()) {
                        map.put(atom, temp.get(atom) * val + map.getOrDefault(atom, 0));
                    }
                }
            } else {
                // map 更新
                // get the atom, get the val, update curr map
                int j = i + 1;
                while (j < formula.length() && Character.isLowerCase(formula.charAt(j))) {
                    j++;
                }

                String atom = formula.substring(i, j);

                int val = 0;
                while (j < formula.length() && Character.isDigit(formula.charAt(j))) {
                    val = val * 10 + (formula.charAt(j++) - '0');
                }
                val = val == 0 ? 1 : val;
                map.put(atom, map.getOrDefault(atom, 0) + val);
                i = j;
            }
        }

        System.out.println("End TreeMap: " + map);
        System.out.println("End Stack: " + stack + "\n");

        StringBuilder sb = new StringBuilder();

        for (String atom : map.keySet()) {
            sb.append(atom);
            sb.append(map.get(atom) == 1 ? "" : map.get(atom));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        NumberOfAtoms s = new NumberOfAtoms();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String n = scan.nextLine();
            System.out.println(s.countOfAtoms(n));
        }
    }
}
