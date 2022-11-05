package com.jdtx.state;


public class UtStatePrint {

    /**
     * Печатает данные таймеров.
     */
    public static void printItems(StateItem stateItem) {
        printItems(false);
    }

    /**
     * Печатает данные таймеров с датами запуска и остановки.
     */
    public static void printItems(boolean printStartStop) {
/*
        for (Map.Entry e : stopWatchItems.entrySet()) {
            String timerName = (String) e.getKey();
            IVariantMap metric = (IVariantMap) e.getValue();

            Date sw_start = metric.getValueDate("sw_start");
            Date sw_stop = metric.getValueDate("sw_stop");
            int sw_steps = metric.getValueInt("sw_steps");
            long sw_duration = getDuration(timerName);

            //
            System.out.println(timerName);
            System.out.println("  " + UtString.padRight("sw_duration", 16) + ": " + durationToStr(sw_duration));

            //
            if (printStartStop) {
                if (metric.get("sw_stop") == null) {
                    System.out.println("  " + UtString.padRight("sw_start", 16) + ": " + sw_start);
                    System.out.println("  " + UtString.padRight("sw_stop", 16) + ": " + "running");
                    System.out.println("  " + UtString.padRight("sw_steps", 16) + ": " + sw_steps);
                } else {
                    System.out.println("  " + UtString.padRight("sw_start", 16) + ": " + sw_start);
                    System.out.println("  " + UtString.padRight("sw_stop", 16) + ": " + sw_stop);
                    System.out.println("  " + UtString.padRight("sw_steps", 16) + ": " + sw_steps);
                }
            }

            //
            for (Map.Entry x : metric.entrySet()) {
                String key = (String) x.getKey();
                if (key.compareTo("sw_start") != 0 &&
                        key.compareTo("sw_stop") != 0 &&
                        key.compareTo("sw_duration") != 0 &&
                        key.compareTo("sw_steps") != 0
                ) {
                    System.out.println("  " + UtString.padRight(key, 16) + ": " + String.valueOf(x.getValue()));
                }
            }
        }
*/

    }

    private String durationToStr(long sw_duration) {
        if (sw_duration > 1000) {
            return sw_duration / 1000 + " sec " + sw_duration % 1000 + " msec";
        } else {
            return sw_duration + " msec";
        }
    }


}
