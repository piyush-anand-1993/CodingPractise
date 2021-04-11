import java.util.*;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<String> retrieveMostFrequentlyUsedWords(String helpText,
                                                 List<String> wordsToExclude)
    {
        if(helpText == null || helpText.isEmpty()) {
            return new ArrayList<>();
        }
        Set<String> exclude = new HashSet<>(wordsToExclude);
        String words[] = helpText.toLowerCase().split("\\W+");
        System.out.println(Arrays.deepToString(words));
        Map<String, Integer> occuranceCount = new HashMap<>();
        int maxCount = 0;

        for(int i=0; i<words.length; i++) {
            if(!exclude.contains(words[i])) {
                int currCount = 0;
                if(occuranceCount.containsKey(words[i])) {
                    currCount = occuranceCount.get(words[i]);
                }
                occuranceCount.put(words[i], currCount + 1);
                if(maxCount < currCount + 1) {
                    maxCount = currCount + 1;
                }
            }
        }

        List<String> sol = new ArrayList<>();
        for(String word: occuranceCount.keySet()) {
            if(occuranceCount.get(word) == maxCount) {
                sol.add(word);
            }
        }
        return sol;
    }

    public static void main(String args[]) {
        Solution obj = new Solution();
        //String help = "Jack and Jill 4 went to 4 the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
        String help = "cheeze said \"cheeze stop that shit\"";
        List<String> exc = new ArrayList<>();
        //{and, he, the, to, is
        exc.add("and");
        exc.add("he");
        exc.add("the");
        exc.add("to");
        exc.add("is");
        System.out.println(obj.retrieveMostFrequentlyUsedWords(help, exc).toString());
    }

}