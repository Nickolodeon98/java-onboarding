package onboarding;

import java.util.*;

public class Problem7 {

    public List<String> findFriends(List<List<String>> friends, String user) { // 자신과 친구인 이름들을 반환한다.
        List<String> userFriends = new ArrayList<>();

        for (List<String> friend : friends) {
            if (friend.get(0).equals(user)) userFriends.add(friend.get(1));
            if (friend.get(1).equals(user)) userFriends.add(friend.get(0));
        }
        return userFriends;
    }

    public List<String> findFriendsRange(List<List<String>> friends) { // 모든 친구들의 목록을 반환한다.
        HashSet<String> friendsRange = new HashSet<>();

        for (List<String> friend : friends) {
            friendsRange.add(friend.get(0));
            friendsRange.add(friend.get(1));
        }
        return new ArrayList<>(friendsRange);
    }

    private int computeFriendScore(List<String> userFriends, List<String> friends) {
        List<String> tmp = new ArrayList<>(userFriends);
        tmp.retainAll(friends);
        return tmp.size() * 10;
    }

    public List<String> recommend(String user, List<List<String>> friends, List<String> visitors) {
        HashMap<String, Integer> score = new HashMap<>();

        List<String> userFriends = findFriends(friends,user); // user 의 친구들이다. 여기선 mrko 의 친구들.

        for (String person : findFriendsRange(friends)) {
            if (person.equals(user)) continue; // 마르코 빼고 모든 사람들의 친구 목록을 구한다.
            int friendScore = computeFriendScore(userFriends, findFriends(friends, person));

            score.put(person, friendScore);
        }

        for (String visitor : visitors) {
            if (score.get(visitor) == null) {
                score.put(visitor, 1);
                continue;
            }
            if (score.get(visitor) == 0) continue;
            score.put(visitor, score.get(visitor) + 1);
        }

        List<Map.Entry<String, Integer>> scores = new ArrayList<>(score.entrySet());
        scores.sort((o1, o2) ->o2.getValue() - o1.getValue());

        List<String> goodFriends = new ArrayList<>();

        for (Map.Entry<String, Integer> scoreEntry : score.entrySet()) {
            if (scoreEntry.getValue() == 0) continue;
            if (goodFriends.size() >= 5) break;
            goodFriends.add(scoreEntry.getKey());
        }

        return goodFriends;
    }

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();
        Problem7 problem7 = new Problem7();
        answer = problem7.recommend(user, friends, visitors);
        return answer;
    }
}
