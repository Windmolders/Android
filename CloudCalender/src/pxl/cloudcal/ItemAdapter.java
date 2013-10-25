package pxl.cloudcal;

import java.util.List;

import pxl.model.Item;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<pxl.model.Item> {

	private List<Item> itemList;
	
	public ItemAdapter(Context context, List<Item> contactsList) {
		super(context, R.layout.item_row_layout, contactsList);
		this.itemList = contactsList;
	}
	
	private static class ViewHolder {
		
		public TextView tv_text;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(rowView == null) {
			LayoutInflater infl = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = infl.inflate(R.layout.item_row_layout, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			
			viewHolder.tv_text = (TextView) rowView.findViewById(R.id.label);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder)rowView.getTag();
		Item item = itemList.get(position);
		holder.tv_text.setText(item.getNaam());
		
		return rowView;
	}
}
