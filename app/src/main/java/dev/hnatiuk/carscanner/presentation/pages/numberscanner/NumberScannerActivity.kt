package dev.hnatiuk.carscanner.presentation.pages.numberscanner

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.SparseArray
import android.view.SurfaceHolder
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.app.ActivityCompat
import androidx.core.util.forEach
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import dev.hnatiuk.carscanner.domain.entity.isCorrectAsNumber
import com.sectumsempra.carinfo.presentation.pages.numberscanner.NumberScannerActivityViewModel
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.databinding.ActivityNumberScannerBinding
import dev.hnatiuk.carscanner.presentation.extensions.setFullScreen
import dev.hnatiuk.carscanner.presentation.pages.base.BaseToolbarActivity
import dev.hnatiuk.carscanner.presentation.pages.base.Depends
import org.jetbrains.anko.displayMetrics

@Depends(R.layout.activity_number_scanner, NumberScannerActivityViewModel::class)
internal class NumberScannerActivity :
    BaseToolbarActivity<ActivityNumberScannerBinding, NumberScannerActivityViewModel>() {

    companion object {
        private const val CAR_NUMBER = "CAR_NUMBER"
    }

    private lateinit var cameraSource: CameraSource
    private lateinit var textRecognizer: TextRecognizer

    override val backButtonIconRes = R.drawable.ic_back_white

    override fun ActivityNumberScannerBinding.initUI() {
        viewModel = this@NumberScannerActivity.viewModel
        setFullScreen()

        buildScannerTools()
        cameraView.holder.addCallback(SurfaceCameraCallback())
        textRecognizer.setProcessor(TextDetectionListener())
    }

    private fun buildScannerTools() {
        textRecognizer = TextRecognizer.Builder(this).build()

        cameraSource = CameraSource.Builder(this, textRecognizer)
            .setRequestedPreviewSize(displayMetrics.heightPixels, displayMetrics.widthPixels)
            .setAutoFocusEnabled(true)
            .setRequestedFps(60f)
            .build()
    }

    private inner class TextDetectionListener : Detector.Processor<TextBlock> {
        override fun release() {}

        override fun receiveDetections(detection: Detector.Detections<TextBlock>?) {
            //val text = detection?.detectedItems?.values()?.joinToString() ?: "empty result"
            //Log.i("textRecognition", text)

            detection?.detectedItems?.forEach { _, textBlock ->
                //Log.i("textRecognition", "${textBlock.value} - ${textBlock.value.isCorrectAsNumber()}")
                if (textBlock.value.isCorrectAsNumber()) {
                    finishWithResult(textBlock.value.replace("\\s".toRegex(), ""))
                    textRecognizer.setProcessor(null)
                }
            }
        }
    }

    private fun finishWithResult(result: String) {
        val intent = Intent().apply { putExtra(CAR_NUMBER, result) }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private inner class SurfaceCameraCallback : SurfaceHolder.Callback {
        @SuppressLint("MissingPermission")
        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            if (ActivityCompat.checkSelfPermission(
                    this@NumberScannerActivity,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            cameraSource.start(binding.cameraView.holder)
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {}

        override fun surfaceCreated(holder: SurfaceHolder) {
            cameraSource.stop()
        }
    }

    fun SparseArray<TextBlock?>.values(): List<String> {
        val list = ArrayList<String>()
        forEach { _, value ->
            list.add(value?.value ?: "empty")
        }
        return list.toList()
    }

    class GetNumber : ActivityResultContract<Unit, String?>() {

        override fun createIntent(context: Context, input: Unit): Intent =
            Intent(context, NumberScannerActivity::class.java)

        override fun parseResult(resultCode: Int, intent: Intent?): String? {
            val number = intent?.getStringExtra(CAR_NUMBER)
            return number.takeIf { it != null && resultCode == Activity.RESULT_OK }
        }
    }
}