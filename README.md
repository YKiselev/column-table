## Synopsis

This is a library with classes defining in-memory column-oriented growing-only table to be used instead of long-living collections of POJOs (to reduce memory consumption). Has columns for all the primitive types and generic one (to store strings for example). Row access is only by index (like in `array index` not `DBMS index`). No fancy stuff like string de-duplication because such functionality is not related to table internals and can be easily added when needed. No queries, no filters. 

## Code Example

Define table
```java
    class MyTable extends AbstractMutableTable {
    
        private final MutableLongArray id = new MutableLongArray();
    
        private final MutableObjectArray<String> name = new MutableObjectArray<>(String.class);
    
        @Override
        protected Iterable<? extends MutableArray> columns() {
            return Arrays.asList(id, name);
        }
    
    }
```

Here we define table with 2 columns of different types.

And fill it...
```java
    int row = table.add();
    table.id.set(row, 123L);
    table.name.set(row, "Zeus");
```

Here we adding new row to the table and then setting id cell value to 123L and name cell value to "Zeus". Our class is derived from com.github.ykiselev.column.table.AbstractMutableTable which implements useful methods like add(). Derived class should implement only one abstract method - com.github.ykiselev.column.table.AbstractMutableTable.columns so that base class will know all it's columns and be able to resize them when needed.

Next thing we can do with our table - serialize it. Each mutable array can be converted to 'normal' Java array by calling method toArray(int length)
```java
    private Object writeReplace() throws ObjectStreamException {
        return new MyTableReplacement(
                rows(),
                id,
                name
        );
    }
```

where
```java
final class MyTableReplacement implements Serializable {

    private static final long serialVersionUID = 4282998382380376807L;

    private long[] id;

    private String[] name;

    MyTableReplacement(int rows, MutableLongArray id, MutableObjectArray<String> name) {
        this.id = id.toArray(rows);
        this.name = name.toArray(rows);
    }

    Object readResolve() throws ObjectStreamException {
        return new MyImmutableTable(
                id,
                name
        );
    }
}
```
and this will deserialize into immutable table
```java
final class MyImmutableTable {

    private final long[] id;

    private final String[] name;

    long id(int index) {
        return id[index];
    }

    String name(int index) {
        return name[index];
    }

    MyImmutableTable(long[] id, String[] name) {
        this.id = id;
        this.name = name;
    }
}
```

## Motivation

To decrease memory footprint in (rare) cases when one need big long-living collection of POJOs. 

## Installation

For maven projects add dependency to pom.xml
```xml
<dependency>
    <groupId>com.github.ykiselev</groupId>
    <artifactId>column-table</artifactId>
    <version>0.6</version>
</dependency>
```

## API Reference

Javadoc is in repo along with library.

## Tests

`mvn clean test` 
or run specific test:
* com.github.ykiselev.column.table.AbstractMutableTableTest


## Contributors

Please e-mail me if you need more info or want to improve something: uze@yandex.ru

## Downloads

Download [the latest jar][dl] or

## License

This library is licensed under the Apache License, Version 2.0.

[dl]: https://search.maven.org/remote_content?g=com.github.ykiselev&a=column-table&v=LATEST
[snap]: https://oss.sonatype.org/content/repositories/snapshots/com/github/ykiselev/column-table/

