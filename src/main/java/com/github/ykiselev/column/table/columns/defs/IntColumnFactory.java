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

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class IntColumnFactory implements ColumnFactory<GrowingColumn<IntColumn>> {

    @Override
    public GrowingColumn<IntColumn> create() {
        return new GrowingIntColumn();
    }

    /**
     *
     */
    private final static class GrowingIntColumn implements GrowingColumn<IntColumn>, Serializable {

        private static final long serialVersionUID = -2237705701576761828L;

        private int[] data = {};

        @Override
        public IntColumn view() {
            return new IntColumn() {
                @Override
                public int getValue(int row) {
                    return GrowingIntColumn.this.data[row];
                }

                @Override
                public void setValue(int row, int value) {
                    GrowingIntColumn.this.data[row] = value;
                }

            };
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }

}