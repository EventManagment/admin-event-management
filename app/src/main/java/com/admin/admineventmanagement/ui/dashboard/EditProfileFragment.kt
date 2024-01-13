package com.admin.admineventmanagement.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.admin.admineventmanagement.R
import com.admin.admineventmanagement.databinding.FragmentUserProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var btnBack: ImageView
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.userProfileFragment)
        }

//        binding.submit.setOnClickListener{
//            findNavController().navigate(R.id.userProfileFragment)
//        }
    }
}