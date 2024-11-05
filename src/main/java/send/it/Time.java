package send.it;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class Time {

    public static ZonedDateTime convertToZonedDateTime(EventDateTime eventDateTime) {
        DateTime dateTime = eventDateTime.getDateTime();
        if (dateTime == null) {
            // Handle the case where only a date is provided
            LocalDate localDate = LocalDate.parse(eventDateTime.getDate().toStringRfc3339());
            return localDate.atStartOfDay(ZoneId.systemDefault());
        } else {
            Instant instant = Instant.ofEpochMilli(dateTime.getValue());
            return ZonedDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId());
        }
    }

    public static boolean checkIfEventIsIn24Hours(ZonedDateTime googleEventDateTime) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime nowTimePlus24Hours = now.plusHours(24);
        boolean isEventInLessThan24Hours = false;
        if (googleEventDateTime.isAfter(now) && googleEventDateTime.isBefore(nowTimePlus24Hours)) {
            isEventInLessThan24Hours = true;
        }
        return isEventInLessThan24Hours;
    }

}
