import java.io.*;
import java.util.*;

public class M02_YouBikeNextArrival {
    static int toMin(String s){
        int h = Integer.parseInt(s.substring(0,2));
        int m = Integer.parseInt(s.substring(3,5));
        return h*60 + m;
    }
    static String toHHMM(int x){
        int h = x/60, m = x%60;
        return String.format("%02d:%02d", h, m);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] t = new int[n];
        for(int i=0;i<n;i++) t[i] = toMin(br.readLine().trim());
        int q = toMin(br.readLine().trim());
        int l=0, r=n; // 找第一個 > q
        while(l<r){
            int mid=(l+r)>>>1;
            if(t[mid] > q) r = mid; else l = mid+1;
        }
        if (l==n) System.out.println("No bike");
        else System.out.println(toHHMM(t[l]));
    }
}
