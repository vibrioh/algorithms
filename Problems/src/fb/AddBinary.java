package fb;

public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int carry = 0;
        while (i < a.length() || i < b.length()) {
            int na = i < a.length() ? a.charAt(a.length() - 1 - i) - '0' : 0;
            int nb = i < b.length() ? b.charAt(b.length() - 1 - i) - '0' : 0;
            int sum = na + nb + carry;
            int pos = sum % 2;
            carry = sum / 2;
            sb.append(pos);
            i++;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        sb.reverse();
        return sb.toString();
    }
}
