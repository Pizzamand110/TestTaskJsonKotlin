package ua.dimaevseenko.testtask

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ua.dimaevseenko.testtask.adapters.RecyclerViewAdapter
import ua.dimaevseenko.testtask.framents.MainFragment
import ua.dimaevseenko.testtask.framents.SecondFragment

public class MainActivity: AppCompatActivity(), RecyclerViewAdapter.ViewHolder.OnImageClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)
    }

    override fun onClick(photo: Photo) {
        supportFragmentManager.beginTransaction().replace(R.id.mainFragmentContainer, SecondFragment(photo)).commit()
        aBar(true)
    }

    fun aBar(flag: Boolean){
        supportActionBar?.setDisplayHomeAsUpEnabled(flag)
        supportActionBar?.setDisplayShowHomeEnabled(flag)
    }

    override fun onBackPressed() {
        var fragments: List<Fragment> = supportFragmentManager.fragments

        for(f: Fragment in fragments){
            if(f.toString()=="MainFragment")
                super.onBackPressed()
            else{
                supportFragmentManager.beginTransaction().replace(R.id.mainFragmentContainer, MainFragment()).commit()
                aBar(false)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.mainFragmentContainer, MainFragment()).commit()
        aBar(false)
        return true
    }
}