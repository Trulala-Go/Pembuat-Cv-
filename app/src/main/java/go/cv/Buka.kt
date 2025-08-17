package go.cv

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.widget.*
import android.view.*
import android.os.Environment
import java.io.File

class Buka : AppCompatActivity() {

    private lateinit var liner: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buka)

        liner = findViewById(R.id.liner)

        AturGrid()
        
        findViewById<ImageView>(R.id.nav).setOnClickListener{
          liner.visibility = if(liner.visibility == View.GONE)View.VISIBLE else View.GONE
        }
    }

    override fun onBackPressed() {
        if (liner.visibility == View.VISIBLE) {
            liner.visibility = View.GONE
        } else {
            Keluar()
        }
    }

    private fun Keluar() {
        AlertDialog.Builder(this)
            .setTitle("Keluar")
            .setMessage("Batal Melihat ?")
            .setPositiveButton("Ya") { _, _ -> finish() }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun AturGrid() {
    val grid = findViewById<GridLayout>(R.id.grid)
    grid.removeAllViews()

    val docs = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

    val daftarFile = docs.listFiles()
    if (daftarFile != null) {
        for (file in daftarFile) {
            if (file.isFile && file.name.lowercase().endsWith(".pdf")) {
                val item = LayoutInflater.from(this).inflate(R.layout.item_horizontal, grid, false)
                val gambar = item.findViewById<ImageView>(R.id.gambar)
                val nama = item.findViewById<TextView>(R.id.nama)

                nama.text = file.name
                gambar.setImageResource(R.drawable.pdf)

                // bisa tambahkan klik listener buat buka pdf
                item.setOnClickListener {
                    Toast.makeText(this, "Buka: ${file.name}", Toast.LENGTH_SHORT).show()
                }

                grid.addView(item)
            }
        }
    }
  }
}