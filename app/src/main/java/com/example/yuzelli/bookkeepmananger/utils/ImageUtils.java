package com.example.yuzelli.bookkeepmananger.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/1/14.
 */

public class ImageUtils {
    // 图片转为文件
    public static boolean saveBitmap2file(Bitmap bmp) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
        int quality = 100;
        OutputStream stream = null;
        try {
            // 判断SDcard状态
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                // 错误提示
                return false;
            }
            // 检查SDcard空间
            File SDCardRoot = Environment.getExternalStorageDirectory();
            if (SDCardRoot.getFreeSpace() < 10000) {
                // 弹出对话框提示用户空间不够
                return false;
            }
            // 在SDcard创建文件夹及文件
            File bitmapFile = new File(SDCardRoot.getPath() + ConstantsUtils.AVATAR_FILE_PATH);
            bitmapFile.getParentFile().mkdirs();// 创建文件夹
            stream = new FileOutputStream(SDCardRoot.getPath() + ConstantsUtils.AVATAR_FILE_PATH);// "/sdcard/"

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmp.compress(format, quality, stream);
    }

}
