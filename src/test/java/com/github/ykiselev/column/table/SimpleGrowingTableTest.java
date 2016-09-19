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

import com.github.ykiselev.column.table.columns.*;
import com.github.ykiselev.column.table.columns.defs.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public class SimpleGrowingTableTest {

    private final SomeGrowingTable table = new SomeGrowingTable(
            new TableBuilder(32)
                    .withColumn(new IntColumnDefinition("id"))
                    .withColumn(new BooleanColumnDefinition("flag"))
                    .withColumn(new ByteColumnDefinition("bt"))
                    .withColumn(new CharColumnDefinition("ch"))
                    .withColumn(new ShortColumnDefinition("sh"))
                    .withColumn(new LongColumnDefinition("serial"))
                    .withColumn(new FloatColumnDefinition("flt"))
                    .withColumn(new DoubleColumnDefinition("dbl"))
                    .withColumn(new StringColumnDefinition("s"))
                    .withColumn(new ObjectColumnDefinition<>("bytes", byte[].class))
                    .build()
    );


    @Test
    public void shouldHaveZeroRows() throws Exception {
        Assert.assertEquals(0, table.rows());
    }

    @Test
    public void shouldAddRow() throws Exception {
        Assert.assertEquals(0, table.addRow());
        Assert.assertEquals(1, table.rows());

        Assert.assertEquals(1, table.addRow());
        Assert.assertEquals(2, table.rows());

        Assert.assertEquals(2, table.addRow());
        Assert.assertEquals(3, table.rows());
    }

    @Test
    public void shouldSetValue() throws Exception {
        table.addRow();
        final int row = table.addRow();

        assertEquals(0, table.getId(row));
        assertFalse(table.flag.getValue(row));
        Assert.assertEquals(0, table.bt.getValue(row));
        assertEquals(0, table.ch.getValue(row));
        Assert.assertEquals(0, table.sh.getValue(row));
        assertEquals(0, table.serial.getValue(row));
        assertEquals(0, table.flt.getValue(row), 0.001);
        assertEquals(0, table.dbl.getValue(row), 0.001);
        assertNull(table.s.getValue(row));
        assertNull(table.bytes.getValue(row));

        table.setId(row, 333);
        table.flag.setValue(row, true);
        table.bt.setValue(row, (byte) 127);
        table.ch.setValue(row, 'Z');
        table.sh.setValue(row, Short.MAX_VALUE);
        table.serial.setValue(row, Long.MAX_VALUE);
        table.flt.setValue(row, Float.MAX_VALUE);
        table.dbl.setValue(row, Double.MAX_VALUE);
        table.s.setValue(row, "abcd");
        table.bytes.setValue(row, new byte[]{1, 2, 3});

        assertEquals(333, table.getId(row));
        assertTrue(table.flag.getValue(row));
        Assert.assertEquals(127, table.bt.getValue(row));
        assertEquals('Z', table.ch.getValue(row));
        Assert.assertEquals(Short.MAX_VALUE, table.sh.getValue(row));
        assertEquals(Long.MAX_VALUE, table.serial.getValue(row));
        assertEquals(Float.MAX_VALUE, table.flt.getValue(row), 0.001);
        assertEquals(Double.MAX_VALUE, table.dbl.getValue(row), 0.001);
        Assert.assertEquals("abcd", table.s.getValue(row));
        Assert.assertArrayEquals(new byte[]{1, 2, 3}, table.bytes.getValue(row));
    }

    private static final class SomeGrowingTable extends AbstractDelegatingGrowingTable {

        private final IntColumn id;

        private final BooleanColumn flag;

        private final ByteColumn bt;

        private final CharColumn ch;

        private final ShortColumn sh;

        private final LongColumn serial;

        private final FloatColumn flt;

        private final DoubleColumn dbl;

        private final StringColumn s;

        private final ObjectColumn<byte[]> bytes;

        int getId(int row) {
            return this.id.getValue(row);
        }

        void setId(int row, int value) {
            this.id.setValue(row, value);
        }

        SomeGrowingTable(GrowingTable growingTable) {
            super(growingTable);
            this.id = growingTable.column(0, IntColumn.class);
            this.flag = growingTable.column(1, BooleanColumn.class);
            this.bt = growingTable.column(2, ByteColumn.class);
            this.ch = growingTable.column(3, CharColumn.class);
            this.sh = growingTable.column(4, ShortColumn.class);
            this.serial = growingTable.column(5, LongColumn.class);
            this.flt = growingTable.column(6, FloatColumn.class);
            this.dbl = growingTable.column(7, DoubleColumn.class);
            this.s = growingTable.column(8, StringColumn.class);
            this.bytes = growingTable.column(9, ObjectColumn.class);
        }
    }
}