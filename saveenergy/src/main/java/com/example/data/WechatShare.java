package com.example.data;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;

import com.example.mUtil.Util;
import com.example.saveenergy.EnergyCalculateActivity;
import com.example.saveenergy.R;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by mc on 16-4-16.
 */
public class WechatShare {

    private static final int THUMB_SIZE = 150;
    private static IWXAPI api =null;
    private static final String APP_ID="wx4ad0828bd3632856";
    private static Context mContext;




    public static void shareWithPhoto(Context context,Bitmap bitmap){
        mContext=context;
        if(api==null){
            api= WXAPIFactory.createWXAPI(mContext, APP_ID, true);
            api.registerApp(APP_ID);
        }
        Bitmap bmp = bitmap;
        WXImageObject imageObject = new WXImageObject(bmp);
        WXMediaMessage msg =new WXMediaMessage();
        msg.mediaObject=imageObject;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp,THUMB_SIZE,THUMB_SIZE,true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
        msg.title="abc-title";
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "img"+String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);

    }


    public static Bitmap myShot(Activity activity) {
        // 获取windows中最顶层的view
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();

        // 获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeights = rect.top;
        Display display = activity.getWindowManager().getDefaultDisplay();

        // 获取屏幕宽和高
        int widths = display.getWidth();
        int heights = display.getHeight();

        // 允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);

        // 去掉状态栏
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
                statusBarHeights, widths, heights - statusBarHeights);

        // 销毁缓存信息
        view.destroyDrawingCache();

        return bmp;
    }
}
