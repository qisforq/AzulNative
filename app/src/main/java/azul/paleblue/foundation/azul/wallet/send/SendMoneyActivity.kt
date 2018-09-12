package azul.paleblue.foundation.azul.wallet.send

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick

class SendMoneyActivity: Activity(), AnkoLogger {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    verticalLayout {
      button("Scan Barcode") {
        onClick {
          val intent = intentFor<ScannerActivity>()
          startActivityForResult(intent, 1)
        }
      }
    }

  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    info("Request code: $requestCode, ResultCode: $resultCode, data: $data")

  }
}
