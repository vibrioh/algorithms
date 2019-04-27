package plt;


import java.util.Scanner;

public class UTF8 {
    public static void main(String[] args) {
        UTF8 u = new UTF8();
//        System.out.println(u.validUtf8ORIstr(new int[]{123, 345, 33, 2, 3})); // true
//        System.out.println(u.validUtf8ORIstr(new int[]{235, 140, 4})); // false
//        System.out.println(u.validUtf8bit(new int[]{123, 345, 33, 2, 3})); // true
//        System.out.println(u.validUtf8bit(new int[]{235, 140, 4})); // false

//        Scanner scan = new Scanner(System.in);
////        String curr = scan.next();  // only string escape any white space
//        String curr = scan.nextLine();  // the whole strings, include white spaces
//        scan.hasNext(); // check if string left
//        System.out.println("curr is: |" + curr + "|");
//        scan.close();

        System.out.println(u.utf8str("12345678")); // false none 0 1 or space
        System.out.println(u.utf8str("000000000")); // false too long
        System.out.println(u.utf8str("0000000")); // false too short
        System.out.println(u.utf8str("01010101")); // true 1-byte
        System.out.println(u.utf8str("  01010101 01010101  01010101  ")); // true
        System.out.println(u.utf8str("11010101 10010101")); // true 2-byte
        System.out.println(u.utf8str("11100101 10101010 10101010")); // true 3-byte
        System.out.println(u.utf8str("11110000 10101010 10101010 10101010")); // true 4-byte
        System.out.println(u.utf8str("11111000 10101010 10101010 10101010 10101010")); // false 5-byte
        System.out.println(u.utf8str(" 11010101 10010101 01010101 01010101  01010101   11110000 10101010 10101010 10101010")); // true combine-byte
        System.out.println(u.utf8str(" 11010101 10010101 01010101 01010101  01010101   11110000 10101010 11010101 10010101 10101010 10101010")); // false insert-byte
        System.out.println(u.utf8str("")); // true ?
        System.out.println(u.utf8str(" ")); // true ?
    }

    public boolean utf8str(String text) {
        // ^ start $ end [] select from + multiple
        if (!text.matches("^[01 ]+$") && !text.equals("")) {
            return false;
        }
        int num = 0;
        String[] strs = text.split(" ");
        for (String str : strs) {
            int len = str.length();
            System.out.println("|" + str + "|" + " " + str.length());
            if (len == 0) {
                continue; // escape multiple white space
            }
//            System.out.println("GET: " + "|" + str + "|" + " " + str.length());
            if (len != 8) {
                return false; // not the correct length
            }
            if (num == 0) {
                for (int i = 0; i < 8; i++) {
                    if (str.charAt(i) == '0') {
                        break;
                    }
                    num++;
                }
                if (num == 0) {
                    continue;
                }
                if (num > 4 || num == 1) {
                    return false;
                }
            } else {
                // String compare using equals()
                if (!str.substring(0, 2).equals("10")) {
                    return false;
                }
            }
            num--;
        }
        return num == 0;
    }

    public boolean validUtf8ORIstr(int[] data) {
        int n = 0;
        for (int i : data) {
            String s = Integer.toBinaryString(i);
            s = s.length() >= 8 ? s.substring(s.length() - 8) : "00000000".substring(s.length()) + s;
            if (n == 0) {
                for (int j = 0; j < 8; j++) {
                    if (s.charAt(j) == '0') {
                        break;
                    }
                    n++;
                }
                if (n == 0) {
                    continue;
                }
                if (n > 4 || n == 1) {
                    return false;
                }
            } else {
                if (!s.substring(0, 2).equals("10")) {
                    return false;
                }
            }
            n--;
        }
        return n == 0;
    }

    public boolean validUtf8bit(int[] data) {
        int numBytes = 0;
        // most significat postion check, 10000000
        int mask1 = 1 << 7;
        // second check, 01000000
        int mask2 = 1 << 6;
        // same logic as string manipulation
        for (int i : data) {
            int mask = mask1;
            if (numBytes == 0) {
                while ((i & mask) != 0) {
                    mask = mask >> 1;
                    numBytes++;
                }
                if (numBytes == 0) {
                    continue;
                }
                if (numBytes > 4 || numBytes == 1) {
                    return false;
                }
            } else {

                if ((i & mask1) == 0 || (i & mask2) != 0) {
                    return false;
                }
            }
            numBytes--;
        }
        return numBytes == 0;
    }

}