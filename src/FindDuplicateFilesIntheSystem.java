import java.util.*;

/*
https://leetcode.com/problems/find-duplicate-file-in-system/

must be solved with a trie
 */
public class FindDuplicateFilesIntheSystem {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> duplicateMap = new HashMap<>();

        for(String dir: paths) {
            String[] temp = dir.split(" ");
            String dirPath = temp[0];
            for(int i = 1; i<temp.length; i++) {
                String str = temp[i];
/*                int idx = str.indexOf('(');
                String path = str.substring(0, idx);
                String content = str.substring(idx + 1, str.length() - 1);*/

                String[] abc = nameContentExtractor(str);
                String fileName = abc[0];
                String content = abc[1];

                if(!duplicateMap.containsKey(content)) {
                    duplicateMap.put(content, new ArrayList<>());
                }
                List<String> list = duplicateMap.get(content);
                list.add(dirPath + '/' + fileName);
                //duplicateMap.put(content, list);
            }
        }

        List<List<String>> sol = new ArrayList<>();
        for(List<String> list: duplicateMap.values()) {
            if (list.size() > 1) sol.add(list);
        }

        return sol;
    }

    private String[] nameContentExtractor(String str) {
        StringBuilder name = new StringBuilder();
        StringBuilder content = new StringBuilder();

        int i=0;
        for(;i<str.length();i++) {
            if(str.charAt(i) == '(') {
                i++;
                break;
            }
            name.append(str.charAt(i));
        }

        for(;i<str.length();i++) {
            if(str.charAt(i) == ')') break;
            content.append(str.charAt(i));
        }
        String[] spr = {name.toString(), content.toString()};
        return spr;
    }

    public static void main(String[] args) {
        String str = "root/c 3.txt(abcd)";
        int idx = str.indexOf('(');
        String path = str.substring(0, idx).replace(' ', '/');
        String content = str.substring(idx + 1, str.length() - 1);
        System.out.println("--------" + path + "----------");
        System.out.println("--------" + content + "----------");
        System.out.println("[[root/a/2.txt, root/c/d/4.txt, root/4.txt], [root/a/1.txt, root/c/3.txt]]");

        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        FindDuplicateFilesIntheSystem obj = new FindDuplicateFilesIntheSystem();
        List<List<String>> sol = obj.findDuplicate(paths);

        System.out.println(sol.toString());
    }
}
