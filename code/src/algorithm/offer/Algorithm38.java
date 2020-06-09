package algorithm.offer;

import java.util.ArrayList;
import java.util.List;

public class Algorithm38 {
    private List<String> result = new ArrayList<>();

    public List<String> permulation(String str) {
        if (str.length() == 0) {
            return result;
        }
        char[] charArr = str.toCharArray();
        boolean[] hasUse = new boolean[charArr.length];
        bracktracking(charArr, hasUse, new StringBuilder());
        return result;
    }

    public void bracktracking(char[] chars, boolean[] hasUse, StringBuilder s) {
        if (chars.length == s.length()) {
            result.add(s.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (hasUse[i]) {
                continue;
            }
            if (i != 0 && chars[i] == chars[i - 1] && !hasUse[i]) {
                continue;
            }
            hasUse[i] = true;
            s.append(chars[i]);
            bracktracking(chars, hasUse, s);
            s.deleteCharAt(s.length() - 1);
            hasUse[i] = false;
        }

    }
}