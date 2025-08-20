import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {
    static int n;
    static int[] val;
    static char[] col; // 'R' or 'B'
    static boolean isNull(int i){
        return i>=n || val[i]==-1;
    }
    static int bh(int i){ // 回傳黑高度，不合法回傳 -1
        if (i>=n || val[i]==-1) return 1; // NIL 為黑，黑高 1
        int L = 2*i+1, R = 2*i+2;
        int bl = bh(L); if(bl==-1) return -1;
        int br = bh(R); if(br==-1) return -1;
        if (bl != br) return -1;
        return bl + (col[i]=='B' ? 1 : 0);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        val = new int[n];
        col = new char[n];
        ArrayList<String> toks = new ArrayList<>();
        while(toks.size() < 2L*n){
            String line = br.readLine();
            if(line==null) break;
            StringTokenizer st = new StringTokenizer(line);
            while(st.hasMoreTokens()) toks.add(st.nextToken());
        }
        for(int i=0, p=0;i<n;i++){
            val[i] = Integer.parseInt(toks.get(p++));
            char c = toks.get(p++).charAt(0);
            if (val[i]==-1) c='B'; // 空節點視為黑
            col[i] = (c=='R') ? 'R' : 'B';
        }
        // 1) 根為黑
        if (n>0 && val[0]!=-1 && col[0]!='B'){
            System.out.println("RootNotBlack");
            return;
        }
        // 2) 不得紅紅相鄰
        for(int i=0;i<n;i++){
            if (val[i]==-1) continue;
            if (col[i]=='R'){
                int L=2*i+1, R=2*i+2;
                if (L<n && val[L]!=-1 && col[L]=='R'){
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
                if (R<n && val[R]!=-1 && col[R]=='R'){
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }
        // 3) 黑高度一致
        if (bh(0)==-1){
            System.out.println("BlackHeightMismatch");
        }else{
            System.out.println("RB Valid");
        }
    }
}
