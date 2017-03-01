package com.epam.javase.t03;

import java.math.BigDecimal;

/**
 * Represents an abstract class for common stationery.
 *
 * Created by aivanov on 3/1/2017.
 */
public abstract class Stationery {

    private String name;
    private BigDecimal price;

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
    public BigDecimal getPrice() {
        return price;
    }
}
