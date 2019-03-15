package com.silentselene.Oral_calculus;

class Constant {
    static final int problemTypes = 13;       //总题目类型
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
            10, 10, 20, 30, 10,
            10, 20, 30, 10, 10,
            10, 20, 30
    };
    static int problemNum = 10;       //每次题目数量
    static int score = 0;                    //当前分数
    static int type = 0;                     //当前类型
    static int viewType = 0;
    static int correct = 0, incorrect = 0, timeout = 0;       //正确，错误题数
    static int totalTime = 0;                   //总用时
    static int year, month, day;
    static boolean isReview;
}

class Board {
    int year, month, day;
    int type, problemNum, totalTime, correct, incorrect, timeout, score;
}

class Incorrect {
    Ret ret = new Ret();
    int type;
}