/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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
package com.myspace.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kie.kogito.eventdriven.rules.AbstractEventDrivenQueryExecutor;
import org.kie.kogito.rules.RuleUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class $QueryType$EventDrivenExecutor extends AbstractEventDrivenQueryExecutor<$DataType$, $ReturnType$> {

    @Autowired
    public $QueryType$EventDrivenExecutor(RuleUnit<$DataType$> ruleUnit, ObjectMapper objectMapper) {
        super(ruleUnit, "$name$", $QueryType$.class, $DataType$.class, objectMapper);
    }
}
