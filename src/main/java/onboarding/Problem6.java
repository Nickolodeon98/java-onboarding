package onboarding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Problem6 {

    public boolean countCommon(String standard, String target) {
        for (int i = 0; i < standard.length() - 1; i++) { // 예) 길이 4일때: substring(2, 4) substring(0, 3) substring(0, 4)
            for (int j = i+2; j <= standard.length(); j++) {
                if (target.contains(standard.substring(i, j))) return true;
            }
        }
        return false;
    }

    public List<String> filterAllNames(List<List<String>> forms, int idx, HashSet<String> result) {
        if (idx == forms.size()) return new ArrayList<>(result);

        String check = forms.get(idx).get(1);


        for (int i = 0; i < forms.size(); i++) {

            if (i == idx) continue;

            if (countCommon(check, forms.get(i).get(1))) {// [이메일, 이름] 형식
                result.add(forms.get(i).get(0));
                result.add(forms.get(idx).get(0));
            }
        }

        return filterAllNames(forms, ++idx, result);
    }

    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = List.of("answer");

        Problem6 problem6 = new Problem6();
        HashSet<String> filter = new HashSet<>();

        answer = problem6.filterAllNames(forms, 0, filter);

        return answer;
    }
}
