package com.baidu.aip.asrwakeup3.core.overead;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.aidl.EventRecognitionService;
import com.baidu.speech.aidl.EventManagerFactory.Stub;
import com.baidu.speech.asr.EventManagerAsr;
import com.baidu.speech.asr.EventManagerSlot;
import com.baidu.speech.asr.EventManagerWp;
//import com.baidu.speech.audio.MicrophoneServer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class EventManagerFactory {
    private static final String TAG = "EventManagerFactory";
    private static boolean kwsLoaded = false;
    private static boolean asrUsing = false;
    private static boolean wpUsing = false;

    public EventManagerFactory() {
    }

    public static final EventManager create(Context var0, String var1) {
        return create(var0, var1, false);
    }

    public static final EventManager create(Context var0, String var1, boolean var2) {
        if (var0 != null && var1 != null && !var1.equals("")) {
            var0 = var0.getApplicationContext();
            if (var2) {
                EventManagerFactory.EventManagerRemote2Local var3 = new EventManagerFactory.EventManagerRemote2Local(var0, var1);
                return var3;
            } else if (var1.equals("asr")) {
                return new EventManagerAsr(var0);
            } else if (var1.equals("wp")) {
                return new EventManagerWp(var0);
            } else {
                return var1.equals("slot") ? new EventManagerSlot(var0) : null;
            }
        } else {
            return null;
        }
    }

    static class EventManagerRemote2Local implements EventManager {
        private com.baidu.speech.aidl.EventManager remoteEM;
        private Context context;
        private String name;
        ExecutorService executor = Executors.newCachedThreadPool();
        final ServiceConnection conn = new ServiceConnection() {
            public void onServiceConnected(ComponentName var1, IBinder var2) {
                com.baidu.speech.aidl.EventManagerFactory var3 = Stub.asInterface(var2);

                try {
                    if (null == EventManagerRemote2Local.this.remoteEM) {
                        com.baidu.speech.aidl.EventManager var4 = var3.create(EventManagerRemote2Local.this.name);
                        EventManagerRemote2Local.this.setRemoteEM(var4);
                    }
                } catch (RemoteException var5) {
                    var5.printStackTrace();
                }

            }

            public void onServiceDisconnected(ComponentName var1) {
                if (EventManagerFactory.kwsLoaded && EventManagerRemote2Local.this.mLis != null) {
                    EventManagerRemote2Local.this.mLis.onEvent("asr.unloaded", (String)null, (byte[])null, 0, 0);
                }

                if (EventManagerFactory.asrUsing && EventManagerRemote2Local.this.mLis != null) {
                    EventManagerRemote2Local.this.mLis.onEvent("asr.exit", (String)null, (byte[])null, 0, 0);
                }

                if (EventManagerFactory.wpUsing && EventManagerRemote2Local.this.mLis != null) {
                    EventManagerRemote2Local.this.mLis.onEvent("wp.exit", (String)null, (byte[])null, 0, 0);
                }

                EventManagerRemote2Local.this.remoteEM = null;
            }
        };
        private EventListener mLis;

        EventManagerRemote2Local(Context var1, String var2) {
            this.context = var1;
            this.name = var2;
        }

        public void setRemoteEM(com.baidu.speech.aidl.EventManager var1) {
            this.remoteEM = var1;
        }

        public void send(final String var1, final String var2, byte[] var3, final int var4, final int var5) {
            this.context.bindService(new Intent(this.context, EventRecognitionService.class), this.conn, 1);
            final byte[] var6 = var3 == null ? new byte[0] : var3;
            if (!"asr.start".equals(var1) && !"asr.kws.load".equals(var1)) {
                if ("wp.start".equals(var1)) {
                    EventManagerFactory.wpUsing = true;
                } else if ("asr.kws.load".equals(var1)) {
                    EventManagerFactory.kwsLoaded = true;
                }
            } else {
                EventManagerFactory.asrUsing = true;
            }

            Runnable var7 = new Runnable() {
                public void run() {
                    if (null == EventManagerRemote2Local.this.remoteEM) {
                        (new Handler(Looper.getMainLooper())).postDelayed(this, 10L);
                    } else {
                        String var1x = var2;
                        if ("asr.start".equals(var1) || "wp.start".equals(var1)) {
                            JSONObject var2x;
                            try {
                                var2x = new JSONObject(var2);
                            } catch (Exception var8) {
                                var2x = new JSONObject();
                            }

                            try {
                                String var3 = var2x.optString("infile");
                                if (!var2x.has("audio.socketport") && !TextUtils.isEmpty(var3)) {
                                    int var4x = 1;
                                    if (var2x.has("audio.source")) {
                                        var4x = var2x.optInt("audio.source");
                                    }

                                    int var5x = MicrophoneServer.create(var3, var4x);
                                    var2x.put("audio.socketport", var5x);
                                    var1x = var2x.toString();
                                }
                            } catch (Exception var7) {
                                var7.printStackTrace();
                            }
                        }

                        try {
                            EventManagerRemote2Local.this.remoteEM.registerListener(new com.baidu.speech.aidl.EventListener.Stub() {
                                public void onEvent(final String var1x, final String var2x, final byte[] var3, final int var4x, final int var5x) throws RemoteException {
                                    if ("asr.exit".equals(var1x)) {
                                        EventManagerFactory.asrUsing = false;
                                    } else if ("wp.exit".equals(var1x)) {
                                        EventManagerFactory.wpUsing = false;
                                    } else if ("asr.unloaded".equals(var1x)) {
                                        EventManagerFactory.kwsLoaded = false;
                                    }

                                    if ("wp.exit".equals(var1x)) {
                                        JSONObject var6x = null;
                                        boolean var7 = false;

                                        try {
                                            var6x = new JSONObject(var2x == null ? "{}" : var2x);
                                            var7 = var6x.optBoolean("_free");
                                            var6x.remove("_free");
                                        } catch (JSONException var9) {
                                            var9.printStackTrace();
                                        }

                                        final String var8 = var6x.toString();
                                        if (var7) {
                                            (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
                                                public void run() {
                                                    EventManagerRemote2Local.this.mLis.onEvent(var1x, var8, var3, var4x, var5x);
                                                }
                                            }, 200L);
                                        } else {
                                            (new Handler(Looper.getMainLooper())).post(new Runnable() {
                                                public void run() {
                                                    EventManagerRemote2Local.this.mLis.onEvent(var1x, var8, var3, var4x, var5x);
                                                }
                                            });
                                        }
                                    } else {
                                        (new Handler(Looper.getMainLooper())).post(new Runnable() {
                                            public void run() {
                                                EventManagerRemote2Local.this.mLis.onEvent(var1x, var2x, var3, var4x, var5x);
                                            }
                                        });
                                    }

                                }
                            });
                            EventManagerRemote2Local.this.remoteEM.send(var1, var1x, var6, var4, var5);
                        } catch (RemoteException var6x) {
                            var6x.printStackTrace();
                            EventManagerRemote2Local.this.remoteEM = null;
                        }

                    }
                }
            };
            (new Handler(Looper.getMainLooper())).postDelayed(var7, 0L);
        }

        public void registerListener(EventListener var1) {
            this.mLis = var1;
        }

        public void unregisterListener(EventListener var1) {
            this.mLis = null;
        }
    }
}
