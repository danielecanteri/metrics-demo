package com.acme;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Gauge;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/demo")
public class DemoController {

	private int gauge = 0;

	@Timed
	@RequestMapping("/1")
	public Message service1() {
		System.out.println("service1");
		return new Message("sevice1");
	}

	@Timed
	@RequestMapping("/2")
	public Message service2() {
		System.out.println("service2");
		return new Message("sevice2");
	}

	@RequestMapping("/gauge")
	public Message gauge(int value) {
		gauge += value;
		return new Message("gauge is now " + gauge);
	}

	@Gauge
	public int getGauge() {
		return gauge;
	}
}
