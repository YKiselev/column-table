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

import com.github.ykiselev.Bytes;
import com.github.ykiselev.column.table.immutable.ObjectArray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public class MutableObjectArrayTest {

    private final MutableObjectArray<String> array = new MutableObjectArray<>(String.class);

    @Test
    public void shouldStore() throws Exception {
        array.capacity(3);
        assertNull(array.get(0));
        assertNull(array.get(1));
        assertNull(array.get(2));

        array.set(0, "a");
        array.set(1, "b");
        array.set(2, "c");
        assertEquals("a", array.get(0));
        assertEquals("b", array.get(1));
        assertEquals("c", array.get(2));
    }

    @Test(expected = ArrayStoreException.class)
    @SuppressWarnings("unchecked")
    public void shouldEnforceType() throws Exception {
        final MutableObjectArray<String> array = new MutableObjectArray<>(String.class);
        array.capacity(1);
        ((MutableObjectArray) array).set(0, 123.0);
    }

    @Test
    public void shouldSerialize() throws Exception {
        array.capacity(2);
        array.set(0, "a");
        array.set(1, "b");
        assertEquals(
                new ObjectArray<>(
                        new String[]{
                                "a",
                                "b"
                        }
                ),
                Bytes.from(Bytes.to(array))
        );
    }

}