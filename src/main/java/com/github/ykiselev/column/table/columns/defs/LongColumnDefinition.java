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
import com.github.ykiselev.column.table.columns.LongColumn;

import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class LongColumnDefinition extends AbstractColumnDefinition {

    @Override
    public Class<?> type() {
        return long.class;
    }

    @Override
    public GrowingColumn createGrowingColumn() {
        return new GrowingLongColumn();
    }

    public LongColumnDefinition(String name) {
        super(name);
    }

    /**
     *
     */
    private final class GrowingLongColumn implements GrowingColumn, LongColumn {

        private long[] data = new long[0];

        @Override
        public AbstractColumnDefinition definition() {
            return LongColumnDefinition.this;
        }

        @Override
        public long getValue(int row) {
            return this.data[row];
        }

        @Override
        public void setValue(int row, long value) {
            this.data[row] = value;
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }
}
