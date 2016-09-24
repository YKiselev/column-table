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

import com.github.ykiselev.column.table.columns.GrowingColumn;
import com.github.ykiselev.column.table.columns.defs.AbstractColumnDefinition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class TableBuilder {

    private final Set<String> names = new HashSet<>();

    private final List<AbstractColumnDefinition> definitions = new ArrayList<>();

    private final int pageSize;

    public TableBuilder(int pageSize) {
        this.pageSize = pageSize;
    }

    public TableBuilder withColumn(AbstractColumnDefinition column) {
        if (!this.names.add(column.name())) {
            throw new IllegalArgumentException("Name already in use: " + column.name());
        }
        this.definitions.add(column);
        return this;
    }

    public GrowingTable build() {
        final GrowingColumn[] columns = new GrowingColumn[this.definitions.size()];
        int i = 0;
        for (AbstractColumnDefinition definition : this.definitions) {
            columns[i] = definition.createGrowingColumn();
            i++;
        }
        return new SimpleGrowingTable(pageSize, columns);
    }

}
