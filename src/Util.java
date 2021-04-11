public class Util {

    public static void arrayPrinter2d(int[][] nums) {
        for(int i=0; i<nums.length; i++) {
            for(int j=0; j<nums[i].length; j++) {
                System.out.print("\t" + nums[i][j]);
            }
            System.out.println();
        }
    }
}
