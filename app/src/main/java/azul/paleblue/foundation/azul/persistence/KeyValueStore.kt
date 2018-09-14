package azul.paleblue.foundation.azul.persistence

import android.content.Context

val prefsPath = "foundation.paleblue.azul"

class KeyValueStore(val context: Context) {

  val inviteKey    = "INVITE_CODE"
  val pushTokenKey = "PUSH_TOKEN"
  val sessionKey   = "SESSION"

  val preferences = context.getSharedPreferences(prefsPath, Context.MODE_PRIVATE)
  
  fun storePushToken(pushToken: String) {
    preferences.edit().putString(pushTokenKey, pushToken).apply()
  }

  fun getPushToken(): String? {
    return preferences.getString(pushTokenKey, null)
  }

  fun storeCode(code: String) {
    preferences.edit().putString(inviteKey, code).apply()
  }

  fun getCode(): String? {
    return preferences.getString(inviteKey, null)
  }
  
  fun storeSessionToken(code: String) {
    preferences.edit().putString(sessionKey, code).apply()
  }

  fun clearSessionToken() {
    preferences.edit().putString(sessionKey, null).apply()
  }

  fun getSessionToken(): String? {
    return preferences.getString(sessionKey, null)
  }

}
