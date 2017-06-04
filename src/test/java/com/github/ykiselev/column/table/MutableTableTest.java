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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public class MutableTableTest {

    private final MutableTable table = new MutableTable(
            new MutableIntArray(),
            new MutableDoubleArray()
    );

    @Before
    public void setUp() throws Exception {
        table.capacity(3);
    }

    @Test
    public void shouldResizeAllColumns() throws Exception {
        table.column(0, MutableIntArray.class).set(2, 1);
        table.column(1, MutableDoubleArray.class).set(2, 3.0);
    }

    @Test
    public void shouldAddRow() throws Exception {
        assertEquals(0, table.add());
        assertEquals(1, table.add());
        assertEquals(2, table.add());
        assertEquals(3, table.add());
        assertTrue(table.capacity() > 3);
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