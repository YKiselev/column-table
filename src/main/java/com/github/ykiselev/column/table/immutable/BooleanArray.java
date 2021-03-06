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

package com.github.ykiselev.column.table.immutable;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;

/**
 * Boolean array backed by array of longs.
 *
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public final class BooleanArray implements Serializable {

    private final long[] array;

    /**
     * Primary ctor.
     *
     * @param array  the source array to copy data from
     * @param start  the index in source array to start copy from
     * @param length the length of range to copy
     */
    public BooleanArray(long[] array, int start, int length) {
        this.array = Arrays.copyOfRange(array, start, length);
    }

    public BooleanArray(long[] array) {
        this(array, 0, array.length);
    }

    public BooleanArray(BitSet bits) {
        this(bits.toLongArray());
    }

    public boolean get(int index) {
        return (array[index / 64] & (1L << (index % 64))) != 0;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new Replacement(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanArray that = (BooleanArray) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    /**
     * Used to replace outer class during serialization
     */
    public static final class Replacement implements Serializable {

        private static final long serialVersionUID = 1075622256604844029L;

        private long[] array;

        public Replacement(long[] array) {
            this.array = array;
        }

        Object readResolve() throws ObjectStreamException {
            return new BooleanArray(array);
        }
    }
}
