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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public class MutableBooleanArrayTest {

    @Test
    public void shouldStore() throws Exception {
        final MutableBooleanArray array = new MutableBooleanArray();

        assertFalse(array.get(10));
        array.set(10, true);
        assertTrue(array.get(10));

        assertFalse(array.get(100));
        array.set(100, true);
        assertTrue(array.get(100));
    }
}