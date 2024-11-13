package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.naming.NameNotFoundException;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            public int compare(String m1, String m2) {
                    int d1 = fromString(m1).days;
                    int d2 = fromString(m2).days;
                    return Integer.compare(d1, d2);
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            public int compare(String m1, String m2) {
                    int o1 = fromString(m1).ordinal();
                    int o2 = fromString(m2).ordinal();
                    return Integer.compare (o1, o2);
            }
        };
    }

    public static Month fromString(String name) {
        List<Month> matches = new ArrayList<>();

        for (Month m : Month.values()) {
            if (m.name.toLowerCase().startsWith(name.toLowerCase())) {
                matches.add(m);
            }
        }

        if (matches.size() > 1) {
            throw new IllegalArgumentException();
        }

        if (matches.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return matches.get(0);
    }

    public enum Month {
        JANUARY("January", 31),
        FEBRUARY("February",28),
        MARCH("March",31),
        APRIL("April",30),
        MAY("May",31),
        JUNE("June",30),
        JULY("July",31),
        AUGUST("August",31),
        SEPTEMBER("September",30),
        OCTOBER("October",31),
        NOVEMBER("November",30),
        DECEMBER("December",31);

        private int days = 0;   
        private String name = null;

        Month(String name, int i) {
            this.days = i;
            this.name = name;
        }  
    }

    public class sortByDate implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(fromString(o1).days, fromString(o2).days);
        }
        
    }

    public class sortByMonthOrder implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(fromString(o1).ordinal(), fromString(o2).ordinal());
        }
        
    }
}
