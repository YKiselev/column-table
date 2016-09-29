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
import com.github.ykiselev.column.table.columns.ShortColumn;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class ShortColumnDefinition implements ColumnDefinition<GrowingColumn>, Serializable {

    private static final long serialVersionUID = 1241473194932966005L;

    @Override
    public Class<?> type() {
        return short.class;
    }

    @Override
    public GrowingColumn createColumn() {
        return new GrowingShortColumn();
    }

    /**
     *
     */
    private final class GrowingShortColumn implements GrowingColumn<ShortColumn>, Serializable {

        private static final long serialVersionUID = -1167094801444725782L;

        private short[] data = {};

        @Override
        public ShortColumn view() {
            return new ShortColumn() {
                @Override
                public short getValue(int row) {
                    return GrowingShortColumn.this.data[row];
                }

                @Override
                public void setValue(int row, short value) {
                    GrowingShortColumn.this.data[row] = value;
                }

                @Override
                public ColumnDefinition definition() {
                    return ShortColumnDefinition.this;
                }
            };
        }

        @Override
        public ColumnDefinition definition() {
            return ShortColumnDefinition.this;
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }
}
