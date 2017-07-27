package com.bitmap.bitmapconclusion.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 创建人：luying
 * 创建时间：2017/7/27
 * 类说明：
 * <p>
 * inJustDecodeBounds: true （boolean）时，表示解码时，只返回bitmap的宽高，并不会将bitmap加载到内存中
 * inSampleSize: 1、2、4、6、8（取值为2的为倍数） 比如当inSampleSize == 2 时，宽高为原来的1/2, 像素为原来的1/4,内存占用为原来的1/4. 结论:宽高缩放为1/inSampleSize, 像素和内存为(1/inSampleSize的二次方)
 * inPreferredConfig: 设置色彩模式,比如 ARGB, RGB等
 */

public class BitmapLoadUtil {

    public static Bitmap decodFromResource(Resources res, int resid, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resid, options);

        options.inSampleSize = calculateBitmapSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resid, options);
    }


    //计算Bitmap大小
    public static int calculateBitmapSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int width = options.outHeight;
        final int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
