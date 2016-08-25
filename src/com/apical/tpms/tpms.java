package com.apical.tpms;

public class tpms { 
    public static final int TPMS_TYPE_ALERT = 0x62;
    public static final int TPMS_TYPE_TIRES = 0x63;
    public static final int TPMS_TYPE_LEARN = 0x66;
    public static final int MAX_TIRES_NUM   = 5;
    public static final int MAX_ALERT_NUM   = 6;

    private long mTpmsContext;

    public void init(String dev) {
        mTpmsContext = nativeInit(dev);
    }

    public void release() {
        nativeFree(mTpmsContext);
    }

    public int configAlert(int i, int hot, int low) {
        return nativeConfigAlert(mTpmsContext, i, hot, low);
    }

    public int configAlert(int[] alerts) {
        return nativeConfigAlert(mTpmsContext, alerts);
    }

    public int requestAlert(int i) {
        return nativeRequestAlert(mTpmsContext, i);
    }

    public int requestTire(int i) {
        return nativeRequestTire(mTpmsContext, i);
    }

    public int unwatchTire(int i) {
        return nativeUnwatchTire(mTpmsContext, i);
    }

    public int learnTire(int i) {
        return nativeLearnTire(mTpmsContext, i);
    }

    public void getParams(int t, int[] params) {
        nativeGetParams(mTpmsContext, t, params);
    }

    private static native long nativeInit(String dev);
    private static native void nativeFree(long  ctxt);

    private static native int  nativeHandShake   (long ctxt);
    private static native int  nativeConfigAlert (long ctxt, int i, int hot, int low);
    private static native int  nativeConfigAlert (long ctxt, int[] alerts);
    private static native int  nativeRequestAlert(long ctxt, int i);
    private static native int  nativeRequestTire (long ctxt, int i);
    private static native int  nativeUnwatchTire (long ctxt, int i);
    private static native int  nativeLearnTire   (long ctxt, int i);
    private static native int  nativeGetParams   (long ctxt, int t, int[] params);

    static {
        System.loadLibrary("tpms_jni");
    }
}







