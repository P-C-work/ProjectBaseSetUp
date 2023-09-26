package com.example.projectbasesetup.data.services


import com.example.projectbasesetup.models.CatsDataModel
import com.example.projectbasesetup.models.PostVoteModel
import com.example.projectbasesetup.models.VoteSuccessModel
import com.example.projectbasesetup.models.VotesDataModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CatsService {


    @GET("v1/images/search")
    suspend fun fetchCatsImages(
        @Query("limit") limit: Int
    ): Response<List<CatsDataModel>>

    /*   @GET("v1/images/{image_id}/analysis")
       suspend fun fetchCatDetails(
           @Path("image_id") imageId: String,
       ): Response<ESimResponse>*/

    @GET("v1/votes")
    suspend fun fetchVotes(): Response<VotesDataModel>

    @POST("v1/images/{image_id}/analysis")
    suspend fun postVote(
        @Body request: PostVoteModel
    ): Response<VoteSuccessModel>
}