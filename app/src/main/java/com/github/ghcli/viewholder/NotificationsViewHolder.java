package com.github.ghcli.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.ghcli.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.notificationReason) TextView notificationReason;
    @BindView(R.id.notificationDescription) TextView notificationDescription;


    public NotificationsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getNotificationReason() {
        return notificationReason;
    }

    public void setNotificationReason(String reason) {
        this.notificationReason.setText(reason);
    }

    public TextView getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String description) { this.notificationDescription.setText(description); }
}