package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    private static final Comparator<String> BY_DAYS = new SortByDays();
    private static final Comparator<String> BY_ORDER = new SortByMonthOrder();

    @Override
    public Comparator<String> sortByDays() {
        return BY_DAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return BY_ORDER;
    }

    private enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }

        public static Month fromString(final String name) {
            Objects.requireNonNull(name);
            Month match = null;
            for(final Month month: values()) {
                if(month.toString().toLowerCase().startsWith(name.toString().toLowerCase())) {
                    if (match != null) {
                        throw new IllegalArgumentException(name + " is ambiguous: both " + match + " and " + month + 
                        " would be valid match",new IllegalArgumentException());
                    }
                    match = month;
                }
            }
            if (match == null) {
                throw new IllegalArgumentException("No matching months for " + name, new IllegalArgumentException());
            }
            return match;
            
        }
    };

    private static class SortByDays implements Comparator<String> {
        @Override
        public int compare(final String s1, final String s2) {
            return Integer.compare(Month.fromString(s1).days, Month.fromString(s2).days);
        }
    }

    private static class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(final String s1, final String s2) {
            return Month.fromString(s1).compareTo(Month.fromString(s2));
        }
    }

}
