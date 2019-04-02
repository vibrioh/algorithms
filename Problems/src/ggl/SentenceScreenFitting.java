package ggl;

/*
直接的方法是每次扫描一行，尝试能放几个，这样时间复杂度会高一点．另外一种方法是把所有的字符串都加起来，然后每次看如果位移一整行的距离是否正好落在这个字符串的空格位置，如果不是的话就退后，直到遇到一个空格．
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        // Please understand that the "start" is actually amount of sentence length loaded ()
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start - 1) % l) != ' ') {
                    start--;
                }
            }
        }

        return start / s.length();
    }
}
