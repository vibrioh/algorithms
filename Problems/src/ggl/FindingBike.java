package ggl;

import java.util.*;

public class FindingBike {

//    char[][] graph =  new char[][]{
//            {'O', 'P', 'O', 'B', 'O', 'O', 'P'},
//            {'O', 'O', 'O', 'O', 'E', 'O', 'O'},
//            {'O', 'O', 'E', 'E', 'O', 'O', 'O'},
//            {'O', 'O', 'O', 'O', 'E', 'O', 'O'},
//            {'B', 'O', 'O', 'B', 'O', 'O', 'B'}
//    };

    char[][] graph =  new char[][]{
            {'O', 'P', 'O', 'B', 'O', 'O', 'P'},
            {'O', 'O', 'O', 'O', 'E', 'O', 'O'},
            {'O', 'O', 'E', 'E', 'O', 'P', 'O'},
            {'O', 'O', 'O', 'O', 'E', 'O', 'B'},
            {'B', 'O', 'O', 'B', 'O', 'O', 'B'}
    };

    List<int[]> persons;
    List<int[]> bikes;

    void getInfo() {
        int m = graph.length;
        int n = graph[0].length;
        persons = new ArrayList<>();
        bikes= new ArrayList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char currChar = graph[r][c];
                if (currChar == 'P') {
                    persons.add(new int[]{r, c});
                }
                if (currChar == 'B') {
                    bikes.add(new int[]{r, c});
                }
            }
        }
    }

    List<int[]> noTieLocal() {
        List<int[]> res = new ArrayList<>();
        Map<int[], Integer> corDis = new HashMap<>();
        Queue<Map.Entry<int[], Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        boolean[][] visitedBike = new boolean[graph.length][graph[0].length];
        for (int[] bike : bikes) {
            for (int[] person : persons) {
                int d = Math.abs(bike[0] - person[0]) + Math.abs(bike[1] - person[1]);
                corDis.put(new int[]{bike[0], bike[1], person[0], person[1]}, d);
            }
        }
        for (Map.Entry<int[], Integer> e : corDis.entrySet()) {
            pq.offer(e);
        }
        int nP = persons.size();
        while (nP > 0) {
            Map.Entry<int[], Integer> curr = pq.poll();
            int[] pair = curr.getKey();
            if (!visitedBike[pair[0]][pair[1]] && !visitedBike[pair[2]][pair[3]]) {
                res.add(pair);
                visitedBike[pair[0]][pair[1]] = true;
                visitedBike[pair[2]][pair[3]] = true;
                nP--;
            }

        }
        return res;
     }

    public void test() {
        FindingBike f = new FindingBike();
        f.getInfo();
        List<int[]> res = f.noTieLocal();
        for (int[] pair : res) {
            for (int cor : pair) {
                System.out.print(cor + " ");
            }
            System.out.println();
        }
    }


}

class TestStatic{
    public static void main(String[] args) {
        FindingBike fb = new FindingBike();
        fb.test();
    }
}
