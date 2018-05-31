package com.pavlenko.jarvel.game.event.callback.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.pavlenko.jarvel.game.event.callback.EventCallbackFactory;
import com.pavlenko.jarvel.game.event.mapping.annotation.EventCallbackEntity;
import com.pavlenko.jarvel.game.fight.FightProcessor;
import com.pavlenko.jarvel.game.fight.impl.SimpleFightProcessor;
import com.pavlenko.jarvel.game.impl.SimpleGameContext;
import com.pavlenko.jarvel.property.PropertyHolder;
import com.pavlenko.jarvel.repository.Repository;
import com.pavlenko.jarvel.repository.impl.SimpleFileRepository;

public class SimpleEventCallbackFactory implements EventCallbackFactory {

	@Override
	public Object createEventCallback(Class<?> klass, SimpleGameContext context) {
		final Repository repo = createRepository();
		try {
			switch (klass.getAnnotation(EventCallbackEntity.class).callbackKey()) {
			case MENU:
				return klass.getConstructor(Repository.class, SimpleGameContext.class).newInstance(repo, context);
			case EXPLORE:
				return klass.getConstructor(Repository.class, SimpleGameContext.class, FightProcessor.class).newInstance(repo,
						context, new SimpleFightProcessor());
			default:
				return klass.getConstructor(Repository.class).newInstance(repo);
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Repository createRepository() {
		final Properties props = new Properties();
		try (FileInputStream fis = new FileInputStream("application.properties")) {
			props.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SimpleFileRepository(new PropertyHolder(props));
	}

}
