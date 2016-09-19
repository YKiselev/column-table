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

package com.github.ykiselev.domain.view.columns.defs;

import com.github.ykiselev.domain.view.columns.GrowingColumn;
import com.github.ykiselev.domain.view.columns.ShortColumn;

import java.util.Arrays;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class ShortColumnDefinition extends AbstractColumnDefinition {

    @Override
    public Class<?> type() {
        return short.class;
    }

    @Override
    public GrowingColumn createColumn() {
        return new GrowingShortColumn();
    }

    public ShortColumnDefinition(String name) {
        super(name);
    }

    /**
     *
     */
    private final class GrowingShortColumn implements GrowingColumn, ShortColumn {

        private short[] data = new short[0];

        @Override
        public AbstractColumnDefinition definition() {
            return ShortColumnDefinition.this;
        }

        @Override
        public short getValue(int row) {
            return this.data[row];
        }

        @Override
        public void setValue(int row, short value) {
            this.data[row] = value;
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }
}
