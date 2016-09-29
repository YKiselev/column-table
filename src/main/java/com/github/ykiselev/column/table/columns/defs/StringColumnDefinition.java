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
import com.github.ykiselev.column.table.columns.StringColumn;

import java.io.Serializable;
import java.util.Arrays;


/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class StringColumnDefinition implements ColumnDefinition<GrowingColumn<StringColumn>>, Serializable {

    private static final long serialVersionUID = -8426448635311377818L;

    @Override
    public Class<?> type() {
        return String.class;
    }

    @Override
    public GrowingColumn<StringColumn> createColumn() {
        return new GrowingStringColumn();
    }

    /**
     *
     */
    private final class GrowingStringColumn implements GrowingColumn<StringColumn>, Serializable {

        private static final long serialVersionUID = -7744077351338636853L;

        private String[] data = {};

        @Override
        public StringColumn view() {
            return new StringColumn() {
                @Override
                public String getValue(int row) {
                    return GrowingStringColumn.this.data[row];
                }

                @Override
                public void setValue(int row, String value) {
                    GrowingStringColumn.this.data[row] = value;
                }

                @Override
                public ColumnDefinition definition() {
                    return StringColumnDefinition.this;
                }
            };
        }

        @Override
        public void grow(int capacity) {
            this.data = Arrays.copyOf(this.data, capacity);
        }
    }
}
