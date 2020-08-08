package indi.tom.algorithm;

/**
 * @Author Tom
 * @Date 2019/11/10 20:11
 * @Version 1.0
 * @Description Eight queens issue.
 */
public class Queen8 {
    //the queens location, the index of the array represent the line number
    //the value of the array element represents the column of the queen location in the chessboard
    private static int[] queens = new int[8];
    //the number of answers
    private static int count;

    public static void main(String[] args) {
        check(0);
        System.out.println("totally there are " + count + " possibilities.");

    }

    //the final method to calculate all the possibility of the 8 queen's location.
    public static void check(int n){
        if(n == 8){
            //means find a possibility, print the current queen array
            print();
            return;
        }
        for (int i = 0; i < 8; i++) {
            queens[n] = i;
            if(judge(n)){
                check(n+1);
            }
        }
    }
    //print the current queens location
    public static void print(){
        count++;
        for (int i = 0; i < queens.length; i++) {
            System.out.printf("%d\t",queens[i]);
        }
        System.out.println();
    }
    //determine whether the given number of queen's location is conflict with the previous queens
    public static boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if(queens[i] == queens[n] || Math.abs(n-i) == Math.abs(queens[n] - queens[i])){
                return false;
            }
        }
        return true;
    }
}
