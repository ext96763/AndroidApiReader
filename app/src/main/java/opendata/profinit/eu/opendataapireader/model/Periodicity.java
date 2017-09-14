package opendata.profinit.eu.opendataapireader.model;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.concurrent.TimeUnit;

/**
 * Represents a single set duration. This is used in many different areas by the application, usually to refer to how
 * often a data file is published or a data source updated.
 */
@RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
public enum Periodicity {
    DAILY(TimeUnit.DAYS.toDays(1)),
    MONTHLY(TimeUnit.DAYS.toDays(30)),
    YEARLY(TimeUnit.DAYS.toDays(365)),
    WEEKLY(TimeUnit.DAYS.toDays(7)),
    APERIODIC(TimeUnit.DAYS.toDays(0)),
    QUARTERLY(TimeUnit.DAYS.toDays(90));

    long duration;

    Periodicity(long l) {
        this.duration = l;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
