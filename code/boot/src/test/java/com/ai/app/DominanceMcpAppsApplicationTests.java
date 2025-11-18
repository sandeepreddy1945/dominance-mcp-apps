package com.ai.app;

import com.ai.app.cas.gen.invoker.RFC3339DateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;

// @SpringBootTest
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
