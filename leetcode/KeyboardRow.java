package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class KeyboardRow {
  public String[] findWords(String[] words) {
    if (words == null || words.length == 0) {
      return new String[0];
    }

    Map<Character, Integer> map = new HashMap<>();
    String[] keyboard = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
    for (int i = 0; i < keyboard.length; i++) {
      for (char c : keyboard[i].toCharArray()) {
        map.put(c, i);
      }
    }


    List<String> list = new ArrayList<>();
    for (String word : words) {
      if (isOneRow(word, map)) {
        list.add(word);
      }
    }

    return list.toArray(new String[list.size()]);
  }

  private boolean isOneRow(String word, Map<Character, Integer> map) {
    char[] wordArray = word.toUpperCase().toCharArray();
    for (int i = 0; i < wordArray.length; i++) {
      if (map.get(wordArray[i]) != map.get(wordArray[0])) {
        return false;
      }
    }
    return true;
  }

  public String[] findWords2(String[] words) {
    return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
  }
}
