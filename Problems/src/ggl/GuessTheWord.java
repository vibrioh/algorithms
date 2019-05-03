package ggl;

import java.util.*;

public class GuessTheWord {
    // 先猜最不unique的， 即zeromatch最少的
    // 再更新为何其matchnum想通的再猜

    class Master {
        public int guess(String word) {
            return 0;
        }
    }

    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i) if (a.charAt(i) == b.charAt(i)) matches++;
        return matches;
    }


    public void findSecretWord1(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            String guess = wordlist[new Random().nextInt(wordlist.length)];
            x = master.guess(guess);
            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist)
                if (match(guess, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[wordlist2.size()]);
        }
    }

    public void findSecretWord(String[] wordlist, Master master) {
        List<String> list = new ArrayList<>();
        for (String str : wordlist) list.add(str);
        for (int i = 0; i < 10; i++) {
            Map<String, Integer> zeroMatch = new HashMap<>();
            for (String s1 : list) {
                zeroMatch.putIfAbsent(s1, 0);
                for (String s2 : list) {
                    if (match(s1, s2) == 0) {
                        zeroMatch.put(s1, zeroMatch.get(s1) + 1);
                    }
                }
            }
            Pair pair = new Pair("", 101);  // list size is 100
            for (String key : zeroMatch.keySet()) {
                if (zeroMatch.get(key) < pair.freq) {
                    pair = new Pair(key, zeroMatch.get(key));
                }
            }
            int matchNum = master.guess(pair.key);
            if (matchNum == 6) return;
            List<String> tmp = new ArrayList<>();
            for (String s : list) {
                if (match(s, pair.key) == matchNum) {
                    tmp.add(s);
                }
            }
            list = tmp;
        }
    }

    private static class Pair {
        String key;
        int freq;

        public Pair(String key, int freq) {
            this.key = key;
            this.freq = freq;
        }
    }


}
