package net.prismarray.smptweaks;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {

    public static String toDate(String unixTimestamp) {
        long epoch = Long.parseLong(unixTimestamp);

        Instant instant = Instant.ofEpochMilli(epoch);

        ZonedDateTime berlinDateTime = instant.atZone(ZoneId.of("Europe/Berlin"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        return berlinDateTime.format(formatter);
    }

}
