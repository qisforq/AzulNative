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
          textView("Main Activity")
          button("Invite Friends") {
              onClick {
                  startActivity<InviteActivity>("id" to 5)
              }
          }
          button("Request Money") {
              onClick {
                  startActivity<RequestMoneyActivity>("id" to 5)
              }
          }
        }
    }
}
