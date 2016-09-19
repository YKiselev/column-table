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

import com.github.ykiselev.column.table.columns.GrowingColumn;
import com.github.ykiselev.column.table.columns.Column;

import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
final class SimpleTable implements Table {

    private final GrowingColumn[] columns;

    private int capacity;

    private int rows;

    private final int pageSize;

    SimpleTable(int pageSize, GrowingColumn... columns) {
        this.pageSize = pageSize;
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
        return (int) (this.pageSize * Math.ceil((double) capacity / this.pageSize));
    }

    @Override
    public int addRow() {
        capacity(this.rows + 1);
        return this.rows++;
    }
}
