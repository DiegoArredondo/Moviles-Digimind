package arredondo.diego.digimind.ui.dashboard

import Task
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import arredondo.diego.digimind.R
import arredondo.diego.digimind.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
//

        var dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        root.showTimePickerButton.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                showTimePickerButton.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        root.saveTaskButton.setOnClickListener {
            var title = txtFormTask.text.toString()
            var time = showTimePickerButton.text.toString()

            var days = ArrayList<String>()

            if(cbMonday.isChecked) days.add("Monday")
            if(cbTuesday.isChecked) days.add("Tuesday")
            if(cbThursday.isChecked) days.add("Thursday")
            if(cbWednesday.isChecked) days.add("Wednesday")
            if(cbFriday.isChecked) days.add("Friday")
            if(cbSaturday.isChecked) days.add("Saturday")
            if(cbSunday.isChecked) days.add("Sunday")

            if(title.trim().isEmpty()) {
                Toast.makeText(root.context, "Let me know what to remind you", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (time.equals("Set Time")) {
                    Toast.makeText(root.context, "Let me know at what time", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (days.isEmpty()) {
                        Toast.makeText(
                            root.context,
                            "Let me know when to do it",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    else {
                        var task = Task(title, time, days)
                        HomeFragment.tasks.add(task)
                        Toast.makeText(root.context,"Task added", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return root
    }
}