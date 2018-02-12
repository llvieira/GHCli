package com.github.ghcli.viewholder;

import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.ghcli.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActionDrawerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.action_drawer) TextView actionText;

    public ActionDrawerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getActionText() {
        return actionText;
    }

    public void setActionText(String actionText) {
        this.actionText.setText(actionText);
    }

    @Override
    public void onClick(View view) {
        Log.d("Click","Clicou");
    }
}
