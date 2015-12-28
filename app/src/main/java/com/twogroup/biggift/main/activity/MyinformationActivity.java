package com.twogroup.biggift.main.activity;

import android.app.AlertDialog;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.utils.ToRoundBitmap;

import java.io.File;
import java.io.FileOutputStream;

public class MyinformationActivity extends BaseActivity {

    private Button tuichu;
    private SharedPreferences.Editor editor;
    private TextView morenmingzi;
    private SharedPreferences my1SharedPreferences;
    private SharedPreferences.Editor editor1;
    private AlertDialog.Builder builder;
    private ImageView changeImage;

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
        setContentView(R.layout.activity_myinformation);
        changeImage = ((ImageView) findViewById(R.id.changeImage));
        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/bigGift/tuxiang.jpg");
        //给图片切成圆角 传入bitmap返回bitmap
        ToRoundBitmap toRoundBitmap1 = new ToRoundBitmap();
        Bitmap bitmap1 = toRoundBitmap1.toRoundBitmap(bitmap);
        changeImage.setImageBitmap(bitmap1);
        initView();

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/bigGift/tuxiang.jpg");
                //给图片切成圆角 传入bitmap返回bitmap
                ToRoundBitmap toRoundBitmap1 = new ToRoundBitmap();
                Bitmap bitmap1 = toRoundBitmap1.toRoundBitmap(bitmap);
                changeImage.setImageBitmap(bitmap1);
                initchangeImage();
            }
        });


    }

    private void initchangeImage() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("更换图像")
                .setMessage("请选择路径").setIcon(R.mipmap.ic_more_action_check_update);
        builder.setNegativeButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//            Intent intent=new Intent(getBaseContext(),PersondataActivity.class);
//                startActivity(intent);
                choseHeadImageFromCameraCapture();


            }
        });
        builder.setPositiveButton("相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intentFromGallery = new Intent();
                // 设置文件类型
                intentFromGallery.setType("image/*");
                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
            }
        });

        builder.show();
    }

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

            changeImage.setImageBitmap(bitmap);
            Canvas canvas = new Canvas(bitmap);
            //加载要保存的画面
//            canvas.drawBitmap(photo, 10, 100, null);
            //保存全部图层
            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
            //存储路径/sdcard/bigGift/tuxiang.jpg
            File file = new File("/sdcard/bigGift");
            if (!file.exists())
                file.mkdirs();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getPath() + "/tuxiang.jpg");
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



    private void initView() {

        my1SharedPreferences = getSharedPreferences("moren",
                MODE_PRIVATE);
        editor1 = my1SharedPreferences.edit();
        //用putBoolean的方法保存数据
        editor1.putString("morenming", "老子不想弄自定义");
        //提交当前数据
        editor1.apply();

        SharedPreferences mySharedPreferences = getSharedPreferences("flag",
                MODE_PRIVATE);
        editor = mySharedPreferences.edit();

        morenmingzi = ((TextView) findViewById(R.id.morenmingzi));
        morenmingzi.setText("老子不想弄自定义");
        tuichu = (Button) findViewById(R.id.tuichudenglu);


        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morenmingzi.setText("未命名");
                Intent intent = new Intent(MyinformationActivity.this, LoginActivity.class);
                startActivity(intent);
                editor.putBoolean("islogin", false);
                //提交当前数据
                Log.i("--3-", "===========");
                editor.apply();
                finish();
            }
        });
        ImageView iv1 = (ImageView) findViewById(R.id.fanhui);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




}
