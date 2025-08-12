import java.util.*;

public class ValidMaxHeapChecker {
    public static class Result {
        public final boolean isValid;
        public final Integer violateIndex;
        Result(boolean v, Integer i){ isValid=v; violateIndex=i; }
        public String toString(){
            return "isValid=" + isValid + (violateIndex==null? "" : (", violateIndex=" + violateIndex));
        }
    }

    public static Result check(int[] a) {
        int n = a.length;
        for (int i = (n - 2) / 2; i >= 0; i--) {
            int l = 2 * i + 1, r = 2 * i + 2;
            if (l < n && a[i] < a[l]) return new Result(false, l);
            if (r < n && a[i] < a[r]) return new Result(false, r);
        }
        return new Result(true, null);
    }

    public static void main(String[] args) {
        System.out.println(check(new int[]{100,90,80,70,60,75,65})); // true
        System.out.println(check(new int[]{100,90,80,95,60,75,65})); // false, violateIndex=3
        System.out.println(check(new int[]{50})); // true
        System.out.println(check(new int[]{})); // true
    }
}
