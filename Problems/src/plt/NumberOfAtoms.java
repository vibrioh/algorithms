package plt;

import java.util.Stack;
import java.util.TreeMap;

public class NumberOfAtoms {
    public String countOfAtoms(String formula) {

        TreeMap<String, Integer> map = new TreeMap<>();
        Stack<TreeMap> stack = new Stack<>();

        int i = 0, len = formula.length();
        while (i < len) {
            if (formula.charAt(i) == '(') {
                stack.push(map);
                map = new TreeMap<>();
                i++;
            } else if (formula.charAt(i) == ')') {
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

        StringBuilder sb = new StringBuilder();

        for (String atom : map.keySet()) {
            sb.append(atom);
            sb.append(map.get(atom) == 1 ? "" : map.get(atom));
        }
        return sb.toString();
    }
}
