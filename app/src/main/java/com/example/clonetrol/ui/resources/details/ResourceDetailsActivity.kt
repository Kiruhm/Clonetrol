package com.example.clonetrol.ui.resources.details

import android.app.ProgressDialog
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.clonetrol.R
import com.example.clonetrol.databinding.ActivityResourceDetailsBinding
import com.example.clonetrol.models.Resource
import com.example.clonetrol.models.enums.Status
import com.example.clonetrol.ui.resources.ResourcesFragment
import com.squareup.picasso.Picasso
import java.lang.StringBuilder
import java.util.*


class ResourceDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResourceDetailsBinding
    private lateinit var resource : Resource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResourceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.hide()
        supportActionBar?.hide()

        intent.getSerializableExtra(ResourcesFragment.RESOURCE)?.let {
            resource = it as Resource

            val value = TypedValue()
            theme.resolveAttribute(R.attr.colorPrimaryVariant, value, true)

            Picasso.get().load(resource.imageRes).into(binding.circleImageView)
            binding.nameTextViewDetails.text = resource.name

            when(resource.status){
                Status.CORRECT -> {
                    binding.statusImageDetails.imageTintList =ContextCompat.getColorStateList(this, value.resourceId)
                    binding.descriptionDetails.text = getString(R.string.correct_description, resource.name)
                }
                Status.WARNING ->{
                    binding.statusImageDetails.imageTintList = ContextCompat.getColorStateList(this, R.color.warningColor)
                    binding.descriptionDetails.text = getString(R.string.warning_description, resource.name)
                }
                Status.ALERT ->{
                    binding.statusImageDetails.imageTintList = ContextCompat.getColorStateList(this, R.color.alertColor)
                    binding.descriptionDetails.text = getString(R.string.alert_description, resource.name)
                }
            }

            binding.button.setOnClickListener { btn ->
                btn.isClickable = false
                val dialog = ProgressDialog.show(this, null, "Sincronizando...", true, false).apply {
                    setOnDismissListener {
                        Toast.makeText(this@ResourceDetailsActivity, "Ha habido un error sincronizando. Vuelva a intentarlo.", Toast.LENGTH_LONG).show()
                    }
                    setIndeterminateDrawable(ContextCompat.getDrawable(this@ResourceDetailsActivity,R.drawable.progress_bar_style))
                }
                Thread{
                    Thread.sleep(3000)
                    runOnUiThread {
                        dialog.dismiss()
                        btn.isClickable = true
                    }
                }.start()
            }

            binding.button2.setOnClickListener {
                Toast.makeText(this@ResourceDetailsActivity, String.format(Locale.getDefault(),"No es posible desconectar a %s ahora mismo.", resource.name), Toast.LENGTH_LONG).show()
            }

        } ?: finish()
    }
}