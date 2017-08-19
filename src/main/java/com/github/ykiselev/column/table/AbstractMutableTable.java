/*
 * Copyright 2016 Yuriy Kiselev (uze@yandex.ru)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.ykiselev.column.table;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public abstract class AbstractMutableTable {

    public static final int MIN_CAPACITY = 8;

    private int capacity;

    private int rows;

    public final int capacity() {
        return capacity;
    }

    public final int rows() {
        return rows;
    }

    protected abstract Iterable<? extends MutableArray> columns();

    public final void capacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Should be greater than zero!");
        }
        for (MutableArray column : columns()) {
            column.capacity(capacity);
        }
        this.capacity = capacity;
        if (rows > capacity) {
            rows = capacity;
        }
    }

    /**
     * Adds new row to the table
     *
     * @return index of new row
     */
    public final int add() {
        return add(1);
    }

    /**
     * Adds {@code count} new rows
     *
     * @param count the number of rows to add
     * @return the index of fist new row
     */
    public final int add(int count) {
        int result = rows;
        rows += count;
        ensureCapacity(rows);
        return result;
    }

    private void ensureCapacity(int required) {
        if (required > capacity) {
            int newCapacity = capacity;
            if (newCapacity < MIN_CAPACITY) {
                newCapacity = MIN_CAPACITY;
            }
            while (required > newCapacity) {
                newCapacity *= 2;
            }
            capacity(newCapacity);
        }
    }
}
