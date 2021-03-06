package com.shivamsingh.blog_lite.ui.features.posts

import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.domain.usecase.FetchPostsUseCase
import com.shivamsingh.blog_lite.ui.base.AbstractPresenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.Presenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.View
import com.shivamsingh.blog_lite.ui.mapper.PostDisplayableItemMapper
import com.shivamsingh.blog_lite.ui.model.PostEntity
import timber.log.Timber
import javax.inject.Inject

class PostsPresenter @Inject constructor(private val fetchPostsUseCase: FetchPostsUseCase,
                                         private val postDisplayableItemMapper: PostDisplayableItemMapper) : AbstractPresenter(), Presenter {
    private var view: View? = null

    override fun takeView(view: View) {
        this.view = view
        disposables.add(fetchPostsUseCase)
    }

    override fun onPostSelection(post: PostEntity) {
        view?.viewPost(post)
    }

    override fun fetchPosts() {
        view?.showLoading()
        fetchPostsUseCase.execute({ showPosts(it) }, { fetchingPostsFailed(it) }, Unit)
    }

    fun showPosts(posts: List<Post>) {
        view?.showPosts(postDisplayableItemMapper.map(posts))
        view?.hideLoading()
    }

    fun fetchingPostsFailed(exception: Throwable) {
        Timber.e(exception)
        view?.showFetchingFailed()
        view?.hideLoading()
    }
}