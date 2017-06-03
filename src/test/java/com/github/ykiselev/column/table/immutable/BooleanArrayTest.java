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

import com.github.ykiselev.Bytes;
import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.*;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public class BooleanArrayTest {

    @Test
    public void shouldConstructFromArrayOfLongs() throws Exception {
        final BooleanArray array = new BooleanArray(new long[]{1L, 1L << (100 - 64)});
        assertTrue(array.get(0));
        assertFalse(array.get(1));
        assertFalse(array.get(99));
        assertTrue(array.get(100));
        assertFalse(array.get(101));
    }

    @Test
    public void shouldConstructFromBitSet() throws Exception {
        final BitSet bitSet = new BitSet();
        bitSet.set(10);
        bitSet.set(75);
        bitSet.set(1000);
        final BooleanArray array = new BooleanArray(bitSet);
        assertFalse(array.get(0));
        assertFalse(array.get(9));
        assertTrue(array.get(10));
        assertFalse(array.get(11));
        assertFalse(array.get(74));
        assertTrue(array.get(75));
        assertFalse(array.get(76));
        assertFalse(array.get(999));
        assertTrue(array.get(1000));
        assertFalse(array.get(1001));
    }

    @Test
    public void shouldSerialize() throws Exception {
        final BooleanArray array = new BooleanArray(
                new long[]{
                        0XAAAAAAAA_AAAAAAAAL,
                        0XAAAAAAAA_AAAAAAAAL
                }
        );
        assertEquals(array, Bytes.from(Bytes.to(array)));
    }
}