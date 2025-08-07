public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int score : scores) {
            sum += score;
            max = Math.max(max, score);
            min = Math.min(min, score);
        }

        double average = (double) sum / scores.length;
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        int countAboveAvg = 0;

        for (int score : scores) {
            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;

            if (score > average) countAboveAvg++;
        }

        System.out.println("📊 成績報表：");
        System.out.println("平均值：" + average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("等級分布：A=" + countA + ", B=" + countB + ", C=" + countC + ", D=" + countD + ", F=" + countF);
        System.out.println("高於平均的人數：" + countAboveAvg);
    }
}

