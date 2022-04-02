package com.base.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.base.data.entities.Movie
import com.base.data.entities.MovieListResponse

class MoviePagingSource(
    private val remoteDataSource: suspend (Int) -> Result<MovieListResponse>
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 1
            when (val response = remoteDataSource(nextPageNumber)) {
                is Result.Success -> {
                    val nextKey = if (response.data?.totalPages != response.data?.page)
                        response.data?.page?.plus(1) else null
                    LoadResult.Page(
                        data = response.data?.results ?: listOf(),
                        prevKey = if (nextPageNumber == 1) null else nextPageNumber,
                        nextKey = nextKey
                    )
                }
                is Result.Error -> {
                    LoadResult.Error(Throwable(response.exception ?: ""))
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}