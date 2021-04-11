class GCD
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED

    public int gcd(int a, int b) {
        if(a == 0) return b;
        if(b == 0) return a;
        if(a == b) return a;
        if(a > b) return gcd(a-b, a);
        return gcd(a,b-a);
    }

    public int generalizedGCD(int num, int[] arr)
    {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 0){
                return 0;
            }
        }

        int currGCD = gcd(arr[0], arr[1]);
        for(int i=2; i<arr.length; i++) {
            currGCD = gcd(currGCD, arr[i]);
        }
        return currGCD;
    }

    public static void main(String args[]) {
        GCD obj = new GCD();
        int arr[] = {14, 378, 28};
        //obj.generalizedGCD(3, arr);
        System.out.println(obj.generalizedGCD(3, arr));
    }
}