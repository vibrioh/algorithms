package ggl;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OnlineElection {
    class TopVotedCandidate {

        TreeMap<Integer, Node> nodeMap;
        Map<Integer, Integer> voteCountMap;
        int max;
        int[] persons;
        int[] times;
        Node cur;

        public TopVotedCandidate(int[] persons, int[] times) {
            nodeMap = new TreeMap<>();
            voteCountMap = new HashMap<>();
            max = -1;
            this.persons = persons;
            this.times = times;
            cur = new Node();
            for (int i = 0; i < times.length; i++) {
                voteCountMap.put(persons[i], voteCountMap.getOrDefault(persons[i], 0) + 1);
                Node maxVoted = new Node();
                maxVoted.pre = cur;
                cur.next = maxVoted;
                if (voteCountMap.get(persons[i]) >= max) {
                    maxVoted.topVotedPerson = persons[i];
                    max = Math.max(max, voteCountMap.get(persons[i]));
                } else {
                    if (cur.topVotedPerson == null) {
                        maxVoted.topVotedPerson = persons[i];
                    } else {
                        maxVoted.topVotedPerson = maxVoted.pre.topVotedPerson;
                    }
                }
                nodeMap.put(times[i], maxVoted);
                cur = cur.next;
            }

        }

        public int q(int t) {
            return nodeMap.floorEntry(t).getValue().topVotedPerson;
        }

        class Node {
            Integer topVotedPerson;
            Node next;
            Node pre;

            public Node() {

            }
        }
    }

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */

}
