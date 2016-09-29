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

import com.github.ykiselev.column.table.columns.Column;
import com.github.ykiselev.column.table.columns.GrowingColumn;
import com.github.ykiselev.column.table.columns.ObjectColumn;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * Instances of column class, created by this definition are serializable if underlying objects are serializable.
 *
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class ObjectColumnDefinition<T> implements ColumnDefinition<GrowingColumn>, Serializable {

    private static final long serialVersionUID = 2772677974218940720L;

    private final Class<T> clazz;

    public ObjectColumnDefinition(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Class<?> type() {
        return this.clazz;
    }

    @Override
    public GrowingColumn createColumn() {
        return new GrowingObjectColumn();
    }

    /**
     *
     */
    private final class GrowingObjectColumn implements GrowingColumn, Serializable {

        private static final long serialVersionUID = 8754760104479856833L;

        @SuppressWarnings("unchecked")
        private T[] data = (T[]) Array.newInstance(ObjectColumnDefinition.this.clazz, 0);

        @Override
        public Column view() {
            return new ObjectColumn<T>() {
                @Override
                public T getValue(int row) {
                    return GrowingObjectColumn.this.data[row];
                }

                @Override
                public void setValue(int row, T value) {
                    GrowingObjectColumn.this.data[row] = value;
                }

                @Override
                public ColumnDefinition definition() {
                    return ObjectColumnDefinition.this;
                }
            };
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }
}
