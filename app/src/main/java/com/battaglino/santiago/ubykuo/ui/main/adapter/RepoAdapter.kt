package com.battaglino.santiago.ubykuo.ui.main.adapter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.battaglino.santiago.ubykuo.R
import com.battaglino.santiago.ubykuo.db.entity.Repo
import kotlinx.android.synthetic.main.listitem_repo.view.*
import kotlin.properties.Delegates


/**
 * Created by Santiago Battaglino.
 */
class RepoAdapter(
        private val context: AppCompatActivity?,
        private val onViewHolderClick: OnViewHolderClick?
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>(), AutoUpdatableAdapter {

    var mRepos: List<Repo> by Delegates.observable(emptyList()) { prop, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(createView(context, parent, viewType), onViewHolderClick)
    }

    override fun getItemCount() = mRepos.size

    private fun getItem(index: Int): Repo {
        return mRepos[index]
    }

    private fun getScore(score: Double?): Float {
        return if (score != null) {
            (score * 5 / 100).toFloat()
        } else {
            0f
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mRepos[position])
    }

    private fun createView(context: AppCompatActivity?, viewGroup: ViewGroup, viewType: Int): View {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(R.layout.listitem_repo, viewGroup, false)
    }

    interface OnViewHolderClick {
        fun repoViewClickFromList(view: View, position: Int, repo: Repo)
    }

    inner class ViewHolder(itemView: View, listener: RepoAdapter.OnViewHolderClick?) :
            RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        init {
            if (listener != null)
                itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onViewHolderClick?.repoViewClickFromList(view, adapterPosition, getItem(adapterPosition))
        }

        fun bind(repo: Repo) = with(itemView) {
            name.text = repo.name
            score.rating = getScore(repo.score)
            fullName.text = repo.fullName
        }
    }
}