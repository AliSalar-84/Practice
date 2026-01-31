public enum Seasons {
    SPRING, SUMMER, AUTUMN, WINTER;

    enum MONTHS {JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER}

    public static boolean shouldTakeACoat(Seasons season) {
        return switch (season) {
            case Seasons.SPRING, Seasons.SUMMER -> false;
            case Seasons.AUTUMN, Seasons.WINTER -> true;
        };
    }

    public static boolean shouldTakeACoat(MONTHS month) {
        var season = Seasons.SeasonForMonth(month);
        return shouldTakeACoat(season);
    }

    public static Seasons SeasonForMonth(MONTHS month) {
        return switch (month) {
            case JANUARY, FEBRUARY, MARCH -> WINTER;
            case APRIL, MAY, JUNE -> SPRING;
            case JULY, AUGUST, SEPTEMBER -> SUMMER;
            case OCTOBER, NOVEMBER, DECEMBER -> AUTUMN;
        };
    }

}


