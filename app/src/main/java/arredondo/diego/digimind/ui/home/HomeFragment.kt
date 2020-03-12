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

    var tasks = ArrayList<Task>();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        cargarTasks()
        root.tasksGridView.adapter = AdaptadorTasks(root.context,tasks)

        return root
    }

    fun cargarTasks(){
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
        tasks.add(Task("Practice", "Everyday", "17:00"))
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

        vista.taskName.setText(task.name)
        vista.taskWhen.setText(task.When)
        vista.taskTime.setText(task.time)
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