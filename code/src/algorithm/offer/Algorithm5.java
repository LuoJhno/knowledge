package algorithm.offer;

public class Algorithm5 {

    public static String replaceSpace(String sourceString) {
        int index = sourceString.length() - 1;

        StringBuilder stringBuilder = new StringBuilder(sourceString);
        for (char c : sourceString.toCharArray()) {
            if (c == ' ') {
                stringBuilder.append("  ");
            }
        }
        int newIndex = stringBuilder.length() - 1;
        for (int i = index; i > 0; i--) {
            char ch = sourceString.charAt(i);
            if (ch == ' ') {
                stringBuilder.setCharAt(newIndex--, '0');
                stringBuilder.setCharAt(newIndex--, '2');
                stringBuilder.setCharAt(newIndex--, '%');
            } else {
                stringBuilder.setCharAt(newIndex--, ch);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace("A B EDS V"));
    }
}
