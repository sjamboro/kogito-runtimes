/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
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
package org.kie.kogito.codegen.rules;

import org.kie.kogito.codegen.api.GeneratedFile;
import org.kie.kogito.codegen.api.Generator;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

public class RuleObjectMapperCodegen {

    private final KogitoBuildContext context;

    public RuleObjectMapperCodegen(KogitoBuildContext context) {
        this.context = context;
    }

    GeneratedFile generate() {
        TemplatedGenerator generator = TemplatedGenerator.builder()
                .withTemplateBasePath(RuleCodegen.TEMPLATE_RULE_FOLDER)
                .build(context, "KogitoObjectMapper");

        return new GeneratedFile(Generator.REST_TYPE,
                generator.generatedFilePath(),
                generator.compilationUnitOrThrow().toString());
    }
}
