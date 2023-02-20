package au.edu.swin.sdmd.l06_detailedimages

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/* v2.0: makes Parcelizable */
@Parcelize
data class Location(val name: String, val author: String,
                    val latitude: Float, val longitude: Float,
                    var visited: Boolean = false) : Parcelable {
}