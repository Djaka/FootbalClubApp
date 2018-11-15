package com.djakapermana.footbalclubapp

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.Toast
import com.djakapermana.footbalclubapp.helper.GeneralFunctions.dateConverter
import com.djakapermana.footbalclubapp.helper.GeneralFunctions.retrofit
import com.djakapermana.footbalclubapp.helper.GeneralFunctions.timeFormater
import com.djakapermana.footbalclubapp.helper.database
import com.djakapermana.footbalclubapp.interfaces.IMatch
import com.djakapermana.footbalclubapp.model.*
import com.djakapermana.footbalclubapp.R.drawable.ic_add_favorites
import com.djakapermana.footbalclubapp.R.drawable.ic_added_favorites
import com.djakapermana.footbalclubapp.R.id.add_to_favorite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchActivity : AppCompatActivity() {

    var menuItem: Menu? = null
    var isFavoriteMatch: Boolean = false
    var detailResult: List<MatchDetail>? = null
    var teamsHome: List<Team>? = null
    var teamsAway: List<Team>? = null
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Detail Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        container.visibility = ScrollView.GONE

        id = intent.getIntExtra("id", 0)

        favoriteState()

        Handler().post(Runnable {
            loadData(id)
        })

    }

    fun loadData(id: Int) {
        val match: IMatch = retrofit.create(IMatch::class.java)

        val call = match.getMatchDetail(id.toString())

        call.enqueue(object : Callback<MatchDetailResult> {
            override fun onResponse(call: Call<MatchDetailResult>, response: Response<MatchDetailResult>) {
                val result = response.body()
                detailResult = result?.events

                supportActionBar?.subtitle = detailResult?.get(0)?.strEvent

                txt_date_event.text = dateConverter(detailResult?.get(0)?.strDate.toString()) + " " + timeFormater(detailResult?.get(0)?.strTime.toString())

                txt_home.text = detailResult?.get(0)?.strHomeTeam
                txt_home_score.text = detailResult?.get(0)?.intHomeScore.toString()
                txt_home_goal.text = detailResult?.get(0)?.strHomeGoalDetails
                txt_home_goal_keeper.text = detailResult?.get(0)?.strHomeLineupGoalkeeper
                txt_home_defense.text = detailResult?.get(0)?.strHomeLineupDefense
                txt_home_forward.text = detailResult?.get(0)?.strHomeLineupForward
                txt_home_substitutes.text = detailResult?.get(0)?.strHomeLineupSubstitutes


                txt_away.text = detailResult?.get(0)?.strAwayTeam
                txt_away_score.text = detailResult?.get(0)?.intAwayScore.toString()
                txt_away_goal.text = detailResult?.get(0)?.strAwayGoalDetails
                txt_away_goal_keeper.text = detailResult?.get(0)?.strAwayLineupGoalkeeper
                txt_away_defense.text = detailResult?.get(0)?.strAwayLineupDefense
                txt_away_forward.text = detailResult?.get(0)?.strAwayLineupForward
                txt_away_substitutes.text = detailResult?.get(0)?.strAwayLineupSubstitutes

                progress.visibility = ProgressBar.GONE
                container.visibility = ScrollView.VISIBLE

//                favoriteState()

                val callHome = match.getImageTeam(detailResult?.get(0)?.idHomeTeam.toString())

                callHome.enqueue(object : Callback<TeamResult> {

                    override fun onResponse(call: Call<TeamResult>, response: Response<TeamResult>) {
                        val resultHome = response.body()
                        teamsHome = resultHome?.teams
                        Picasso.get()
                                .load(teamsHome?.get(0)?.strTeamBadge)
                                .placeholder(R.drawable.ic_image)
                                .error(R.drawable.ic_broken)
                                .into(img_home)
                    }

                    override fun onFailure(call: Call<TeamResult>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

                val callAway = match.getImageTeam(detailResult?.get(0)?.idAwayTeam.toString())
                callAway.enqueue(object : Callback<TeamResult> {

                    override fun onResponse(call: Call<TeamResult>, response: Response<TeamResult>) {
                        val resultAway = response.body()
                        teamsAway = resultAway?.teams
                        Picasso.get()
                                .load(teamsAway?.get(0)?.strTeamBadge)
                                .placeholder(R.drawable.ic_image)
                                .error(R.drawable.ic_broken)
                                .into(img_away)
                    }

                    override fun onFailure(call: Call<TeamResult>, t: Throwable) {
                        t.printStackTrace()
                    }

                })
            }

            override fun onFailure(call: Call<MatchDetailResult>, t: Throwable) {
                progress.visibility = ProgressBar.GONE
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavoriteMatch) {
                    removeFromFavorite()
                } else {
                    addToFavorite()
                }

                isFavoriteMatch = !isFavoriteMatch
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavoriteMatch)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_favorites)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE,
                        FavoriteMatch.idEvent to detailResult?.get(0)?.idEvent,
                        FavoriteMatch.strEvent to detailResult?.get(0)?.strEvent,
                        FavoriteMatch.strHomeTeam to detailResult?.get(0)?.strHomeTeam,
                        FavoriteMatch.strAwayTeam to detailResult?.get(0)?.strAwayTeam,
                        FavoriteMatch.intHomeScore to detailResult?.get(0)?.intHomeScore,
                        FavoriteMatch.intAwayScore to detailResult?.get(0)?.intAwayScore,
                        FavoriteMatch.strHomeGoalDetails to detailResult?.get(0)?.strHomeGoalDetails,
                        FavoriteMatch.strHomeRedCards to detailResult?.get(0)?.strHomeRedCards,
                        FavoriteMatch.strHomeYellowCards to detailResult?.get(0)?.strHomeYellowCards,
                        FavoriteMatch.strHomeLineupGoalkeeper to detailResult?.get(0)?.strHomeLineupGoalkeeper,
                        FavoriteMatch.strHomeLineupDefense to detailResult?.get(0)?.strHomeLineupDefense,
                        FavoriteMatch.strHomeLineupMidfield to detailResult?.get(0)?.strHomeLineupMidfield,
                        FavoriteMatch.strHomeLineupForward to detailResult?.get(0)?.strHomeLineupForward,
                        FavoriteMatch.strHomeLineupSubstitutes to detailResult?.get(0)?.strHomeLineupSubstitutes,
                        FavoriteMatch.strHomeFormation to detailResult?.get(0)?.strHomeFormation,
                        FavoriteMatch.strAwayRedCards to detailResult?.get(0)?.strAwayRedCards,
                        FavoriteMatch.strAwayYellowCards to detailResult?.get(0)?.strAwayYellowCards,
                        FavoriteMatch.strAwayGoalDetails to detailResult?.get(0)?.strAwayGoalDetails,
                        FavoriteMatch.strAwayLineupGoalkeeper to detailResult?.get(0)?.strAwayLineupGoalkeeper,
                        FavoriteMatch.strAwayLineupDefense to detailResult?.get(0)?.strAwayLineupDefense,
                        FavoriteMatch.strAwayLineupMidfield to detailResult?.get(0)?.strAwayLineupMidfield,
                        FavoriteMatch.strAwayLineupForward to detailResult?.get(0)?.strAwayLineupForward,
                        FavoriteMatch.strAwayLineupSubstitutes to detailResult?.get(0)?.strAwayLineupSubstitutes,
                        FavoriteMatch.strAwayFormation to detailResult?.get(0)?.strAwayFormation,
                        FavoriteMatch.intHomeShots to detailResult?.get(0)?.intHomeShots,
                        FavoriteMatch.intAwayShots to detailResult?.get(0)?.intAwayShots,
                        FavoriteMatch.idHomeTeam to detailResult?.get(0)?.idHomeTeam,
                        FavoriteMatch.idAwayTeam to detailResult?.get(0)?.idAwayTeam,
                        FavoriteMatch.strDate to detailResult?.get(0)?.strDate,
                        FavoriteMatch.strTime to detailResult?.get(0)?.strTime
                )

                FavoriteMatchFragment().loadData()
            }
            Snackbar.make(gen_container, "This Event Added to Favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(gen_container, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE, "(ID_EVENT = {id})", "id" to id)
            }
            Snackbar.make(gen_container, "Removed to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(gen_container, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
                    .whereArgs("(ID_EVENT = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            isFavoriteMatch = favorite.isNotEmpty()
//            if (!favorite.isEmpty()) isFavoriteMatch = tru
        }
    }
}
