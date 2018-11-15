package com.djakapermana.footbalclubapp.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.djakapermana.footbalclubapp.*
import com.djakapermana.footbalclubapp.helper.GeneralFunctions.dateConverter
import com.djakapermana.footbalclubapp.helper.GeneralFunctions.retrofit
import com.djakapermana.footbalclubapp.helper.GeneralFunctions.timeFormater
import com.djakapermana.footbalclubapp.interfaces.IMatch
import com.djakapermana.footbalclubapp.model.FavoriteMatch
import com.djakapermana.footbalclubapp.model.Match
import com.djakapermana.footbalclubapp.model.Team
import com.djakapermana.footbalclubapp.model.TeamResult
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterMatch(private val matches:List<Match>, private val context: Context): RecyclerView.Adapter<MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.row_match,parent,false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holderFavorite: MatchViewHolder, position: Int) {
        holderFavorite.bindItem(matches[position], context)
    }
}

class MatchViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
    private val textViewDate:TextView = itemView!!.findViewById(R.id.txt_date)
    private val textViewHome:TextView = itemView!!.findViewById(R.id.txt_home)
    private val textViewHomeScore:TextView = itemView!!.findViewById(R.id.txt_home_score)
    private val textViewAway:TextView = itemView!!.findViewById(R.id.txt_away)
    private val textViewAwayScore:TextView = itemView!!.findViewById(R.id.txt_away_score)
    private val linMatch:LinearLayout = itemView!!.findViewById(R.id.lin_match)
    private val imageHome:ImageView = itemView!!.findViewById(R.id.img_home)
    private val imageAway:ImageView = itemView!!.findViewById(R.id.img_away)

    fun bindItem(matches: Match, context:Context){

        val macth: IMatch = retrofit.create(IMatch::class.java)
        val callHome = macth.getImageTeam(matches.idHomeTeam.toString())

        callHome.enqueue(object : Callback<TeamResult> {

            override fun onResponse(call: Call<TeamResult>, response: Response<TeamResult>) {
                val result = response.body()
                val teams:List<Team> = result!!.teams
                Picasso.get()
                        .load(teams[0].strTeamBadge)
                        .placeholder(R.drawable.ic_image)
                        .error(R.drawable.ic_broken)
                        .into(imageHome)
            }

            override fun onFailure(call: Call<TeamResult>, t: Throwable) {
                t.printStackTrace()
            }
        })

        val callAway = macth.getImageTeam(matches.idAwayTeam.toString())
        callAway.enqueue(object :Callback<TeamResult>{

            override fun onResponse(call: Call<TeamResult>, response: Response<TeamResult>) {
                val result = response.body()
                val teams:List<Team> = result!!.teams
                Picasso.get()
                        .load(teams[0].strTeamBadge)
                        .placeholder(R.drawable.ic_image)
                        .error(R.drawable.ic_broken)
                        .into(imageAway)
            }

            override fun onFailure(call: Call<TeamResult>, t: Throwable) {
                t.printStackTrace()
            }

        })

        textViewDate.text = dateConverter(matches.strDate) + " " + timeFormater(matches.strTime)
        textViewHome.text = matches.strHomeTeam
        textViewHomeScore.text = matches.intHomeScore.toString()
        textViewAway.text = matches.strAwayTeam
        textViewAwayScore.text = matches.intAwayScore.toString()
        linMatch.setOnClickListener {
            val intent = Intent(context, DetailMatchActivity::class.java)
            intent.putExtra("id",matches.idEvent)
            context.startActivity(intent)
        }
    }
}