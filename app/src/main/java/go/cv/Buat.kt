package go.cv

import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.net.*
import android.content.*
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import android.graphics.pdf.PdfDocument
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

class Buat :AppCompatActivity(){

  private lateinit var liner:LinearLayout
  private lateinit var foto:ImageView

  override fun onCreate(savedInstanceState:Bundle?){
    super.onCreate(savedInstanceState)
      setContentView(R.layout.buat)
      
      liner = findViewById(R.id.liner)
      foto = findViewById<ImageView>(R.id.foto)
      
      Tombol()
      BacaNyata()
  }
  
  override fun onBackPressed(){
    if(liner.visibility == View.VISIBLE){liner.visibility = View.GONE}
    else{Keluar()}
  }
  
  private fun Keluar(){
    AlertDialog.Builder(this)
      .setTitle("Keluar")
      .setMessage("Batal buat dan tinggalkan ?")
      .setPositiveButton("Ya"){_,_-> finish()}
      .setNegativeButton("Batal", null)
      .show()
  }
  
  private fun Tombol(){
    findViewById<ImageView>(R.id.nav).setOnClickListener{
      liner.visibility = if(liner.visibility == View.GONE)View.VISIBLE else View.GONE
    }
    
    findViewById<TextView>(R.id.keluar).setOnClickListener{
      Keluar()
    }
    
    findViewById<ImageView>(R.id.lihat).setOnClickListener{
      liner.visibility = View.GONE
    }
    
    findViewById<ImageView>(R.id.simpan).setOnClickListener{
      Simpan()
    }
    
    foto.setOnClickListener {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
      intent.addCategory(Intent.CATEGORY_OPENABLE)
      intent.type = "image/*"
      startActivityForResult(intent, 100)
    }

  }
  
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == 100 && resultCode == RESULT_OK) {
        val uri = data?.data
        val fotoOut = findViewById<ImageView>(R.id.foto_out)
        fotoOut.setImageURI(uri)  
    }
    
    liner.visibility = View.GONE
  }
  

private fun Simpan() {
    val tulis = EditText(this)
    val utama = findViewById<LinearLayout>(R.id.utama)

    AlertDialog.Builder(this)
        .setTitle("Beri nama")
        .setView(tulis)
        .setPositiveButton("Simpan") { _, _ ->
            val namaFile = tulis.text.toString().ifEmpty { "GoCv" }

            val folder = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                "GoCv"
            )
            if (!folder.exists()) folder.mkdirs()

            val file = File(folder, "$namaFile.pdf")

            val document = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(
                utama.width,
                utama.height,
                1
            ).create()

            val page = document.startPage(pageInfo)
            utama.draw(page.canvas)
            document.finishPage(page)

            try {
                document.writeTo(FileOutputStream(file))
                Toast.makeText(this, "Tersimpan di: ${file.absolutePath}", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Gagal simpan: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                document.close()
            }
        }
        .setNegativeButton("Batal", null)
        .show()
    }

  private fun BacaNyata() {
    val nama = findViewById<EditText>(R.id.nama)
    val wa = findViewById<EditText>(R.id.wa)
    val nomor = findViewById<EditText>(R.id.nomor)
    val umur = findViewById<EditText>(R.id.umur)
    val email = findViewById<EditText>(R.id.email)
    val prefensi = findViewById<EditText>(R.id.prefensi)
    val domisili = findViewById<EditText>(R.id.domisili)
    val agama = findViewById<EditText>(R.id.agama)
    val lulusan = findViewById<EditText>(R.id.lulusan)
    val ttl = findViewById<EditText>(R.id.ttl)
    val sd = findViewById<EditText>(R.id.sd)
    val smp = findViewById<EditText>(R.id.smp)
    val sma = findViewById<EditText>(R.id.sma)
    val kuliah = findViewById<EditText>(R.id.kuliah)
    val pengalamanSatu = findViewById<EditText>(R.id.pengalamanSatu)
    val pengalamanDua = findViewById<EditText>(R.id.pengalamanDua)
    
    val namaOut = findViewById<TextView>(R.id.nama_out)
    val umurOut = findViewById<TextView>(R.id.umur_out)
    val waOut = findViewById<TextView>(R.id.wa_out)
    val nomorOut = findViewById<TextView>(R.id.nomor_out)
    val emailOut = findViewById<TextView>(R.id.email_out)
    val prefensiOut = findViewById<TextView>(R.id.prefensi_out)
    val domisiliOut = findViewById<TextView>(R.id.domisili_out)
    val agamaOut = findViewById<TextView>(R.id.agama_out)
    val lulusanOut = findViewById<TextView>(R.id.lulusan_out)
    val ttlOut = findViewById<TextView>(R.id.ttl_out)
    val sdOut = findViewById<TextView>(R.id.sd_out)
    val smpOut = findViewById<TextView>(R.id.smp_out)
    val smaOut = findViewById<TextView>(R.id.sma_out)
    val kuliahOut = findViewById<TextView>(R.id.kuliah_out)
    val pengalamanSatuOut = findViewById<TextView>(R.id.pengalamanSatu_out)
    val pengalamanDuaOut = findViewById<TextView>(R.id.pengalamanDua_out)
    

    nama.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            namaOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    wa.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            waOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    email.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            emailOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    nomor.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            nomorOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    prefensi.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            prefensiOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    umur.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            umurOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    domisili.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            domisiliOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    agama.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            agamaOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    lulusan.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            lulusanOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    ttl.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            ttlOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    sd.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            sdOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    smp.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            smpOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    sma.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            smaOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    kuliah.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            kuliahOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    pengalamanSatu.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            pengalamanSatuOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
    pengalamanDua.addTextChangedListener(object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            pengalamanDuaOut.text = s.toString()
        }
        override fun afterTextChanged(s: android.text.Editable?) {}
    })
    
  }
  
}
