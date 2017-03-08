package com.epam.javase.t02;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/8/2017.
 */
public class HandbookTest {
    @Test
    public void testGetQuestionsIDsReturnNotEmptyData() throws Exception {
        Handbook handbook = new Handbook();

        assertFalse(handbook.getQuestionsIDs().isEmpty());
        handbook.getQuestionsIDs().stream().forEach(s -> assertFalse(s.isEmpty()));
    }

    @Test
    public void getQuestionByID() throws Exception {

    }

    @Test
    public void getAnswer1() throws Exception {

    }

    @Test
    public void changeLocale() throws Exception {

    }

    @Test
    public void getQuestions() throws Exception {
        Handbook data = new Handbook();

//        assertTrue(data.getQuestions().size() != 0);
//        data.getQuestions().stream().forEach(s -> assertFalse(s.isEmpty()));
    }

    @Test
    public void getAnswer() throws Exception {
        Handbook data = new Handbook();

//        data.getQuestions().stream().forEach(s -> assertFalse(data.getAnswer(s).isEmpty()));
    }

}