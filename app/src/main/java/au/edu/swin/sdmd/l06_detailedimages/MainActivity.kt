package au.edu.swin.sdmd.l06_detailedimages

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    var station: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        station = Location("Glenferrie Station", "Ada May Plante",
            -37.821539f, 145.036473f)

        val vStation = findViewById<TextView>(R.id.station)
        vStation.setOnClickListener {
            // todo:
            // - sets up to start the detail activity
            // - pass selected view item data to the detailed activity
            val intent = Intent(this, DetailActivity::class.java)

            /* v1.0:
            intent.putExtra("name", station.name)
            intent.putExtra("author", station.author)
            intent.putExtra("latitude", station.latitude)
            intent.putExtra("longitude", station.longitude)*/

            // v2.0: use Parcelable object
            intent.putExtra("station", station)

            // v3.0: startActivity(intent)
            startForResult.launch(intent)
        }
    }

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        // handle results that comes back
        result ->
            when(result.resultCode) {
                RESULT_OK -> {
                    // set the station text color accordingly
                    val vStation = findViewById<TextView>(R.id.station)

                    val data = result.data
                    val visited = data?.getParcelableExtra<Location>("visited")
                    visited?.let {
                        vStation.setTextColor(if (it.visited) Color.GREEN else Color.RED)
                        // record visited for subsquent passing
                        station = visited
                    }
                }
            }
    }
}