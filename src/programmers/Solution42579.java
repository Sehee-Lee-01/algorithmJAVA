package programmers;

import java.util.*;

class Solution {

    static class Music {
        int num = -1;
        int listenCnt = 0;

        Music(int num, int listenCnt) {
            this.num = num;
            this.listenCnt = listenCnt;
        }
    }

    static class GenreInfo {
        long total = 0;
        PriorityQueue<Music> musics = new PriorityQueue<>((m1, m2) -> {
            if (m1.listenCnt != m2.listenCnt) {
                return m2.listenCnt - m1.listenCnt;
            }
            return m1.num - m2.num;
        });

        void addMusic(int num, int listenCnt) {
            musics.add(new Music(num, listenCnt));
            total += listenCnt;
        }
    }

    public int[] solution(String[] genres, int[] plays) {

        Map<String, GenreInfo> genreMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            GenreInfo genreInfo = genreMap.get(genres[i]);
            if (genreInfo == null) {
                genreInfo = new GenreInfo();
                genreInfo.addMusic(i, plays[i]);
                genreMap.put(genres[i], genreInfo);
            } else {
                genreInfo.addMusic(i, plays[i]);
            }
        }

        List<Integer> preAnswer = new ArrayList<>();
        genreMap.values().stream().sorted((g1, g2) -> (int) (g2.total - g1.total))
                .forEach(gInfo -> {
                    PriorityQueue<Music> musics = gInfo.musics;
                    preAnswer.add(musics.poll().num);

                    if (!musics.isEmpty()) {
                        preAnswer.add(musics.poll().num);
                    }
                });

        int[] answer = new int[preAnswer.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = preAnswer.get(i);
        }

        return answer;
    }
}