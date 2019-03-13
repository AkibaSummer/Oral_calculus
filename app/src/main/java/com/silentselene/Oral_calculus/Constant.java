package com.silentselene.Oral_calculus;

class Constant {
    static int problemNum = 10;       //每次题目数量
    static final int problemTypes = 13;       //总题目类型
    static int score = 0;                    //当前分数
    static int type = 0;                     //当前类型

    final static int[] type_name = {
            R.string.p_1,
            R.string.p_2,
            R.string.p_3,
            R.string.p_4,
            R.string.p_5,
            R.string.p_6,
            R.string.p_7,
            R.string.p_8,
            R.string.p_9,
            R.string.p_10,
            R.string.p_11,
            R.string.p_12,
            R.string.p_13,
    };

    final static int[] type_time = { //每题时间
            10, 10, 10, 10, 10,
            10, 10, 10, 10, 10,
            10, 10, 10
    };
}

class Board implements Comparable<Board> {
    String name;
    int score;

    @Override
    public int compareTo(Board o) {
        return -Integer.compare(score, o.score);
    }
}
