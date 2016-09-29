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
import com.github.ykiselev.column.table.columns.defs.ColumnDefinition;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public final class GrowingTableBuilder extends AbstractTableBuilder<GrowingTable, GrowingColumn> {

    @Override
    public GrowingTable build() {
        final GrowingColumn[] columns = new GrowingColumn[this.definitions.size()];
        int i = 0;
        for (ColumnDefinition<? extends GrowingColumn> definition : this.definitions) {
            columns[i] = definition.createColumn();
            i++;
        }
        return new SimpleGrowingTable(columns);
    }

}
