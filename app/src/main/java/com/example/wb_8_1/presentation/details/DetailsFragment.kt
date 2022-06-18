package com.example.wb_8_1.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.wb_8_1.R
import com.example.wb_8_1.databinding.FragmentDetailsBinding
import com.example.wb_8_1.domain.model.DotaHeroModelDomain
import com.example.wb_8_1.utils.Constants

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        binding?.detailsToolbar?.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        binding?.apply {

            val hero = arguments?.getSerializable("hero") as DotaHeroModelDomain

            detailsToolbar.title = hero.name

            heroNameDetailsTextView.text = hero.name
            strTextView.text = "${hero.baseStr.toInt()} + ${hero.strGain}"
            agiTextView.text = "${hero.baseAgi.toInt()} + ${hero.agiGain}"
            intTextView.text = "${hero.baseInt.toInt()} + ${hero.intGain}"
            attackTypeTextView.text = hero.attackType
            baseHealthTextView.text = "${hero.baseHealth.toInt()} + ${hero.baseHealthRegen}"
            baseManaTextView.text = "${hero.baseMana.toInt()} + ${hero.baseManaRegen}"
            baseMrTextView.text = "${hero.baseMr.toInt()}%"
            baseArmorTextView.text = "${hero.baseArmor.toInt()}"
            damageTextView.text = "${hero.baseAttackMin.toInt()} - ${hero.baseAttackMax.toInt()}"
            attackRangeTextView.text = "${hero.attackRange.toInt()}"
            moveSpeedTextView.text = "${hero.moveSpeed.toInt()}"
            legsTextView.text = "${hero.legs}"

            heroIconDetailsImageView.load(
                Constants.BASE_IMAGE_URL
                        + hero.icon
            ) {
                placeholder(R.drawable.dota_icon)
                error(R.drawable.dota_icon)
            }

            heroImageImageView.load(
                Constants.BASE_IMAGE_URL
                        + hero.img
            ){
                error(R.drawable.dota_icon)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}