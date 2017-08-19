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

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public class AbstractMutableTableTest {

    private final MyTable table = new MyTable();

    @Test
    public void shouldAddOneRow() throws Exception {
        assertEquals(0, table.rows());
        assertEquals(0, table.add());
        assertEquals(1, table.add());
        assertEquals(2, table.rows());

        table.id().set(1, 100);
        assertEquals(100, table.id().get(1));

        table.name().set(1, "a");
        assertEquals("a", table.name().get(1));
    }

    @Test
    public void shouldAddManyRows() throws Exception {
        assertEquals(0, table.rows());
        assertEquals(0, table.add(10));
        assertEquals(10, table.add(10));
        assertEquals(20, table.rows());

        table.id().set(19, 5);
        assertEquals(5, table.id().get(19));

        table.name().set(19, "a");
        assertEquals("a", table.name().get(19));
    }

    @Test
    public void shouldTrimRows() throws Exception {
        assertEquals(0, table.add());
        assertEquals(1, table.add());
        assertEquals(2, table.add());
        assertEquals(3, table.rows());
        table.capacity(1);
        assertEquals(1, table.rows());
    }
}

final class MyTable extends AbstractMutableTable {

    private final MutableLongArray id = new MutableLongArray();

    private final MutableObjectArray<String> name = new MutableObjectArray<>(String.class);

    MutableLongArray id() {
        return id;
    }

    MutableObjectArray<String> name() {
        return name;
    }

    @Override
    protected Iterable<? extends MutableArray> columns() {
        return Arrays.asList(id, name);
    }
}