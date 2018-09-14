package azul.paleblue.foundation.azul.wallet.send

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import azul.paleblue.foundation.azul.R
import com.sandro.bitcoinpaymenturi.BitcoinPaymentURI
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick

class SendMoneyActivity : Activity(), AnkoLogger {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_send_money)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_send_money, menu)

    return true
  }
  
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.getItemId()) {
      R.id.barcode -> {
        startActivityForResult(intentFor<ScannerActivity>(), 1)

        return true
      }
      else -> return super.onOptionsItemSelected(item)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    info("Request code: $requestCode, ResultCode: $resultCode, data: $data")

    if (data != null) {
      val barcodeType = data.getStringExtra("barcodeFormat")
      val barcodePayload = data.getStringExtra("barcodeTextPayload")

      handleUri(barcodePayload)
    }
  }

  fun handleUri(uri: String) {
    val bitcoinPaymentURI = BitcoinPaymentURI.parse(uri)

    bitcoinPaymentURI.getAddress()
    bitcoinPaymentURI.getAmount()
    bitcoinPaymentURI.getLabel()
    bitcoinPaymentURI.getMessage()
    bitcoinPaymentURI.getParameters().size

    info("Uri handled")
  }

}
