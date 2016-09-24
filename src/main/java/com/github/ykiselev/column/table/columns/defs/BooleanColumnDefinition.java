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
import com.github.ykiselev.column.table.columns.GrowingColumn;

import java.util.BitSet;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class BooleanColumnDefinition extends AbstractColumnDefinition {

    public BooleanColumnDefinition(String name) {
        super(name);
    }

    @Override
    public Class<?> type() {
        return boolean.class;
    }

    @Override
    public GrowingColumn createGrowingColumn() {
        return new GrowingBooleanColumn();
    }

    /**
     *
     */
    private final class GrowingBooleanColumn implements GrowingColumn, BooleanColumn {

        private final BitSet bits = new BitSet();

        @Override
        public AbstractColumnDefinition definition() {
            return BooleanColumnDefinition.this;
        }

        @Override
        public boolean getValue(int row) {
            return this.bits.get(row);
        }

        @Override
        public void setValue(int row, boolean value) {
            this.bits.set(row, value);
        }

        @Override
        public void grow(int capacity) {
            // no-op
        }
    }
}
