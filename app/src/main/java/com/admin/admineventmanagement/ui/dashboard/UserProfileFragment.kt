package com.admin.admineventmanagement.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.admin.admineventmanagement.R
import com.admin.admineventmanagement.databinding.FragmentUserProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var btnBack: ImageView
    private lateinit var btnEdit: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.homeFragment)
        }
        binding.edit.setOnClickListener{
            findNavController().navigate(R.id.editProfile)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}