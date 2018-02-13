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
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;
import com.github.ghcli.util.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMyFollowingAdapter extends RecyclerView.Adapter<ListMyFollowingAdapter.MyViewHolder> {

    private List<GitHubUser> mList;
    private LayoutInflater mLayoutInflater;
    private Context context;
    private IGitHubUser iGitHubUser;


    public ListMyFollowingAdapter(Context c, List<GitHubUser> l, IGitHubUser iGitHubUser){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        context = c;
        this.iGitHubUser = iGitHubUser;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_following_card, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final GitHubUser item = mList.get(position);

        if(item.getName() != null){
            holder.rvTxtNomeUser.setText(String.valueOf(item.getName()));
        }else{
            holder.rvTxtNomeUser.setText("");
        }

        holder.rvTxtDescricao.setText(item.getBio());

        holder.rvTxtLocalizacao.setText(item.getLocation());

        holder.rvTxtLoginUser.setText(item.getLogin());

        holder.switchFollow.setChecked(true);

        holder.switchFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.switchFollow.isChecked()){
                    Message.showToast("UNFOLLOW", context);
                    Call<Void> call = iGitHubUser.unfollow(
                            Authentication.getToken(context),
                            item.getLogin());

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call,
                                               @NonNull Response<Void> response) {
                            if (response.isSuccessful()) {
                                holder.switchFollow.setChecked(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }else{
                    Message.showToast("FOLLOW", context);

                    Call<Void> call = iGitHubUser.follow(
                            Authentication.getToken(context),
                            item.getLogin());

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call,
                                               @NonNull Response<Void> response) {
                            if (response.isSuccessful()) {
                                holder.switchFollow.setChecked(true);
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            }
        });

        Picasso.with(context).load(item.getAvatarUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.rvImgUser);



    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView rvTxtNomeUser;
        public TextView rvTxtLocalizacao;
        public TextView rvTxtLoginUser;
        public TextView rvTxtDescricao;
        public ImageView rvImgUser;
        public ToggleButton switchFollow;




        public MyViewHolder(View itemView) {
            super(itemView);

            rvImgUser = (ImageView) itemView.findViewById(R.id.rvImgUser);

            rvTxtNomeUser = (TextView) itemView.findViewById(R.id.rvTxtNomeUser);
            rvTxtLocalizacao = (TextView) itemView.findViewById(R.id.rvTxtLocalizacao);
            rvTxtLoginUser = (TextView) itemView.findViewById(R.id.rvTxtLoginUser);
            rvTxtDescricao = (TextView) itemView.findViewById(R.id.rvTxtDescricao);
            switchFollow = (ToggleButton) itemView.findViewById(R.id.switchFollow);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
