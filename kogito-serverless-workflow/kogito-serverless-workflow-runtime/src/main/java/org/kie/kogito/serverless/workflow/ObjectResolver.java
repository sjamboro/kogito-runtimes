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
package org.kie.kogito.serverless.workflow;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.jackson.utils.JsonObjectUtils;

import com.fasterxml.jackson.databind.JsonNode;

public class ObjectResolver extends JsonNodeResolver {

    public ObjectResolver(String exprLang, Object expr, String paramName) {
        super(exprLang, expr, paramName);
    }

    @Override
    protected Object evalExpression(Object inputModel, KogitoProcessContext context) {
        return JsonObjectUtils.simpleToJavaValue((JsonNode) super.evalExpression(inputModel, context));
    }
}
