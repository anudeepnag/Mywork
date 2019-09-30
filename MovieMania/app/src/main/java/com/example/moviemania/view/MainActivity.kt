package com.example.moviemania.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.moviemania.R
import com.example.moviemania.supporting.ItemClickListent
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    private var twoPane: Boolean = false
    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
        initView()

    }

    private fun injectDependency() {
        AndroidInjection.inject(this)
    }
    private fun initView() {
        if (twoPane) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            val newCurrentFragment = MovieListFragment()
            newCurrentFragment.setOnItemListner(itemClickEvent)
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            newCurrentFragment.apply {


                arguments = Bundle().apply {

                    putBoolean(MainActivity.MULTI_PANE, false)
                }
            }
            transaction.replace(R.id.item_detail_container, newCurrentFragment, "tag")
            transaction.commit()
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            val newCurrentFragment =
                supportFragmentManager.findFragmentById(R.id.list_fragment) as MovieListFragment
            newCurrentFragment.setOnItemListner(itemClickEvent)
        }

    }




    var itemClickEvent: ItemClickListent = object : ItemClickListent {
        override fun onItemClick(position: Int) {
            Log.d(MovieListFragment.TAG,"Request Movie-Id"+position)
           if (twoPane) {
               val transaction: FragmentTransaction? =
                   this@MainActivity.supportFragmentManager?.beginTransaction()
               val newCurrentFragment = DescriptionFragment()


               newCurrentFragment.apply {


                   arguments = Bundle().apply {

                       putInt(MOVIE_ID, position)
                   }
               }

               transaction?.replace(R.id.item_detail_container, newCurrentFragment, "tag")
               transaction?.addToBackStack(null)
               transaction?.commit()
           } else {
               val descriptionFragament: DescriptionFragment =
                   supportFragmentManager?.findFragmentById(R.id.detail_fragment) as DescriptionFragment
               descriptionFragament.getDetailsOfMovieId(position)

           }

        }
    }
    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val MULTI_PANE = "MultiPane"
        const val MOVIE_ID = "MovieId"
    }

}
