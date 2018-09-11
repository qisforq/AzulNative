package azul.paleblue.foundation.azul

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
          padding = dip(30)
          button("Main Activity") {
              onClick {
                  toast("Hello, world!")
                  startActivity<InviteActivity>("id" to 5)
              }
          }
        }
    }
}
