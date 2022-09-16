package ru.skypro.course2.algorithms.CustomArrayListTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.course2.algorithms.CustomArrayList;
import ru.skypro.course2.algorithms.CustomListInterface;
import ru.skypro.course2.algorithms.exception.ElementNotFoundException;
import ru.skypro.course2.algorithms.exception.IndexBoundException;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListIncorrectTest {

    private CustomListInterface<String> out;

    @BeforeEach
    void setUp() {
        out = new CustomArrayList<>();
        out.add("1");
        out.add("2");
        out.add("3");
    }

    @Test
    void addMiddleTest() {
        assertThrows(IndexBoundException.class, () -> out.add(10,"3"));
    }

    @Test
    void setTest() {
        assertThrows(IndexBoundException.class, () -> out.set(10, "3"));
    }

    @Test
    void removeByIndexUnboundTest() {
        assertThrows(IndexBoundException.class, () -> out.remove(10));
    }

    @Test
    void removeByIndexNotFoundTest() {
        assertThrows(ElementNotFoundException.class, () -> out.remove(String.valueOf(Integer.MAX_VALUE)));
    }

    @Test
    void getByIndexTest() {
        assertThrows(IndexBoundException.class, () -> out.get(Integer.MAX_VALUE));
    }

    @Test
    void equalsTest() {
        assertThrows(IllegalArgumentException.class, () -> out.equals(null));
    }
}