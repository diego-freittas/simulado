package br.com.razila.simulado;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
		"spring.config.location=classpath:/application-test.yaml"
})
@ActiveProfiles("test")
class SimuladoApplicationTests {

	@Test
	void contextLoads() {
	}

}
