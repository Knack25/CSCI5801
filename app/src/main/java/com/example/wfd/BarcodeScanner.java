package com.example.wfd;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

public class BarcodeScanner extends CameraActivity {

    String rawValue;



     String scanBarcodes(InputImage image) {

         rawValue = null; //set to null initially at beginning of every scan
         // [START set_detector_options]

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
                        if(rawValue != null){
                            Intent intent = new Intent(this, MainActivity.class);
                            intent.putExtra("UPC", rawValue);
                            startActivity(intent);
                        }


//                        System.out.println("raw value = " + rawValue);  //debugging line




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


            return rawValue;       //returns null on failure, a UPC on success.

    }


//end of class
}
