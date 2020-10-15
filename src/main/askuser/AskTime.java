package askuser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AskTime {

    //EFFECTS:  Returns true if String (time) matches time format HH:MM (24 hour)
    public static boolean isStringTime(String time) {
        String check = time;
        Pattern p = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$");
        Matcher m = p.matcher(check);
        return m.matches();
    }
}

