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

import com.github.ykiselev.column.table.columns.Column;
import com.github.ykiselev.column.table.columns.GrowingColumn;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
final class SimpleGrowingTable implements GrowingTable, Serializable {

    private static final long serialVersionUID = 4768696390401496922L;

    private static final int DEFAULT_CAPACITY = 10;

    /**
     * @see java.util.ArrayList#MAX_ARRAY_SIZE
     */
    private static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    private final GrowingColumn[] columns;

    private transient Column[] views;

    private int capacity;

    private int rows;

    /**
     * @param columns the table columns
     */
    SimpleGrowingTable(GrowingColumn... columns) {
        this.columns = Arrays.copyOf(columns, columns.length);
    }

    @Override
    public int columns() {
        return this.columns.length;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public void capacity(int value) {
        if (this.capacity < value) {
            value = refine(value, this.capacity);
            for (GrowingColumn column : this.columns) {
                column.grow(value);
            }
            // only if all columns were resized successfully
            this.capacity = value;
        }
    }

    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public Column column(int column) {
        if (this.views == null) {
            this.views = new Column[this.columns.length];
        }
        Column result = this.views[column];
        if (result == null) {
            result = this.columns[column].view();
            this.views[column] = result;
        }
        return result;
    }

    private int refine(int capacity, int oldCapacity) {
        final int minCapacity = Math.max(capacity, DEFAULT_CAPACITY);
        int result = oldCapacity + (oldCapacity >> 1);
        if (result - minCapacity < 0) {
            result = minCapacity;
        }
        if (result - MAX_ARRAY_LENGTH > 0) {
            result = MAX_ARRAY_LENGTH;
        }
        return result;
    }

    @Override
    public int addRow() {
        capacity(this.rows + 1);
        return this.rows++;
    }
}
