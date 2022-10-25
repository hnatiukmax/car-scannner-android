package dev.hnatiuk.carscanner.presentation.pages.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.hnatiuk.carscanner.databinding.FragmentHistoryBinding
import org.jetbrains.anko.AnkoContext

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return SettingsFragmentUI().createView(AnkoContext.create(requireContext(), this))
    }
}