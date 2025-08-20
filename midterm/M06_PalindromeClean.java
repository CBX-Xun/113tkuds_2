import java.io.*;

public class M06_PalindromeClean {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int i=0, j=s.length()-1;
        while(i<j){
            char ci = s.charAt(i), cj = s.charAt(j);
            if (!Character.isLetter(ci)){ i++; continue; }
            if (!Character.isLetter(cj)){ j--; continue; }
            if (Character.toLowerCase(ci) != Character.toLowerCase(cj)){
                System.out.println("No");
                return;
            }
            i++; j--;
        }
        System.out.println("Yes");
    }
}
