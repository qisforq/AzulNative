package azul.paleblue.foundation.azul

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.*

class ProfileActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
          button("Profile Activity")
        }
    }
}
