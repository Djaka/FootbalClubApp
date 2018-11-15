package com.djakapermana.footbalclubapp.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.djakapermana.footbalclubapp.model.FavoriteMatch
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 2) {

    companion object {
        var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {

            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }

            return instance as DatabaseOpenHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteMatch.TABLE_FAVORITE, true,
                FavoriteMatch.idEvent to INTEGER + PRIMARY_KEY,
                FavoriteMatch.strEvent to TEXT,
                FavoriteMatch.strHomeTeam to TEXT,
                FavoriteMatch.strAwayTeam to TEXT,
                FavoriteMatch.intHomeScore to INTEGER,
                FavoriteMatch.intAwayScore to INTEGER,
                FavoriteMatch.strHomeGoalDetails to TEXT,
                FavoriteMatch.strHomeRedCards to TEXT,
                FavoriteMatch.strHomeYellowCards to TEXT,
                FavoriteMatch.strHomeLineupGoalkeeper to TEXT,
                FavoriteMatch.strHomeLineupDefense to TEXT,
                FavoriteMatch.strHomeLineupMidfield to TEXT,
                FavoriteMatch.strHomeLineupForward to TEXT,
                FavoriteMatch.strHomeLineupSubstitutes to TEXT,
                FavoriteMatch.strHomeFormation to TEXT,
                FavoriteMatch.strAwayRedCards to TEXT,
                FavoriteMatch.strAwayYellowCards to TEXT,
                FavoriteMatch.strAwayGoalDetails to TEXT,
                FavoriteMatch.strAwayLineupGoalkeeper to TEXT,
                FavoriteMatch.strAwayLineupDefense to TEXT,
                FavoriteMatch.strAwayLineupMidfield to TEXT,
                FavoriteMatch.strAwayLineupForward to TEXT,
                FavoriteMatch.strAwayLineupSubstitutes to TEXT,
                FavoriteMatch.strAwayFormation to TEXT,
                FavoriteMatch.intHomeShots to TEXT,
                FavoriteMatch.intAwayShots to TEXT,
                FavoriteMatch.idHomeTeam to INTEGER,
                FavoriteMatch.idAwayTeam to INTEGER,
                FavoriteMatch.strDate to TEXT,
                FavoriteMatch.strTime to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
    }
}

val Context.database:DatabaseOpenHelper
get() = DatabaseOpenHelper.getInstance(applicationContext)