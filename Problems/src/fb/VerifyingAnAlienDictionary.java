package fb;

public class VerifyingAnAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        int[] charDict = new int[26];
        int orderPos = 0;
        for (char c : order.toCharArray()) {
            charDict[c - 'a'] = ++orderPos;
        }
        for (int i = 0; i < words.length - 1; i++) {
            String s1 = words[i];
            String s2 = words[i + 1];
            int idx = 0;
            while (idx <= s1.length() && idx <= s2.length()) {
                int o1 = idx == s1.length() ? 0 : charDict[s1.charAt(idx) - 'a'];
                int o2 = idx == s2.length() ? 0 : charDict[s2.charAt(idx) - 'a'];
                // System.out.println(o1 + " " + o2);
                // compare too, correct then break checking, go on with others
                if (o1 < o2) {
                    break;
                }
                // anything wrong return false
                if (o1 > o2) {
                    return false;
                }
                idx++;
            }
        }
        // only when all checked correct, then return true
        return true;
    }
}
