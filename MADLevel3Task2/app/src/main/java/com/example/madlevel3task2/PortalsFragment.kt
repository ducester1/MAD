package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_portals.*

private var portals: ArrayList<Portal> = arrayListOf()

class PortalsFragment : Fragment() {

    private var portalAdapter: PortalAdapter = PortalAdapter(portals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_portals.adapter = portalAdapter
        rv_portals.layoutManager = GridLayoutManager(this.context, 2)

        portalAdded()
    }

    private fun portalAdded() {
        val portalName = arguments?.getString(ARG_PORTAL_TITLE)
        val portalUrl = arguments?.getString(ARG_PORTAL_URL)
        if (!portalName.isNullOrBlank() && !portalUrl.isNullOrBlank()) {
            val portal = Portal(portalName, portalUrl)
            portals.add(portal)
            portalAdapter.notifyDataSetChanged()
        }
    }
}