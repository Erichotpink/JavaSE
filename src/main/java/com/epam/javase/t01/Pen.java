package com.epam.javase.t01;

import java.awt.*;
import java.util.Objects;

/**
 * Created by aivanov on 2/25/2017.
 */
public class Pen {

    private Color color;
    private double cost;
    private double lineWidth;

    public Pen(double cost) {
        this(Color.BLUE, cost, 0.5);
    }

    public Pen(double cost, double lineWidth) {
        this(Color.BLUE, cost, lineWidth);
    }

    public Pen(Color color, double cost, double lineWidth) {
        Objects.requireNonNull(color, "Color cannot be null.");
        if (cost <= 0.0) {
            throw new IllegalArgumentException("Cost cannot be negative. Specified value: " + cost);
        } else if (lineWidth <= 0) {
            throw new IllegalArgumentException("Line width cannot be negative. Specified value: " + lineWidth);
        }

        this.color = color;
        this.cost = cost;
        this.lineWidth = lineWidth;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Pen pen = (Pen) other;

        return (Double.compare(this.cost, pen.cost) != 0)
                && (Double.compare(this.lineWidth, pen.lineWidth) != 0)
                && this.color.equals(pen.color);
    }

    @Override
    public int hashCode() {
        int result;

        result = color.hashCode();
        result = 31 * result + Double.hashCode(cost);
        result = 31 * result + Double.hashCode(lineWidth);

        return result;
    }

    @Override
    public String toString() {
        return "Pen{" +
                "color=" + color +
                ", cost=" + cost +
                ", lineWidth=" + lineWidth +
                '}';
    }
}
