package wipdev.cloud.poc;

import org.camunda.bpm.engine.RuntimeService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.assertj.core.api.Assertions.*;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import wipdev.cloud.poc.camunda.process.FirstDelegate;
import wipdev.cloud.poc.service.FirstService;
import org.camunda.bpm.engine.test.assertions.*;


import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;


@RunWith(MockitoJUnitRunner.class) // initializes all necessary @Mock fields
@Deployment(resources = { "process/poc/bpmn/poc.bpmn" })
public class ProcessTest {

//	private static final String BUSINESS_KEY = "poc" + UUID.randomUUID().toString().replace("-", "");
	private static final String PROCESS_KEY = "poc_process";
	
	@Rule
	@ClassRule
	public static ProcessEngineRule engineRule = TestCoverageProcessEngineRuleBuilder.create().build();

	// -----------------
	// PREPARE MOCKS
	// -----------------

	// mock the services which can be configured in the tests if needed
	@Mock
	private FirstService firstService;

	// inject the services into the delegates
	@InjectMocks
	private FirstDelegate firstDelegate;

	@Before
	public void setup() {
		// register the delegates
		Mocks.register("firstDelegate", firstDelegate);
	}

	@After
	public void tearDown() {
		// reset all mocks and delete the deployments (including running instances)
		Mocks.reset();
	    cleanDeployments();
	}

	@Test
	public void shouldStartPocProcess() {

//		ProcessInstance instance = runtimeService().startProcessInstanceByKey(PROCESS_KEY);
		
		ProcessInstance instance = runtimeService()
				.startProcessInstanceByKey(PROCESS_KEY);
		
		  assertThat(instance)
	      .isActive();
		  System.out.println("passou");
		  
		  
	}

	  protected void cleanDeployments() {
		    for (org.camunda.bpm.engine.repository.Deployment deployment : repositoryService().createDeploymentQuery().list()) {
		      repositoryService().deleteDeployment(deployment.getId(), true);
		    }
		  }
}
