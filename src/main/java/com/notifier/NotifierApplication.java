package com.notifier;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;


@SpringBootApplication
@EnableScheduling
public class NotifierApplication {
	//static final Logger log = LoggerFactory.getLogger(SpringLoggerApplication.class);
	public static void main(String[] args) {
	SpringApplication.run(NotifierApplication.class, args);
//		log.info("Before Starting application");
//		SpringApplication.run(SpringLoggerApplication.class, args);
//		log.debug("Starting my application in debug with {} args", args.length);
//		log.info("Starting my application with {} args.", args.length);
	}

}
