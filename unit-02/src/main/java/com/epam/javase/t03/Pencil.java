package com.epam.javase.t03;

import java.awt.*;
import java.math.BigDecimal;

/**
 * Represents a simple pencil.
 *
 * Created by aivanov on 3/1/2017.
 */
public class Pencil extends Stationery {

    private double lineWidth;

    public Pencil(double price) {
        this(price, 0.5);
    }

    public Pencil(double price, double width) {
        this.price = price;
        this.lineWidth = width;
        this.name = "Pencil";
    }

    public double getLineWidth() {
        return lineWidth;
    }
}
