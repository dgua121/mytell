package com.baidu.aip.asrwakeup3.core.overead;

import android.media.AudioRecord;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.speech.utils.LogUtil;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MicrophoneServer implements Runnable {
    public static final String TAG = MicrophoneServer.class.getSimpleName();
    public static final int S_DATA_LENGTH = 1920000;
    public static final int S_LENGTH = 640;
    private static final int PORT = 3277;
    private final byte[] sData = new byte[1920000];
    private final int sLen = 640;
    private long sLimit = 0L;
    private ArrayList<MicrophoneServer.SocketWrap> mRemoteOutputStreams = new ArrayList();
    private String mInfile;
    private int mAudioSource;
    private LocalServerSocket mServerSocket;
    private String SOCKET_ADDRESS = "com.baidu.speech";
    private DataInputStream mIn = null;
    private final int mServerPort;
    private static HashMap<String, MicrophoneServer> sPorts = new HashMap();
    private boolean firstStart = true;
    ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
    Future<Integer> future;

    public static int create(String var0, int var1) throws IOException {
        HashMap var2 = sPorts;
        synchronized(sPorts) {
            MicrophoneServer var3 = (MicrophoneServer)sPorts.get(var0);
            if (var3 == null) {
                try {
                    MicrophoneServer var4 = new MicrophoneServer(var0, var1);
                    sPorts.put(var0, var4);
                } catch (Exception var6) {
                    var6.printStackTrace();
                    return 3277;
                }
            }

            return ((MicrophoneServer)sPorts.get(var0)).mServerPort;
        }
    }

    private MicrophoneServer(String var1, int var2) throws IOException {
        this.mInfile = var1;
        this.mAudioSource = var2;
        Log.i(TAG, "infile:" + var1 + "  audioSource:" + var2);
        int var3;
        if (TextUtils.isEmpty(var1)) {
            var3 = (new Random()).nextInt(1000);
            this.mServerSocket = new LocalServerSocket(this.SOCKET_ADDRESS + "_" + var3);
            this.mServerPort = var3;
        } else {
            var3 = (new Random()).nextInt(1000);
            this.mServerSocket = new LocalServerSocket(this.SOCKET_ADDRESS + "_" + var3);
            this.mServerPort = var3;
        }

        Thread var4 = new Thread() {
            public void run() {
                try {
                    while(true) {
                        LocalSocket var1 = MicrophoneServer.this.mServerSocket.accept();
                        synchronized(MicrophoneServer.this.mRemoteOutputStreams) {
                            MicrophoneServer.SocketWrap var16 = new MicrophoneServer.SocketWrap(var1);
                            MicrophoneServer.this.mRemoteOutputStreams.add(var16);
                            LogUtil.i(MicrophoneServer.TAG, new String[]{"add wrap socket, mRemoteOutputStreams size = " + MicrophoneServer.this.mRemoteOutputStreams.size() + " firstStart = " + MicrophoneServer.this.firstStart});
                            if (MicrophoneServer.this.mRemoteOutputStreams.size() == 1 && MicrophoneServer.this.firstStart) {
                                MicrophoneServer.this.firstStart = false;
                                if (MicrophoneServer.this.mIn != null) {
                                    try {
                                        MicrophoneServer.this.mIn.close();
                                        MicrophoneServer.this.mIn = null;
                                    } catch (Exception var12) {
                                        var12.printStackTrace();
                                    }
                                }

                                InputStream var17 = MicrophoneServer.this.createInputStream(MicrophoneServer.this.mInfile, MicrophoneServer.this.mAudioSource);
                                MicrophoneServer.this.mIn = new DataInputStream(var17);
                                (new Thread(MicrophoneServer.this)).start();
                            }
                        }
                    }
                } catch (Exception var15) {
                    LogUtil.d(MicrophoneServer.TAG, new String[]{" mRemoteOutputStreams.size：" + MicrophoneServer.this.mRemoteOutputStreams.size()});
                    MicrophoneServer.this.firstStart = true;
                    synchronized(MicrophoneServer.this.mRemoteOutputStreams) {
                        Iterator var3 = MicrophoneServer.this.mRemoteOutputStreams.iterator();

                        while(var3.hasNext()) {
                            LocalSocket var4 = (LocalSocket)var3.next();

                            try {
                                var4.getOutputStream().close();
                                var4.close();
                            } catch (IOException var11) {
                                var11.printStackTrace();
                            }
                        }

                        MicrophoneServer.this.mRemoteOutputStreams.clear();
                    }

                    if (MicrophoneServer.this.mIn != null) {
                        try {
                            MicrophoneServer.this.mIn.close();
                            MicrophoneServer.this.mIn = null;
                        } catch (Exception var10) {
                            var10.printStackTrace();
                        }
                    }

                    try {
                        MicrophoneServer.this.mServerSocket.close();
                    } catch (Exception var9) {
                        var9.printStackTrace();
                    }

                    synchronized(MicrophoneServer.sPorts) {
                        MicrophoneServer.sPorts.remove(MicrophoneServer.this.mInfile);
                    }

                    var15.printStackTrace();
                }
            }
        };
        var4.start();
    }

    public void run() {
        try {
            ArrayList var2;
            int var3;
            try {
                boolean var58 = false;

                while(true) {
                     int var59 = (int)(this.sLimit % (long)this.sData.length);

                    try {
                        this.mIn.readFully(this.sData, var59, 640);
                    } catch (EOFException var53) {
                        var53.printStackTrace();
                        this.firstStart = true;
                        break;
                    }

                    this.sLimit += 640L;
                    var59 += 640;
                    var2 = this.mRemoteOutputStreams;
                    synchronized(this.mRemoteOutputStreams) {
                        for(var3 = 0; var3 < this.mRemoteOutputStreams.size(); ++var3) {
                            final MicrophoneServer.SocketWrap var61 = (MicrophoneServer.SocketWrap)this.mRemoteOutputStreams.get(var3);

                            try {
                                long var62 = var61.getPosition(this.sLimit);
                                final int var7 = (int)(var62 % (long)this.sData.length);
                                final OutputStream var8 = var61.getOutputStream();
                                final int var9 = var59 - var7;
                                final int finalVar5 = var59;
                                this.future = this.newSingleThreadExecutor.submit(new Callable<Integer>() {
                                    public Integer call() throws Exception {
                                        if (var9 >= 0) {
                                            var8.write(MicrophoneServer.this.sData, var7, var9);
                                        } else {
                                            var8.write(MicrophoneServer.this.sData, var7, MicrophoneServer.this.sData.length - var7);
                                            var8.write(MicrophoneServer.this.sData, 0, finalVar5);
                                        }

                                        var61.setPosition(MicrophoneServer.this.sLimit);
                                        return 0;
                                    }
                                });
                                this.future.get(1000L, TimeUnit.MILLISECONDS);
                            } catch (TimeoutException var49) {
                                throw var49;
                            } catch (Exception var50) {
                                var50.printStackTrace();
                                this.mRemoteOutputStreams.remove(var61);
                                var61.getOutputStream().close();
                                var61.close();
                            }
                        }

                        if (this.mRemoteOutputStreams.size() <= 0) {
                            this.firstStart = true;
                            break;
                        }
                    }
                }
            } catch (TimeoutException var55) {
                TimeoutException var1 = var55;
                var55.printStackTrace();
                HashMap var60 = sPorts;
                synchronized(sPorts) {
                    try {
                        this.mServerSocket.close();
                    } catch (Exception var47) {
                        var1.printStackTrace();
                    }

                    sPorts.remove(this.mInfile);
                }
            } catch (Exception var56) {
                this.firstStart = true;
                var2 = this.mRemoteOutputStreams;
                synchronized(this.mRemoteOutputStreams) {
                    for(var3 = 0; var3 < this.mRemoteOutputStreams.size(); ++var3) {
                        LocalSocket var4 = (LocalSocket)this.mRemoteOutputStreams.get(var3);

                        try {
                            OutputStream var5 = var4.getOutputStream();
                            byte[] var6;
                            if (TextUtils.isEmpty(this.mInfile)) {
                                var6 = new byte[]{0, 0, 0, 0, 0, 0};
                            } else {
                                var6 = new byte[]{1, 0, 0, 0, 0, 0};
                            }

                            var5.write(var6, 0, var6.length);
                        } catch (Exception var46) {
                            var46.printStackTrace();
                        }
                    }
                }
            }
        } finally {
            ArrayList var16 = this.mRemoteOutputStreams;
            synchronized(this.mRemoteOutputStreams) {
                LogUtil.i(TAG, new String[]{"finally, mRemoteOutputStreams size = " + this.mRemoteOutputStreams.size() + " firstStart = " + this.firstStart});
                if (this.firstStart) {
                    Iterator var17 = this.mRemoteOutputStreams.iterator();

                    while(var17.hasNext()) {
                        LocalSocket var18 = (LocalSocket)var17.next();

                        try {
                            var18.getOutputStream().close();
                            var18.close();
                        } catch (IOException var45) {
                            var45.printStackTrace();
                        }
                    }

                    this.mRemoteOutputStreams.clear();

                    try {
                        if (this.mIn != null) {
                            this.mIn.close();
                            this.mIn = null;
                        }
                    } catch (Exception var44) {
                        var44.printStackTrace();
                    }
                }

            }
        }

    }

    private InputStream createInputStream(String var1, int var2) throws Exception {
        if (var1 != null && !var1.equals("")) {
            if (var1.startsWith("#")) {
                Matcher var8 = Pattern.compile("^#(.*)[#.](.*?)\\(").matcher(var1);
                if (var8.find()) {
                    String var7 = var8.group(1);
                    String var9 = var8.group(2);
                    return (InputStream)Class.forName(var7).getMethod(var9).invoke((Object)null);
                } else {
                    throw new IOException("can not create inputStream");
                }
            } else {
                String var6;
                if (var1.startsWith("res://")) {
                    var6 = var1.replaceFirst("res://", "").replaceFirst("/", "");
                    return this.getClass().getResourceAsStream("/" + var6);
                } else if (!var1.startsWith("asset://") && !var1.startsWith("assets://")) {
                    if (var1.startsWith("tcp://")) {
                        var6 = var1.replaceFirst("tcp://", "").replaceFirst("/", "");
                        int var4 = Integer.parseInt(var6);
                        Socket var5 = new Socket("localhost", var4);
                        return var5.getInputStream();
                    } else {
                        return new FileInputStream(var1);
                    }
                } else {
                    var6 = var1.replaceFirst("assets://", "").replaceFirst("/", "");
                    if (var1.startsWith("asset://")) {
                        var6 = var1.replaceFirst("asset://", "").replaceFirst("/", "");
                    }

                    return this.getClass().getResourceAsStream("/assets/" + var6);
                }
            }
        } else {
            MicrophoneServer.MicInputStream var3 = new MicrophoneServer.MicInputStream(var2, 16000);
            return var3;
        }
    }

    private static class SocketWrap extends LocalSocket {
        private long mPosition = -1L;
        private final LocalSocket mSocket;
        byte[] data = new byte[8];

        public SocketWrap(LocalSocket var1) {
            this.mSocket = var1;

            try {
                this.mSocket.setSoTimeout(2000);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }

        public long getPosition(long var1) {
            if (this.mPosition >= 0L) {
                return this.mPosition;
            } else {
                InputStream var3 = null;

                try {
                    var3 = this.mSocket.getInputStream();
                    var3.read(this.data, 0, this.data.length);
                    long var5 = this.byteArrayToInt(this.data);
                    Log.i(MicrophoneServer.TAG, "audio mills is " + var5);
                    if (var5 > 0L) {
                        long var7 = System.currentTimeMillis() - var5;
                        long var9 = Math.min(Math.max(0L, var7), var1 / 32L);
                        this.mPosition = var9 / 20L * 20L * 32L;
                    } else {
                        this.mPosition = 6400L;
                    }

                    this.mPosition = (var1 - this.mPosition + 1920000L) % 1920000L;
                } catch (Exception var11) {
                    this.mPosition = (var1 - 6400L + 1920000L) % 1920000L;
                    var11.printStackTrace();
                }

                return this.mPosition;
            }
        }

        public void setPosition(long var1) {
            this.mPosition = var1;
        }

        public synchronized void close() throws IOException {
            this.mSocket.close();
        }

        public OutputStream getOutputStream() throws IOException {
            return this.mSocket.getOutputStream();
        }

        public void shutdownOutput() throws IOException {
            this.mSocket.shutdownOutput();
        }

        private long byteArrayToInt(byte[] var1) {
            byte[] var2 = new byte[8];
            int var3 = var2.length - 1;

            for(int var4 = 0; var3 >= 0; ++var4) {
                if (var4 < var1.length) {
                    var2[var3] = var1[var4];
                } else {
                    var2[var3] = 0;
                }

                --var3;
            }

            long var19 = (long)(var2[0] & 255) << 56;
            long var5 = (long)(var2[1] & 255) << 48;
            long var7 = (long)(var2[2] & 255) << 40;
            long var9 = (long)(var2[3] & 255) << 32;
            long var11 = (long)(var2[4] & 255) << 24;
            long var13 = (long)(var2[5] & 255) << 16;
            long var15 = (long)(var2[6] & 255) << 8;
            long var17 = (long)(var2[7] & 255);
            return var19 + var5 + var7 + var9 + var11 + var13 + var15 + var17;
        }
    }

    public static class MicInputStream extends InputStream {
        private String TAG = MicrophoneServer.MicInputStream.class.getSimpleName();
        private static final int DEFAULT_BUFFER_SIZE = 160000;
        private AudioRecord mAudioRecord;

        public MicInputStream(int var1, int var2) {
            try {
                this.mAudioRecord = new AudioRecord(var1, var2, 16, 2, 160000);
                LogUtil.i("audioSource : ", new String[]{var1 + ""});
                LogUtil.i(this.TAG, new String[]{"startRecordingAndCheckStatus recorder status is " + this.mAudioRecord.getState()});
                this.mAudioRecord.startRecording();
                int var3 = 0;
                byte[] var4 = new byte[32];

                for(int var5 = 0; var5 < 10; ++var5) {
                    int var6 = this.mAudioRecord.read(var4, 0, var4.length);
                    if (var6 > 0) {
                        var3 += var6;
                        break;
                    }
                }

                if (var3 <= 0) {
                    this.mAudioRecord.release();
                    new Exception("bad recorder, read(byte[])");
                }
            } catch (Exception var15) {
                var15.printStackTrace();
            } finally {
                label143: {
                    if (this.mAudioRecord == null || this.mAudioRecord.getRecordingState() == 3) {
                        int var10000 = this.mAudioRecord.getState();
                        AudioRecord var10001 = this.mAudioRecord;
                        if (var10000 != 0) {
                            break label143;
                        }
                    }

                    try {
                        this.mAudioRecord.release();
                    } catch (Exception var14) {
                        var14.printStackTrace();
                    }

                    LogUtil.d(this.TAG, new String[]{"recorder start failed, RecordingState=" + this.mAudioRecord.getRecordingState()});
                }

            }

        }

        public int read(byte[] var1, int var2, int var3) throws IOException {
            if (this.mAudioRecord == null) {
                throw new IOException("audio recorder is null");
            } else {
                int var4 = this.mAudioRecord.read(var1, var2, var3);
                LogUtil.v(this.TAG, new String[]{" AudioRecord read: len:" + var4 + " byteOffset:" + var2 + " byteCount:" + var3});
                if (var4 >= 0 && var4 <= var3) {
                    return var4;
                } else {
                    throw new IOException("audio recdoder read error, len = " + var4);
                }
            }
        }

        public void close() throws IOException {
            if (this.mAudioRecord != null) {
                this.mAudioRecord.release();
            }

        }

        public int read() throws IOException {
            throw new IOException("read not support");
        }
    }
}

