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

	/* 
	 * Di gunakan untuk memberitahu kalau listview kita ada 
	 * 2 jenis row yaitu row menu dan row header
	 * 
	 */
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	/* 
	 * Method ini akan memberitahu kita type row dalam listview
	 * 
	 */
	@Override
	public int getItemViewType(int position) {
		return getItem(position).isGroupTitle() ? 0 : 1;
	}
	
	/*
	 * Method ini akan memberi informasi apakah row enable atau disable 
	 * yang artinya dapat di klik atau tidak
	 * 
	 */
	@Override
	public boolean isEnabled(int position) {
		return !getItem(position).isGroupTitle();
	}

	/*
	 * Method untuk mengarahkan object dari model kita ke 
	 * layout yang telah kita buat
	 * 
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// MenuViewHolder ini di gunakan untuk mapping layout row yang telah kita buat
		MenuViewHolder holder = null;
		
		int type = getItemViewType(position);
		MenuModel menu= getItem(position);
		
		if (convertView == null) {
			//kita cek apakah row header atau row menu
			switch (type) {
			case 0:
				/*
				 * jika type row adalah row header maka kita akan mengambil menu_row_group.xml
				 * sebagai layout dan menginisialisasi object holder
				 * karena di layout menu_row_group.xml hanya menampilkan title group maka
				 * kita hanya perlu mapping textview group_title
				 */
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.menu_row_group, null);
				holder = new MenuViewHolder(
						(TextView) convertView.findViewById(R.id.group_title),
						null, null);
				break;
			case 1:
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.menu_row, null);
				/*
				 * row type menu
				 */
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
		
		/*
		 * menampilkan value dari object menu ke dalam layout
		 */
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
