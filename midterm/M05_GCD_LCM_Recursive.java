/* 
 * Time Complexity: O(log min(a,b))
 * 說明：遞迴歐幾里得演算法；LCM 以 (a / gcd) * b 避免中途溢位。
 */
import java.io.*;
import java.util.*;

public class M05_GCD_LCM_Recursive {
    static long gcd(long a, long b){
        if (b==0) return Math.abs(a);
        return gcd(b, a%b);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long g = gcd(a,b);
        long l = (a / g) * b; // safe for 1e9 範圍
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + Math.abs(l));
    }
}
