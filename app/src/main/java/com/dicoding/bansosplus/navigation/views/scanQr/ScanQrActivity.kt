package com.dicoding.bansosplus.navigation.views.scanQr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.ActivityScanQrBinding
import com.dicoding.bansosplus.navigation.data.model.ValidateRegisItem
import java.util.Timer
import java.util.TimerTask
import androidx.lifecycle.Observer
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.navigation.BottomNavActivity
import java.text.SimpleDateFormat
import java.util.Locale


class ScanQrActivity : AppCompatActivity() {
    lateinit var binding : ActivityScanQrBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: ScanQrViewModel
    lateinit var codeScanner : CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        codeScanner()

        setPermission()

        viewModel = ViewModelProvider(this, ScanQrViewModelFactory(sessionManager)).get(ScanQrViewModel::class.java)

        val status = Observer<ValidateRegisItem?> { newStatus ->
            Log.e("QR Codes", "new status: ${newStatus}")
            if (newStatus != null) {
                Log.e("QR Codes", "new status: ${newStatus.status}")
                if (newStatus.status == "TAKEN") {
                    binding.takenWithPlaceholder.text = "Diambil oleh:"
                    binding.recipientName.text = newStatus.userName
                    newStatus.updatedAt.let { value ->
                        val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(value)
                        binding.takenDate.text = "Pada tanggal: ${formattedDate}"
                    }

                    binding.statusRegisImage.setImageResource(R.drawable.checklist)

                    val task = object : TimerTask() {
                        override fun run() {
                            val intent = Intent(this@ScanQrActivity, BottomNavActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    Timer().schedule(task, 4000)
                } else {
                    binding.statusRegisImage.setImageResource(R.drawable.delete_button)
                    binding.recipientName.text = "QR Tidak Valid"
                }
            }
        }
        viewModel.bansosStatusItem.observe(this, status)

    }

    private fun codeScanner() {
        Log.e("QR Codes", "start scan")
        codeScanner = CodeScanner(this, binding.scanner)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    Log.e("QR Codes", "${it.text}")
                    viewModel.validateRegisStatus(it.text)
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }

            binding.scanner.setOnClickListener {

                codeScanner.startPreview()

            }
        }

    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.startPreview()
    }

    private fun setPermission() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if(permission != PackageManager.PERMISSION_GRANTED) {
            makeReq()
        }
    }

    private fun makeReq() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CAMERA), 101
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permission: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults)
        when(requestCode) {
            101 -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Needed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}