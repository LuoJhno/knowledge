package algorithm.offer;

public class Algorithm20 {
    public static boolean isNumberic(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([Ee][+-]?\\d+)?");
    }

    public static void main(String[] args) {
        System.out.println(isNumberic("+100".toCharArray()));
    }

}