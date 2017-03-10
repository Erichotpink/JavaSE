package com.epam.javase.t03;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/8/2017.
 */
public class ArticleParserTest {

    private ArticleParser parser = new ArticleParser();

    @Test
    public void checkSentencesWithRef() throws Exception {
        String str1 = "Электродвижущая сила (э.д.с.) элементарных спиновых зарядов, " +
                "в батарее элементарных спиновых зарядов (Рис. 6) равна сумме э.д.с. " +
                "элементарных трубок гамма-нейтрино (Рис. 2), составляющих «спиновую батарею» " +
                "зарядов в спиновой трубке протона.";
        String str2 = "Элементарный неэлектростатический заряд состоит из двух элементарных" +
                " электростатических (кулоновских) зарядов противоположного знака в элементарных " +
                "трубках гамма-коллапсаров (Рис. 2).";

        List<String> list = parser.getSentencesWithRef();

        assertFalse(list.isEmpty());
        assertTrue(list.contains(str1));
        assertTrue(list.contains(str2));
    }

    @Test
    public void checkSentencesWithWrongRefOrder() throws Exception {

        String str1 = "В седьмой спиновой гамма-трубке позитрона происходит деление одного" +
                " гамма-коллапсара (Рис. 2) на две «семёрки» (Рис. 1).";

        String str2 = "Электродвижущая сила (э.д.с.) элементарных спиновых зарядов, в батарее элементарных " +
                "спиновых зарядов (Рис. 6) равна сумме э.д.с. элементарных трубок гамма-нейтрино (Рис. 2), " +
                "составляющих «спиновую батарею» зарядов в спиновой трубке протона.";

        List<String> list = parser.getSentencesWithWrongRefOrder();

        assertFalse(list.isEmpty());
        assertTrue(list.contains(str1));
        assertTrue(list.contains(str2));
    }

}