package com.elsy.simpleinstagram;

import com.elsy.simpleinstagram.util.ActivityHelper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ValidationsUnitTest {

    @Test
    public void invalidHostname_isCorrect() {
        assertEquals(false, ActivityHelper.isValidHostame("google.com"));
    }

    @Test
    public void validHostname_isCorrect() {
        assertEquals(true, ActivityHelper.isValidHostame("photomaton.herokuapp.com"));
    }

}
