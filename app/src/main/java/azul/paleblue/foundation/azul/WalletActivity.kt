package azul.paleblue.foundation.azul

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.*

class WalletActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        verticalLayout {
          padding = dip(30)
          
          button("Wallet Activity")
          editText {
            hint = "Name"
            textSize = 24f
          }
          editText {
            hint = "Password"
            textSize = 24f
          }
          button("Login") {
            textSize = 26f
          }
        }

    }
}
