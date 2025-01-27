package onboarding;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class Problem2 {
    public String interpret(String toAnalyze) {
        Stack<Character> charStack = new Stack<>();
        char[] separated = toAnalyze.toCharArray();

        for (char c : separated) {
            if (!charStack.empty() && charStack.peek() == c) {
                charStack.pop();
                continue;
            }
            charStack.add(c);
        }
        // Wrapper 클래스가 되어서 Object 를 사용하는지? Character::toString 작동 안함
        return charStack.stream().map(Object::toString).reduce("", (a,b)->String.join("", a, b));
    }

    public static String solution(String cryptogram) {
        String answer = "answer";
        Problem2 problem2 = new Problem2();
        answer = problem2.interpret(cryptogram);
        return answer;
    }
}
