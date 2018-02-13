package com.github.ghcli.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.github.ghcli.R;
import com.github.ghcli.models.GitEvent;
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;
import com.github.ghcli.util.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBrowserFollowingAdapter extends RecyclerView.Adapter<ListBrowserFollowingAdapter.MyViewHolder> {

    private List<GitEvent> mList;
    private LayoutInflater mLayoutInflater;
    private Context context;
    private IGitHubUser iGitHubUser;


    public ListBrowserFollowingAdapter(Context c, List<GitEvent> l, IGitHubUser iGitHubUser){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        context = c;
        this.iGitHubUser = iGitHubUser;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_browser_following_card, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final GitEvent item = mList.get(position);


        holder.rvTxtNomeUserBrowser.setText(item.getActor().getName());

        holder.rvTxtAction.setText(item.getType());

        holder.rvTxtDate.setText(item.getDate());

        holder.rvTxtRepository.setText(item.getRepo().getName());


        Picasso.with(context).load(item.getActor().getAvatarUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.rvImgUserBrowser);



    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView rvTxtNomeUserBrowser;
        public TextView rvTxtAction;
        public TextView rvTxtRepository;
        public TextView rvTxtDate;
        public ImageView rvImgUserBrowser;




        public MyViewHolder(View itemView) {
            super(itemView);

            rvImgUserBrowser = (ImageView) itemView.findViewById(R.id.rvImgUserBrowser);
            rvTxtNomeUserBrowser = (TextView) itemView.findViewById(R.id.rvTxtNomeUserBrowser);
            rvTxtAction = (TextView) itemView.findViewById(R.id.rvTxtAction);
            rvTxtRepository = (TextView) itemView.findViewById(R.id.rvTxtRepository);
            rvTxtDate = (TextView) itemView.findViewById(R.id.rvTxtDate);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
