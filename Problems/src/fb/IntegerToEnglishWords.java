package fb;

import java.math.BigInteger;
import java.util.Scanner;

public class IntegerToEnglishWords {
    private static String[] IN_TWENTY = new String[]{
            "",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
    };

    private static String[] IN_HUNDRED = new String[]{
            "",
            "Ten",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    };

    private static String[] OVER_THOUSAND = new String[]{
            "",
            "Thousand",
            "Million",
            "Billion"
    };

    public String numberToWords(int num) {
        // here if num is BigInteger, use  num.equals(BigInteger.valueOf(0),  remainder = num.mod(BigInteger.valueOf(1000)).intValue()
        if (num == 0) {
            return "Zero";
        }
        String res = "";
        int i = 0;
        while (num > 0) {
            String pre = helper(num % 1000);
            // check whether pre is "" is important, or you will add OVER_THOUSAND to nothing
            if (pre != "") {
                res = pre + " " + OVER_THOUSAND[i] + " " + res;
            }
            num /= 1000;
            i++;
        }
        // trim is important to get rid of redundant spaces
        return res.trim();
    }

    String helper(int n) {
        String s;
        if (n < 20) {
            s = IN_TWENTY[n];
        } else if (n < 100) {
            s = IN_HUNDRED[n / 10] + " " + IN_TWENTY[n % 10];
        } else {
            s = IN_TWENTY[n / 100] + " Hundred " + helper(n % 100);
        }
        // trim is important to get rid of redundant spaces, , and keep "" to be ""
        return s.trim();
    }


}


class NumberToText {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BigInteger num = BigInteger.valueOf(0);
        String[] st = {"", " thousand ", " million ", " billion ", " trillion ", " quadrillion ",
                " quintillion ", " sextillion ", " septillion "};
        while (num.compareTo(BigInteger.valueOf(0)) < 1 || num.toString().length() > 3 * st.length) {
            System.out.println("Enter a positive integer: ");
            num = scan.nextBigInteger();
        }
        String text = "";
        for (int i = 0; i < st.length && !num.equals(BigInteger.valueOf(0)); i++) {
            //take the remainder to find corresponding st[i]'s words
            int remainder = num.mod(BigInteger.valueOf(1000)).intValue();
            if (remainder > 0) text = get_string(remainder) + st[i] + text;
            //remove the last three digits
            num = num.divide(BigInteger.valueOf(1000));
        }
        System.out.println(text);
    }

    public static String get_string(int n) {
        //convert three digit number into words
        String[] digit = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
        if (n < 20) return digit[n];
        else if (n < 100) return tens[n / 10] + digit[n % 10];
        else return digit[n / 100] + " hundread " + get_string(n % 100);
    }
}



