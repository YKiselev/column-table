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
import com.github.ykiselev.domain.view.columns.ObjectColumn;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class ObjectColumnDefinition<T> extends AbstractColumnDefinition {

    private final Class<T> clazz;

    public ObjectColumnDefinition(String name, Class<T> clazz) {
        super(name);
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

    private final class GrowingObjectColumn implements GrowingColumn, ObjectColumn<T> {

        private T[] data = (T[]) Array.newInstance(clazz, 0);

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }

        @Override
        public T getValue(int row) {
            return this.data[row];
        }

        @Override
        public void setValue(int row, T value) {
            this.data[row] = value;
        }

        @Override
        public AbstractColumnDefinition definition() {
            return ObjectColumnDefinition.this;
        }
    }
}
