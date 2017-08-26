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

import com.github.ykiselev.Bytes;
import com.github.ykiselev.column.table.immutable.LongArray;
import com.github.ykiselev.column.table.immutable.ObjectArray;
import org.junit.Test;

import java.io.ObjectStreamException;
import java.io.Serializable;
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

    @Test
    public void shouldSerialize() throws Exception {
        table.add();
        table.id().set(0, 123);
        table.name().set(0, "abc");
        final MyImmutableTable result = (MyImmutableTable) Bytes.from(Bytes.to(table));
        assertEquals(123, result.id().get(0));
        assertEquals("abc", result.name().get(0));
    }
}

final class MyTable extends AbstractMutableTable implements Serializable {

    private final MutableLongArray id;

    private final MutableObjectArray<String> name;

    MutableLongArray id() {
        return id;
    }

    MutableObjectArray<String> name() {
        return name;
    }

    MyTable() {
        this(new MutableLongArray(), new MutableObjectArray<>(String.class));
    }

    MyTable(MutableLongArray id, MutableObjectArray<String> name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected Iterable<? extends MutableArray> columns() {
        return Arrays.asList(id, name);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new MyTableReplacement(
                rows(),
                id,
                name
        );
    }

}

final class MyImmutableTable {

    private final int rows;

    private final LongArray id;

    private final ObjectArray<String> name;

    LongArray id() {
        return id;
    }

    ObjectArray<String> name() {
        return name;
    }

    MyImmutableTable(int rows, LongArray id, ObjectArray<String> name) {
        this.rows = rows;
        this.id = id;
        this.name = name;
    }
}

final class MyTableReplacement implements Serializable {

    private static final long serialVersionUID = 4282998382380376807L;

    private int rows;

    private long[] id;

    private String[] name;

    MyTableReplacement(int rows, MutableLongArray id, MutableObjectArray<String> name) {
        this.rows = rows;
        this.id = id.toArray(rows);
        this.name = name.toArray(rows);
    }

    Object readResolve() throws ObjectStreamException {
        return new MyImmutableTable(
                rows,
                new LongArray(id),
                new ObjectArray<>(name)
        );
    }
}
