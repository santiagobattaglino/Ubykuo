package com.battaglino.santiago.ubykuo.ui.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.battaglino.santiago.ubykuo.R
import com.battaglino.santiago.ubykuo.db.entity.Repo
import kotlinx.android.synthetic.main.listitem_repo.view.*

/**
 * Created by Santiago Battaglino.
 */
class RepoAdapter(
        private val context: Context,
        private val clickListener: RepoAdapter.OnViewHolderClick?,
        internal var mRepos: List<Repo>?) :
        RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        bindView(getItem(position), holder)
    }

    private fun bindView(repo: Repo?, viewHolder: RepoViewHolder) {
        if (repo != null) {
            setUpRepo(repo, viewHolder)
        }
    }

    private fun setUpRepo(repo: Repo, viewHolder: RepoViewHolder) {
        viewHolder.text1.text = repo.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(createView(context, parent, viewType), clickListener)
    }

    private fun createView(context: Context, viewGroup: ViewGroup, viewType: Int): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(R.layout.listitem_repo, viewGroup, false)
    }

    override fun getItemCount(): Int {
        return mRepos?.size ?: 0
    }

    private fun getItem(index: Int): Repo? {
        return if (mRepos != null && index < mRepos!!.size) mRepos!![index] else null
    }

    interface OnViewHolderClick {
        fun onClick(view: View, position: Int, item: Repo?)
    }

    inner class RepoViewHolder
    internal constructor(
            view: View,
            listener: RepoAdapter.OnViewHolderClick?
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val text1 = view.text1!!

        init {
            if (listener != null)
                view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            clickListener?.onClick(view, adapterPosition, getItem(adapterPosition))
        }
    }
}