package plt;

import java.util.HashMap;
import java.util.Random;

/*
Performance Analysis

The number of URLs that can be encoded is quite large in this case, nearly of the order (10+26*2)^6(10+26∗2)
6
 .

The length of the encoded URLs is fixed to 6 units, which is a significant reduction for very large URLs.

The performance of this scheme is quite good, due to a very less probability of repeated same codes generated.

We can increase the number of encodings possible as well, by increasing the length of the encoded strings. Thus, there exists a tradeoff between the length of the code and the number of encodings possible.

Predicting the encoding isn't possible in this scheme since random numbers are used.
 */

public class TinyURL {
    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    HashMap<String, String> map = new HashMap<>();
    Random rand = new Random();
    String key = getRand();

    public String getRand() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(alphabet.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    public String encode(String longUrl) {
        while (map.containsKey(key)) {
            key = getRand();
        }
        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
}
