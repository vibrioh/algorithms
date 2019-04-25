package fb;

import java.util.*;

public class AccountsMerge {


    public List<List<String>> accountsMergeUF(List<List<String>> accounts) {
        // By confirmation of the size
        DSU dsu = new DSU(10001);
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToID = new HashMap<>();
        int id = 0;
        for (List<String> account : accounts) {
            String name = "";
            for (String email : account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                emailToName.put(email, name);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }

        Map<Integer, List<String>> ans = new HashMap();
        for (String email : emailToName.keySet()) {
            int index = dsu.find(emailToID.get(email));
            ans.computeIfAbsent(index, x -> new ArrayList()).add(email);
        }
        for (List<String> component : ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }
        return new ArrayList(ans.values());

    }

    // Disjoint Set Union data structure, with ranks, build with O(n), then find/union with O(1)
    class DSU {
        int[] parents;
        int[] ranks;

        DSU(int n) {
            parents = new int[n];
            ranks = new int[n];

            // all node's root is itself
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        // find the root
        public int find(int x) {
            return parents[x] == x ? x : find(parents[x]);
        }

        //union two on root (path compression), lower rank to higher
        public boolean union(int x, int y) {
            int xr = find(x);
            int yr = find(y);

            if (xr == yr) {
                return false;
            }

            if (ranks[xr] < ranks[yr]) {
                parents[xr] = yr;
            } else if (ranks[xr] > ranks[yr]) {
                parents[yr] = xr;
            } else {
                // increase rank for equaled union
                parents[xr] = yr;
                ranks[yr]++;
            }

            return true;
        }

    }


    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // build graph with HashMap!
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();
        for (List<String> account : accounts) {
            String name = "";
            for (String email : account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x -> new ArrayList<String>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x -> new ArrayList<String>()).add(email);
                emailToName.put(email, name);
            }
        }

        // DFS
        Set<String> seen = new HashSet();
        List<List<String>> ans = new ArrayList();
        for (String email : graph.keySet()) {
            // if unseen find connected component
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack();
                stack.push(email);
                List<String> component = new ArrayList();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei : graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }
}
