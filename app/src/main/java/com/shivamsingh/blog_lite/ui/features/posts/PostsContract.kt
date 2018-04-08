package com.shivamsingh.blog_lite.ui.features.posts

import com.shivamsingh.blog_lite.ui.base.BasePresenter
import com.shivamsingh.blog_lite.ui.base.BaseView
import com.shivamsingh.blog_lite.ui.base.PresenterLifecycle
import com.shivamsingh.blog_lite.ui.features.posts.entity.PostViewEntity

interface PostsContract {

    interface Presenter : BasePresenter<View>, PresenterLifecycle {

        fun loadPosts()
    }

    interface View : BaseView<Presenter> {

        fun showPosts(posts: List<PostViewEntity>)
    }
}