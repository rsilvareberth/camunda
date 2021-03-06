package wipdev.cloud.poc.camunda.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import wipdev.cloud.poc.service.FirstService;

@Component
public class FirstDelegate implements JavaDelegate{
	
	private final FirstService firstService;
	
	public FirstDelegate(FirstService firstService) {
		this.firstService = firstService; 
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("Executou FirstDelegate!");
		System.out.println(firstService.calculateTest(1L, 2L));
		
		
	}

}
