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

import static org.junit.Assert.assertEquals;

/**
 * @author Yuriy Kiselev (uze@yandex.ru).
 */
public class FloatArrayTest {

    @Test
    public void shouldConstructFromArrayOfLongs() throws Exception {
        assertEquals(
                123f,
                new FloatArray(
                        new float[]{123f}
                ).get(0),
                0.0000001f
        );
    }

    @Test
    public void shouldSerialize() throws Exception {
        final FloatArray array = new FloatArray(
                new float[]{1f, 2f, 3f}
        );
        assertEquals(array, Bytes.from(Bytes.to(array)));
    }

}