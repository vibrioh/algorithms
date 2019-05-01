package plt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* technical interview 3
   Parse File, file includes following special characters:
            ,  separating characters on both side to two fields
            |  separating characters on both side to two lines
            “  characters inside of a pair of quotes are no longer special characters
            \  character right after back slash is no longer special character
   Example input:   dog, cat, "chicken, duck"|"says \"hi\"", good\|bad,"use \"\\\\\" to comment" | end
   Example output: [ ['dog', 'cat', 'chicken, duck'],
                             ['says "hi"', 'good|bad', 'use "\\" to comment'],
                             ['end']
                           ]
  1. cat,dog|duck|goose   =>   [["cat", "dog"], ["duck"], ["goose"]]
  2. help,"hello,world","pipe|line"  =>  [["help", "hello,world", "pipe|line"]]
  3. help,hello\,world,pipe\|line,back\\|slash,\"quote,me\"  =>  [["help", "hello,world", "pipe|line", "back\"], ["slash", ""quote", "me""]]
 */
public class CsvParser {
    public List<List<String>> parser(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        List<String> line = new ArrayList<>();
        boolean quote = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // whenever meet escape, pass the next to sb
            if (c == '\\') {
                i++;
                if (i < s.length()) {
                    sb.append(s.charAt(i));
                }
                continue;
            }
            // after escape, whenever meet ", boolean reverse
            if (c == '"') {
                quote = !quote;
                continue;
            }
            // not in quote, stop of the word, add word, renew word
            if (!quote && c == ',') {
                line.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            // not in quote, stop of the line, add word, add line (deep copy by new) ,renew word, renew line
            if (!quote && c == '|') {
                line.add(sb.toString());
                sb = new StringBuilder();
//                System.out.println("line: " + line);
                res.add(new ArrayList<>(line));
                line = new ArrayList<>();
                continue;
            }
            // others, increase word
            sb.append(c);
        }
        line.add(sb.toString());
        res.add(line);
        return res;
    }

    public static void main(String[] args) {
        CsvParser s = new CsvParser();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String n = scan.nextLine();
            System.out.println(s.parser(n));
        }
    }
}
