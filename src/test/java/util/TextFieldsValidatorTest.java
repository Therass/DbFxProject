package util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TextFieldsValidatorTest {

    String firstString;
    String secondString;
    String thirdString;
    String fourthString;
    String emptyString = "";

    @Before
    public void setUp() {

        firstString = "fruit";
        secondString = "sort";
        thirdString = "amount";
        fourthString = "provider";
    }

    @Test
    public void isValidFourArgs() {
        TextFieldsValidator textFieldsValidator = new TextFieldsValidator();

        boolean actual = textFieldsValidator.isValid(firstString, secondString, thirdString, fourthString);

        boolean expected = true;

        assertEquals(expected, actual);

    }

    @Test
    public void isValidThreeArgs() {
        TextFieldsValidator textFieldsValidator = new TextFieldsValidator();

        boolean actual = textFieldsValidator.isValid(firstString, secondString, thirdString);

        boolean expected = true;

        assertEquals(expected, actual);

    }

    @Test
    public void isValidTwoArgs() {
        TextFieldsValidator textFieldsValidator = new TextFieldsValidator();

        boolean actual = textFieldsValidator.isValid(firstString, secondString);

        boolean expected = true;

        assertEquals(expected, actual);

    }

    @Test(timeout = 500) //still dont know how this works
    public void isValidOneArgs() {
        TextFieldsValidator textFieldsValidator = new TextFieldsValidator();

        boolean actual = textFieldsValidator.isValid(firstString);

        boolean expected = true;

        assertEquals(expected, actual);

    }

    @Test(expected = ExceptionInInitializerError.class)
    public void isValidEmptyString() {
        TextFieldsValidator textFieldsValidator = new TextFieldsValidator();

        boolean actual = textFieldsValidator.isValid(emptyString);

        boolean expected = false;

        assertEquals(expected, actual);
        assertFalse(actual);

    }

    @Ignore
    @Test
    public void alwaysFail() {
        Assert.fail();
    }
}


