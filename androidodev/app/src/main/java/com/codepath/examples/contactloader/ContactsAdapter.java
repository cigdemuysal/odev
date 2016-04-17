package com.codepath.examples.contactloader;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactsAdapter extends ArrayAdapter<Contact> {

	public ContactsAdapter(Context context, List<Contact> contacts) {
		super(context, 0, contacts);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item
		Contact contact = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			view = inflater.inflate(R.layout.adapter_contact_item, parent, false);
		}

		TextView tvName = (TextView) view.findViewById(R.id.tvName);
		tvName.setText(contact.name);

		ImageView tvImage=(ImageView)view.findViewById(R.id.imageView);
		tvImage.setImageResource(R.drawable.icon);

		TextView tvPhone = (TextView) view.findViewById(R.id.tvPhone);
		tvPhone.setText("");



		if (contact.numbers.size() > 0 && contact.numbers.get(0) != null) {
			tvPhone.setText(contact.numbers.get(0).number);
		}
		return view;
	}

}
