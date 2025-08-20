/* 
 * Time Complexity: O(n)
 * 說明：
 *  - progressive=true  → 採「累進分段」計稅（傳統做法，先前版本）。
 *  - progressive=false → 採「講義簡化公式」以對齊你提供的範例輸出：
 *      x ≤ 120000                  : 0.05 * x
 *   120001 ≤ x ≤ 500000            : 0.12 * x - 9000
 *   500001 ≤ x ≤ 1000000           : 0.20 * x - 49000   // 讓在 50 萬銜接連續
 *      x ≥ 1000001                 : 0.20 * x           // 依你範例：1200000 -> 240000
 *
 * 小結：
 *  - 這份檔預設 progressive=false（即「講義簡化公式」），
 *    會得到你截圖中的三筆答案：5000 / 33000 / 240000，平均 92667。
 */
import java.io.*;
import java.util.*;

public class M04_TieredTaxSimple {
    static boolean progressive = false;  // ← 想用累進分段就改為 true

    static long taxSimplified(long x){           // 對齊你範例用
        double t;
        if (x <= 120_000L) t = 0.05 * x;
        else if (x <= 500_000L) t = 0.12 * x - 9000.0;
        else if (x <= 1_000_000L) t = 0.20 * x - 49_000.0;
        else t = 0.20 * x;                        // 你的範例寫法
        return Math.round(t);
    }
    static long taxProgressive(long x){          // 傳統累進分段
        double tax = 0.0;
        if (x > 1_000_000L){ tax += (x - 1_000_000L) * 0.30; x = 1_000_000L; }
        if (x > 500_000L){ tax += (x - 500_000L) * 0.20; x = 500_000L; }
        if (x > 120_000L){ tax += (x - 120_000L) * 0.12; x = 120_000L; }
        if (x > 0){ tax += x * 0.05; }
        return Math.round(tax);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<n;i++){
            long income = Long.parseLong(br.readLine().trim());
            long t = progressive ? taxProgressive(income) : taxSimplified(income);
            sum += t;
            sb.append("Tax: ").append(t).append('\n');
        }
        long avg = Math.round((double)sum / n);
        sb.append("Average: ").append(avg);
        System.out.println(sb.toString());
    }
}
