package ru.sysoevm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestUnpack {

    Unpack unp;
    String str = "";
    String input = "";

    @Before
    public void setUp() {
        unp = new Unpack();
    }

    @Test
    public void whenThereOneLevelNesting() throws InputErrorException {
        input = "3[xyz]4[xy]z";
        str = unp.unpackStr(input);
        assertThat(str, is("xyzxyzxyzxyxyxyxyz"));
    }

    @Test
    public void whenThereTwoLevelsNesting() throws InputErrorException {
        input = "2[3[x]y]";
        str = unp.unpackStr(input);
        assertThat(str, is("xxxyxxxy"));
    }

    @Test(expected = InputErrorException.class)
    public void whenInputFormatIsIncorrect() throws InputErrorException {
        input = "3[xyz4[xy]z";
        unp.unpackStr(input);
    }
}
