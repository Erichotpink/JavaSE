package com.epam.javase.t04;

/**
 * Represents an abstract class for common stationery.
 *
 * Created by aivanov on 3/1/2017.
 */
public abstract class Stationery {

    protected String name;
    protected double price;

    /**
     * Get the stationery name.
     *
     * @return stationery name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the stationery price
     *
     * @return stationery price
     */
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Stationery{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
