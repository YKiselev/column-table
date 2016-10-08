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

import com.github.ykiselev.column.table.columns.DoubleColumn;
import com.github.ykiselev.column.table.columns.GrowingColumn;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class DoubleColumnFactory implements ColumnFactory<GrowingColumn<DoubleColumn>> {

    @Override
    public GrowingColumn<DoubleColumn> create() {
        return new GrowingDoubleColumn();
    }

    /**
     *
     */
    private final static class GrowingDoubleColumn implements GrowingColumn<DoubleColumn>, Serializable {

        private static final long serialVersionUID = 6894129003994939790L;

        private double[] data = {};

        @Override
        public DoubleColumn view() {
            return new DoubleColumn() {
                @Override
                public double getValue(int row) {
                    return GrowingDoubleColumn.this.data[row];
                }

                @Override
                public void setValue(int row, double value) {
                    GrowingDoubleColumn.this.data[row] = value;
                }

            };
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }
}
