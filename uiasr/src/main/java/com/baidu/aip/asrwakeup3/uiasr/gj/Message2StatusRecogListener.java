package com.baidu.aip.asrwakeup3.uiasr.gj;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.baidu.aip.asrwakeup3.core.recog.RecogResult;
import com.baidu.aip.asrwakeup3.uiasr.Dailog.Dialog1;
import com.baidu.aip.asrwakeup3.uiasr.service.callService;
import com.baidu.speech.asr.SpeechConstant;


/**
 * Created by fujiayi on 2017/6/16.
 * 业务逻辑监听
 */

public class Message2StatusRecogListener extends dguaback {
    private Handler handler;

    private long speechEndTime = 0;

    private boolean needTime = true;

    private static final String TAG = "MesStatusRecogListener";
    public Context aa;

    public Message2StatusRecogListener(Handler handler, Context aaa) {
        this.handler = handler;
        aa=aaa;
    }


    @Override
    public void onAsrReady() {
        super.onAsrReady();
        speechEndTime = 0;
        sendStatusMessage(SpeechConstant.CALLBACK_EVENT_WAKEUP_READY, "引擎就绪，可以开始说话。");
    }

    @Override
    public void onAsrBegin() {
        super.onAsrBegin();
        //sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_BEGIN, "检测到用户说话");
    }

    @Override
    public void onAsrEnd() {
        super.onAsrEnd();
        speechEndTime = System.currentTimeMillis();
       // sendMessage("【asr.end事件】检测到用户说话结束");
    }

    @Override
    public void onAsrPartialResult(String[] results, RecogResult recogResult) {
        //sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL,"临时识别结果，结果是“" + results[0] + "”；原始json：" + recogResult.getOrigalJson());
        Message msg = Message.obtain();
        if(recogResult.getOrigalJson().length()>=3) {
            msg.what = 121;
            msg.obj = recogResult.getOrigalJson();
            callService.handler.sendMessage(msg);
        }
        super.onAsrPartialResult(results, recogResult);
    }

    @Override
    public void onAsrFinalResult(String[] results, RecogResult recogResult) {
        super.onAsrFinalResult(results, recogResult);
       /* String message = "识别结束，结果是" + results[0] + "”";
        sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL,
                message + "；原始json：" + recogResult.getOrigalJson());
        if (speechEndTime > 0) {
            long currentTime = System.currentTimeMillis();
            long diffTime = currentTime - speechEndTime;
            message += "；说话结束到识别结束耗时【" + diffTime + "ms】" + currentTime;

        }
        speechEndTime = 0;**/
       // sendMessage(message, status, true);
       // final String params1 = recogResult.getbest_result();
        /*Message msg = Message.obtain();
        msg.what = 100001;
        msg.obj = recogResult.getbest_result();
        callService.handler.sendMessage(msg);*/

        Message msg = Message.obtain();
        msg.what = 1000;
        msg.obj = recogResult.getbest_result();
        Dialog1.handler.sendMessage(msg);


        //sendMessage(message,status,true);
    }

    @Override
    public void onAsrFinishError(int errorCode, int subErrorCode, String descMessage,
                                 RecogResult recogResult) {
        super.onAsrFinishError(errorCode, subErrorCode, descMessage, recogResult);
        String message = "【asr.finish事件】识别错误, 错误码：" + errorCode + " ," + subErrorCode + " ; " + descMessage;
        sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL, message);
        if (speechEndTime > 0) {
            long diffTime = System.currentTimeMillis() - speechEndTime;
            message += "。说话结束到识别结束耗时【" + diffTime + "ms】";
        }
        speechEndTime = 0;
        sendMessage(message, status, true);
        speechEndTime = 0;
    }

    @Override
    public void onAsrOnlineNluResult(String nluResult) {
        super.onAsrOnlineNluResult(nluResult);
        if (!nluResult.isEmpty()) {
            sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL, "原始语义识别结果json：" + nluResult);
        }
    }

    @Override
    public void onAsrFinish(RecogResult recogResult) {
        super.onAsrFinish(recogResult);
        //sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_FINISH, "识别一段话结束。如果是长语音的情况会继续识别下段话。");

    }

    /**
     * 长语音识别结束
     */
    @Override
    public void onAsrLongFinish() {
        super.onAsrLongFinish();
        sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_LONG_SPEECH, "长语音识别结束。");
    }


    /**
     * 使用离线命令词时，有该回调说明离线语法资源加载成功
     */
    @Override
    public void onOfflineLoaded() {
       // sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_LOADED, "离线资源加载成功。没有此回调可能离线语法功能不能使用。");
    }

    /**
     * 使用离线命令词时，有该回调说明离线语法资源加载成功
     */
    @Override
    public void onOfflineUnLoaded() {
        sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_UNLOADED, "离线资源卸载成功。");
    }

    @Override
    public void onAsrExit() {
        super.onAsrExit();
        sendStatusMessage(SpeechConstant.CALLBACK_EVENT_ASR_EXIT, "识别引擎结束并空闲中");
    }

    private void sendStatusMessage(String eventName, String message) {
        message = "[" + eventName + "]" + message;
        sendMessage(message, status);
    }

    private void sendMessage(String message) {
        sendMessage(message, WHAT_MESSAGE_STATUS);
    }

    private void sendMessage(String message, int what) {
        sendMessage(message, what, false);
    }


    private void sendMessage(String message, int what, boolean highlight) {


        if (needTime && what != STATUS_FINISHED) {
            message += "  ;time=" + System.currentTimeMillis();
        }
        if (handler == null) {
            Log.i(TAG, message);
            return;
        }
        Message msg = Message.obtain();
        msg.what = what;
        msg.arg1 = status;
        if (highlight) {
            msg.arg2 = 1;
        }
        msg.obj = message + "\n";
        handler.sendMessage(msg);
    }
}
