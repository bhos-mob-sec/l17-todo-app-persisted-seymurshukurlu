package az.edu.bhos.l14todoapp.flows.edit

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import az.edu.bhos.l14todoapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditTodoActivity : AppCompatActivity() {

    private val viewModel: EditTodoViewModel by viewModel()

    private lateinit var backBtn: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_todo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI()

        backBtn.setOnClickListener {
            finish()
        }

        viewModel.selectedCategory.observe(this) { _ ->
        }
    }

    private fun setupUI() {
        backBtn = findViewById(R.id.backBtn)
    }
}