package com.djakapermana.footbalclubapp


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.djakapermana.footbalclubapp.adapter.AdapterFavoriteMatch
import com.djakapermana.footbalclubapp.helper.database
import com.djakapermana.footbalclubapp.model.FavoriteMatch
import kotlinx.android.synthetic.main.fragment_favorite_match.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMatchFragment : Fragment() {

    private lateinit var adapterFavorite: AdapterFavoriteMatch
    var favorites:List<FavoriteMatch> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_match, container, false)

        loading()

        view.swip_refresh.setOnRefreshListener {
            view.swip_refresh.isRefreshing = false

            loading()
        }

        return view
    }

    fun loading() {
        view?.progress?.visibility = ProgressBar.VISIBLE
        view?.rec_favorite_match?.visibility = RecyclerView.GONE
        Handler().post(Runnable {
            loadData()
        })
    }

    fun loadData() {
        view?.context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
            favorites = result.parseList(classParser())
        }

        adapterFavorite = AdapterFavoriteMatch(favorites, view?.context)
        adapterFavorite.notifyDataSetChanged()

        view?.progress?.visibility = ProgressBar.GONE
        view?.rec_favorite_match?.visibility = RecyclerView.VISIBLE

        view?.rec_favorite_match?.layoutManager = LinearLayoutManager(view?.context)
        view?.rec_favorite_match?.adapter = adapterFavorite
    }


}
