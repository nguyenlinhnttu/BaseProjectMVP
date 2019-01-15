package com.base.android.common;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.base.android.BuildConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

/**
 *     <uses-permission android:name="android.permission.CAMERA" />
 *    <uses-feature
 *         android:name="android.hardware.camera"
 *         android:required="true" />
 *     <uses-feature android:name="android.hardware.camera.autofocus" />
 */
public class IntentHelper {

    private final static int CAMERA_CODE = 1;
    private final static int GALLERY_CODE = 2;


    public static void chooseFileIntent(Activity activity, int type, File imgFile, boolean isMultiSelect) {
        try {
            switch (type) {
                case CAMERA_CODE:
                    Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //you must setup two line below
                        takePhotoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        takePhotoIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    }
                    Uri filePath = Uri.fromFile(imgFile);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        filePath = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", imgFile);
                        activity.grantUriPermission("com.android.camera", filePath,
                                Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    }

                    takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, filePath);

                    if (takePhotoIntent.resolveActivity(activity.getPackageManager()) != null)
                        activity.startActivityForResult(takePhotoIntent, type);
                    break;
                case GALLERY_CODE:
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, isMultiSelect);
                    intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
                    if (intent.resolveActivity(activity.getPackageManager()) != null)
                        activity.startActivityForResult(intent, type);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPathFromUri(Context context, Uri uri) {
        String filePath = null;
        if (uri != null && "content".equals(uri.getScheme())) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                filePath = cursor.getString(0);
                cursor.close();
                return filePath;
            }
            return uri.getPath();
        }
        return filePath;
    }

    public static File getFileFromUri(Context context, Uri fileUri) {
        ContentResolver contentResolver = context.getContentResolver();
        File tmp = null;
        try {
            InputStream inputStream = contentResolver.openInputStream(fileUri);
            tmp = createImageFile("tmp", context);
            OutputStream os = new FileOutputStream(tmp.getAbsolutePath());

            byte[] buffer = new byte[1024];
            int bytesRead;
            //read from is to buffer
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            //flush OutputStream to write any buffered data to file
            os.flush();
            os.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tmp;
    }

    public static File createImageFile(String fileName, Context context) throws IOException {
        // Create an image file name
        if (fileName == null)
            fileName = "JPEG_cache";
        String imageFileName = fileName + "_" + new Date().getTime() / 1000;
        //File storageDir = mContext.getFilesDir();

        ContextWrapper cw = new ContextWrapper(context);

        //File storageDir = cw.getDir("image", Context.MODE_PRIVATE);
        File storageDir;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        else
            storageDir = Environment.getExternalStorageDirectory();
        if (storageDir == null)
            storageDir = cw.getDir("image", Context.MODE_PRIVATE);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }

    public static boolean deleteFile(File f) {
        try {
            if (f.exists()) {
                if (f.delete())
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
