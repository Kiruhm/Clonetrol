package com.example.clonetrol.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clonetrol.R
import com.example.clonetrol.databinding.FragmentContactsBinding
import com.example.clonetrol.models.Android
import com.example.clonetrol.models.Clone
import com.example.clonetrol.models.Resource
import com.example.clonetrol.models.enums.Status
import com.example.clonetrol.ui.chat.callbacks.ContactCallback
import com.example.clonetrol.ui.chat.lists.ContactsListAdapter
import java.lang.Exception

class ContactsFragment: Fragment() {

    companion object Example{
        const val CONTACT = "contact"
        private val resourcesList: List<Resource> = listOf(
            Clone("1", "Clon Juan", R.drawable.empresario, Status.CORRECT),
            Android("2", "Androide Ben", R.drawable.robot_1, Status.WARNING),
            Android("3", "Androide Shawn", R.drawable.robot_2, Status.ALERT),
            Clone("4", "Clon Pedro", R.drawable.empresario, Status.WARNING)
        )
    }

    private var _binding: FragmentContactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    private fun startChatActivity(contact: Resource) {
        try {
            startActivity(Intent(activity, ChatActivity::class.java).putExtra(CONTACT, contact))
            activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        } catch (e: Exception){e.stackTrace}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        binding.contactsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ContactsListAdapter(resourcesList, context, object : ContactCallback{
                override fun onContactSelected(contact: Resource) {
                    startChatActivity(contact)
                }
            })
        }
    }
}