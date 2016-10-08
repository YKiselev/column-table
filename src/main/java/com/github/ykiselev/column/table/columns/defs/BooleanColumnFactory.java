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

import com.github.ykiselev.column.table.columns.BooleanColumn;
import com.github.ykiselev.column.table.columns.Column;
import com.github.ykiselev.column.table.columns.GrowingColumn;

import java.io.Serializable;
import java.util.BitSet;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class BooleanColumnFactory implements ColumnFactory<GrowingColumn> {

    @Override
    public GrowingColumn create() {
        return new GrowingBooleanColumn();
    }

    /**
     *
     */
    private final static class GrowingBooleanColumn implements GrowingColumn, Serializable {

        private static final long serialVersionUID = -992926317534435923L;

        private final BitSet bits = new BitSet();

        @Override
        public Column view() {
            return new BooleanColumn() {
                @Override
                public boolean getValue(int row) {
                    return GrowingBooleanColumn.this.bits.get(row);
                }

                @Override
                public void setValue(int row, boolean value) {
                    GrowingBooleanColumn.this.bits.set(row, value);
                }

            };
        }

        @Override
        public void grow(int capacity) {
            // no-op
        }
    }
}
