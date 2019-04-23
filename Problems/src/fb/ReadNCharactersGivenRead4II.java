package fb;

public class ReadNCharactersGivenRead4II {
    // idx where in the buff4 read
    private int readI = 0;
    // idx where int the buff4 write into buf
    private int writeI = 0;
    // the char[] used to get buff4
    private char[] buff = new char[4];

    public int read(char[] buf, int n) {
        // for each i there will be one char written in buff, so i is the actually num
        for (int i = 0; i < n; i++) {
            // when all read buff4 written into buf
            if (readI == writeI) {
                // read a new buff4
                writeI = read4(buff);
                // idx for write refresh
                readI = 0;
                // if nothing to write, return the num of written
                if (writeI == 0) return i;
            }
            // each time just write one to buf
            buf[i] = buff[readI++];
        }
        // all n cycles done, all n char in the buf
        return n;
    }

    private int read4(char[] buff) {
        return 0;
    }
}
