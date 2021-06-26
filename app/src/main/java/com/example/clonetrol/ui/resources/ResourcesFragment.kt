package com.example.clonetrol.ui.resources

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clonetrol.R
import com.example.clonetrol.databinding.FragmentResourcesBinding
import com.example.clonetrol.models.Android
import com.example.clonetrol.models.Clone
import com.example.clonetrol.models.Resource
import com.example.clonetrol.models.enums.Status
import com.example.clonetrol.ui.resources.callbacks.ResourceCallback
import com.example.clonetrol.ui.resources.details.ResourceDetailsActivity
import com.example.clonetrol.ui.resources.list.ResourceListAdapter

class ResourcesFragment : Fragment() {

    companion object Example{
        const val RESOURCE = "resource"
        private val resourcesList: List<Resource> = listOf(
            Clone("1", "Clon Juan", R.drawable.empresario, Status.CORRECT),
            Android("2", "Androide Ben", R.drawable.robot_1, Status.WARNING),
            Android("3", "Androide Shawn", R.drawable.robot_2, Status.ALERT),
            Clone("4", "Clon Pedro", R.drawable.empresario, Status.WARNING)
        )

    }

    private var _binding: FragmentResourcesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResourcesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.resourcesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ResourceListAdapter(resourcesList, context, object : ResourceCallback{
                override fun onResourcePressed(resource: Resource) {
                    startResourceDetailsActivity(resource)
                }

            })
        }


        return root
    }

    private fun startResourceDetailsActivity(resource: Resource) {
        activity?.let {
            startActivity(Intent(activity, ResourceDetailsActivity::class.java).putExtra(RESOURCE,resource))
            it.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}