package com.hecaibao88.dirtygame.utils;

import android.util.Log;

/**
 * @author
 * @ClassName: L
 * @Description: log
 * @date 15/11/1 上午10:41
 */
public class L {


    /**
     *
     */
    public static boolean debug = true;

    public static boolean debugLog = true;

    public static void debug(String TAG, String info) {
        if (debugLog) {
            Log.d(TAG, "" + info);
        }
    }

    public static void error(String TAG, Exception ex) {

        if (debugLog) {
            ex.printStackTrace();
            Log.w(TAG, "ex: " + ex.toString());
        }
    }

    public static void error(String TAG, Error err) {
        if (debugLog) {
            err.printStackTrace();
            Log.e(TAG, "err: " + err.toString());
        }
    }
}
