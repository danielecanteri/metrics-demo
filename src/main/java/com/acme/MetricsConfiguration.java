package com.acme;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

@Configuration
@EnableMetrics
public class MetricsConfiguration extends MetricsConfigurerAdapter {

	@Override
	public MetricRegistry getMetricRegistry() {
		MetricRegistry metricRegistry = new MetricRegistry();
		metricRegistry.register("jvm.gc", new GarbageCollectorMetricSet());
		return metricRegistry;
	}

	@Override
	public void configureReporters(MetricRegistry metricRegistry) {
		registerReporter(ConsoleReporter.forRegistry(metricRegistry).build())
				.start(20, TimeUnit.SECONDS);
		registerReporter(
				GraphiteReporter.forRegistry(metricRegistry).build(
						new Graphite("192.168.99.100", 2003))).start(20,
				TimeUnit.SECONDS);
	}

}