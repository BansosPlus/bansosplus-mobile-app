package com.dicoding.bansosplus.navigation.views.scanQr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager


class ScanQrActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: ScanQrViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)

    }
}