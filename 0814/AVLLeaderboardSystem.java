import java.util.*;

/**
 * 排行榜（分數高→低）。鍵值 = (-score, playerId) 使高分排前。
 * 支援：add/update、getRank(playerId)、topK(k)
 * 以 AVL + 子樹大小維護秩序統計。
 */
public class AVLLeaderboardSystem {

    // 唯一鍵結構：分數降序＝scoreNeg 升序；同分以 playerId 升序
    static class Key implements Comparable<Key>{
        final int scoreNeg;
        final int playerId;
        Key(int score, int playerId){ this.scoreNeg = -score; this.playerId=playerId; }
        @Override public int compareTo(Key o){
            if(this.scoreNeg!=o.scoreNeg) return Integer.compare(this.scoreNeg, o.scoreNeg);
            return Integer.compare(this.playerId, o.playerId);
        }
        @Override public String toString(){ return String.format("(score=%d,id=%d)", -scoreNeg, playerId); }
    }

    static class Node {
        Key key; int height, size; // size = 子樹節點數（秩序統計）
        Node left, right;
        Node(Key k){ key=k; height=1; size=1; }
    }

    private Node root;
    private final Map<Integer,Integer> idToScore = new HashMap<>(); // playerId -> score

    private int h(Node n){ return n==null?0:n.height; }
    private int sz(Node n){ return n==null?0:n.size; }
    private void upd(Node n){ if(n!=null){ n.height=1+Math.max(h(n.left),h(n.right)); n.size=1+sz(n.left)+sz(n.right);} }
    private int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }

    private Node rotL(Node x){
        Node y=x.right, T2=y.left;
        y.left=x; x.right=T2; upd(x); upd(y); return y;
    }
    private Node rotR(Node y){
        Node x=y.left, T2=x.right;
        x.right=y; y.left=T2; upd(y); upd(x); return x;
    }
    private Node rebalance(Node n){
        int b=bf(n);
        if(b>1){ if(bf(n.left)<0) n.left=rotL(n.left); return rotR(n); }
        if(b<-1){ if(bf(n.right)>0) n.right=rotR(n.right); return rotL(n); }
        return n;
    }

    private Node insert(Node n, Key k){
        if(n==null) return new Node(k);
        int cmp = k.compareTo(n.key);
        if(cmp<0) n.left=insert(n.left,k);
        else if(cmp>0) n.right=insert(n.right,k);
        else return n; // 不會碰到（id 唯一，更新用刪+插）
        upd(n); return rebalance(n);
    }
    private Node delete(Node n, Key k){
        if(n==null) return null;
        int cmp = k.compareTo(n.key);
        if(cmp<0) n.left=delete(n.left,k);
        else if(cmp>0) n.right=delete(n.right,k);
        else{
            if(n.left==null || n.right==null){
                n = (n.left!=null)?n.left:n.right;
            }else{
                // 後繼
                Node s=n.right;
                while(s.left!=null) s=s.left;
                n.key = s.key;
                n.right = delete(n.right, s.key);
            }
        }
        if(n==null) return null;
        upd(n); return rebalance(n);
    }

    // 公開 API
    public void addOrUpdateScore(int playerId, int score){
        // 若存在舊分數，先刪舊鍵
        Integer old = idToScore.get(playerId);
        if(old!=null){
            root = delete(root, new Key(old, playerId));
        }
        idToScore.put(playerId, score);
        root = insert(root, new Key(score, playerId));
    }

    // 取得玩家名次（1 為第一名）
    public int getRank(int playerId){
        Integer sc = idToScore.get(playerId);
        if(sc==null) return -1;
        Key k = new Key(sc, playerId);
        return 1 + countLess(root, k); // 因為我們用 (scoreNeg 升序)，小於 k 的代表分數更高或同分 id 更小
    }
    // 計算樹中 < k 的節點數
    private int countLess(Node n, Key k){
        if(n==null) return 0;
        int cmp = k.compareTo(n.key);
        if(cmp<=0){ // k <= n => 往左
            return countLess(n.left, k);
        }else{ // k > n => 左子樹全 + 本身 + 右子遞迴
            return sz(n.left) + 1 + countLess(n.right, k);
        }
    }

    // 取前 K 名（回傳 (playerId, score) 陣列）
    public List<int[]> topK(int K){
        List<int[]> out = new ArrayList<>();
        reverseInorder(root, out, K); // 因 (scoreNeg 升序)，右邊其實是較低分；我們想高→低，應該是中序直接讀即可
        // 修正：因為 Key 是 scoreNeg 升序，小的代表高分，所以中序即為高→低
        out.clear();
        inorderHighToLow(root, out, K);
        return out;
    }
    private void inorderHighToLow(Node n, List<int[]> out, int k){
        if(n==null || out.size()>=k) return;
        inorderHighToLow(n.left, out, k);
        if(out.size()<k) out.add(new int[]{n.key.playerId, -n.key.scoreNeg});
        inorderHighToLow(n.right, out, k);
    }

    // 附帶：列出全部（高→低）
    public List<int[]> all(){
        List<int[]> out = new ArrayList<>();
        inorderHighToLow(root, out, Integer.MAX_VALUE);
        return out;
    }

    // （未用到）反向中序範例
    private void reverseInorder(Node n, List<int[]> out, int k){
        if(n==null || out.size()>=k) return;
        reverseInorder(n.right, out, k);
        if(out.size()<k) out.add(new int[]{n.key.playerId, -n.key.scoreNeg});
        reverseInorder(n.left, out, k);
    }

    public static void main(String[] args){
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.addOrUpdateScore(101, 1200);
        lb.addOrUpdateScore(102, 1800);
        lb.addOrUpdateScore(103, 1500);
        lb.addOrUpdateScore(104, 1800); // 同分以 id 小者在前

        System.out.println("全部(高→低):");
        for(int[] p: lb.all()) System.out.println("id="+p[0]+", score="+p[1]);

        System.out.println("Rank(102) = " + lb.getRank(102)); // 1 或 2（與 104 同分但 id 小優先，102<104 故 102 第1）
        System.out.println("Rank(104) = " + lb.getRank(104));

        lb.addOrUpdateScore(103, 2000); // 更新升至第一
        System.out.println("更新後前3:");
        for(int[] p: lb.topK(3)) System.out.println("id="+p[0]+", score="+p[1]);
        System.out.println("Rank(103) = " + lb.getRank(103)); // 1
    }
}
