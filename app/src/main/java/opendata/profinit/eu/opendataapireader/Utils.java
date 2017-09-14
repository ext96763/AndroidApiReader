package opendata.profinit.eu.opendataapireader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by shorcicka on 11.09.2017.
 */

public class Utils {

    public static String formatLongToDate(Long dateLong){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(new Date(dateLong));

    }

}
