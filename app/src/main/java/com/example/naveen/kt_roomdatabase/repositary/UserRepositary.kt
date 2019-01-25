package com.example.naveen.kt_roomdatabase.repositary

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.naveen.kt_roomdatabase.model.Article
import com.example.naveen.kt_roomdatabase.model.ServerData
import com.example.naveen.kt_roomdatabase.roomdb.User
import com.example.naveen.kt_roomdatabase.roomdb.UserDAO
import com.example.naveen.kt_roomdatabase.roomdb.UserRoomDatabase
import com.example.naveen.kt_roomdatabase.webservices.ApiStories
import com.example.naveen.kt_roomdatabase.webservices.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepositary(application: Application) {

    private var userDAO: UserDAO
    private var allUserData: LiveData<List<User>>
    private var allServerData : LiveData<List<Article?>>? = null

    init {
        val userDb: UserRoomDatabase = UserRoomDatabase.getAppDataBase(application.applicationContext)!!
        userDAO = userDb.getUserDao()
        allUserData = userDAO.getAllData()

    }


    fun insertData(user: User) {
        InsertDataAsynchTask(userDAO).execute(user)

//            InsertDataAsynchTask(userDAO).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,user)
//        else
    }

    fun getAllData(): LiveData<List<User>> {
        return allUserData
    }


    companion object {

        class InsertDataAsynchTask(val userDAO: UserDAO) : AsyncTask<User, Void, Void>() {

            override fun doInBackground(vararg params: User?): Void? {
                userDAO.insert(params[0])
                return null
            }
        }

        class DeleteAllDataAsynchTask(val userDAO: UserDAO) : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                userDAO.deleteAllData()
                return null
            }
        }

        class DeleteDataAsynchTask(val userDAO: UserDAO) : AsyncTask<User, Void, Void>() {

            override fun doInBackground(vararg params: User): Void? {
                userDAO.deleteUser(params[0])
                return null
            }
        }
    }

    fun deleteAll() {
        DeleteAllDataAsynchTask(userDAO).execute()
    }


    fun deleteUser(user: User) {
        DeleteDataAsynchTask(userDAO).execute(user)
    }

    fun getDataFromServer(): LiveData<List<Article?>>? {
        val retrofitCall = RetrofitClient.getRetrotit().create(ApiStories::class.java)
        val networkCall = retrofitCall.getIndianNewsList(0, 10)
//        networkCall.enqueue(object : retrofit2.Callback<ServerData> {
//
//            override fun onResponse(call: Call<ServerData>?, response: Response<ServerData>?) {
//                if (response != null) {
//                    val productList: ArrayList<Article?> = response.body()!!.articles
//
//
//                }
//            }
//
//            override fun onFailure(call: Call<ServerData>?, t: Throwable?) {
//                //  homeView.hideProgress()
//            }
//        })
//
//        networkCall.enqueue(object : Callback<ServerData>>{
//
//        })

//       allServerData = null
        networkCall.enqueue(object : Callback<LiveData<ServerData>> {
            override fun onFailure(call: Call<LiveData<ServerData>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<LiveData<ServerData>>, response: Response<LiveData<ServerData>>) {
                if (response != null) {
                      val productList: LiveData<List<Article?>> = response.body()?.value?.articles!!
                      allServerData = productList
                }
            }

        })
        return allServerData
    }


}