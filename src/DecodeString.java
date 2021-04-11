import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * https://leetcode.com/problems/decode-string/
 */
public class DecodeString {

    public String decodeString(String s) {
        StringBuilder sol = new StringBuilder();

        Stack<String> stack = new Stack<>();

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if(Character.isDigit(c)) {
                String tmp = "";
                if(!stack.isEmpty() && isNumeric(stack.peek())) {
                    tmp = stack.pop() + c;
                }
                else {
                    tmp = tmp + c;
                }
                stack.push(tmp);
                continue;
            }

            if(c == '[') {
                stack.add("[");
                continue;
            }

            if(c == ']') {
                //String tmpStr = "";
                StringBuilder tmpStr = new StringBuilder();
                String top;
                do {
                    top = stack.pop();
                    char cTmp = top.charAt(0);
                    if(Character.isLetter(cTmp)) {
                        //tmpStr = top + tmpStr;
                        tmpStr = new StringBuilder(top).append(tmpStr);
                    }
                }
                while ('[' != top.charAt(0));

                top = stack.pop();
                int k = Integer.parseInt(top);
                StringBuilder encodedString = new StringBuilder();
                while(k > 0) {
                    encodedString.append(tmpStr);
                    --k;
                }
                stack.push(encodedString.toString());
                continue;
            }

            if(Character.isLetter(c)) {
                StringBuilder strTmp = new StringBuilder();
                strTmp.append(c);
                while((i + 1) < s.length() && Character.isLetter(s.charAt(i+1))){
                    strTmp.append(s.charAt(i+1));
                    i++;
                }
                stack.push(strTmp.toString());
                continue;
            }
        }

        for(String str:stack){
            sol = sol.append(str);
        }
        return sol.toString();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void main(String args[]) {
        DecodeString obj = new DecodeString();
        System.out.println(obj.decodeString("3[a]2[bc]"));
        System.out.println(obj.decodeString("3[a2[c]]"));
        System.out.println(obj.decodeString("2[abc]3[cd]ef"));
        System.out.println(obj.decodeString("abc3[cd]xyz"));
    }
}
