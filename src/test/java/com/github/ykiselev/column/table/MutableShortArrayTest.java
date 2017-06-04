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

import static org.junit.Assert.assertEquals;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public class MutableShortArrayTest {

    @Test
    public void shouldStore() throws Exception {
        final MutableShortArray array = new MutableShortArray();

        array.capacity(3);

        assertEquals(0, array.get(0));
        assertEquals(0, array.get(1));
        assertEquals(0, array.get(2));

        array.set(0, (short) 1);
        array.set(1, (short) 2);
        array.set(2, (short) 3);

        assertEquals(1, array.get(0));
        assertEquals(2, array.get(1));
        assertEquals(3, array.get(2));
    }

}