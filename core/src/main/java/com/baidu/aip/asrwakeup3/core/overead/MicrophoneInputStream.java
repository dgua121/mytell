package com.baidu.aip.asrwakeup3.core.overead;

import android.media.AudioRecord;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import com.baidu.speech.audio.MicrophoneServer;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class MicrophoneInputStream extends InputStream {
    private static final String TAG = "MicrophoneInputStream";
    private final LocalSocket socket;
    private final InputStream source;
    private String SOCKET_ADDRESS;

    public MicrophoneInputStream() throws IOException {
        this(1, 16000);
    }

    public MicrophoneInputStream(int var1) throws IOException {
        this(1, var1);
    }

    public MicrophoneInputStream(AudioRecord var1) throws IOException {
        this(1, 16000, (InputStream)null, var1);
    }

    public MicrophoneInputStream(int var1, int var2) throws IOException {
        this(var1, var2, (InputStream)null);
    }

    public MicrophoneInputStream(int var1, InputStream var2) throws IOException {
        this(1, var1, var2);
    }

    public MicrophoneInputStream(int var1, int var2, InputStream var3) throws IOException {
        this(var1, var2, var3, (AudioRecord)null);
    }

    public MicrophoneInputStream(int var1, int var2, InputStream var3, AudioRecord var4) throws IOException {
        this.SOCKET_ADDRESS = "com.baidu.speech";
        final LocalSocket[] var5 = new LocalSocket[1];
        int var6 = MicrophoneServer.create("", var1);
        Future var7 = Executors.newSingleThreadExecutor().submit(new Callable<LocalSocket>() {
            public LocalSocket call() throws Exception {
                var5[0] = new LocalSocket();
                var5[0].connect(new LocalSocketAddress(MicrophoneInputStream.this.SOCKET_ADDRESS));
                var5[0].getOutputStream().write(0);
                int var1 = var5[0].getInputStream().read(new byte[6400]);
                if (var1 == 6) {
                    var5[0].close();
                    throw new IOException("Recorder open failed");
                } else {
                    return var5[0];
                }
            }
        });

        try {
            this.socket = (LocalSocket)var7.get(23000L, TimeUnit.MILLISECONDS);
            this.source = var5[0].getInputStream();
        } catch (Exception var9) {
            if (var5[0] != null) {
                var5[0].close();
            }

            if (var3 != null) {
                var3.close();
            }

            throw new IOException(var9);
        }

        (new Thread() {
            public void run() {
                byte[] var1 = new byte[6400];

                try {
                    while(true) {
                        MicrophoneInputStream.this.socket.getInputStream().read(var1);
                    }
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }
        }).start();
    }

    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] var1, int var2, int var3) throws IOException {
        return this.source.read(var1, var2, var3);
    }

    public void close() throws IOException {
        super.close();
        if (this.source != null) {
            this.source.close();
        }

        if (this.socket != null) {
            this.socket.close();
        }

    }

    public long mills() {
        return 0L;
    }

    public void mills(long var1) {
    }
}

