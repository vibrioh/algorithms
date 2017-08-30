package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
  class Solution {
    public List<String> fizzBuzz(int n) {
      List<String> res = new ArrayList<>();

      for (int i = 1; i <= n; i++) {
        String str = "";
        if (i % 3 == 0) {
          str += "Fizz";
        }
        if (i % 5 == 0) {
          str += "Buzz";
        }
        if (str != "") {
          res.add(str);
        } else {
          res.add(i + "");
        }
      }

      return res;
    }
  }

  public class Solution2 {
    public List<String> fizzBuzz(int n) {
      List<String> ret = new ArrayList<String>(n);
      for(int i=1,fizz=0,buzz=0;i<=n ;i++){
        fizz++;
        buzz++;
        if(fizz==3 && buzz==5){
          ret.add("FizzBuzz");
          fizz=0;
          buzz=0;
        }else if(fizz==3){
          ret.add("Fizz");
          fizz=0;
        }else if(buzz==5){
          ret.add("Buzz");
          buzz=0;
        }else{
          ret.add(String.valueOf(i));
        }
      }
      return ret;
    }
  }
}
