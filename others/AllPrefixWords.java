package others;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllPrefixWords {

  public static List<Integer> index(String[] drugs, String prefix) {
    List<Integer> index = new ArrayList<>();
    int lenP = prefix.length();
    int lenD = drugs.length;
    int i =0, j = 0;
    while (i < lenD && j < lenP) {
      if (drugs[i].length() < lenP) {
        i++;
        continue;
      }
      if (drugs[i].charAt(j) != prefix.charAt(j)) {
        i++;
      } else {
        j++;
        if (j == lenP && i < lenD) {
          index.add(i);
          j--;
          i++;
          if (index.size() == 3) {
            return index;
          }
        }
      }
      if (j > 0 && drugs[i].charAt(j - 1) != prefix.charAt(j - 1)) {
        return index;
      }
    }
    return index;
  }



  public static void main(String[] args) {
//    List<String> drugs = new ArrayList<>();
//    Scanner scanner =new Scanner(System.in);
//    while (scanner.hasNext()) {
//      String line = scanner.nextLine();
//      if (!line.equals("")) {
//        drugs.add(line);
//      }
//    }
//    System.out.print(drugs);
    String[] drugs = {"ACETAMINOPHEN","ASPERGEL", "ASPRIN", "ASPERTAME", "ASP", "ATAVAN", "BUPROPION"};
    String prefix1 = "AV";

    List<Integer> index1 = index(drugs, prefix1);

    System.out.print(index1);
    System.out.print("\n");
    int size_res = index1.size();
    if (size_res == 0) {
      System.out.print("<NONE>");
    } else {
      for (int i = 0; i < size_res; i++) {
        int index = index1.get(i);
        System.out.print(drugs[index]);
        if (i != size_res -1 ) {
          System.out.print("\n");
        }
      }
    }

    System.out.println("\n");


    String prefix2 = "AT";
    List<Integer> index2 = index(drugs, prefix2);
    System.out.print(index2);
    System.out.print("\n");
    size_res = index2.size();
    if (size_res == 0) {
      System.out.print("<NONE>");
    } else {
      for (int i = 0; i < size_res; i++) {
        int index = index2.get(i);
        System.out.print(drugs[index]);
        if (i != size_res -1 ) {
          System.out.print("\n");
        }
      }
    }

    System.out.println("\n");


    String prefix3 = "ASP";
    List<Integer> index3 = index(drugs, prefix3);
    System.out.print(index3);
    System.out.print("\n");
    size_res = index3.size();
    if (size_res == 0) {
      System.out.print("<NONE>");
    } else {
      for (int i = 0; i < size_res; i++) {
        int index = index3.get(i);
        System.out.print(drugs[index]);
        if (i != size_res -1 ) {
          System.out.print("\n");
        }
      }
    }

  }
}
