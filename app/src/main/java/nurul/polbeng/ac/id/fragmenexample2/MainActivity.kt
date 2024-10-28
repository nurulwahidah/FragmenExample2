package nurul.polbeng.ac.id.fragmentexample1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import nurul.polbeng.ac.id.fragmenexample2.R

class MainActivity : AppCompatActivity(), Coordinator {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragBookDescription: BookDescription
    private lateinit var fragBookTitle: BookTitle

    private val BOOK_TITLE_TAG = "bookTitleFragment"
    private val BOOK_DESCRIPTION_TAG = "bookDescriptionFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            fragBookTitle = BookTitle()
            fragBookDescription = BookDescription()
        }else{
            fragBookTitle =
                supportFragmentManager.findFragmentByTag(BOOK_TITLE_TAG) as BookTitle
            fragBookDescription =
                supportFragmentManager.findFragmentByTag(BOOK_DESCRIPTION_TAG) as BookDescription
        }
        if (!fragBookTitle.isInLayout) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragtop.id, fragBookTitle,
                    BOOK_TITLE_TAG)
                .replace(binding.fragbottom.id, fragBookDescription,
                    BOOK_DESCRIPTION_TAG)
                .commit()
        }

    }
    override fun onBookChanged(index:Int) {
        fragBookDescription.changeDescription(index)
        val frag =
            supportFragmentManager.findFragmentById(R.id.fragment_book_description)
        if (frag is BookDescription) {
            frag.changeDescription(index)
        }
    }
}