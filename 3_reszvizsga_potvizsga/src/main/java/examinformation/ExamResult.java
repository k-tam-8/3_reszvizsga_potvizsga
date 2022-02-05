package examinformation;

import java.util.Comparator;

public class ExamResult  {
    private int practice;
    private int theory;

    public ExamResult(int theory, int practice) {
        this.theory = theory;
        this.practice = practice;
    }

    public int getPractice() {
        return practice;
    }

    public int getTheory() {
        return theory;
    }


}
