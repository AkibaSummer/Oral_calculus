package com.silentselene.Oral_calculus;

import java.util.Random;

class Ret {
    String problem, ans;

    Ret() {
        problem = "p";
        ans = "a";
    }

    Ret(String _problem, String _ans) {
        problem = _problem;
        ans = _ans;
    }

    @Override
    public String toString() {
        return "problem:" + problem + ";  ans:" + ans;
    }
}

final class getProblem {
    private static Random rand = new Random();

    private getProblem() {
    }

    private static Ret generateAdd(int max, int num, int flag) {   //生成加法运算 i:最大的数字 num:数字数量 flag:生成的位置
        int[] nums = new int[max];
        int sum1 = 0, sum2 = nums[0] = rand.nextInt(max) + 1;

        for (int i = 1; i <= num; i++)
            sum1 += nums[i] = rand.nextInt(max);
        for (int i = 1; i < num; i++)
            sum2 -= nums[i] = nums[i] * sum2 / sum1;
        nums[num] = sum2;

        StringBuilder problem = new StringBuilder();
        if (flag == 0) {
            for (int i = 1; i <= num; i++) {
                problem.append(String.valueOf(nums[i])).append(i == num ? " = " : " + ");
            }
            problem.append('?');
            return new Ret(problem.toString(), String.valueOf(nums[0]));
        } else {
            int loc = rand.nextInt(num) + 1;
            for (int i = 1; i <= num; i++) {
                problem.append(i == loc ? "?" : String.valueOf(nums[i])).append(i == num ? " = " : " + ");
            }
            problem.append(String.valueOf(nums[0]));
            return new Ret(problem.toString(), String.valueOf(nums[loc]));
        }
    }

    private static Ret generateSub(int max, int num, int flag) {   //生成减法运算 i:最大的数字 num:数字数量 flag:生成的位置
        int[] nums = new int[max];
        int sum1 = 0, sum2 = nums[1] = rand.nextInt(max) + 1;

        sum1 += rand.nextInt(max);
        for (int i = 2; i <= num; i++)
            sum1 += nums[i] = rand.nextInt(max);

        for (int i = 2; i <= num; i++)
            sum2 -= nums[i] = nums[i] * sum2 / sum1;
        nums[0] = sum2;

        StringBuilder problem = new StringBuilder();
        if (flag == 0) {
            for (int i = 1; i <= num; i++) {
                problem.append(nums[i]).append(i == num ? " = " : " - ");
            }
            problem.append("?");
            return new Ret(problem.toString(), String.valueOf(nums[0]));
        } else {
            int loc = rand.nextInt(num) + 1;
            for (int i = 1; i <= num; i++) {
                problem.append(i == loc ? "?" : String.valueOf(nums[i])).append(i == num ? " = " : " - ");
            }
            problem.append(String.valueOf(nums[0]));
            return new Ret(problem.toString(), String.valueOf(nums[loc]));
        }
    }

    private static Ret p1() { //10以内的加法
        return generateAdd(9, 2, 0);
    }

    private static Ret p2() { //20以内的加法
        return generateAdd(20, 2, 0);
    }

    private static Ret p3() { //100以内加法
        return generateAdd(99, rand.nextInt(1) + 2, 0);
    }

    private static Ret p4() { //万以内的加法
        return generateAdd(10000, rand.nextInt(2) + 2, 0);
    }

    private static Ret p5() { //10以内的加减法
        if (rand.nextBoolean()) return generateAdd(9, 2, rand.nextInt(1));
        else return generateSub(9, 2, rand.nextInt(1));
    }

    private static Ret p6() { //20以内的加减法
        if (rand.nextBoolean()) return generateAdd(20, 2, rand.nextInt(1));
        else return generateSub(20, 2, rand.nextInt(1));
    }

    private static Ret p7() { //100以内的加减法
        if (rand.nextBoolean()) return generateAdd(99, rand.nextInt(1) + 2, rand.nextInt(1));
        else return generateSub(99, rand.nextInt(1) + 2, rand.nextInt(1));
    }

    private static Ret p8() { //万以内的加减法
        if (rand.nextBoolean()) return generateAdd(10000, rand.nextInt(2) + 2, rand.nextInt(1));
        else return generateSub(10000, rand.nextInt(2) + 2, rand.nextInt(1));
    }

    private static Ret p9() { //表内乘法
        int a = rand.nextInt(9) + 1, b = rand.nextInt(9) + 1;
        return new Ret(a + " * " + b + " = ?", String.valueOf(a * b));
    }

    private static Ret p10() { //表内除法
        int a = rand.nextInt(9) + 1, b = rand.nextInt(9) + 1;
        return new Ret(a * b + " / " + a + " = ?", String.valueOf(b));
    }

    private static Ret p11() { //表内乘除法
        if (rand.nextBoolean()) return p9();
        else return p10();
    }

    private static Ret p12() { //带余除法
        int a = rand.nextInt(9) + 1, b = rand.nextInt(9) + 1, c = rand.nextInt(a);
        if (rand.nextBoolean())
            return new Ret((a * b + c) + " / " + a + " = ? …… " + c, String.valueOf(b)); //求商
        else return new Ret((a * b + c) + " / " + a + " = " + b + " …… ?", String.valueOf(c)); //求余数
    }

    private static Ret p13() { //两位数的乘法
        int a = rand.nextInt(90) + 10, b = rand.nextInt(90) + 10;
        return new Ret(a + " * " + b + " = ?", String.valueOf(a * b));
    }

    static Ret get(int i) {
        i++;
        switch (i) {
            case 1:
                return p1();
            case 2:
                return p2();
            case 3:
                return p3();
            case 4:
                return p4();
            case 5:
                return p5();
            case 6:
                return p6();
            case 7:
                return p7();
            case 8:
                return p8();
            case 9:
                return p9();
            case 10:
                return p10();
            case 11:
                return p11();
            case 12:
                return p12();
            case 13:
                return p13();
            default:
                return new Ret("not","type"+i);
        }
    }
}
