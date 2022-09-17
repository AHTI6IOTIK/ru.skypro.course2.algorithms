package ru.skypro.course2.algorithms;

import ru.skypro.course2.algorithms.exception.ElementNotFoundException;
import ru.skypro.course2.algorithms.exception.IndexBoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CustomArrayList<ElementsType> implements CustomListInterface<ElementsType> {
    private static final int BASE_SIZE_BUFFER = 10;

    private Object[] buffer;

    private int size = 0;

    public CustomArrayList() {
        buffer = new Object[BASE_SIZE_BUFFER];
    }

    public CustomArrayList(int size) {
        buffer = new Object[size];
    }

    @Override
    public ElementsType add(ElementsType item) {
        buffer[size++] = item;
        grow();

        return item;
    }

    @Override
    public ElementsType add(int index, ElementsType item) {
        indexBoundGuard(index);

        if (++size > buffer.length) {
            grow();
        }

        shiftForward(index);

        buffer[index] = item;
        return item;
    }

    @Override
    public ElementsType set(int index, ElementsType item) {
        indexBoundGuard(index);
        return (ElementsType) (buffer[index] = item);
    }

    @Override
    public ElementsType remove(ElementsType item) {
        int index = indexOf(item);
        if (index < 0) {
            throw new ElementNotFoundException();
        }
        remove(index);
        return item;
    }

    @Override
    public ElementsType remove(int index) {
        indexBoundGuard(index);
        Object result = buffer[index];
        shiftBack(index);
        buffer[size--] = null;
        return (ElementsType) result;
    }

    @Override
    public boolean contains(ElementsType item) {
        buffer = sort(buffer, size);
        return search(item);
    }

    @Override
    public int indexOf(ElementsType item) {
        for (int i = 0; i < size; i++) {
            if (buffer[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(ElementsType item) {
        for (int i = size - 1; i >= 0; i--) {
            if (buffer[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ElementsType get(int index) {
        indexBoundGuard(index);
        return (ElementsType) buffer[index];
    }

    @Override
    public boolean equals(CustomListInterface<ElementsType> otherList)  {
        if (null == otherList) {
            throw new IllegalArgumentException();
        }

        if (this == otherList) {
            return true;
        }

        if (size != otherList.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            ElementsType o1 = (ElementsType) buffer[i];
            Object o2 = otherList.get(i);

            if (!o1.equals(o2)) {
                return false;
            }
        }
        return true;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        buffer = new Object[BASE_SIZE_BUFFER];
    }

    @Override
    public ElementsType[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(buffer, 0, result, 0, size);
        return (ElementsType[]) result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    private void indexBoundGuard(int index) {
        if (index > size) {
            throw new IndexBoundException();
        }
    }

    private void shiftForward(int index) {
        for (int i = size - 1; i >= index; i--) {
            buffer[i + 1] = buffer[i];
        }
    }

    private void shiftBack(int index) {
        for (int i = index; i < size - 1; i++) {
            buffer[i] = buffer[i + 1];
        }
    }

    private void grow() {
        if (size >= buffer.length) {
            Object[] newBuffer = new Object[buffer.length * 2];
            System.arraycopy(buffer, 0, newBuffer, 0, size);
            buffer = newBuffer;
        }
    }

    private Object[] sort(Object[] arr, int size) {
        if (size < 2) {
            return arr;
        }

        Object pivot = arr[0];
        List<Object> left = new ArrayList<>();
        List<Object> right = new ArrayList<>();

        for (int i = 1; i < size; i++) {
            Object o = arr[i];
            if (o.hashCode() <= pivot.hashCode()) {
                left.add(o);
            } else {
                right.add(o);
            }
        }

        return Stream.of(
            sort(left.toArray(), left.size()),
            new Object[]{pivot},
            sort(right.toArray(), right.size())
        ).flatMap(Stream::of).toArray();
    }

    private boolean search(ElementsType item) {
        int min = 0;
        int max = size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.hashCode() == buffer[mid].hashCode()) {
                return true;
            }

            if (item.hashCode() < buffer[mid].hashCode()) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return false;
    }
}
