/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
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
package org.jbpm.process;

import org.drools.core.common.InternalWorkingMemory;
import org.drools.kiesession.session.ProcessRuntimeFactory;
import org.jbpm.process.instance.InternalProcessRuntime;
import org.jbpm.process.instance.ProcessRuntimeFactoryServiceImpl;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.jbpm.test.util.AbstractBaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.kie.kogito.jobs.DurationExpirationTime;
import org.kie.kogito.jobs.ExactExpirationTime;
import org.kie.kogito.jobs.JobsService;
import org.kie.kogito.jobs.ProcessInstanceJobDescription;
import org.kie.kogito.services.uow.CollectingUnitOfWorkFactory;
import org.kie.kogito.services.uow.DefaultUnitOfWorkManager;
import org.kie.kogito.timer.TimerInstance;
import org.kie.services.jobs.impl.LegacyInMemoryJobService;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimerTest extends AbstractBaseTest {

    public void addLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    private int counter = 0;

    static {
        ProcessRuntimeFactory.setProcessRuntimeFactoryService(new ProcessRuntimeFactoryServiceImpl());
    }

    @Test
    @Disabled
    public void testTimer() {
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        RuleFlowProcessInstance processInstance = new RuleFlowProcessInstance() {
            private static final long serialVersionUID = 510l;

            public void signalEvent(String type, Object event) {
                if ("timerTriggered".equals(type)) {
                    TimerInstance timer = (TimerInstance) event;
                    logger.info("Timer {} triggered", timer.getId());
                    counter++;
                }
            }
        };
        processInstance.setKnowledgeRuntime(((InternalWorkingMemory) kruntime.getKieSession()).getKnowledgeRuntime());
        processInstance.setId("1234");
        InternalProcessRuntime processRuntime = ((InternalProcessRuntime) ((InternalWorkingMemory) kruntime.getKieSession()).getProcessRuntime());
        processRuntime.getProcessInstanceManager().internalAddProcessInstance(processInstance);

        new Thread(() -> kruntime.getKieSession().fireUntilHalt()).start();
        JobsService jobService = new LegacyInMemoryJobService(kruntime, new DefaultUnitOfWorkManager(new CollectingUnitOfWorkFactory()));

        ProcessInstanceJobDescription desc = ProcessInstanceJobDescription.of(ExactExpirationTime.now(), processInstance.getStringId(), "test");
        String jobId = jobService.scheduleProcessInstanceJob(desc);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        assertEquals(1, counter);

        counter = 0;
        desc = ProcessInstanceJobDescription.of(DurationExpirationTime.after(500), processInstance.getStringId(), "test");
        jobId = jobService.scheduleProcessInstanceJob(desc);
        assertEquals(0, counter);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        assertEquals(1, counter);

        counter = 0;
        desc = ProcessInstanceJobDescription.of(DurationExpirationTime.repeat(500, 300L), processInstance.getStringId(), "test");
        jobId = jobService.scheduleProcessInstanceJob(desc);
        assertEquals(0, counter);
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            // do nothing
        }
        assertEquals(1, counter);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        // we can't know exactly how many times this will fire as timers are not precise, but should be at least 4
        assertTrue(counter >= 4);

        jobService.cancelJob(jobId);
        int lastCount = counter;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        assertEquals(lastCount, counter);
    }

}
