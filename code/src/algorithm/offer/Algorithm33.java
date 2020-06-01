package algorithm.offer;

public class Algorithm33 {
    public static boolean verifySequenceOfBST(int[] seq) {
        if (seq == null || seq.length == 0) {
            return false;
        }
        return verify(0, seq.length - 1, seq);
    }

    public static boolean verify(int start, int end, int[] seq) {
        if (end - start < 1) {
            return true;
        }
        int currentIdex = start;
        int lastIndexValue = seq[end];
        while (seq[currentIdex] < lastIndexValue) {
            currentIdex++;
        }
        for (int i = currentIdex; i < end; i++) {
            if (seq[currentIdex] > end) {
                return false;
            }
        }
        return verify(start, currentIdex-1, seq) && verify(currentIdex , end-1, seq);
    }
}