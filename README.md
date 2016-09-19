## Synopsis

This is a library with classes defining in-memory column-oriented growing-only table to be used instead of long-living collections of POJOs (to reduce memory consumption). Has columns for all the primitive types, strings and also generic one (to store arrays for example). 

## Code Example

Define table
```java
    Table table = new TableBuilder(32)
                    .withColumn(new IntColumnDefinition("id"))
                    .withColumn(new BooleanColumnDefinition("flag"))
                    .withColumn(new ByteColumnDefinition("bytee"))
                    .withColumn(new CharColumnDefinition("character"))
                    .withColumn(new ShortColumnDefinition("shorty"))
                    .withColumn(new LongColumnDefinition("serial number"))
                    .withColumn(new FloatColumnDefinition("floaty"))
                    .withColumn(new DoubleColumnDefinition("double"))
                    .withColumn(new StringColumnDefinition("name"))
                    .withColumn(new ObjectColumnDefinition<>("password hash", byte[].class))
                    .build()
```

Here we define table with 10 columns of different types, 32 - is an initial table capacity (in rows), column names are not necessary and provided just for convenience.

And fill it...
```java
    int row = table.addRow();
    table.column(1, BooleanColumn.class).setValue(row, false);
```

Here we adding new row to the table and then setting cell value (row, 1) to false (1 - is a zero-based column index). Of course it would be more handy to create wrapper class for each table, similar to this:
```java
    private static final class SomeTable extends AbstractDelegatingTable {

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
        
// column getters here...
 
        SomeTable(Table table) {
            super(table);
            this.id = table.column(0, IntColumn.class);
            this.flag = table.column(1, BooleanColumn.class);
            this.bt = table.column(2, ByteColumn.class);
            this.ch = table.column(3, CharColumn.class);
            this.sh = table.column(4, ShortColumn.class);
            this.serial = table.column(5, LongColumn.class);
            this.flt = table.column(6, FloatColumn.class);
            this.dbl = table.column(7, DoubleColumn.class);
            this.s = table.column(8, StringColumn.class);
            this.bytes = table.column(9, ObjectColumn.class);
        }
    }
```

Then one can use `table.getFlag().setValue(row, true);` instead.


## Motivation

To decrease memory footprint in cases when one need big long-living collection of POJOs.

## Installation

For maven projects add dependency to pom.xml
```xml
<dependency>
    <groupId>com.github.ykiselev</groupId>
    <artifactId>column-table</artifactId>
    <version>x.xx</version>
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

