package com.fei.root.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.fei.root.common.utils.AppInfo;
import com.fei.root.common.utils.Storage;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.LinkedList;
import java.util.List;

public class CrashHandler implements UncaughtExceptionHandler {
    public static final String TAG = CrashHandler.class.getSimpleName();
    private static final String FILE_KEY = "crashs";
    private static final int TOAST_TIME = 2000;
    private static LinkedList<String> crashStacks;
    private static CrashHandler instance;
    private final Context context;
    private UncaughtExceptionHandler defaultHandler;

    private Handler handler;

    private CrashHandler(Context context) {
        this.context = context;
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 在Application中初始化一次即可
     */
    public static void init(Context context) throws Exception {
        if (CrashHandler.instance != null) {
            throw new Exception("The CrashHandler is already initialized.");
        }

        crashStacks = Storage.getObject(context, FILE_KEY);
        if (crashStacks == null) {
            crashStacks = new LinkedList<>();
        }

        CrashHandler.instance = new CrashHandler(context);
    }

    public static CrashHandler getInstance() {
        if (CrashHandler.instance == null) {
            throw new RuntimeException("The CrashHandler is not initialized.");
        }
        return CrashHandler.instance;
    }

    public static List<String> getCrashLog() {
        return crashStacks;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!this.handleException(ex) && this.defaultHandler != null) {
            this.defaultHandler.uncaughtException(thread, ex);
        } else {
            //发完toast再退出APP
            try {
                Thread.sleep(CrashHandler.TOAST_TIME);
                System.exit(1);
            } catch (InterruptedException e) {
                Log.e(CrashHandler.TAG, "Error : ", e);
            } catch (Exception e) {
                Log.e(CrashHandler.TAG, "Error : ", e);
            }
        }
    }

    private boolean handleException(final Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(CrashHandler.TAG, throwable.toString());
        }
        if (throwable == null) {
            return BuildConfig.DEBUG;
        }

        new Thread(() -> {
            Looper.prepare();
            handler.post(() -> Toast.makeText(context, "未知Crash!!!", Toast.LENGTH_SHORT).show());
            CrashHandler.this.saveCrashToFile(throwable);
            Looper.loop();
        }).start();

        return BuildConfig.DEBUG;
    }

    public void saveCrashToFile(Throwable throwable) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        throwable.printStackTrace(printWriter);

        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        String result = info.toString();
        printWriter.close();
        Log.e(CrashHandler.TAG, result);

        try {
            Context context = MultiApplication.getContext();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("time", System.currentTimeMillis() + "");
            jsonObject.put("systemVersion", AppInfo.getSystemVersion());
            jsonObject.put("versionName", AppInfo.getVersionName(MultiApplication.getContext()));
            jsonObject.put("clientType", AppInfo.getClientType());
            jsonObject.put("ChannelId", AppInfo.getChannelName());
            jsonObject.put("exception", result);

            if (crashStacks.size() >= 5) {
                crashStacks.removeLast();
            }
            crashStacks.addFirst(jsonObject.toString());
            Storage.putObject(context, FILE_KEY, crashStacks);
        } catch (Exception e) {
            Log.e(CrashHandler.TAG, "save crash to file fails", e);
        }
    }

}
