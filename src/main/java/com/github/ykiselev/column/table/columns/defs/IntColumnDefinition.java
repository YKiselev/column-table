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

package com.github.ykiselev.column.table.columns.defs;

import com.github.ykiselev.column.table.columns.GrowingColumn;
import com.github.ykiselev.column.table.columns.IntColumn;

import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class IntColumnDefinition extends AbstractColumnDefinition {

    @Override
    public Class<?> type() {
        return int.class;
    }

    @Override
    public GrowingColumn createColumn() {
        return new GrowingIntColumn();
    }

    public IntColumnDefinition(String name) {
        super(name);
    }

    /**
     *
     */
    private final class GrowingIntColumn implements GrowingColumn, IntColumn {

        private int[] data = new int[0];

        @Override
        public AbstractColumnDefinition definition() {
            return IntColumnDefinition.this;
        }

        @Override
        public int getValue(int row) {
            return this.data[row];
        }

        @Override
        public void setValue(int row, int value) {
            this.data[row] = value;
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }
}
