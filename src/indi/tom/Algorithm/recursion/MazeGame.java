package indi.tom.Algorithm.recursion;

import sun.font.FontRunIterator;

/**
 * @Author Tom
 * @Date 2019/11/10 14:52
 * @Version 1.0
 * @Description 迷宫游戏。规则定义：
 * 1. 创建一个 7*8的二维数组，代表一个地图或者棋盘
 * 2. 小球只能上下左右走
 * 3. 走到指定目标节点
 * 4. 数组的值：0 代表没有走过，1代表墙，2代表走过 3 代表走过，但是没走通
 *
 */
public class MazeGame {
    //定义地图
    private static int[][] map = {
            {1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1},
            {1,0,1,0,0,0,1},
            {1,0,1,0,0,0,1},
            {1,0,1,0,0,0,1},
            {1,0,1,0,0,0,1},
            {1,0,1,0,0,0,1},
            {1,1,1,1,1,1,1}
    };

    public static void main(String[] args) {
        setWay(map, 3, 1);
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.printf("%d\t",map[i][j]);
            }
            System.out.println();
        }
    }

    //给定地图和起始坐标（x,y）, 目的坐标（6,5）,选择可行的路径
    //并将经过的路径设置成2，走过的走不通的设置成3
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){
            return true;
        }else{
            if(map[i][j] == 0){
                map[i][j] = 2;
                //按照策略下-右-上-左的策略
                if(setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                }else if (setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else{
                    map[i][j] = 3;
                    return false;
                }
            }else{//map[i][j] != 0, it could be 1,2,3
                return false;
            }
        }

    }

}
