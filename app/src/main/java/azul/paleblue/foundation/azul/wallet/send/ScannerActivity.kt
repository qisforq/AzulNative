package azul.paleblue.foundation.azul.wallet.send

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.zxing.Result
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity: Activity(), ZXingScannerView.ResultHandler, AnkoLogger {
  private var mScannerView: ZXingScannerView? = null

  public override fun onCreate(state: Bundle?) {
    super.onCreate(state)
    mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
    setContentView(mScannerView)                // Set the scanner view as the content view
  }

  public override fun onResume() {
    super.onResume()
    mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
    mScannerView!!.startCamera()          // Start camera on resume
  }

  public override fun onPause() {
    super.onPause()
    mScannerView!!.stopCamera()           // Stop camera on pause
  }

  override fun handleResult(rawResult: Result) {
    // Do something with the result here
    debug(rawResult.getText()) // Prints scan results
    debug(rawResult.getBarcodeFormat().toString()) // Prints the scan format (qrcode, pdf417 etc.)


    val intent = Intent()
    intent.putExtra("barcodeTextPayload", rawResult.text)
    intent.putExtra("barcodeFormat", rawResult.barcodeFormat.toString())

    setResult(1, intent)
    finish()
    // If you would like to resume scanning, call this method below:
    //mScannerView!!.resumeCameraPreview(this)
  }
}
