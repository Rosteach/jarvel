package com.pavlenko.jarvel;

import java.util.List;

import org.junit.Test;

import com.pavlenko.jarvel.game.event.EventProcessor;
import com.pavlenko.jarvel.game.event.callback.impl.SimpleEventCallbackFactory;
import com.pavlenko.jarvel.game.event.impl.EventMapBasedExecutor;
import com.pavlenko.jarvel.game.event.impl.SimpleEventProcessor;
import com.pavlenko.jarvel.game.event.mapping.EventCallbackMapper;
import com.pavlenko.jarvel.model.CharachterSetupModel;

public class JarvelTest {

	@Test
	public void test() {
		final EventCallbackMapper eventCallbackMapper = new EventCallbackMapper(new SimpleEventCallbackFactory());
		final EventProcessor eventProcessor = new SimpleEventProcessor(
				new EventMapBasedExecutor(eventCallbackMapper.createEventMap()));

		int iter = 100;
		int i = 0;
		while (i != iter) {
			final List<String> model = CharachterSetupModel.createMicroModel();
			for (String text : model) {
				System.out.println(text);
				eventProcessor.processEvent(text);
			}
			++i;
		}

	}

}
