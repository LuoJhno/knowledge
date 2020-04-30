package algorithm.offer;

public class Algorithm16 {

    public static double power(double base, int exponent) {
        if (base == 0 || base == 1) {
            return base;
        }
        if (exponent == 0) {
            return 1;
        }
        boolean isNegative = false;
        if (exponent < 0) {
            exponent = (-exponent);
            isNegative = true;
        }
        double result = power(base * base, exponent / 2);
        if (exponent % 2 == 1) {
            result *= base;
        }
        if (isNegative) {
            result = 1 / result;
        }
        return result;
    }

    public static void main(String[] arg) {
        System.out.println(power(2, 5));
    }
}