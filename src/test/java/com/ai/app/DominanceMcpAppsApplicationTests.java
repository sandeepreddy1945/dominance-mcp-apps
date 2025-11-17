package com.ai.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import com.ai.app.cas.gen.invoker.RFC3339DateFormat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class DominanceMcpAppsApplicationTests {

	@Test
	void contextLoads() throws ParseException {

		DateFormat dateFormat = new RFC3339DateFormat();
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = dateFormat.parse("2025-11-08T20:48:28.793581");
		System.out.println(date);
		LocalDateTime date1 = LocalDateTime.parse("2025-11-08T20:48:28.793581");
		System.out.println(date1);
	}

}
