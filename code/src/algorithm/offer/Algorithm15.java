package algorithm.offer;

public class Algorithm15 {

    public static int numberOf1(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) > 0) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    public static int numberOf1Of2(int n) {
        int i = 1;
        int count = 0;
        while (i != 0) {
            if ((n & i) > 0) {
                count++;
            }
            i <<= 1;
        }
        return count;
    }

    public static int numberOf1Of3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] arg) {
        System.out.println(numberOf1(11));
        System.out.println(numberOf1Of2(11));
        System.out.println(numberOf1Of3(11));
    }
}