package onboarding;

public class Problem3 {

    public int countEachNums(String flow, String target) {
        int count = 0;
        while(flow.contains(target)) {
            flow = flow.replaceFirst(target, "");
            count++;
        }
        return count;
    }

    public int countClap(int num) {
        int clapCount = 0;
        for (int i = 1; i <= num; i++) {
            String numStr = Integer.toString(i);
            clapCount += countEachNums(numStr, "3")
                    + countEachNums(numStr, "6")
                    + countEachNums(numStr, "9");
        }
        return clapCount;
    }

    public static int solution(int number) {
        int answer = 0;
        Problem3 problem3 = new Problem3();
        answer = problem3.countClap(number);
        return answer;
    }
}
