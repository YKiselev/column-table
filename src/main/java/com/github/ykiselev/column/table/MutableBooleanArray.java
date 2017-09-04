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

import com.github.ykiselev.column.table.immutable.BooleanArray;

import java.util.BitSet;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public final class MutableBooleanArray extends MutableArray {

    private final BitSet bits = new BitSet();

    @Override
    void capacity(int capacity) {
        // no-op
    }

    public boolean get(int index) {
        return bits.get(index);
    }

    public void set(int index, boolean value) {
        bits.set(index, value);
    }

    public long[] toArray() {
        return bits.toLongArray();
    }

    public BooleanArray toBooleanArray() {
        return new BooleanArray(toArray());
    }
}
