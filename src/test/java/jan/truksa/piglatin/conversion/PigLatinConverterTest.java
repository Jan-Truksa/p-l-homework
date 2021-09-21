package jan.truksa.piglatin.conversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PigLatinConverterTest {


    @Test
    void consonantTest() {
        final String pigLatinText = new PigLatinConverter().apply("Hello");
        assertEquals(pigLatinText,"Ellohay");
    }

    @Test
    void vowelTest() {
        final String pigLatinText = new PigLatinConverter().apply("apple");
        assertEquals(pigLatinText,"appleway");
    }

    @Test
    void endsWithWayTest() {
        final String pigLatinText = new PigLatinConverter().apply("stairway");
        assertEquals(pigLatinText,"stairway");
    }

    @Test
    void punctuationTest() {
        final String pigLatinText = new PigLatinConverter().apply("can’t");
        final String pigLatinOtherText = new PigLatinConverter().apply("end.");
        assertEquals(pigLatinText,"antca’y");
        assertEquals(pigLatinOtherText,"endway.");
    }

    @Test
    void hyphensTest() {
        final String pigLatinText = new PigLatinConverter().apply("this-thing");
        assertEquals(pigLatinText,"histay-hingtay");
    }

    @Test
    void capitalizationTest() {
        final String pigLatinText = new PigLatinConverter().apply("Beach");
        final String pigLatinOtherText = new PigLatinConverter().apply("McCloud");
        assertEquals(pigLatinText,"Eachbay");
        assertEquals(pigLatinOtherText,"CcLoudmay");
    }

}
