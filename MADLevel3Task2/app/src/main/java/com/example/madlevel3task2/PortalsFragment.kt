package com.example.madlevel3task2

import CustomTabHelper
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_portals.*

private var portals: ArrayList<Portal> = arrayListOf()

class PortalsFragment : Fragment() {

    private var customTabHelper: CustomTabHelper = CustomTabHelper()
    private var portalAdapter: PortalAdapter = PortalAdapter(portals) { portal : Portal -> portalItemClicked(portal)}

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

    private fun portalItemClicked(portal: Portal) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        val packageName = customTabHelper.getPackageNameToUse(this.requireContext(), portal.url.toString())

        if (packageName == null) {
            println("Empty package name")
        } else {
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(this.requireContext(), Uri.parse(portal.url))
        }
    }
}