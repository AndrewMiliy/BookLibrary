package repositories;

import models.UserModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ElasticArray<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object[] elements;
    private int size;

    public ElasticArray() {
        elements = new Object[10];
        size = 0;
    }

    // Add an element
    public void add(T element) {
        if (size == elements.length) {
            increaseSize();
        }
        elements[size++] = element;
    }

    // Get an element by index
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    // Remove an element by index
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T element = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;

        return element;
    }

    // Get the size of the array
    public int size() {
        return size;
    }

    // Private method to increase the size of the array when needed
    private void increaseSize() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }
    public T find(Predicate<T> predicate) {
        for (int i = 0; i < size; i++) {
            T element = (T) elements[i];
            if (predicate.test(element)) {
                return element;
            }
        }
        return null; // Если не найдено соответствующих элементов
    }
    public int findIndexOf(Predicate<T> predicate) {
        for (int i = 0; i < size; i++) {
            T element = (T) elements[i];
            if (predicate.test(element)) {
                return i;
            }
        }
        return -1; // Если не найдено соответствующих элементов
    }

    public List<T> findAll(Predicate<T> predicate) {
        List<T> foundElements = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            T element = (T) elements[i];
            if (predicate.test(element)) {
                foundElements.add(element);
            }
        }
        return foundElements;
    }

}

