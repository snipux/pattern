package com.snipux.navigationdrawer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.snipux.navigationdrawer.R;

public class MenuAdapter extends ArrayAdapter<MenuModel> {

	public MenuAdapter(Context context) {
		super(context, 0);
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		return getItem(position).isGroupTitle() ? 0 : 1;
	}

	@Override
	public boolean isEnabled(int position) {
		return !getItem(position).isGroupTitle();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MenuViewHolder holder = null;
		int type = getItemViewType(position);
		MenuModel menu= getItem(position);
		
		if (convertView == null) {
			switch (type) {
			case 0:
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.menu_row_group, null);
				holder = new MenuViewHolder(
						(TextView) convertView.findViewById(R.id.group_title),
						null, null);
				break;
			case 1:
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.menu_row, null);
				holder = new MenuViewHolder(
						(TextView) convertView.findViewById(R.id.menu_title),
						(ImageView) convertView.findViewById(R.id.row_icon),
						(TextView) convertView.findViewById(R.id.row_counter));
				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (MenuViewHolder) convertView.getTag();
		}
		
		holder.titleView.setText(menu.getTitle());
		if(type != 0){
			holder.iconView.setImageResource(menu.getIcon());
			if (menu.getCounter() > 0) {
				holder.counterView.setVisibility(View.VISIBLE);
				holder.counterView.setText(menu.getCounter() + "");
			} else {
				holder.counterView.setVisibility(View.GONE);
			}
		}
		return convertView;
	}

	public static class MenuViewHolder {
		public final TextView titleView;
		public final ImageView iconView;
		public final TextView counterView;

		public MenuViewHolder(TextView titleView, ImageView iconView,
				TextView counterView) {
			this.titleView = titleView;
			this.iconView = iconView;
			this.counterView = counterView;
		}
	}
}
