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

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public abstract class AbstractDelegatingGrowingTable implements GrowingTable {

    private final GrowingTable table;

    public GrowingTable table() {
        return table;
    }

    protected AbstractDelegatingGrowingTable(GrowingTable table) {
        this.table = table;
    }

    @Override
    public int columns() {
        return this.table.columns();
    }

    @Override
    public int capacity() {
        return this.table.capacity();
    }

    @Override
    public void capacity(int value) {
        this.table.capacity(value);
    }

    @Override
    public int rows() {
        return this.table.rows();
    }

    @Override
    public Column column(int column) {
        return this.table.column(column);
    }

    @Override
    public int addRow() {
        return this.table.addRow();
    }
}
