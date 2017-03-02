package com.epam.javase.t05;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.MessageFormat;
import java.util.DoubleSummaryStatistics;

/**
 * Represents subjects and min/max grades for the subjects.
 * Each subject restricts grade value by integer/double.
 *
 * Created by aivanov on 2/28/2017.
 */
public enum Subject {
    MATH(1,5, true),
    ENGLISH(1, 100, true),
    CHEMISTRY(1, 10, true),
    HISTORY(0.1, 10.1, false),
    SPANISH(0.1, 10.0, false),
    BIOLOGY(0.1, 100.0, false);

    private final double minGrade;
    private final double maxGrade;
    private boolean isInteger;

    Subject(double minGrade, double maxGrade, boolean isInteger) {
        this.minGrade = minGrade;
        this.maxGrade = maxGrade;
        this.isInteger = isInteger;
    }

    /**
     * Converts grade to integer or double to be in line with subject restriction.
     *
     * @param grade grade to convert
     * @return return converted number
     */
    public Number convertGradeToAcceptableFormat(Number grade) {

        double temp = grade.doubleValue();

        if (Double.compare(temp, minGrade) < 0 ||
                Double.compare(temp, maxGrade) > 0) {

               throw new IllegalArgumentException(MessageFormat.format("The value {0} is out of range {1} - {2}.",
                       grade, minGrade, maxGrade));
        }

        if (isInteger) {
            return new Integer(grade.intValue());
        }

        return new Double(temp);
    }

    public double getMinGrade() {
        return minGrade;
    }

    public double getMaxGrade() {
        return maxGrade;
    }
}

