import java.util.*;

public class RecursiveTreePreview {

    // 模擬檔案系統
    static class Folder {
        String name;
        List<Object> contents;

        Folder(String name) {
            this.name = name;
            this.contents = new ArrayList<>();
        }

        void add(Object obj) {
            contents.add(obj);
        }
    }

    public static int countFiles(Object node) {
        if (node instanceof String) return 1;
        int count = 0;
        Folder folder = (Folder) node;
        for (Object o : folder.contents)
            count += countFiles(o);
        return count;
    }

    public static void printMenu(Object[] menu, int level) {
        for (Object item : menu) {
            if (item instanceof String)
                System.out.println("  ".repeat(level) + "- " + item);
            else
                printMenu((Object[]) item, level + 1);
        }
    }

    public static List<Object> flatten(List<Object> nested) {
        List<Object> result = new ArrayList<>();
        for (Object o : nested) {
            if (o instanceof List<?>)
                result.addAll(flatten((List<Object>) o));
            else
                result.add(o);
        }
        return result;
    }

    public static int maxDepth(Object obj) {
        if (!(obj instanceof List<?>)) return 1;
        int max = 0;
        for (Object o : (List<?>) obj)
            max = Math.max(max, maxDepth(o));
        return max + 1;
    }

    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add("file1.txt");
        Folder sub = new Folder("sub");
        sub.add("file2.txt");
        sub.add("file3.txt");
        root.add(sub);
        System.out.println("📁 總檔案數：" + countFiles(root));

        Object[] menu = {"首頁", new Object[]{"產品", "服務", new Object[]{"子選單"}}};
        System.out.println("📑 多層選單：");
        printMenu(menu, 0);

        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, 4)), 5);
        System.out.println("🌲 展平後：" + flatten(nestedList));
        System.out.println("📏 最大深度：" + maxDepth(nestedList));
    }
}
