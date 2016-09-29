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

import com.github.ykiselev.column.table.columns.ByteColumn;
import com.github.ykiselev.column.table.columns.GrowingColumn;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class ByteColumnDefinition implements ColumnDefinition<GrowingColumn<ByteColumn>>, Serializable {

    private static final long serialVersionUID = -6606506721929316548L;

    @Override
    public Class<?> type() {
        return byte.class;
    }

    @Override
    public GrowingColumn<ByteColumn> createColumn() {
        return new GrowingByteColumn();
    }

    /**
     *
     */
    private final class GrowingByteColumn implements GrowingColumn<ByteColumn>, Serializable {

        private static final long serialVersionUID = -6462691810065903496L;

        private byte[] data = {};

        @Override
        public ByteColumn view() {
            return new ByteColumn() {
                @Override
                public byte getValue(int row) {
                    return GrowingByteColumn.this.data[row];
                }

                @Override
                public void setValue(int row, byte value) {
                    GrowingByteColumn.this.data[row] = value;
                }

                @Override
                public ColumnDefinition definition() {
                    return ByteColumnDefinition.this;
                }
            };
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }
}
