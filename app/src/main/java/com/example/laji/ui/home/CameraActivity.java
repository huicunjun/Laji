package com.example.laji.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.CameraView;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.example.laji.R;
import com.example.laji.ui.BHttp;
import com.example.laji.ui.Config;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;

import static java.lang.Thread.sleep;

public class CameraActivity extends AppCompatActivity {
    private static final String TAG = "TTTTTTTTTTTTTTTTTTTT";
    String mBackCameraId;
    CameraCharacteristics mBackCameraCharacteristics;
    String mFrontCameraId;
    CameraCharacteristics mFrontCameraCharacteristics;

    String mCameraId;
    TextureView mTextureView;
    CameraManager mCameraManager;
    int iii = 1;

    JCameraView jCameraView;
    CameraView ccc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mTextureView = findViewById(R.id.surface);

//        try {
//            getCameraInfo();
//            openCamera(400, 800);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }

        jCameraView = (JCameraView) findViewById(R.id.jcameraview);
        ccc = findViewById(R.id.ccc);

        initXX();

//        initJeptCXXX();
        initJeptCXXX11();
//        mSwitchCamera.setVisibility(INVISIBLE);
//        mFlashLamp.setVisibility(INVISIBLE);
//        machine.capture();

        try {
//            Field machine = jCameraView.getClass().getDeclaredField("machine");
//            machine.setAccessible(true);
        } catch (Exception e) {

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    //   jCameraView.confirmState(JCameraView.TYPE_PICTURE);
//                                    ProgressDialog progressDialog = new ProgressDialog(context);
//                                    progressDialog.setTitle("抓拍第" + iii + "次...");
//                                    progressDialog.show();
                                    View lay = LayoutInflater.from(context).inflate(R.layout.sdsadas, null, false);
//                                    setImg2(lay.findViewById(R.id.iv1));
                                    jCameraView.setImg2(lay.findViewById(R.id.iv1));
                                    BHttp.setModeImage(lay.findViewById(R.id.iv2));
                                    AlertDialog progressDialog = new AlertDialog.Builder(context)
//                                            .setTitle("处理中...")
                                            .setView(lay)
                                            .show();
                                    iii++;


                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                sleep(4000);
                                                runOnUiThread(() -> {
                                                    try {
                                                        Toast.makeText(context, "处理完成", Toast.LENGTH_SHORT).show();
                                                        progressDialog.dismiss();
                                                    } catch (Exception e) {
                                                    }
                                                });
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }).start();
//                                AlertDialog show = new AlertDialog.Builder(getApplicationContext()).show();
//                                show.setTitle();

                                } catch (Exception e) {
                                }
                            }

                            private void setImg2(ImageView tempView) {
                                try {

                                    int childCount = jCameraView.getChildCount();
                                    for (int i = 0; i < childCount; i++) {
                                        View childAt = jCameraView.getChildAt(i);
                                        if (childAt.callOnClick()) {

                                        }
                                        System.out.println(childAt+"          VVVVVVVVVVVVVVV");
                                    }
//                                    FrameLayout viewById = findViewById(R.id.root);
//                                    Bitmap b = Bitmap.createBitmap(200 , 200, Bitmap.Config.ARGB_8888);
//                                    Canvas c = new Canvas(b);
//                                    viewById.layout(0, 0, viewById.getLayoutParams().width, viewById.getLayoutParams().height);
//                                    viewById.draw(c);
//                                    viewById.setDrawingCacheEnabled(true);
//                                    viewById.buildDrawingCache();
//                                    Bitmap bitmap = viewById.getDrawingCache();
//                                    tempView.setImageBitmap(bitmap);
                                } catch (Exception e) {

                                }
                            }
                        });
                        sleep(Config.TIME * 1000);
                    } catch (Exception e) {

                    }
                }
            }
        }).start();
    }

    private void initJeptCXXX11() {
    }

    private void initJeptCXXX() {
        imageCapture = new ImageCapture.Builder()
//                .setTargetRotation(ccc.getDisplay().getRotation())
                .build();
        ListenableFuture<ProcessCameraProvider> lifecycleOwner = ProcessCameraProvider.getInstance(context);
        lifecycleOwner.addListener(new Runnable() {
            @Override
            public void run() {

                try {
                    ProcessCameraProvider cameraProvider = lifecycleOwner.get();
                    Preview preview = new Preview.Builder()
                            .build();
                    CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
                    cameraProvider.unbindAll();

                    cameraProvider.bindToLifecycle(context, cameraSelector, preview);
//                    preview ?.setSurfaceProvider(viewFinder.createSurfaceProvider())
                } catch (Exception e) {

                }
            }
        }, ContextCompat.getMainExecutor(context));

    }

    public void takePhoto() {
        File photoFile = new File("/t" + System.currentTimeMillis() + ".jpg");
        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(photoFile).build();
        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                Uri uri = Uri.fromFile(photoFile);
                Toast.makeText(context, uri.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, uri.toString());
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Log.e(TAG, "Photo capture failed: ${exc.message}", exception);
            }
        });

    }

    ImageCapture imageCapture;
    CameraActivity context = CameraActivity.this;

    private void initXX() {

//设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
//设置只能录像或只能拍照或两种都可以（默认两种都可以）
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);

