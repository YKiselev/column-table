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

package com.github.ykiselev.column.table;

import com.github.ykiselev.column.table.columns.Column;
import com.github.ykiselev.column.table.columns.defs.ColumnDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public abstract class AbstractTableBuilder<T extends Table, C> {

    protected final List<ColumnDefinition<? extends C>> definitions = new ArrayList<>();

    public final AbstractTableBuilder<T, C> withColumn(ColumnDefinition<? extends C> column) {
        this.definitions.add(column);
        return this;
    }

    public abstract T build();
}
