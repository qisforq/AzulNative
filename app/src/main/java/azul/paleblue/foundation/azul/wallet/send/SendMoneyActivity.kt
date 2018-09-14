package azul.paleblue.foundation.azul.wallet.send

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import azul.paleblue.foundation.azul.AzulApplication
import azul.paleblue.foundation.azul.R
import com.sandro.bitcoinpaymenturi.BitcoinPaymentURI
import org.jetbrains.anko.*

class SendMoneyActivity : Activity(), AnkoLogger {
  
  lateinit var model: SendMoneyModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    val app = application as AzulApplication
    model = app.sendMoneyModel

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
    val bitcoinPaymentUri = BitcoinPaymentURI.parse(uri)

    bitcoinPaymentUri.getAddress()
    bitcoinPaymentUri.getAmount()
    bitcoinPaymentUri.getLabel()
    bitcoinPaymentUri.getMessage()
    bitcoinPaymentUri.getParameters().size

    info("Uri handled")
  }

}
