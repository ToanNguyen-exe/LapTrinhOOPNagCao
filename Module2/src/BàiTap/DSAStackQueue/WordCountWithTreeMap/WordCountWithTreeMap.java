package DSAStackQueue.WordCountWithTreeMap;

import java.util.TreeMap;

public class WordCountWithTreeMap {
    public static void main(String[] args) {
        String input = "java is fun and java is powerful and fun";

        String[] words = input.toLowerCase().split("\\s+");
        TreeMap<String, Integer> wordCount = new TreeMap<>();
        for (String word : words) {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 2);
            }
        }

        System.out.println("Word Counts:");
        for (String word : wordCount.keySet()) {
            System.out.println(word + ": " + wordCount.get(word));
        }
    }


}
