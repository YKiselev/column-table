## Synopsis

This is a library with classes defining in-memory column-oriented growing-only table to be used instead of long-living collections of POJOs (to reduce memory consumption). Has columns for all the primitive types, strings and also generic one (to store arrays for example). Row access is only by index (like in `array index` not `DBMS index`). No fancy stuff like string de-duplication because such functionality is not related to table internals and can be easily added when needed. No queries, no filters. 

## Code Example

Define table
```java
    Table table = new TableBuilder()
                    .withColumn(new IntColumnDefinition())
                    .withColumn(new BooleanColumnDefinition())
                    .withColumn(new ByteColumnDefinition())
                    .withColumn(new CharColumnDefinition())
                    .withColumn(new ShortColumnDefinition())
                    .withColumn(new LongColumnDefinition())
                    .withColumn(new FloatColumnDefinition())
                    .withColumn(new DoubleColumnDefinition())
                    .withColumn(new StringColumnDefinition())
                    .withColumn(new ObjectColumnDefinition<>(byte[].class))
                    .build()
```

Here we define table with 10 columns of different types.

And fill it...
```java
    int row = table.addRow();
    ((BooleanColumn)table.column(1)).setValue(row, false);
```

Here we adding new row to the table and then setting cell value (row, 1) to false (1 - is a zero-based column index). Of course it would be more handy to create wrapper class for each table, similar to this:
```java
    public class SomeGrowingTable extends AbstractDelegatingGrowingTable {

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

        public int id(int row) {
            return id.getValue(row);
        }

        public void id(int row, int value) {
            id.setValue(row, value);
        }

        public boolean flag(int row) {
            return flag.getValue(row);
        }

        public void flag(int row, boolean value) {
            flag.setValue(row, value);
        }

        public byte bt(int row) {
            return bt.getValue(row);
        }

        public void bt(int row, byte value) {
            bt.setValue(row, value);
        }

        public char ch(int row) {
            return ch.getValue(row);
        }

        public void ch(int row, char value) {
            ch.setValue(row, value);
        }

        public short sh(int row) {
            return sh.getValue(row);
        }

        public void sh(int row, short value) {
            sh.setValue(row, value);
        }

        public long serial(int row) {
            return serial.getValue(row);
        }

        public void serial(int row, long value) {
            serial.setValue(row, value);
        }

        public float flt(int row) {
            return flt.getValue(row);
        }

        public void flt(int row, float value) {
            flt.setValue(row, value);
        }

        public double dbl(int row) {
            return dbl.getValue(row);
        }

        public void dbl(int row, double value) {
            dbl.setValue(row, value);
        }

        public String s(int row) {
            return s.getValue(row);
        }

        public void s(int row, String value) {
            s.setValue(row, value);
        }

        public byte[] bytes(int row) {
            return bytes.getValue(row);
        }

        public void bytes(int row, byte[] value) {
            bytes.setValue(row, value);
        }

        @SuppressWarnings("unchecked")
        public SomeGrowingTable(GrowingTable growingTable) {
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
```

Then one can use `table.flag(row, true);` instead.


## Motivation

To decrease memory footprint in (rare) cases when one need big long-living collection of POJOs. 

## Installation

For maven projects add dependency to pom.xml
```xml
<dependency>
    <groupId>com.github.ykiselev</groupId>
    <artifactId>column-table</artifactId>
    <version>0.4</version>
</dependency>
```

## API Reference

Javadoc is in repo along with library.

## Tests

`mvn clean test` 
or run specific test:
* com.github.ykiselev.domain.view.SimpleTableTest


## Contributors

Please e-mail me if you need more info or want to improve something: uze@yandex.ru

## Downloads

Download [the lates jar][dl] or

## License

This library is licensed under the Apache License, Version 2.0.

[dl]: https://search.maven.org/remote_content?g=com.github.ykiselev&a=column-table&v=LATEST
[snap]: https://oss.sonatype.org/content/repositories/snapshots/com/github/ykiselev/column-table/

