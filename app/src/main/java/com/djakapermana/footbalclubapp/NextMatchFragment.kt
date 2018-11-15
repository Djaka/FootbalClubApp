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
import android.widget.Toast
import com.djakapermana.footbalclubapp.adapter.AdapterMatch
import com.djakapermana.footbalclubapp.helper.GeneralFunctions
import com.djakapermana.footbalclubapp.interfaces.IMatch
import com.djakapermana.footbalclubapp.model.NextMatchResult
import kotlinx.android.synthetic.main.fragment_next_match.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_next_match, container, false)

        loading(view)

        view.swip_refresh.setOnRefreshListener {
            view.swip_refresh.isRefreshing = false

            loading(view)
        }

        return view
    }

    fun loading(view: View) {
        view.progress.visibility = ProgressBar.VISIBLE
        view.rec_next_match.visibility = RecyclerView.GONE

        Handler().post(Runnable {
            loadData(view)
        })
    }

    fun loadData(view: View) {
        val macth: IMatch = GeneralFunctions.retrofit.create(IMatch::class.java)
        val call = macth.getNextMatch()

        call.enqueue(object : Callback<NextMatchResult> {
            override fun onResponse(call: Call<NextMatchResult>, response: Response<NextMatchResult>) {
                val result = response.body()
                val adapter = AdapterMatch(result!!.events, view.context)

                view.progress.visibility = ProgressBar.GONE
                view.rec_next_match.visibility = RecyclerView.VISIBLE

                view.rec_next_match.layoutManager = LinearLayoutManager(view.context)
                view.rec_next_match.adapter = adapter

            }

            override fun onFailure(call: Call<NextMatchResult>, t: Throwable) {
                view.progress.visibility = ProgressBar.GONE
                Toast.makeText(view.context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}
