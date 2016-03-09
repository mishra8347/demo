package com.mishra.mock.utilities;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

public class DeviceUtility
{

    public static int[] getDeviceHeightWidth(Context context)
    {
        int[] widthHeight = new int[2];
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
            widthHeight[0] = size.x;
            widthHeight[1] = size.y;
        } else {
            widthHeight[0] = display.getWidth();
            widthHeight[1] = display.getHeight();
        }
        return widthHeight;
    }
}
