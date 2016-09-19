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

package com.github.ykiselev.domain.view.columns.defs;

import com.github.ykiselev.domain.view.columns.GrowingColumn;

/**
 * @author Yuriy Kiselev uze@yandex.ru.
 */
public abstract class AbstractColumnDefinition {

    private final String name;

    public abstract Class<?> type();

    public String name() {
        return name;
    }

    public abstract GrowingColumn createColumn();

    protected AbstractColumnDefinition(String name) {
        this.name = name;
    }
}
