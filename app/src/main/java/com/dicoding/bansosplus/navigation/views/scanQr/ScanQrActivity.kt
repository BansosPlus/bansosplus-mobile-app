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
import com.dicoding.bansosplus.navigation.views.profile.ProfileFragment


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

        Log.e("QR", "start activity")

        codeScanner()

        setPermission()

        viewModel = ViewModelProvider(this, ScanQrViewModelFactory(sessionManager)).get(ScanQrViewModel::class.java)

        val status = Observer<ValidateRegisItem?> { newStatus ->
            if (newStatus != null) {
                binding.statusRegis.text = newStatus.status

                val task = object : TimerTask() {
                    override fun run() {
                        val intent = Intent(this@ScanQrActivity, ProfileFragment::class.java)
                        startActivity(intent)
                    }
                }
                Timer().schedule(task, 5000)
            }
        }
        viewModel.bansosStatusItem.observe(this, status)

    }

    private fun codeScanner() {
        Log.e("QR", "start scan")
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
                    viewModel.validateRegisStatus(it.text).toString()
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