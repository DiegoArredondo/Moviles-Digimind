package arredondo.diego.digimind.ui.home

import Task
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import arredondo.diego.digimind.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.task_view.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel



    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        root.tasksGridView.adapter = AdaptadorTasks(root.context,tasks)

        return root
    }

    fun cargarTasks(){
        var days = ArrayList<String>()
        days.add("Monday")
        days.add("Wednesday")
        days.add("Friday")
        tasks.add(Task("Practice", "17:00", days))
        tasks.add(Task("Practice", "17:00", days))
        tasks.add(Task("Practice", "17:00", days))
        tasks.add(Task("Practice", "17:00", days))
        tasks.add(Task("Practice", "17:00", days))
        tasks.add(Task("Practice", "17:00", days))

    }
}


private class AdaptadorTasks: BaseAdapter {
    var tasks = ArrayList<Task>()
    var contexto: Context? = null

    constructor(contexto: Context, tasks: ArrayList<Task>){
        this.contexto = contexto
        this.tasks = tasks
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var task = tasks[position]
        var inflador = LayoutInflater.from(contexto)
        var vista = inflador.inflate(R.layout.task_view, null)

        vista.taskName.text = task.title
        vista.taskTime.text = task.time
        vista.taskWhen.text = if(task.days.size == 7) "Everyday" else task.days.toString().replace("[", "").replace("]","")
        return vista
    }

    override fun getItem(position: Int): Any {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return tasks.size
    }

}