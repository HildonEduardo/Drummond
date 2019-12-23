package com.help.drummond.exceptions;

public class ExceptionHandlerManager {

    private static ExceptionHandlerManager instance;
    private Thread.UncaughtExceptionHandler exceptionHandler;
    private CrashHandlingListener listener;

    public static ExceptionHandlerManager getInstance() {
        if (instance == null) {
            instance = new ExceptionHandlerManager();
        }
        return instance;
    }

    public void initHandling(CrashHandlingListener listener) {
        this.listener = listener;

        replaceDefaultExceptHandling();
    }

    private void replaceDefaultExceptHandling() {
        final Thread.UncaughtExceptionHandler oldHandler =
                Thread.getDefaultUncaughtExceptionHandler();

        exceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                //Do your own error handling here

                listener.onCrashHappened(paramThrowable.getMessage());

                if (oldHandler != null) {
                    oldHandler.uncaughtException(paramThread, paramThrowable); //Delegates to Android's error handling
                } else {
                    System.exit(2); //Prevents the service/app from freezing
                }
            }
        };

        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    }
}
