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

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class LongColumnFactory implements ColumnFactory<GrowingColumn<LongColumn>> {

    @Override
    public GrowingColumn<LongColumn> create() {
        return new GrowingLongColumn();
    }

    /**
     *
     */
    private final static class GrowingLongColumn implements GrowingColumn<LongColumn>, Serializable {

        private static final long serialVersionUID = -8248841743027153836L;

        private long[] data = {};

        @Override
        public LongColumn view() {
            return new LongColumn() {
                @Override
                public long getValue(int row) {
                    return GrowingLongColumn.this.data[row];
                }

                @Override
                public void setValue(int row, long value) {
                    GrowingLongColumn.this.data[row] = value;
                }

            };
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }
}