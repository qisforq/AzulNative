package azul.paleblue.foundation.azul.wallet.receive

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
// import android.R
import android.os.Bundle
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class RequestMoneyActivity : Activity() {

  val QRcodeWidth = 200

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    verticalLayout {
      padding = dip(30)

      val qrCodeView = imageView()

      button("Make QR Code") {
        onClick {
          makeQrCode(qrCodeView)
        }
      }
    }

  }

  fun makeQrCode(imageView: ImageView) {
    val bitmap = encodeText("Text to encode")
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
    for (y in 0 .. height - 1) {
      val offset = y * width
      for (x in 0 .. width - 1) {
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

fun encodeText(text: String): Bitmap? {
  val multiFormatWriter = MultiFormatWriter()
  try {
    val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200)
    //val barcodeEncoder = BarcodeEncoder()
    //val bitmap = barcodeEncoder.createBitmap(bitMatrix)
    return createBitmap(bitMatrix)
  } catch (e: WriterException) {
    e.printStackTrace()
    return null
  }
}

//  private fun encodeText(Value: String): Bitmap? {
//    val bitMatrix: BitMatrix
//
//      bitMatrix = MultiFormatWriter().encode(
//          Value,
//          BarcodeFormat.QR_CODE,
//          QRcodeWidth,
//          QRcodeWidth
//      )
//
//
//    val bitMatrixWidth = bitMatrix.getWidth()
//
//    val bitMatrixHeight = bitMatrix.getHeight()
//
//    val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)
//
//    for (y in 0 until bitMatrixHeight) {
//      val offset = y * bitMatrixWidth
//
//      for (x in 0 until bitMatrixWidth) {
//
//        pixels[offset + x] = if (bitMatrix.get(x, y))
//        //resources.getColor(R.color.black)
//          0x000000.opaque
//        else
//          0xffffff.opaque
//      }
//    }
//    val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)
//
//    bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
//    return bitmap
//  }
}
