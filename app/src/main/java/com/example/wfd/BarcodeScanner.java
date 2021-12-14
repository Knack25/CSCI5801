package com.example.wfd;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;

import com.example.wfd.pantry.Add_Ingredient_Popup;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

public class BarcodeScanner extends CameraActivity {

    String rawValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rawValue = null;

    }






     String scanBarcodes(InputImage image) {
         BarcodeScannerOptions options =
                new BarcodeScannerOptions.Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_UPC_A,
                                Barcode.FORMAT_UPC_E
                        )
                        .build();
        com.google.mlkit.vision.barcode.BarcodeScanner scanner = BarcodeScanning.getClient();

        Task<List<Barcode>> result = scanner.process(image)
                .addOnSuccessListener(barcodes -> {
                    // Task completed successfully
                    // [START_EXCLUDE]
                    // [START get_barcodes]
                    for (Barcode barcode: barcodes) {
                        rawValue = barcode.getRawValue();   //on successfully reading a barcode, we get the raw value (UPC value for now).
                        Log.d("BarcodeDEBUG", "raw value scanned");
                    }

                    // [END get_barcodes]
                    // [END_EXCLUDE]
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        // It's gonna fail a lot so keep this empty unless you have a plan
                    }
                });

            if(rawValue != null) return rawValue;
            else return "Fail";
              //returns null on failure, a UPC on success.

    }


//end of class
}
