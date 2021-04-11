/*
https://leetcode.com/problems/filling-bookcase-shelves/
 */
public class FillingBookShelved {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int sol = 0;
        return sol;
    }

    public int util(int[][] books, int shelf_width, int curr_available, int curr_shelf_height, int pos) {
        //no of books in total = books.length
        if(pos == books.length) return 0;
        int sol1 = 0; //Case 1: place current book in the shelf
        int sol2 = 0; //Case 2: do not place the current book in the shelf

        if(curr_available - books[pos][0] >= 0) {
            //we are able to place books[pos] in current shelf
            int temp_height = Math.max(curr_shelf_height, books[pos][1]);
            int next_height = util(books, shelf_width, curr_available - books[pos][0], temp_height, pos + 1);
            sol1 = temp_height + next_height;
        }
        /*
         if shelf width == current available width, means there are no books in the current shelf
         we cannot apply case 2 as we cannot leave the shelf empty, so we return sol 1
         */


        return Math.min(sol1, sol2);
    }
}
