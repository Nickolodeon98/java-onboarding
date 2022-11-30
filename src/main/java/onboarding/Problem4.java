package onboarding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem4 {

    interface Alphabet {
        char size(char alphabet, char interpreter);
    }

    /* 아스키 코드 상으로 앞에서 몇번째인지 확인하고 뒤에서 몇번째까지 빼서 리턴한다. */
    public String convertAlphabet(char alphabet, Alphabet alphabetType) {
        if (alphabet == ' ') return String.valueOf(alphabet);
        int distFromFront = alphabet - alphabetType.size(alphabet, 'A') + 1; // 앞에서 몇번째인지, 예) 'C' 는 67 - 65 + 1 = 3번째이다.

        char converted = (char) (alphabetType.size(alphabet, 'Z') - distFromFront + 1); // 앞에서 떨어져 있는만큼 뒤에서 세서 알파벳을 찾아 변환한다. 예) 90 - 'C' (3) + 1 = 88 ('X') 이다.
        return String.valueOf(converted);
    }

    public String combineAlphabet(String word) {

        String[] strAsChar = word.split("");

        List<String> convertedStr = Arrays.stream(strAsChar)
                .map(str->convertAlphabet(str.charAt(0), (ch1, ch2) -> Character.isLowerCase(ch1) ? Character.toLowerCase(ch2) : ch2))
                .collect(Collectors.toList());

        String interpreted = "";
        for (String element : convertedStr) {
            interpreted = String.join("", interpreted, element);
        }
        return interpreted;
    }

    public static String solution(String word) {
        String answer = "";

        Problem4 problem4 = new Problem4();

        answer = problem4.combineAlphabet(word);

        return answer;
    }
}
