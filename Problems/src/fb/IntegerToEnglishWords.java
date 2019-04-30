package fb;

public class IntegerToEnglishWords {
    private String[] IN_TWENTY = new String[]{
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

    private String[] IN_HUNDRED = new String[]{
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

    private String[] OVER_THOUSAND = new String[]{
            "",
            "Thousand",
            "Million",
            "Billion"
    };

    public String numberToWords(int num) {
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
