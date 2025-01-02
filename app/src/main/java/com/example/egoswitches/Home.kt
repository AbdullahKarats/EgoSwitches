package com.example.egoswitches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.egoswitches.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var bottomNav: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNav = (activity as MainActivity).binding.bottomNavigation
        binding.egoSwitch.isChecked = true
        setupSwitches()
        setupBottomNavListener()
    }

    private fun setupSwitches() {
        val switchData = listOf(
            Pair("Giveness", R.drawable.giving),
            Pair("Optimism", R.drawable.optimistic),
            Pair("Respect", R.drawable.respect),
            Pair("Kindness", R.drawable.kindness),
            Pair("Happiness", R.drawable.happiness)
        )
        val switches = listOf(
            binding.givingSwitch,
            binding.kindnessSwitch,
            binding.respectSwitch,
            binding.optimismSwitch,
            binding.happinessSwtich
        )

        binding.egoSwitch.setOnCheckedChangeListener { _, isChecked ->
            bottomNav.visibility = if (isChecked) View.VISIBLE else View.INVISIBLE
            switches.forEach { it.isEnabled = isChecked }
        }

        switches.forEachIndexed { index, switch ->
            switch.setOnCheckedChangeListener { _, isChecked ->
                val (title, iconResId) = switchData[index]
                if (bottomNav.menu.size() < 5) {
                    if (isChecked) {
                        // Switch açıldığında item ekle
                        bottomNav.menu.add(0, index, index, title).setIcon(iconResId)
                    } else {
                        // Switch kapandığında item'ı kaldır
                        bottomNav.menu.removeItem(index)
                    }
                } else {
                    Toast.makeText(context, "5TEN FAZLA SWİTCH AÇILAMAZ", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // BottomNavigationView item seçildiğinde fragment yönlendirme
    private fun setupBottomNavListener() {
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.giving -> {
                    findNavController().navigate(R.id.giving)
                    true
                }

                R.id.optimism -> {
                    findNavController().navigate(R.id.optimism)
                    true
                }

                R.id.respect -> {
                    findNavController().navigate(R.id.respect)
                    true
                }

                R.id.kindness -> {
                    findNavController().navigate(R.id.kindness)
                    true
                }

                R.id.happiness -> {
                    findNavController().navigate(R.id.happiness)
                    true
                }

                else -> false
            }
        }
    }
}
