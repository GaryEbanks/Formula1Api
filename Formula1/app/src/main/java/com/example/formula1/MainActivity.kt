package com.example.formula1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.example.formula1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShow.setOnClickListener {
            searchDriverData()
        }
    }

    fun getRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://ergast.com/api/f1/2022/drivers/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchDriverData(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val txtFilter: String = binding.etDriverName.text.toString().lowercase()
                val call = getRetrofit().create(ApiService::class.java).getDriverData("${txtFilter}.json")
                if (call.isSuccessful){
                    val givenName: String = call.body()?.MRData?.DriverTable?.Drivers?.get(0)?.givenName.toString()
                    val familyName: String = call.body()?.MRData?.DriverTable?.Drivers?.get(0)?.familyName.toString()
                    val dateOfBirth: String = call.body()?.MRData?.DriverTable?.Drivers?.get(0)?.dateOfBirth.toString()
                    val nationality: String = call.body()?.MRData?.DriverTable?.Drivers?.get(0)?.nationality.toString()
                    val number: String = call.body()?.MRData?.DriverTable?.Drivers?.get(0)?.permanentNumber.toString()
                    val code: String = call.body()?.MRData?.DriverTable?.Drivers?.get(0)?.code.toString()

                    binding.TvGivenName.text = "Nombre: $givenName"
                    binding.TvFamilyName.text = "Apellido: $familyName"
                    binding.TvDateOfBirth.text = "Fecha de Nacimiento: $dateOfBirth"
                    binding.TvNationality.text = "Nacionalidad: $nationality"
                    binding.TvNumber.text = "Numero: $number"
                    binding.TvCode.text = "Codigo: $code"
                }

            } catch (ex: Exception){
                val msg = Toast.makeText(this@MainActivity,"CONNECTION ERROR", Toast.LENGTH_LONG)
                msg.setGravity(Gravity.CENTER,0,0)
                msg.show()
            }
        }
    }
}