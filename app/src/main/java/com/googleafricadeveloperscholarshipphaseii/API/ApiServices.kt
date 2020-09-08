package com.testkotlin.API

import com.googleafricadeveloperscholarshipphaseii.model.hours.resonse.ResponseHours
import com.googleafricadeveloperscholarshipphaseii.model.skills.response.ResponseSkills
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    //region learning route

    @GET("api/hours")
    fun getHours(): Observable<List<ResponseHours>>

    @GET("api/skilliq")
    fun getSkills(): Observable<List<ResponseSkills>>
    //endregion

    //region doc route
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun uploadDoc(
        @Field("entry.1824927963") email: String,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") linkProject: String
    ): Observable<ResponseBody>
    //endregion
}