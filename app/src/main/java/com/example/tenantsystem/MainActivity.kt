package com.example.tenantmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantsystem.databinding.ActivityMainBinding
import android.net.Uri

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastTenant: Tenant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Task 2
        val email = intent.getStringExtra("EMAIL")
        if (email != null) {
            Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()
        }

        //  Task 3 validation
        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            var valid = true
            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Required"
                valid = false
            }
            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Required"
                valid = false
            }
            if (rent.isEmpty()) {
                binding.rentEditText.error = "Required"
                valid = false
            }
            if (!valid) return@setOnClickListener

            val tenant = Tenant(name, phone, rent)
            binding.tenant = tenant
            lastTenant = tenant
        }

        // Task 1: SHARE: implicit Intent (ACTION_SEND)
        binding.shareButton.setOnClickListener {
            val tenant = lastTenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, tenant.summary())
            startActivity(Intent.createChooser(shareIntent, "Share tenant"))
        }
        // CALL TENANT
        binding.callButton.setOnClickListener {
            val tenant = lastTenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${tenant.phone}"))
            startActivity(dialIntent)
        }
    }
}