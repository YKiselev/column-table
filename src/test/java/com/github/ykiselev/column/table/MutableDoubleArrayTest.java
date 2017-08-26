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
public class MutableDoubleArrayTest {

    private final MutableDoubleArray array = new MutableDoubleArray();

    @Test
    public void shouldStore() throws Exception {
        array.capacity(3);
        assertEquals(0, array.get(0), 0.000001);
        assertEquals(0, array.get(1), 0.000001);
        assertEquals(0, array.get(2), 0.000001);

        array.set(0, 1.0);
        array.set(1, 2.0);
        array.set(2, 3.0);
        assertEquals(1.0, array.get(0), 0.000001);
        assertEquals(2.0, array.get(1), 0.000001);
        assertEquals(3.0, array.get(2), 0.000001);
    }

    @Test
    public void shouldSerialize() throws Exception {
        array.capacity(3);
        array.set(0, 1.0);
        array.set(1, 2.0);
        array.set(2, 3.0);
        assertArrayEquals(
                new double[]{1.0, 2.0, 3.0},
                array.toArray(3),
                0.00001
        );
    }

}