/**
 * https://leetcode.com/problems/multiply-strings/
 */
public class MultiplyString {
    public String multiply(String num1, String num2) {
        if(num1.length() < num2.length()) {
            return multiply(num2, num1);
        }

        char[] a = reverse(num1.toCharArray());
        char[] b = reverse(num2.toCharArray());
        char[] sol = {'0'};
        int shift = 0;

        for(int i=0; i<b.length; i++) {
            char[] curr = {'0'};
            int mul = b[i]-'0';
            for(int j=0; j<mul; j++) {
                curr = adder(curr, a);
            }
            for(int j=0; j<shift; j++) {
                curr = x10(curr);
            }
            sol = adder(curr, sol);
            shift++;
        }

        return new String(reverse(sol));
    }

    //a and b are in reverse order
    public char[] adder(char[] a, char[] b) {
        if(a.length < b.length) {
            return adder(b, a);
        }
        //a is either equal or greater than b

        char[] sol = new char[a.length];
        int i=0;    int j=0;    int k=0;
        int carry = 0;
        while(j<b.length) {
            int curr = (a[i++] - '0') + (b[j++] - '0') + carry;
            carry = curr/10;
            curr = curr%10;
            sol[k++] = (char)('0' + curr);
        }

        while(i<a.length) {
            int curr = (a[i++] - '0') +  carry;
            carry = curr/10;
            curr = curr%10;
            sol[k++] = (char)('0' + curr);
        }

        if(carry > 0) {
            char[] sol2 = new char[sol.length+1];
            int l=0;
            for(; l<sol.length;l++) {
                sol2[l] = sol[l];
            }
            sol2[l] = (char)('0' + carry);
            sol=sol2;
        }

        return sol;
    }

    public char[] x10(char[] a) {
        char[] sol = new char[a.length+1];
        sol[0] = '0';
        for(int l=1; l<sol.length;l++) {
            sol[l] = a[l-1];
        }
        return sol;
    }

    public char[] reverse(char[] a) {
        if(a.length == 0)   return new char[0];
        char[] sol = new char[a.length];

        for(int i=0; i<a.length; i++) {
            sol[i] = a[a.length -1 -i];
        }
        return sol;
    }

    public static void main(String args[]) {
        MultiplyString obj = new MultiplyString();
        /*String a = "0000";
        String b = "99999999";

        char[] sol = obj.adder(obj.reverse(a.toCharArray()), obj.reverse(b.toCharArray()));

        //System.out.println(String.valueOf(sol));
        System.out.println(String.valueOf(obj.reverse(sol)));

        String c = "00";
        char[] sol2 = obj.x10(obj.reverse(c.toCharArray()));
        System.out.println(String.valueOf(obj.reverse(sol2)));*/

        String x = "52";
        String y = "0";

        System.out.println(obj.multiply(x, y));
        System.out.println(obj.multiply(y, x));
    }
}
