package com.example.ps.mvvm;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.ps.mvvm.view.MainActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Commen {

    @Mock
    Context context;
    @Mock
    Drawable drawable;
    @Mock
    MainActivity mainActivity;
    @Mock
    Resources resources;
    @Mock
    Uri uri;
    @Mock
    ContentResolver contentResolver;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(context.getContentResolver()).thenReturn(contentResolver);
        when(context.getResources()).thenReturn(resources);
        when(context.getResources().getDrawable(anyInt())).thenReturn(drawable);
    }

    @Test
    public void test(){
        getBitmapFromVectorDrawable(context, drawable);
        decodeUriToBitmap(context, uri);
    }

    public Commen() {

    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, Drawable drawableId) {
        Drawable drawable = drawableId;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static Bitmap decodeUriToBitmap(Context mContext, Uri sendUri) {
        Bitmap getBitmap = null;
        try {
            InputStream image_stream;
            try {
                image_stream = mContext.getContentResolver().openInputStream(sendUri);
                getBitmap = BitmapFactory.decodeStream(image_stream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getBitmap;
    }

    public static String changeDurationFormat(long duration) {
        int seconds = (int) (duration / 1000);
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format(Locale.ENGLISH, "%02d", minutes) + ":" + String.format(Locale.ENGLISH, "%02d", seconds);
    }

}
