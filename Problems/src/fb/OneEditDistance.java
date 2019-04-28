package fb;

public class OneEditDistance {
    /*
    public String substring(int beginIndex)
    Returns a new string that is a substring of this string. The substring begins with the character at the specified index and extends to the end of this string.
    Examples:

     "unhappy".substring(2) returns "happy"
     "Harbison".substring(3) returns "bison"
     "emptiness".substring(9) returns "" (an empty string)

    Parameters:
    beginIndex - the beginning index, inclusive.
    Returns:
    the specified substring.
    Throws:
    IndexOutOfBoundsException - if beginIndex is negative or larger than the length of this String object.
     */

    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null || Math.abs(s.length() - t.length()) > 1 || s.equals(t)) {
            return false;
        }
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(i).equals(t.substring(i + 1)) || s.substring(i + 1).equals(t.substring(i)) || s.substring(i + 1).equals(t.substring(i + 1));
            }
        }
        return true;
    }
}
