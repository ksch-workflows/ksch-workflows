/*
 * Copyright 2019 KS-plus e.V.
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

package ksch.wicket;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class MockBean<T> {

    @NonNull
    private final Class<T> classToBeMocked;

    public Class<T> getBeanClass() {
        return classToBeMocked;
    }

    public Supplier<T> getSupplier() {
        return () -> Mockito.mock(classToBeMocked);
    }

    public static <T> MockBean of(Class<T> classToBeMocked) {
        return new MockBean<>(classToBeMocked);
    }
}
