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

import java.io.*;

import static org.junit.Assert.*;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public class SimpleGrowingTableTest {

    private final SomeGrowingTable table = new SomeGrowingTable(
            new GrowingTableBuilder()
                    .withColumn(new IntColumnFactory())
                    .withColumn(new BooleanColumnFactory())
                    .withColumn(new ByteColumnFactory())
                    .withColumn(new CharColumnFactory())
                    .withColumn(new ShortColumnFactory())
                    .withColumn(new LongColumnFactory())
                    .withColumn(new FloatColumnFactory())
                    .withColumn(new DoubleColumnFactory())
                    .withColumn(new StringColumnFactory())
                    .withColumn(new ObjectColumnFactory<>(byte[].class))
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

        assertEquals(0, table.id(row));
        assertFalse(table.flag(row));
        Assert.assertEquals(0, table.bt(row));
        assertEquals(0, table.ch(row));
        Assert.assertEquals(0, table.sh(row));
        assertEquals(0, table.serial(row));
        assertEquals(0, table.flt(row), 0.001);
        assertEquals(0, table.dbl(row), 0.001);
        assertNull(table.s(row));
        assertNull(table.bytes(row));

        table.id(row, 333);
        table.flag(row, true);
        table.bt(row, (byte) 127);
        table.ch(row, 'Z');
        table.sh(row, Short.MAX_VALUE);
        table.serial(row, Long.MAX_VALUE);
        table.flt(row, Float.MAX_VALUE);
        table.dbl(row, Double.MAX_VALUE);
        table.s(row, "abcd");
        table.bytes(row, new byte[]{1, 2, 3});

        assertEquals(333, table.id(row));
        assertTrue(table.flag(row));
        Assert.assertEquals(127, table.bt(row));
        assertEquals('Z', table.ch(row));
        Assert.assertEquals(Short.MAX_VALUE, table.sh(row));
        assertEquals(Long.MAX_VALUE, table.serial(row));
        assertEquals(Float.MAX_VALUE, table.flt(row), 0.001);
        assertEquals(Double.MAX_VALUE, table.dbl(row), 0.001);
        Assert.assertEquals("abcd", table.s(row));
        Assert.assertArrayEquals(new byte[]{1, 2, 3}, table.bytes(row));
    }

    private byte[] toBytes(Object value) throws IOException {
        final ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bs)) {
            oos.writeObject(value);
        }
        return bs.toByteArray();
    }

    private Object fromBytes(byte[] raw) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(raw))) {
            return ois.readObject();
        }
    }

    @Test
    public void shouldSerialize() throws Exception {
        int row = this.table.addRow();
        this.table.id(row, 333);
        this.table.flag(row, true);
        this.table.bt(row, (byte) 127);
        this.table.ch(row, 'Z');
        this.table.sh(row, Short.MAX_VALUE);
        this.table.serial(row, Long.MAX_VALUE);
        this.table.flt(row, Float.MAX_VALUE);
        this.table.dbl(row, Double.MAX_VALUE);
        this.table.s(row, "abcd");
        this.table.bytes(row, new byte[]{1, 2, 3});
        final byte[] bytes = toBytes(this.table.table());
        final SomeGrowingTable table = new SomeGrowingTable((GrowingTable) fromBytes(bytes));
        assertEquals(1, table.rows());
        assertEquals(333, table.id(row));
        assertTrue(table.flag(row));
        Assert.assertEquals(127, table.bt(row));
        assertEquals('Z', table.ch(row));
        Assert.assertEquals(Short.MAX_VALUE, table.sh(row));
        assertEquals(Long.MAX_VALUE, table.serial(row));
        assertEquals(Float.MAX_VALUE, table.flt(row), 0.001);
        assertEquals(Double.MAX_VALUE, table.dbl(row), 0.001);
        Assert.assertEquals("abcd", table.s(row));
        Assert.assertArrayEquals(new byte[]{1, 2, 3}, table.bytes(row));
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

        int id(int row) {
            return id.getValue(row);
        }

        void id(int row, int value) {
            id.setValue(row, value);
        }

        boolean flag(int row) {
            return flag.getValue(row);
        }

        void flag(int row, boolean value) {
            flag.setValue(row, value);
        }

        byte bt(int row) {
            return bt.getValue(row);
        }

        void bt(int row, byte value) {
            bt.setValue(row, value);
        }

        char ch(int row) {
            return ch.getValue(row);
        }

        void ch(int row, char value) {
            ch.setValue(row, value);
        }

        short sh(int row) {
            return sh.getValue(row);
        }

        void sh(int row, short value) {
            sh.setValue(row, value);
        }

        long serial(int row) {
            return serial.getValue(row);
        }

        void serial(int row, long value) {
            serial.setValue(row, value);
        }

        float flt(int row) {
            return flt.getValue(row);
        }

        void flt(int row, float value) {
            flt.setValue(row, value);
        }

        double dbl(int row) {
            return dbl.getValue(row);
        }

        void dbl(int row, double value) {
            dbl.setValue(row, value);
        }

        String s(int row) {
            return s.getValue(row);
        }

        void s(int row, String value) {
            s.setValue(row, value);
        }

        byte[] bytes(int row) {
            return bytes.getValue(row);
        }

        void bytes(int row, byte[] value) {
            bytes.setValue(row, value);
        }

        @SuppressWarnings("unchecked")
        SomeGrowingTable(GrowingTable growingTable) {
            super(growingTable);
            this.id = (IntColumn) growingTable.column(0);
            this.flag = (BooleanColumn) growingTable.column(1);
            this.bt = (ByteColumn) growingTable.column(2);
            this.ch = (CharColumn) growingTable.column(3);
            this.sh = (ShortColumn) growingTable.column(4);
            this.serial = (LongColumn) growingTable.column(5);
            this.flt = (FloatColumn) growingTable.column(6);
            this.dbl = (DoubleColumn) growingTable.column(7);
            this.s = (StringColumn) growingTable.column(8);
            this.bytes = (ObjectColumn<byte[]>) growingTable.column(9);
        }
    }
}