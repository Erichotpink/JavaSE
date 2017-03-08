package com.epam.javase.t02;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/8/2017.
 */
public class HandbookTest {

    private final Handbook handbook = new Handbook();
    private final Set<String> set = handbook.getQuestionsIDs();

    @Test
    public void testGetQuestionsIDsReturnNotEmptyData() throws Exception {
        assertFalse(handbook.getQuestionsIDs().isEmpty());
        handbook.getQuestionsIDs().stream().forEach(s -> assertFalse(s.isEmpty()));
    }

    @Test
    public void testGetQuestionByIDReturnNotEmptyData() throws Exception {
        set.stream().forEach(s -> assertFalse(handbook.getQuestionByID(s).isEmpty()));
    }

    @Test
    public void testIfWeHaveAnswerForEachQuestions() throws Exception {
        set.stream().forEach(s -> assertFalse((handbook.getAnswer(s).isEmpty())));

    }
}