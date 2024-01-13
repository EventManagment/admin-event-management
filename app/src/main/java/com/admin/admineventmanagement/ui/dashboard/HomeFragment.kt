package com.admin.admineventmanagement.ui.dashboard

import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.admin.admineventmanagement.R
import com.admin.admineventmanagement.adapter.UserEventAdapter
import com.admin.admineventmanagement.custom.style.SpaceItemDecoration
import com.admin.admineventmanagement.databinding.FragmentHomeBinding
import com.admin.admineventmanagement.viewmodel.ManagementViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private val viewModel: ManagementViewModel by activityViewModels()

    private lateinit var pieChart: PieChart

    private val calendar: Calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        pieChart = binding.pieChart

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing) // Define the desired spacing in pixels
        binding.recyclerView.addItemDecoration(SpaceItemDecoration(spacingInPixels))
        binding.recyclerView.adapter = UserEventAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }

        val entries: MutableList<PieEntry> = ArrayList()
//        entries.add(PieEntry(viewModel.userJoin.value?.size!!.toFloat(), "User Join"))
//        entries.add(PieEntry(viewModel.userAbsent.value?.size!!.toFloat(), "User Absent"))
//        entries.add(PieEntry(viewModel.userJoinAndRegister.value?.size!!.toFloat(), "User Join and Register"))

        entries.add(PieEntry(30f, "UserJoin"))
        entries.add(PieEntry(20f, "UserAbsent"))
        entries.add(PieEntry(50f, "UserJoin&Register"))

        val dataSet = PieDataSet(entries, null)
        dataSet.isHighlightEnabled = true
        dataSet.colors = listOf(Color.RED, Color.GREEN, Color.rgb(0,191,255))
        dataSet.valueTextSize = 16f
        dataSet.valueTextColor = Color.WHITE
        dataSet.sliceSpace = 3f

        val legend: Legend = pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.textSize = 16f
        legend.setDrawInside(false)

        pieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            legend.isEnabled = true
            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(16f)
            setHoleColor(Color.WHITE)
            centerText = "Attendance: 150"
//            centerText = viewModel.userJoin.value?.size.toString()
//            animateY(1000, Easing.EaseInOutQuad)
        }

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}