/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.compiler.canonical.descriptors;

import java.util.function.Supplier;

import org.kie.kogito.process.workitems.impl.WorkItemParamResolver;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

public class WorkItemParamResolverSupplier implements Supplier<Expression> {

    private Class<? extends WorkItemParamResolver> clazz;
    private Supplier<Expression>[] args;

    public WorkItemParamResolverSupplier(Class<? extends WorkItemParamResolver> clazz, Supplier<Expression>... args) {
        this.clazz = clazz;
        this.args = args;
    }

    @Override
    public Expression get() {
        ObjectCreationExpr result = new ObjectCreationExpr().setType(clazz.getCanonicalName());
        for (Supplier<Expression> arg : args) {
            result.addArgument(arg.get());
        }
        return result;
    }
}
