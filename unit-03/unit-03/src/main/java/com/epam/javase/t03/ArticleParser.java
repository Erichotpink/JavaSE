package com.epam.javase.t03;

import java.io.InputStream;

/**
 * Created by aivanov on 3/8/2017.
 */
public class ArticleParser {

    private final InputStream stream = getClass().getResourceAsStream("resources/com.epam.javase.t03/article.html");

    public ArticleParser() {
        System.out.println(stream == null);
    }

    public static void main(String[] args) {
        ArticleParser temp = new ArticleParser();

    }
}
