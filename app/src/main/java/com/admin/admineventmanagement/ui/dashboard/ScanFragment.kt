package com.admin.admineventmanagement.ui.dashboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.admin.admineventmanagement.R
import com.admin.admineventmanagement.databinding.FragmentScanBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * A simple [Fragment] subclass.
 * Use the [ScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScanFragment : Fragment() {
    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeScanner: BarcodeScanner
    private lateinit var camera: Camera
    private lateinit var qrImage: PreviewView

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
        return binding.root
    }

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

        // Initialize camera executor
        cameraExecutor = Executors.newSingleThreadExecutor()

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
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.error))
                    .setMessage(getString(R.string.camera_permission))
                    .setPositiveButton(R.string.ok) { dialog, _ ->
                        // START THE GAME!
                        dialog.cancel()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ ->
                        // User cancelled the dialog.
                        dialog.cancel()
                    }
                    .show()
            }
        }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        Log.d("QRScanFragment", "Scanned QR Code")

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            qrImage = requireView().findViewById(R.id.qr_image)

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(qrImage.surfaceProvider)
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            // Set up the barcodeScanner
            val imageAnalysis = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, { imageProxy ->
                        val mediaImage = imageProxy.image
                        if (mediaImage != null) {
                            val inputImage = InputImage.fromMediaImage(
                                mediaImage,
                                imageProxy.imageInfo.rotationDegrees
                            )
                            startScanning(inputImage)
                        }
                        imageProxy.close()
                    })
                }

            camera = cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                imageAnalysis
            )

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    // Call this method to process the scanned QR code
    private fun processBarcodeResult(barcode: Barcode) {
        // TODO: Handle the scanned QR code data
        val qrCodeData = barcode.displayValue
        binding.result.text = qrCodeData
        // Navigate or perform further actions based on the scanned data
        // For example, you can use findNavController().navigate(...) to navigate to another fragment
//        findNavController().navigate(R.id.homeFragment)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.success))
            .setMessage(getString(R.string.scan_success, qrCodeData))
            .setPositiveButton(R.string.ok) { dialog, _ ->
                        // START THE GAME!
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ ->
                        // User cancelled the dialog.
                        dialog.cancel()
                    }
                    .show()
    }

    // Call this method to start scanning using the camera
    private fun startScanning(image: InputImage) {
        barcodeScanner.process(image)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    processBarcodeResult(barcode)
//                    val bounds = barcode.boundingBox
//                    val corners = barcode.cornerPoints
//
//                    val rawValue = barcode.rawValue
//
//                    val valueType = barcode.valueType
//                    // See API reference for complete list of supported types
//                    when (valueType) {
//                        Barcode.TYPE_WIFI -> {
//                            val ssid = barcode.wifi!!.ssid
//                            val password = barcode.wifi!!.password
//                            val type = barcode.wifi!!.encryptionType
//                        }
//                        Barcode.TYPE_URL -> {
//                            val title = barcode.url!!.title
//                            val url = barcode.url!!.url
//                        }
//                        else -> {
//                            processBarcodeResult(barcode)
//                        }
//                    }
                }
            }
            .addOnFailureListener {
                Log.d("QRScanFragment", "Scanned QR Code: $it")
                // Handle failure
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
        _binding = null
    }
}