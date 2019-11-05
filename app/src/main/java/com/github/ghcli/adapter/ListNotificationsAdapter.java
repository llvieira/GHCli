package com.github.ghcli.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.models.GitHubNotification;
import com.github.ghcli.models.GitHubNotificationSubject;
import com.github.ghcli.models.GitHubRepository;
import com.github.ghcli.viewholder.NotificationsViewHolder;
import com.github.ghcli.viewholder.RepositoriesViewHolder;

import java.text.DateFormat;
import java.util.List;

public class ListNotificationsAdapter extends RecyclerView.Adapter<NotificationsViewHolder> {

    private List<GitHubNotification> notifications;

    public ListNotificationsAdapter(List<GitHubNotification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public NotificationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.notifications_content, parent, false);

        NotificationsViewHolder holderNotification = new NotificationsViewHolder(view);

        return holderNotification;
    }

    @Override
    public void onBindViewHolder(NotificationsViewHolder holder, int position) {
        GitHubNotification notification = notifications.get(position);

        if (notification.getReason() != null) {
            holder.setNotificationReason(notification.getReason());
        }

        if (notification.getSubject() != null) {
            GitHubNotificationSubject subject = notification.getSubject();

            if (subject.getTitle() != null) {
                holder.setNotificationDescription(subject.getTitle());
            }
        }

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}