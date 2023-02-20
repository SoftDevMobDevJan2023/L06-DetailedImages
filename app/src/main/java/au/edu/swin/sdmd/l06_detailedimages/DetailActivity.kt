package au.edu.swin.sdmd.l06_detailedimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    var station: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        /*v1.0:
        val name = intent.getStringExtra("name")
        val author = intent.getStringExtra("author")*/

        // v2.0:
        station = intent.getParcelableExtra<Location>("station")

        val vName = findViewById<TextView>(R.id.name)
        //vName.text = name
        vName.text = station?.name

        findViewById<TextView>(R.id.author).text =
            // author
            station?.author

        findViewById<TextView>(R.id.latitude).text =
            station?.latitude.toString()

        findViewById<TextView>(R.id.longitude).text =
            station?.longitude.toString()

        // version 3.0
        station?.let {
            findViewById<Switch>(R.id.visited).isChecked = it.visited
        }

    }

    override fun onBackPressed() {
        // pass result back to main activity
        station?.visited = findViewById<Switch>(R.id.visited).isChecked

        val intent = intent.putExtra("visited", station)

        setResult(RESULT_OK, intent)

        super.onBackPressed()
    }
}