package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import lombok.Getter;

/**
 * Created by bartoszpietrzak on 22/10/2017.
 */
@Getter
public class SpringContextBuilder
{
	private AbstractApplicationContext context;
	private List<Class<?>> configs = new ArrayList();
	private List<ApplicationContextInitializer<?>> initializers = new ArrayList();

	public SpringContextBuilder start(Class... applicationConfigs)
	{
		return this.start(ClassUtils.getDefaultClassLoader(), applicationConfigs);
	}

	private SpringContextBuilder start(ClassLoader classLoader, Class... applicationConfigs)
	{
		this.build(applicationConfigs);
		this.withClassLoader(classLoader);
		this.context.registerShutdownHook();
		this.context.refresh();
		return this;
	}

	private SpringContextBuilder build(Class... applicationConfigs)
	{
		if (ObjectUtils.isEmpty(applicationConfigs))
		{
			throw new IllegalArgumentException("Expected at least one applicationConfig!");
		}
		HashSet classes = new HashSet();
		classes.addAll(Arrays.asList(applicationConfigs));
		this.configs.addAll(classes);
		if (this.context == null)
		{
			this.context = this.getDefaultContext(this.configs);
		}

		return this;
	}

	private AnnotationConfigApplicationContext getDefaultContext(final List<Class<?>> configs)
	{
		this.addInitializers(new ApplicationContextInitializer()
		{
			@Override
			public void initialize(ConfigurableApplicationContext configurableApplicationContext)
			{
				AnnotationConfigApplicationContext annotationContext = (AnnotationConfigApplicationContext) configurableApplicationContext;
				initialize(annotationContext);
			}

			private void initialize(AnnotationConfigApplicationContext applicationContext)
			{
				applicationContext.register((Class[]) configs.toArray(new Class[configs.size()]));
			}
		});
		return new AnnotationConfigApplicationContext();
	}

	private SpringContextBuilder addInitializers(ApplicationContextInitializer... initializers)
	{
		this.initializers.addAll(Arrays.<ApplicationContextInitializer<?>>asList(initializers));
		return this;
	}

	private void withClassLoader(ClassLoader classLoader)
	{
		this.context.setClassLoader(classLoader);
	}
}
