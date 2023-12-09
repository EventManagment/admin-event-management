package com.admin.admineventmanagement.dashboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.admin.admineventmanagement.databinding.FragmentScanBinding
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.ExecutorService

/**
 * A simple [Fragment] subclass.
 * Use the [ScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScanFragment : Fragment() {
//    companion object {
//        private val TAG = ScanFragment::class.simpleName
//        private var onScan: ((barcodes: List<Barcode>) -> Unit)? = null
//        fun startScanner(context: Context) {
//
//        }
//    }

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraSelector: CameraSelector
    private lateinit var processCameraProvider: ProcessCameraProvider
    private lateinit var cameraPreview: Preview
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeScanner: BarcodeScanner

    companion object {
        const val CAMERA_PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScanBinding.inflate(inflater, container, false)


//        cameraSelector = cameraSelector.Builder()
//            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//            .build()
//        cameraSelector = CameraSelector.Builder()
//            .requireLensFacing(LENS_FACING_BACK)
//            .build()

//        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
//        cameraProviderFuture.addListener(
//            {
//                processCameraProvider = cameraProviderFuture.get()
//                bindCameraPreview()
//            },
//            ContextCompat.getMainExecutor(requireContext())
//        )
//        barcode scanner
//        val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient(
//            BarcodeScannerOptions.Builder()
//                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
//                .build()
//        )
//      image analyze
//        val imageAnalyze = ImageAnalysis.Builder()
//            .setTargetRotation(rotation)
//            .build()

        return binding.root
    }

//    private fun bindCameraPreview() {
//        cameraPreview = Preview.Builder().build()
//        cameraPreview.setSurfaceProvider(getSurfaceProvider())
//        processCameraProvider.bindToLifecycle(this, cameraSelector, cameraPreview)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize barcode scanner
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
//            .setZoomSuggestionOptions(
//                new ZoomSuggestionOptions.Builder(zoomCallback)
//                    .setMaxSupportedZoomRatio(maxSupportedZoomRatio)
//                    .build())
            .build()
        barcodeScanner = BarcodeScanning.getClient(options)

        // Check camera permission
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private val requestCameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCamera()
            } else {
                // Handle permission denied
            }
        }

    private fun startCamera() {
        // TODO: Initialize camera preview and start scanning
    }

    // Call this method to process the scanned QR code
    private fun processBarcodeResult(barcode: Barcode) {
        // TODO: Handle the scanned QR code data
        val qrCodeData = barcode.displayValue
        Log.d("QRScanFragment", "Scanned QR Code: $qrCodeData")

        // Navigate or perform further actions based on the scanned data
        // For example, you can use findNavController().navigate(...) to navigate to another fragment
    }

    // Call this method to start scanning using the camera
    private fun startScanning(image: InputImage) {
        barcodeScanner.process(image)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    processBarcodeResult(barcode)
                }
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
        _binding = null
    }
}