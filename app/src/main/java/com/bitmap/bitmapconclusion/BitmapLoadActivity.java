package com.bitmap.bitmapconclusion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitmap.bitmapconclusion.util.BitmapLoadUtil;

/**
 * 创建人：luying
 * 创建时间：2017/7/27
 * 类说明：图片高效加载
 */

public class BitmapLoadActivity extends AppCompatActivity{
    private ImageView actualImage, halfSizeActualImage, halfSizeChangeImage;
    private TextView actualMemory, halfSizeActualMemory, halfSizeChangeMemory;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_bitmap);
        actualImage = (ImageView) findViewById(R.id.image_actual_size);
        halfSizeActualImage = (ImageView) findViewById(R.id.image_actual_half_size);
        halfSizeChangeImage = (ImageView) findViewById(R.id.image_change_half_size);

        actualMemory = (TextView) findViewById(R.id.image_actual_memory);
        halfSizeActualMemory = (TextView) findViewById(R.id.image_actual_half_memory);
        halfSizeChangeMemory = (TextView) findViewById(R.id.image_change_half_memory);

        actualImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.test_image));
        halfSizeActualImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.test_image));
        halfSizeChangeImage.setImageBitmap(BitmapLoadUtil.decodFromResource(getResources(), R.mipmap.test_image, 120, 75));
        actualMemory.setText(calculateBitmapInfo(actualImage));
        halfSizeActualMemory.setText(calculateBitmapInfo(halfSizeActualImage));
        halfSizeChangeMemory.setText(calculateBitmapInfo(halfSizeChangeImage));
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String calculateBitmapInfo(ImageView imageView) {
                Drawable drawable = imageView.getDrawable();
                 if (drawable != null) {
                     BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                 Bitmap bitmap = bitmapDrawable.getBitmap();
                     return "w ="+bitmap.getWidth()+"  h"+bitmap.getHeight()+"  memory ="+ bitmap.getAllocationByteCount();
                 }
                 return "空";
             }
}
