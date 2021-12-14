package com.example.wfd;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.Size;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.example.wfd.pantry.Add_Ingredient_Popup;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.common.InputImage;

import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CameraActivity extends AppCompatActivity {

    //barcode scanning and camera initialization stuff
    private static final int REQUEST_CODE_CAMERA_PERMISSION = 200;
    private static final String[] PERMISSIONS = {Manifest.permission.CAMERA};
    private MutableLiveData<ProcessCameraProvider> cameraProviderLiveData;
    Executor executor;
    private long mLastAnalysisResultTime;
    BarcodeScanner barcodeScanner;
   // TextView rawUPC;
    PreviewView view_finder;
    TextView fpsCounter;
    Intent intent;
    String ingName;
    String ingAmount;
    String ingType;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        // rawUPC = findViewById(R.id.upcRawText);
         fpsCounter = findViewById(R.id.fpsText);


         Intent intentTemp = getIntent();
          ingName = intentTemp.getStringExtra("NAME");
          ingAmount = intentTemp.getStringExtra("AMOUNT");
          ingType = intentTemp.getStringExtra("TYPE");
         intent = new Intent(this, Add_Ingredient_Popup.class);
         intent.putExtra("TYPE", ingType);
         intent.putExtra("AMOUNT", ingAmount);
         intent.putExtra("NAME", ingName);

        if(checkPermission()){
            startCamera();
        }
    }


    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE_CAMERA_PERMISSION);
            return false;
        }
        return true;
    }

    @SuppressLint("MissingSuperCall") //idk what original function did so i dont feel comfortable calling super
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(
                        this,
                        "You can't scan barcodes without granting CAMERA permission",
                        Toast.LENGTH_LONG)
                        .show();
                finish();
            } else {
                startCamera();
            }
        }
    }

    private void startCamera() {
        barcodeScanner = new BarcodeScanner();
        if (cameraProviderLiveData == null) {

            cameraProviderLiveData = new MutableLiveData<>();

            ListenableFuture<ProcessCameraProvider> cameraProviderFuture
                    = ProcessCameraProvider.getInstance(this);

            cameraProviderFuture.addListener(() -> {
                try {
                    cameraProviderLiveData.setValue(cameraProviderFuture.get());
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {

                    // This should never be reached.
                }
            }, ContextCompat.getMainExecutor(this));
        }

    }

    @SuppressLint({"UnsafeExperimentalUsageError", "UnsafeOptInUsageError"})    //the getImage() method is outdated but it still works
        //Unsafe to who?
    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {

        view_finder = findViewById(R.id.view_finder);

        Preview preview = new Preview.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .build();

        //Tells the program which camera to use
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        executor = Executors.newSingleThreadExecutor();

        //sets up analysis case. This is where we will get the image data to funnel into MLkit.
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setTargetResolution(new Size(1080, 1920))  //large resolution is required for a good analysis
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        //setting the analyzer lets us make our own analysis. This is where we analyze the image.
        imageAnalysis.setAnalyzer(executor, image -> {
            int rotationDegrees = image.getImageInfo().getRotationDegrees();


           String bcode = barcodeScanner.scanBarcodes(InputImage.fromMediaImage(image.getImage(), rotationDegrees));
            if(bcode != "Fail"){
                intent.putExtra("UPC", bcode);
                startActivity(intent);
            }

            //tries to scan for barcodes every frame




            Log.d("DEBUG", "img Analysis Block");      //for debugging

            if(SystemClock.elapsedRealtime() - mLastAnalysisResultTime < 500) {
                image.close();
                return;
            }

            //this little bit just shows the FPS on screen. I think this makes the image analysis take longer, which means MLkit has longer to identify the barcode
            runOnUiThread(() -> {

                long duration = SystemClock.elapsedRealtime() - mLastAnalysisResultTime;
                double fps;

                if(duration > 0)
                    fps = 60.f / duration;
                else
                    fps = 60.f;

                fpsCounter.setText(String.format(Locale.US, "%.1f fps", fps));

            });

            mLastAnalysisResultTime = SystemClock.elapsedRealtime();
            image.close();
        });
        if( barcodeScanner.rawValue != null) {
            Log.d("SUCCESS", "SUCCESS!!");
            intent = new Intent(this, Add_Ingredient_Popup.class);
            intent.putExtra("UPC", barcodeScanner.rawValue);
            startActivity(intent);
        }

        cameraProvider.unbindAll();
        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this,
                cameraSelector, imageAnalysis, preview);

        view_finder = findViewById(R.id.view_finder);
        preview.setSurfaceProvider(view_finder.getSurfaceProvider());

    }

}
