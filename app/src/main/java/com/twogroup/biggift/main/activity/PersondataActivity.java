package com.twogroup.biggift.main.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.utils.ToRoundBitmap;

import java.io.File;
import java.io.FileOutputStream;

public class PersondataActivity extends BaseActivity {

    private ImageView tookphone;


    private RelativeLayout rl1, rl2, rl3;
    private ImageView img1, image;
    SharedPreferences sharedPreferences;
    private String name;
    private TextView img2;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 300;
    private static int output_Y = 300;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_persondata);

        image = (ImageView) findViewById(R.id.tookphone);
        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/song/xuanzhuan.png");
        //给图片切成圆角 传入bitmap返回bitmap
        ToRoundBitmap toRoundBitmap = new ToRoundBitmap();
        Bitmap bitmap1 = toRoundBitmap.toRoundBitmap(bitmap);
        image.setImageBitmap(bitmap1);


        sharedPreferences = getSharedPreferences("tuxiang", MODE_PRIVATE);


        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PersondataActivity.this);
                builder.setIcon(R.mipmap.ic_launch);
                builder.setTitle("提示");
                builder.setMessage("请选择你要进行的操作");
                //  第一个按钮
                builder.setPositiveButton("图库", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        choseHeadImageFromGallery();
                    }
                });
                //  中间的按钮
                builder.setNeutralButton("相机", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        choseHeadImageFromCameraCapture();
                    }
                });
                //  Diglog的显示
                builder.create().show();
            }
        });

    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }
        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;
            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            //给图片切成圆角 传入bitmap返回bitmap
            ToRoundBitmap toRoundBitmap = new ToRoundBitmap();
            Bitmap bitmap = toRoundBitmap.toRoundBitmap(photo);

            image.setImageBitmap(bitmap);
            Canvas canvas = new Canvas(bitmap);
            //加载要保存的画面
//            canvas.drawBitmap(photo, 10, 100, null);
            //保存全部图层
            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
            //存储路径
            File file = new File("/sdcard/song/");
            if (!file.exists())
                file.mkdirs();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getPath() + "/xuanzhuan.png");
                photo.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
                System.out.println("saveBmp is here");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }
}



