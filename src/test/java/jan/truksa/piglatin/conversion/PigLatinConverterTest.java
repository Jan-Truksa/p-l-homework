package jan.truksa.piglatin.conversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PigLatinConverterTest {


    @Test
    void consonantTest() {
        final String pigLatinText = new PigLatinConverter().apply("Hello");
        assertEquals("Ellohay", pigLatinText);
    }

    @Test
    void vowelTest() {
        final String pigLatinText = new PigLatinConverter().apply("apple");
        assertEquals("appleway", pigLatinText);
    }

    @Test
    void endsWithWayTest() {
        final String pigLatinText = new PigLatinConverter().apply("stairway");
        assertEquals("stairway", pigLatinText);
    }

    @Test
    void punctuationTest() {
        final String pigLatinText = new PigLatinConverter().apply("can’t");
        final String pigLatinOtherText = new PigLatinConverter().apply("end.");
        assertEquals("antca’y", pigLatinText);
        assertEquals("endway.", pigLatinOtherText);
    }

    @Test
    void hyphensTest() {
        final String pigLatinText = new PigLatinConverter().apply("this-thing");
        assertEquals("histay-hingtay", pigLatinText);
    }

    @Test
    void capitalizationTest() {
        final String pigLatinText = new PigLatinConverter().apply("Beach");
        final String pigLatinOtherText = new PigLatinConverter().apply("McCloud");
        assertEquals("Eachbay",pigLatinText);
        assertEquals("CcLoudmay", pigLatinOtherText);
    }

}
