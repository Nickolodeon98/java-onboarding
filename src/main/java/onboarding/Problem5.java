package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Problem5 {

    public static List<Integer> solution(int money) {
        List<Integer> answer = Collections.emptyList();
        int[] units = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};

        answer = new ArrayList<>();

        int i = 0;

        while (i < 9) {
            answer.add(money / units[i]);
            money -= answer.get(answer.size()-1) * units[i++];
        }

        return answer;
    }
}
