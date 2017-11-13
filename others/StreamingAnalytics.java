package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StreamingAnalytics {

  public static void main(String[] args) {
    List<String> input = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      if (line.equals("")) {
        break;
      } else {
        input.add(line);
      }
    }

    System.out.println(input);

    Map<String, Map<String, Integer>> records = new HashMap<>();

    for (String str : input) {
      String[] items = str.split(",");
      String date = items[0];
      String product = items[2];
      Integer quantity = Integer.parseInt(items[1]);
      if (records.containsKey(date)) {
        if (records.get(date).containsKey(product)) {
          records.get(date).put(product, records.get(date).get(product) + quantity);
        } else {
          records.get(date).put(product, quantity);
        }
      } else {
        Map<String, Integer> products = new HashMap<>();
        products.put(product, quantity);
        records.put(date, products);
      }
    }

    // TreeMap to store values of HashMap
    TreeMap<String, Map<String, Integer>> sorted = new TreeMap<>(records);

    System.out.println(Arrays.asList(sorted));

    for (Map.Entry<String, Map<String, Integer>> entry : sorted.entrySet()) {
      String date = entry.getKey();
      Map<String, Integer> products = entry.getValue();
      int total = 0;
      for (int i : products.values()) {
        total += i;
      }
      int num = products.size();
      float mean = (float)total / (float)num;
      String average = String.format("%.02f", mean);
      System.out.println(date + "," + total + "," + average + "," + num);
    }
  }
}
