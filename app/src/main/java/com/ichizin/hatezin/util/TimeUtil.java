package com.ichizin.hatezin.util;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import timber.log.Timber;

/**
 *
 *
 * @author ichizin
 */
public class TimeUtil {

    public static String convertDate(@NonNull String iso8601UTCString) {
        return convertDate(iso8601UTCString, "yyyy-MM-dd HH:mm:ss");
    }

    public static String convertDate(@NonNull String iso8601UTCString, String convertFormat) {

        try {
            SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
            iso8601Format.setTimeZone(TimeZone.getDefault());
            Date date = iso8601Format.parse(iso8601UTCString);
            SimpleDateFormat sdf = new SimpleDateFormat(convertFormat);
            sdf.setTimeZone(TimeZone.getDefault());
            return sdf.format(date);
        } catch (ParseException e) {
            Timber.e(e.getMessage());
            return null;
        }
    }
}
