package net.sourceforge.tess4j.util;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public final class OsLocaleUtil {
    private static final String LOCALE = "C";
    private static String restoreLocale;

    public static void initLocale() {
        restoreLocale = CLibrary.INSTANCE.setlocale(CLibrary.LC_ALL, null);
        CLibrary.INSTANCE.setlocale(CLibrary.LC_ALL, LOCALE);
    }

    public static void restoreLocale() {
        if (restoreLocale != null) {
            CLibrary.INSTANCE.setlocale(CLibrary.LC_ALL, restoreLocale);
            restoreLocale = null;
        }
    }

    private static interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);


        int LC_CTYPE = 0;
        int LC_NUMERIC = 1;
        int LC_ALL = 6;

        // char *setlocale(int category, const char *locale);
        String setlocale(int category, String locale);
    }

    private OsLocaleUtil() {

    }
}
