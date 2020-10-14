package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

const val ARG_PORTAL_TITLE = "arg_portal_name"
const val ARG_PORTAL_URL = "arg_portal_url"

class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etUrl.setText(R.string.url_start)

        view.findViewById<Button>(R.id.btn_add_portal).setOnClickListener {
            onAddPortal()
        }
    }

    private fun onAddPortal() {
        val portalTitle = etTitle.text.toString()
        val portalUrl =  etUrl.text.toString()

        if(!portalTitle.isNullOrBlank() && !portalUrl.isNullOrBlank()) {
            val args = Bundle()
            args.putString(ARG_PORTAL_TITLE, portalTitle)
            args.putString(ARG_PORTAL_URL, portalUrl)

            findNavController().navigate(R.id.action_addPortalFragment_to_portalsFragment, args)
        } else {
            Toast.makeText(activity, "Fields cannot be empty!", Toast.LENGTH_LONG).show()
        }
    }
}