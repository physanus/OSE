package de.danielprinz.technikum.wordcounter;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class FileHandlerTest {

    private static final double DELTA = 0.00000000001;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCounter() throws Exception {

        Map<String, Integer> occurrences = WordAnalyser.getOccurrences(" a b, c. d\n \ne \n f gg h2     i\tj");

        Map<String, Integer> expected = new HashMap<String, Integer>() {{
            put("a", 1);
            put("b", 1);
            put("c", 1);
            put("d", 1);
            put("e", 1);
            put("f", 1);
            put("gg", 1);
            put("h2", 1);
            put("i", 1);
            put("j", 1);
        }};

        TestCase.assertEquals("something", expected, occurrences);

    }


    @Test
    public void testSorter() throws Exception {

        Map<String, Integer> toSort = new HashMap<String, Integer>() {{
            put("a", 9);
            put("b", 2);
            put("c", 4);
            put("d", 3);
            put("e", 6);
            put("f", 5);
            put("g", 1);
            put("h", 8);
            put("i", 7);
        }};

        Map<String, Integer> expected = new HashMap<String, Integer>() {{
            put("a", 9);
            put("h", 8);
            put("i", 7);
            put("e", 6);
            put("f", 5);
            put("c", 4);
            put("d", 3);
            put("b", 2);
            put("g", 1);
        }};

        TestCase.assertEquals("something", expected, MapUtils.sortByValue(toSort));

    }

}