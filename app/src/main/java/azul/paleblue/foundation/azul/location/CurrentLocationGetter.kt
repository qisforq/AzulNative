package azul.paleblue.foundation.azul.location

import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnSuccessListener

class CurrentLocationGetter(context: Context) {


  private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    fun getCurrentLocation(onSuccess: OnSuccessListener<Location?>) {
//         TODO: Return Current Location
//         see https://developer.android.com/training/location/retrieve-current
        val task = fusedLocationClient.lastLocation
        task.addOnSuccessListener(onSuccess)
        task.run {  }
    }
}


