package plt;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append("/").append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int sh = s.indexOf("/", i);
            int sz = Integer.valueOf(s.substring(i, sh));
            i = sh + sz + 1;
            res.add(s.substring(sh + 1, i));
        }
        return res;
    }
}
