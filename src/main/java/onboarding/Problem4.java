package onboarding;

public class Problem4 {

    interface Alphabet {
        char size(char alphabet);
    }

    /* 아스키 코드 상으로 앞에서 몇번째인지 확인하고 뒤에서 몇번째까지 빼서 리턴한다. */
    public char convertAlphabet(char alphabet, Alphabet alphabetType) {
        int distFromFront = alphabet - alphabetType.size('A') + 1; // 앞에서 몇번째인지, 예) 'C' 는 67 - 65 + 1 = 3번째이다.

        return (char) (alphabetType.size('Z') - distFromFront + 1); // 앞에서 떨어져 있는만큼 뒤에서 세서 알파벳을 찾아 변환한다. 예) 90 - 'C' (3) + 1 = 88 ('X') 이다.
    }

    public static String solution(String word) {
        String answer = "";



        return answer;
    }
}
