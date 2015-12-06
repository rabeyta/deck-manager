package me.abeyta.it.definitions;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import me.abeyta.it.ITTestConfig;
import me.abeyta.it.IntegrationTestState;

@ContextConfiguration(classes=ITTestConfig.class)
public class CommonDefinitions {
	
	@Autowired
	private IntegrationTestState testState;
	
	@BeforeScenario
	@AfterScenario
	public void clearTestState() {
		testState.clear();
	}

}
