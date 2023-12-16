package com.dicoding.bansosplus.navigation.views.home.detailBansos.pengajuanBansos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.models.auth.BansosRegistrationRequest
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.pref.UserPreferences
import com.dicoding.bansosplus.repository.BansosRegistrationRepository
import com.dicoding.bansosplus.repository.UserRepository
import kotlinx.coroutines.launch

class PengajuanBansosActivity : AppCompatActivity() {
    private lateinit var activitySessionManager: SessionManager
//    private lateinit var userPreferences: UserPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_bansos)

        activitySessionManager = SessionManager(this)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        val intent = intent
        if (intent.hasExtra("bansosId")) {
            val bansosId = intent.getStringExtra("bansosId")

            if (bansosId != null) {
                // Get Spinner
                val incomeSpinner: Spinner = findViewById(R.id.spinner_income)
                val luasLantaiSpinner: Spinner = findViewById(R.id.spinner_luas_lantai)
                val kualitasDindingSpinner: Spinner = findViewById(R.id.spinner_kualitas_dinding)
                val jumlahMakanSpinner: Spinner = findViewById(R.id.spinner_jumlah_makan)
                val bahanBakarSpinner: Spinner = findViewById(R.id.spinner_bahan_bakar)
                val pendidikanSpinner: Spinner = findViewById(R.id.spinner_pendidikan)
                val asetSpinner: Spinner = findViewById(R.id.spinner_aset)
                val berobatSpinner: Spinner = findViewById(R.id.spinner_berobat)
                val tanggunganSpinner: Spinner = findViewById(R.id.spinner_tanggungan)

                // Value Array
                val incomeArray = arrayOf("", "<500 ribu", "500 ribu-1 juta", "1 juta-1.5 juta", ">1.5 juta")
                val luasLantaiArray = arrayOf("", "Diatas 8m²", "Dibawah 8m²")
                val kualitasDindingArray = arrayOf("", "Buruk", "Normal", "Bagus")
                val jumlahMakanArray = arrayOf("", "0", "1", "2", "3")
                val bahanBakarArray = arrayOf("", "Kayu/Arang", "Gas/LPG")
                val pendidikanArray = arrayOf("", "SD", "SMP", "SMA", "Sarjana")
                val asetArray = arrayOf("", "<500 ribu", "500 ribu-1 juta", "1 juta-1.5 juta", ">1.5 juta")
                val berobatArray = arrayOf("", "Mampu", "Tidak Mampu")
                val tanggunganArray = arrayOf("", "0", "1", "2", ">2")

                // Adapter
                val incomeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, incomeArray)
                val luasLantaiAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, luasLantaiArray)
                val kualitasDindingAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kualitasDindingArray)
                val jumlahMakanAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, jumlahMakanArray)
                val bahanBakarAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bahanBakarArray)
                val pendidikanAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pendidikanArray)
                val asetAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, asetArray)
                val berobatAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, berobatArray)
                val tanggunganAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tanggunganArray)

                // Set Dropdown
                incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                luasLantaiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                kualitasDindingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                jumlahMakanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                bahanBakarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                pendidikanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                asetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                berobatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                tanggunganAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                // Set Adapter
                incomeSpinner.adapter = incomeAdapter
                luasLantaiSpinner.adapter = luasLantaiAdapter
                kualitasDindingSpinner.adapter = kualitasDindingAdapter
                jumlahMakanSpinner.adapter = jumlahMakanAdapter
                bahanBakarSpinner.adapter = bahanBakarAdapter
                pendidikanSpinner.adapter = pendidikanAdapter
                asetSpinner.adapter = asetAdapter
                berobatSpinner.adapter = berobatAdapter
                tanggunganSpinner.adapter = tanggunganAdapter

                lifecycleScope.launch() {
                    try {
                        val response = UserRepository(activitySessionManager).get()
                        if (response.isSuccessful) {
                            val user = response.body()?.data
                            Log.i("BANSOS", "Get user successfully")

                            if (user != null) {
                                findViewById<EditText>(R.id.et_nik).setText(user.nik)
                                findViewById<EditText>(R.id.et_nama).setText(user.name)
                                findViewById<EditText>(R.id.et_no_kk).setText(user.noKk)
                                incomeSpinner.setSelection(incomeArray.indexOf(user.income))
                                luasLantaiSpinner.setSelection(incomeArray.indexOf(user.floorArea))
                                kualitasDindingSpinner.setSelection(kualitasDindingArray.indexOf(user.wallQuality))
                                jumlahMakanSpinner.setSelection(jumlahMakanArray.indexOf(user.numberOfMeals))
                                bahanBakarSpinner.setSelection(bahanBakarArray.indexOf(user.fuel))
                                pendidikanSpinner.setSelection(pendidikanArray.indexOf(user.education))
                                asetSpinner.setSelection(asetArray.indexOf(user.totalAsset))
                                berobatSpinner.setSelection(berobatArray.indexOf(user.treatment))
                                tanggunganSpinner.setSelection(tanggunganArray.indexOf(user.numberOfDependents))
                            }
                        } else {
                            Log.e("BANSOS", "Response failed")
                        }
                    } catch (e: Exception) {
                        Log.e("BANSOS", "Connection failed")
                    }
                }

                val btnAjukanBantuan: Button = findViewById(R.id.btn_ajukan_bantuan)
                btnAjukanBantuan.setOnClickListener {
                    lifecycleScope.launch() {
                        registerBansos(
                            bansosId.toInt(),
                            findViewById<EditText>(R.id.et_nik).text.toString().trim(),
                            findViewById<EditText>(R.id.et_nama).text.toString().trim(),
                            findViewById<EditText>(R.id.et_no_kk).text.toString().trim(),
                            incomeSpinner.selectedItem.toString(),
                            luasLantaiSpinner.selectedItem.toString(),
                            kualitasDindingSpinner.selectedItem.toString(),
                            jumlahMakanSpinner.selectedItem.toString(),
                            bahanBakarSpinner.selectedItem.toString(),
                            pendidikanSpinner.selectedItem.toString(),
                            asetSpinner.selectedItem.toString(),
                            berobatSpinner.selectedItem.toString(),
                            tanggunganSpinner.selectedItem.toString()
                        )
                    }
                }
            }
        } else {
            Toast.makeText(this, "No bansosId found in the intent", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private suspend fun registerBansos(
        bansos_id: Int,
        nik: String,
        nama: String,
        no_kk: String,
        income: String,
        floor_area: String,
        wall_quality: String,
        number_of_meals: String,
        fuel: String,
        education: String,
        total_asset: String,
        treatment: String,
        number_of_dependents: String
    ) {
        val request = BansosRegistrationRequest()
        request.bansosId = bansos_id
        request.nik = nik
        request.name = nama
        request.noKk = no_kk
        request.income = income
        request.floorArea = floor_area
        request.wallQuality = wall_quality
        request.numberOfMeals = number_of_meals
        request.fuel = fuel
        request.education = education
        request.totalAsset = total_asset
        request.treatment = treatment
        request.numberOfDependents = number_of_dependents

        try {
            val response = BansosRegistrationRepository(activitySessionManager).registerBansos(request)
            if (response.isSuccessful) {
                Log.i("BANSOS", "Register bansos successfully")

                val intent = Intent(this, BottomNavActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.e("BANSOS", "Response failed")
            }
        } catch (e: Exception) {
            Log.e("BANSOS", "Connection failed")
        }
    }
}