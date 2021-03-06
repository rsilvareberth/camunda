package wipdev.cloud.poc.service;

import org.springframework.stereotype.Service;

@Service
public class FirstService {
	
	public FirstService() {
	}
	
	public Long calculateTest(Long valueA, Long valueB) {
		return valueA + valueB;
	}

}
