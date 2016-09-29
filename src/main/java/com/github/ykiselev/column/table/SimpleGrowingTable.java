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

    private final GrowingColumn[] columns;

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
            value = refine(value);
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
    public <T extends Column> T column(int column, Class<T> columnClass) {
        return columnClass.cast(this.columns[column]);
    }

    private int refine(int capacity) {
        int result = capacity + (capacity >> 1);
        if (result < 0) {
            result = capacity;
        }
        if (result < DEFAULT_CAPACITY) {
            result = DEFAULT_CAPACITY;
        }
        return result;
    }

    @Override
    public int addRow() {
        capacity(this.rows + 1);
        return this.rows++;
    }
}
