package azul.paleblue.foundation.azul.wallet.receive

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import azul.paleblue.foundation.azul.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.Dimension
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick
import com.sandro.bitcoinpaymenturi.BitcoinPaymentURI



class RequestMoneyActivity : Activity(), AnkoLogger {

  lateinit var imageView: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_request_money)

    imageView = findViewById<ImageView>(R.id.qrCode)
    
    val amountEdit  = findViewById<EditText>(R.id.amount)
    val messageEdit = findViewById<EditText>(R.id.message)

    findViewById<Button>(R.id.makeQrCode).onClick {
      val uri = makeUri(
          "myAddress",
          amountEdit.text.toString(),
          "Alice",
          messageEdit.text.toString(),
          "http://paleblue.foundation/payment/1234"
      )

      showQrCode(uri)
    }
  }

  // val requestMoneyUri = "bitcoin:mjSk1Ny9spzU2fouzYgLqGUD8U41iR35QN?amount=0.10&label=Example+Merchant&message=Order+of+flowers+%26+chocolates&r=https://example.com/pay/mjSk1Ny9spzU2fouzYgLqGUD8U41iR35QN"

  fun makeUri(address: String, amount: String, label: String, message: String, requestUrl: String): Uri {
    return Uri.Builder().path(address)
        .appendQueryParameter("amount", amount)
        .appendQueryParameter("label", label)
        .appendQueryParameter("message", message)
        .appendQueryParameter("r", requestUrl)
        .build()
    //return BitcoinPaymentURI.Builder()
    //    .address(address)
    //    .amount(amount)
    //    .label(label)
    //    .message(message)
    //    // .parameter("foo", "bar")
    //    // .requiredParameter("fiz", "biz")
    //    .build()
  }

  fun showQrCode(uri: Uri) {
    val bitmap = encodeText(uri.toString(), Dimension(imageView.width, imageView.height))
    if (bitmap == null) {
      error("Bitmap was null!")
    } else {
      imageView.setImageBitmap(bitmap)
    }
  }

  fun createBitmap(matrix: BitMatrix): Bitmap {
    val width = matrix.getWidth()
    val height = matrix.getHeight()
    val pixels = IntArray(width * height)
    for (y in 0..height - 1) {
      val offset = y * width
      for (x in 0..width - 1) {
        pixels[offset + x] = if (matrix.get(x, y)) {
          BLACK
        } else {
          WHITE
        }
      }
    }

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
    return bitmap
  }

  fun encodeText(text: String, dimensions: Dimension): Bitmap? {
    val multiFormatWriter = MultiFormatWriter()
    try {
      info(dimensions)
      val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, dimensions.width, dimensions.height)
      return createBitmap(bitMatrix)
    } catch (e: WriterException) {
      e.printStackTrace()
      return null
    }
  }

}
