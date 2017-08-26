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

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public class MutableLongArrayTest {

    private final MutableLongArray array = new MutableLongArray();

    @Test
    public void shouldStore() throws Exception {
        array.capacity(3);
        assertEquals(0, array.get(0));
        assertEquals(0, array.get(1));
        assertEquals(0, array.get(2));

        array.set(0, 1L);
        array.set(1, 2L);
        array.set(2, 3L);
        assertEquals(1L, array.get(0));
        assertEquals(2L, array.get(1));
        assertEquals(3L, array.get(2));
    }

    @Test
    public void shouldSerialize() throws Exception {
        array.capacity(3);
        array.set(0, 1L);
        array.set(1, 2L);
        array.set(2, 3L);
        assertArrayEquals(
                new long[]{1L, 2L, 3L},
                array.toArray(3)
        );
    }

}