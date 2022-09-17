package ru.skypro.course2.algorithms.CustomArrayListTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.course2.algorithms.CustomArrayList;
import ru.skypro.course2.algorithms.CustomListInterface;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    private CustomListInterface<String> out;

    @BeforeEach
    void setUp() {
        out = new CustomArrayList<>();
        out.add("1");
        out.add("2");
        out.add("3");
    }

    @Test
    void addTailTest() {
        out.add("4");

        assertArrayEquals(new String[]{"1", "2", "3", "4"}, out.toArray());
    }

    @Test
    void addTailReturnValueTest() {
        String expected = "4";

        assertEquals(expected, out.add("4"));
    }

    @Test
    void addMiddleTest() {
        out.add(1,"3");

        assertArrayEquals(new String[]{"1", "3", "2", "3"}, out.toArray());
    }

    @Test
    void addMiddleReturnValueTest() {
        String expected = "4";

        assertEquals(expected, out.add(1, "4"));
    }

    @Test
    void setTest() {
        out.set(1, "3");

        assertArrayEquals(new String[]{"1", "3", "3"}, out.toArray());
    }

    @Test
    void setReturnValueTest() {
        assertEquals("3", out.set(1, "3"));
    }

    @Test
    void removeByIndexTest() {
        out.remove(0);
        assertArrayEquals(new String[]{"2", "3"}, out.toArray());
    }

    @Test
    void removeByIndexReturnValueTest() {
        String expected = "1";

        assertEquals(expected, out.remove(0));
    }

    @Test
    void removeByElementTest() {
        String testString = "3";
        out.remove(testString);

        assertArrayEquals(new String[]{"1", "2"}, out.toArray());
    }

    @Test
    void removeByElementReturnValueTest() {
        String testString = "3";
        assertEquals(testString, out.remove(testString));
    }

    @Test
    void containsElementTest() {
        String testString = "3";
        assertTrue(out.contains(testString));
    }

    @Test
    void containsNotIssetElementTest() {
        String testString = "100";
        assertFalse(out.contains(testString));
    }

    @Test
    void indexOfTest() {
        String testString = "2";
        assertEquals(1, out.indexOf(testString));
    }

    @Test
    void indexOfNotIssetElementTest() {
        String testString = "12";
        assertEquals(-1, out.indexOf(testString));
    }

    @Test
    void lastIndexOfTest() {
        String testString = "3";
        out.add("4");
        assertEquals(2, out.lastIndexOf(testString));
    }

    @Test
    void lastIndexOfNotIssetElementTest() {
        String testString = "13";
        out.add("4");
        assertEquals(-1, out.lastIndexOf(testString));
    }

    @Test
    void getByIndexTest() {
        assertEquals("2", out.get(1));
    }

    @Test
    void equalsTest() {
        CustomListInterface<String> expected = new CustomArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");

        assertTrue(expected.equals(out));
    }

    @Test
    void notEqualsTest() {
        CustomListInterface<String> expected = new CustomArrayList<>();
        expected.add("1");
        expected.add("3");

        assertFalse(expected.equals(out));
    }

    @Test
    void sizeTest() {
        assertEquals(3, out.size());
    }

    @Test
    void decreaseSizeTest() {
        out.remove(0);
        assertEquals(2, out.size());
    }

    @Test
    void increaseSizeTest() {
        out.add("0");
        assertEquals(4, out.size());
    }

    @Test
    void isEmptyTest() {
        out.clear();
        assertTrue(out.isEmpty());
    }

    @Test
    void clearTest() {
        out.clear();
        assertEquals(0, out.size());
    }

    @Test
    void sortTest() {
        out.add("1010");
        out.add("101");
        out.add("150");
        out.add("120");
        out.add("30");
        out.add("50");
        out.add("500");
        out.add("110");
        out.add("10");
        out.add("180");
        out.contains("10");

        String[] expected = new String[]{"1", "2", "3", "10", "30", "50", "101", "110", "120", "150", "180", "500", "1010"};

        assertArrayEquals(
            expected,
            out.toArray()
        );
    }

    @Test
    void toArrayTest() {
        assertArrayEquals(new String[]{"1", "2", "3"}, out.toArray());
    }

    @Test
    void toArrayEmptyTest() {
        assertArrayEquals(new String[0], (new CustomArrayList<String>()).toArray());
    }

    @Test
    void testToStringTest() {
        assertEquals("[1, 2, 3]", out.toString());
    }

    @Test
    void testEmptyToStringTest() {
        assertEquals("[]",  (new CustomArrayList<String>()).toString());
    }
}