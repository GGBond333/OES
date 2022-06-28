package com.oes.gbloes.utils;

public class ExamUtil {

    public static Integer scoreFromVM(String score) {
        if (score == null) {
            return null;
        } else {
            return (int) (Float.parseFloat(score) * 10);
        }
    }
}