//设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
//JCameraView监听
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                //打开Camera失败回调
                Log.i("CJT", "open camera error");
            }

            @Override
            public void AudioPermissionError() {
                //没有录取权限回调
                Log.i("CJT", "AudioPermissionError");
            }
        });


        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取图片bitmap
                try {
                    Log.i("JCameraView", "bitmap = " + bitmap.getWidth());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                try {
                    Log.i("CJT", "url = " + url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //左边按钮点击事件
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                CameraActivity.this.finish();
            }
        });

        jCameraView.setRightClickListener(new ClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(CameraActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }

//
//    private void getCameraInfo() {
//        try {
//
//            //获取相机服务 CameraManager
//            mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//
//            //遍历设备支持的相机 ID ，比如前置，后置等
//            String[] cameraIdList = mCameraManager.getCameraIdList();
//            for (String cameraId : cameraIdList) {
//                // 拿到装在所有相机信息的  CameraCharacteristics 类
//                CameraCharacteristics characteristics = mCameraManager.getCameraCharacteristics(cameraId);
//                //拿到相机的方向，前置，后置，外置
//                Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
//                if (facing != null) {
//                    //后置摄像头
//                    if (facing == CameraCharacteristics.LENS_FACING_BACK) {
//                        mBackCameraId = cameraId;
//                        mBackCameraCharacteristics = characteristics;
//                    } else if (facing == CameraCharacteristics.LENS_FACING_FRONT) {
//                        //前置摄像头
//                        mFrontCameraId = cameraId;
//                        mFrontCameraCharacteristics = characteristics;
//                    }
//                    mCameraId = cameraId;
//                }
//
//                //是否支持 Camera2 的高级特性
//                Integer level = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
//                /**
//                 * 不支持 Camera2 的特性
//                 */
//                if (level == null || level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
//                    //  Toast.makeText(this, "您的手机不支持Camera2的高级特效", Toast.LENGTH_SHORT).show();
//                    //   break;
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    Integer mSensorOrientation;
//    ImageReader mImageReader;
//
//    private void openCamera(int width, int height) {
//
//
//        try {
//            //判断不同摄像头，拿到 CameraCharacteristics
//            CameraCharacteristics characteristics = mCameraId.equals(mBackCameraId) ? mBackCameraCharacteristics : mFrontCameraCharacteristics;
//            //拿到配置的map
//            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
//
//            //获取摄像头传感器的方向
//            mSensorOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
//            //获取预览尺寸
//            Size[] previewSizes = map.getOutputSizes(SurfaceTexture.class);
//            //获取最佳尺寸
////        Size bestSize = getBestSize(width, height, previewSizes);
//            /**
//             * 配置预览属性
//             * 与 Cmaera1 不同的是，Camera2 是把尺寸信息给到 Surface (SurfaceView 或者 ImageReader)，
//             * Camera2 会根据 Surface 配置的大小，输出对应尺寸的画面;
//             * 注意摄像头的 width > height ，而我们使用竖屏，所以宽高要变化一下
//             */
////        mTextureView.getSurfaceTexture().setDefaultBufferSize(bestSize.getHeight(), bestSize.getWidth());
////            mTextureView.getSurfaceTexture().setDefaultBufferSize(100, 200);
//
//            /**
//             * 设置图片尺寸，这里图片的话，选择最大的分辨率即可
//             */
//            Size[] sizes = map.getOutputSizes(ImageFormat.JPEG);
//            Size largest = Collections.max(
//                    Arrays.asList(sizes),
//                    new Comparator<Size>() {
//                        @Override
//                        public int compare(Size o1, Size o2) {
//                            return o1.getHeight() - o2.getHeight();
//                        }
//                    });
//            //设置imagereader，配置大小，且最大Image为 1，因为是 JPEG
//            mImageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(),
//                    ImageFormat.JPEG, 1);
//
//            //拍照监听
//            mImageReader.setOnImageAvailableListener(new MyOnImageAvailableListener(), null);
//            //打开摄像头，监听数据
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            mCameraManager.openCamera(mCameraId, new CameraDeviceCallback(), null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static class MyOnImageAvailableListener implements ImageReader.OnImageAvailableListener {
//
//        @Override
//        public void onImageAvailable(ImageReader reader) {
//
//        }
//    }
//
//    public static class CameraDeviceCallback extends CameraDevice.StateCallback {
//
//        @Override
//        public void onOpened(@NonNull CameraDevice camera) {
//
//        }
//
//        @Override
//        public void onDisconnected(@NonNull CameraDevice camera) {
//
//        }
//
//        @Override
//        public void onError(@NonNull CameraDevice camera, int error) {
//
//        }
//    }
}