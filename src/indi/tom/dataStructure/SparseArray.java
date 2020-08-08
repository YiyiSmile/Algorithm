package indi.tom.dataStructure;

/**
 * @Author totian
 * @Date 2019/11/8 21:03
 * @Version 1.0
 * @Description Convert a two dimensional array representing a chessboard into a sparse array,
 * where the value 1 of the array represents a white chess piece, 2 represents a black chess piece.
 */
public class SparseArray {
    public static void main(String[] args) {
        /**
         * define the original two-dimensional array.
         */
        int[][] chessArray = new int[11][11];
        chessArray[2][3] = 1;
        chessArray[3][6] = 2;

//        for (int[] row : chessArray) {
//            for (int i : row) {
//                System.out.printf("%d\t", i);
//            }
//            System.out.println();
//        }
        /**
         * Convert the original array to the sparse array.
         * 1. get the number of non-zero element
         */
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if(chessArray[i][j] != 0){
                    sum++;
                }
            }
        }
//        System.out.println(sum);
        /**
         2. Define the spares array and set the value for the elements
         */
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        int row = 1;

        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if(chessArray[i][j] != 0){
                       sparseArray[row][0] = i;
                       sparseArray[row][1] = j;
                       sparseArray[row][2] = chessArray[i][j];
                       row++;
                }
            }
        }
        for (int i = 0; i < sum+1; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d\t",sparseArray[i][j]);
            }
            System.out.println();
        }

        /**
         * Converts the spares array to the two dimensional array.
         */
        int row1 = sparseArray[0][0];
        int col1 = sparseArray[0][1];
        int[][] chessArray2 = new int[row1][col1];

//        int row2 = 0;
//        int col2 = 0;
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        for (int i = 0; i < chessArray2.length; i++) {
            for (int j = 0; j < chessArray2[i].length; j++) {
                System.out.printf("%d\t",chessArray2[i][j]);
            }
            System.out.println();
        }

    }
}
