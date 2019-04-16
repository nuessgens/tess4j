package net.sourceforge.tess4j.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

import net.sourceforge.tess4j.ITesseract;

public final class LocaleAwareTesseact {

    public static ITesseract wrap(ITesseract delegate) {
        ItesseractInvocationHandler h = new ItesseractInvocationHandler(delegate);
        Object resp = Proxy.newProxyInstance(delegate.getClass().getClassLoader(), new Class<?>[] { ITesseract.class }, h);
        return ITesseract.class.cast(resp);
    }

    private static class ItesseractInvocationHandler implements InvocationHandler {
        private final Object delegate;

        private ItesseractInvocationHandler(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean changeLocale = !method.getDeclaringClass().equals(Object.class); // NOT toString, clone, equals hashcode etc
            String lcAllRestore = null;
            if (changeLocale) {
                lcAllRestore = CLibrary.INSTANCE.setlocale(CLibrary.LC_ALL, null);
                CLibrary.INSTANCE.setlocale(CLibrary.LC_ALL, "C");
            }
            try {
                return method.invoke(delegate, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            } finally {
                if (changeLocale) {
                    CLibrary.INSTANCE.setlocale(CLibrary.LC_ALL, lcAllRestore);
                }
            }
        }

    }

    private static interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

        String LOCALE = "C";

        int LC_CTYPE = 0;
        int LC_NUMERIC = 1;
        int LC_ALL = 6;

        // char *setlocale(int category, const char *locale);
        String setlocale(int category, String locale);
    }

    private LocaleAwareTesseact() {

    }
}
