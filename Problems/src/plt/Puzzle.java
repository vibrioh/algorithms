package plt;

// This is the text editor interface. 
// Anything you type or change here will be seen by the other person in real time.
/*
.From 1point 3acres bbs
        ONE
        +ONE
        ----
        TWO

        231
        +231
        ----
        462
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Represents a puzzle, i.e. "first + second = result"
public class Puzzle {
    public final String first; // e.g., "ONE"
    public final String second; // e.g., "ONE"
    public final String result; // e.g., "TWO"

    public Puzzle(String first, String second, String result) {
        this.first = first;
        this.second = second;
        this.result = result;
    }
}

class Puzzles {
    // Checks whether or not the proposedSolution is correct.
    // E.g., puzzle={"ONE", "ONE", "TWO"}, proposedSolution={O: 2, N:3, E:1, T:4, W:6} -> true. 1point3acres
    public boolean checkSolution(Puzzle puzzle,
                                 Map<Character, Integer> proposedSolution) {
        String first = puzzle.first;
        String second = puzzle.second;
        String result = puzzle.result;
        int firstNum = getNumber(first, proposedSolution);
        int secondNum = getNumber(second, proposedSolution);
        int resultNum = getNumber(result, proposedSolution);
        return firstNum + secondNum == resultNum;
    }


    private int getNumber(String s, Map<Character, Integer> proposedSolution) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            num = num * 10 + proposedSolution.get(c);
        }
        return num;
    }

    // Gets all the characters in the puzzle.
    // E.g., [O, N, E, T, W]
    public Set<Character> getChars(Puzzle puzzle) {
        String first = puzzle.first;
        String second = puzzle.second;
        String result = puzzle.result;
        Set<Character> set = new HashSet<Character>();
        addChars(first, set);
        addChars(second, set);
        addChars(result, set);
        return set;
    }

    private void addChars(String s, Set<Character> set) {
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
    }

    // Feel free to add any helper functions you wish

    public Map<Character, Integer> solve(Puzzle puzzle) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        Set<Character> set = getChars(puzzle);
        if (getNextNum(set, map, puzzle)) {
            return map;
        } else {
            return null;
        }
    }

    private boolean getNextNum(Set<Character> set, Map<Character, Integer> map, Puzzle puzzle) {
        if (map.size() == set.size()) {
            if (checkSolution(puzzle, map)) {
                return true;
            } else {
                return false;
            }
        }

        for (Character setChar : set) {
            if (map.containsKey(setChar)) continue;
            for (int i = 0; i < 10; i++) {
                map.put(setChar, i);
                if (getNextNum(set, map, puzzle)) {
                    return true;
                } else {
                    map.remove(setChar);
                }
            }
        }
        return false;

    }
}
