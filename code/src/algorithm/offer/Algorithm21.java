package algorithm.offer;

public class Algorithm21 {

    public static void reOrderArray(int[] array) {
        int count = 0;
        for (int num : array) {
            if (num % 2 == 1) {
                count++;
            }
        }
        int[] copy = array.clone();
        int index = 0;
        for (int num : copy) {
            if (num % 2 == 1) {
                array[index++] = num;
            } else {
                array[count++] = num;
            }
        }
    }

    public static void reOrderArray2(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int n = array.length;
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 != 0) {
                    int t = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arrary = {1,2,3,4,5,6,7,8};
        reOrderArray(arrary);
        System.out.println(arrary);
        int[] arrary1 = {7,9,2,4,0,1,5,2,6};
        reOrderArray(arrary1);
        System.out.println(arrary1);
    }

}