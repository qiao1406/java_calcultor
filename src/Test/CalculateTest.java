package Test;

import Calculator.Calculate;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class CalculateTest {
    static Calculate cal;

    @Before
    public void setUp() {
        cal = Calculate.getCalculate();
    }


    @Test
    public void calResult() throws Exception {
        assertEquals("2",  cal.calResult("1+1"));
        assertEquals("-2", cal.calResult("-1*2.0"));
        assertEquals("15241577639079420", cal.calResult("1234567890*12345678"));
        assertEquals("72", cal.calResult("2*(1+5*(2+5))"));
    }

    @Test
    public void checkFormat() {
        Class fakeClass = cal.getClass();
        try {
            Method checkFormat = fakeClass.getDeclaredMethod("checkFormat", String.class);
            checkFormat.setAccessible(true);

            // Wrong Case
            assertFalse((Boolean) checkFormat.invoke(cal, ")1+2"));
            assertFalse((Boolean) checkFormat.invoke(cal, "1+)2"));
            assertFalse((Boolean) checkFormat.invoke(cal, "(1+2)(1*67)"));
            assertFalse((Boolean) checkFormat.invoke(cal, "1+2(2*4)"));
            assertFalse((Boolean) checkFormat.invoke(cal, "1+2."));
            assertFalse((Boolean) checkFormat.invoke(cal, "(.2*6)"));
            assertFalse((Boolean) checkFormat.invoke(cal, "(2+)"));
            assertFalse((Boolean) checkFormat.invoke(cal, "(+)"));
            assertFalse((Boolean) checkFormat.invoke(cal, "333*"));


            // Right Case
            assertTrue((Boolean) checkFormat.invoke(cal, "1+2*5"));

        } catch ( NoSuchMethodException e ) {
            System.out.println("没有这个方法：checkFormat");
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();
        } catch ( InvocationTargetException e ) {
            e.printStackTrace();
        }
    }

}