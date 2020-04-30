package algorithm.offer;

public class Algorithm17 {

    public static void printNumberMaxNDigitNumber(int n) {
        if (n == 0) {
            return;
        }
        char[] number = new char[n];
        printNumber1ToNDigitNumber(number, 0);
    }

    private static void printNumber1ToNDigitNumber(char[] number, int digit) {
        if (digit == number.length) {
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[digit] = (char) (i + '0');
            printNumber1ToNDigitNumber(number, digit + 1);
        }
    }

    private static void printNumber(char[] number) {
        int index = 0;
        while (index < number.length && number[index] == '0') {
            index++;
        }
        while (index < number.length) {
            System.out.print(number[index++]);
        }
        System.out.println();
    }

    public static void main(String[] arg) {
        printNumberMaxNDigitNumber(3);
    }
}