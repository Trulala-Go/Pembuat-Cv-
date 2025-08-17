
package go.cv

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import android.view.*
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var status: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        status = findViewById(R.id.status)

        findViewById<TextView>(R.id.buka).setOnClickListener {
            startActivity(Intent(this, Buka::class.java))
        }

        findViewById<TextView>(R.id.keluar).setOnClickListener {
            Keluar()
        }

        status.setOnClickListener {
            MintaIzin()
        }

        findViewById<TextView>(R.id.buat).setOnClickListener {
            startActivity(Intent(this, Buat::class.java))
        }

        AturIkon()
    }

    override fun onBackPressed() {
        Keluar()
    }

    private fun Keluar() {
        AlertDialog.Builder(this)
            .setTitle("Keluar")
            .setMessage("Apakah ingin meninggalkan Aplikasi?")
            .setPositiveButton("Ya") { _, _ -> finish() }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun AturIkon() {
        status.setImageResource(
            if (CekIzin()) R.drawable.sd_terima
            else R.drawable.sd_tolak
        )
    }
    
    override fun onResume(){
      super.onResume()
      AturIkon()
    }

  private fun CekIzin(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // Android 11+ → cek izin MANAGE_EXTERNAL_STORAGE
        Environment.isExternalStorageManager()
    } else {
        // Android < 11 → cek WRITE_EXTERNAL_STORAGE
        val izin = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        izin == PackageManager.PERMISSION_GRANTED
    }
  }

  private fun MintaIzin() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        try {
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:$packageName")
            startActivity(intent)
        } catch (e: Exception) {
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            startActivity(intent)
        }
    } else {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }
  }
}