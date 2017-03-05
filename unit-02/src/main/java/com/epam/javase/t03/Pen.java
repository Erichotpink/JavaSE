package com.epam.javase.t03;

import java.awt.*;
import java.math.BigDecimal;

/**
 * Represents a simple pen.
 *
 * Created by aivanov on 3/1/2017.
 */
public class Pen extends Stationery {

    private Color color;
    private double lineWidth;

    public Pen(double price) {
        this(price, Color.BLUE, 0.5);
    }

    public Pen(double price, Color color, double width) {
        this.price = price;
        this.color = color;
        this.lineWidth = width;
        this.name = "Pen";
    }

    public Color getColor() {
        return color;
    }

    public double getLineWidth() {
        return lineWidth;
    }

}
