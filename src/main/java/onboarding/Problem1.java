package onboarding;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/* 페이지 번호는 1 ~ 400 이다. 왼쪽은 홀수, 오른쪽은 짝수이다.
 * 페이지 번호의 각 자리의 숫자들을 살펴본다. */
class Problem1 {
    enum Error {
        RANGE(getPageInfo().contains(1) || getPageInfo().contains(400)),
        EVEN_ODD(getPageInfo().get(0) % 2 != 1 || getPageInfo().get(1) % 2 != 0),
        BIGGER_LEFT(getPageInfo().get(0) >= getPageInfo().get(1)),
        IMPROPER_PAGE((getPageInfo().get(1) - getPageInfo().get(0)) > 1);

        final boolean value;

        static List<Integer> pageInfo;
        Error(boolean value) {
            this.value = value;
        }

        public boolean getValue() {
            return value;
        }
        public static List<Integer> getPageInfo() {
            return pageInfo;
        }

        public static void setPageInfo(List<Integer> pageInfo) {
            Error.pageInfo = pageInfo;
        }
    }

    interface Operator {
        int operate(int initialValue, int control);
    }

    /* 각 자리 숫자들을 더하는 메서드 */
    public int operateOnEveryDigit(int pageNum, Operator operator) {
        /* 예) 132
         * 132 % 10 = 2
         * 132 / 10 = 13
         * 13 % 10 = 3
         * 13 / 10 = 1
         * 1 % 10 = 1
         * 1 / 10 = 0*/
        int sum = 0;
        while (pageNum > 0) {
            sum = operator.operate(sum, pageNum);
            pageNum /= 10;
        }
        return sum;
    }

    public int scoreOf(List<Integer> pages, Comparator<Integer> comparator) {
        int score = 0;
        for (int page : pages) {
            int added = operateOnEveryDigit(page, (a, b) -> a + (b % 10));
            int multiplied = operateOnEveryDigit(page, (a, b) -> a == 0 ? b % 10 : a * (b % 10));

            int addedScore = comparator.compare(score, added);
            score = comparator.compare(addedScore, multiplied);
        }
        return score;
    }

    public boolean isInvalid(List<Integer> pages) {
        Error.setPageInfo(pages);
        return Error.RANGE.value
                || Error.EVEN_ODD.value
                || Error.BIGGER_LEFT.value
                || Error.IMPROPER_PAGE.value;
    }

    public int interpretResult(int result) {
        if (result > 0) return 1;
        if (result < 0) return 2;
        return 0;
    }

    public int gameResult(List<Integer> pobi, List<Integer> crong) {
        if (isInvalid(pobi) || isInvalid(crong)) return -1;
        Comparator<Integer> max = (a, b) -> a > b ? a : b;
        return interpretResult(scoreOf(pobi, max) - scoreOf(crong, max));
    }



    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int answer = Integer.MAX_VALUE;
        Problem1 problem1 = new Problem1();

        return problem1.gameResult(pobi, crong);
    }
}