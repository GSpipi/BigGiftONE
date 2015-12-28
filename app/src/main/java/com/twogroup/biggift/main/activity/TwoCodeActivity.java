package com.twogroup.biggift.main.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.activity.zxing.ZXingScannerViewNew;

import java.util.ArrayList;
import java.util.List;

public class TwoCodeActivity extends BaseActivity implements ZXingScannerViewNew.ResultHandler, ZXingScannerViewNew.QrSize, View.OnClickListener {



        ZXingScannerViewNew scanView;
        private TextView result;
        private ImageView imageView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            scanView = new ZXingScannerViewNew(this);
            scanView.setContentView(R.layout.activity_two_code);

            scanView.setQrSize(this);
            setContentView(scanView);
            setupFormats();
            initUI();
        }

        private void initUI() {
            findViewById(R.id.confirm).setOnClickListener(this);
            result= (TextView) findViewById(R.id.editText);
            imageView  = (ImageView) findViewById(R.id.saoyisao_iv_fanhui_special_two);
            imageView.setOnClickListener(this);
        }

        @Override
        protected void onResume() {
            super.onResume();
            scanView.setResultHandler(this);
            scanView.startCamera(-1);
            scanView.setFlash(false);
            scanView.setAutoFocus(true);
        }

        @Override
        public void handleResult(Result rawResult) {
            result.setText(rawResult.toString());
        }

        @Override
        protected void onPause() {
            super.onPause();
            scanView.stopCamera();
        }

        public void setupFormats() {
            List<BarcodeFormat> formats = new ArrayList<BarcodeFormat>();
            formats.add(BarcodeFormat.QR_CODE);
            if (scanView != null) {
                scanView.setFormats(formats);
            }
        }

        @Override
        public Rect getDetectRect() {
            View view = findViewById(R.id.scan_window);
            int top = ((View) view.getParent()).getTop() + view.getTop();
            int left = view.getLeft();
            int width = view.getWidth();
            int height = view.getHeight();
            Rect rect = null;
            if (width != 0 && height != 0) {
                rect = new Rect(left, top, left + width, top + height);
                addLineAnim(rect);
            }
            return rect;
        }

        private void addLineAnim(Rect rect) {
            ImageView imageView = (ImageView) findViewById(R.id.scanner_line);
            imageView.setVisibility(View.VISIBLE);
            if (imageView.getAnimation() == null) {
                TranslateAnimation anim = new TranslateAnimation(0, 0, 0, rect.height());
                anim.setDuration(1500);
                anim.setRepeatCount(Animation.INFINITE);
                imageView.startAnimation(anim);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.confirm:
                    //TODO something
                    String URL = result.getText().toString().trim();
                    if (!TextUtils.isEmpty(URL)){
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(URL);
                        intent.setData(content_url);
                        startActivity(intent);
                    }
                    break;
                case R.id.saoyisao_iv_fanhui_special_two:
                    finish();
                    break;
            }

        }
    }


