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

import java.util.Arrays;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public final class MutableTable {

    private final MutableArray[] columns;

    public MutableTable(MutableArray... columns) {
        this.columns = Arrays.copyOf(columns, columns.length);
    }

    public void capacity(int capacity) {
        for (MutableArray column : columns) {
            column.capacity(capacity);
        }
    }

    public <T> T column(int index, Class<T> clazz) {
        return clazz.cast(columns[index]);
    }

}
