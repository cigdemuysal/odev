package com.codepath.examples.contactloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	ArrayList<Contact> listContacts;
	ListView lvContacts;
	RadioButton rbtn_recovery;
	ArrayList<Contact> lvContacts2;
	HashMap<String, String> map1 = new HashMap<>();
	HashMap<String, String> map2 = new HashMap<>();
	List<Contact> contacts_list;
	ContactsAdapter adapterContacts;
	 List<Contact> contacts_backup;
	List<Contact> contacts_phone;
	String currentno = "", currentname = "",prevno="",prevname="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listContacts = new ContactFetcher(this).fetchAll();

		lvContacts = (ListView) findViewById(R.id.listView);
		adapterContacts = new ContactsAdapter(this, listContacts);
		lvContacts.setAdapter(adapterContacts);

		lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


				Uri number = Uri.parse("tel:05553658595");
				Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
				startActivity(callIntent);

			}
		});
	}
	public void backup(View v) {
		try {
			File txtfile = getFileStreamPath("backup.txt");
			String data = "";
			if (!txtfile.exists()) {
				txtfile.createNewFile();
			}
			FileOutputStream writer = openFileOutput(txtfile.getName(), Context.MODE_PRIVATE);
			Cursor contact = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
			String name = "", phone_number = "";

			while (contact.moveToNext()) {
				name = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				phone_number = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

				if (!map1.containsKey(name)) {
					map1.put(name, phone_number);
				} else {
					if (!map2.containsKey(name)) {
						map2.put(name, phone_number);
					}
				}
			}
			Iterator save1 = map1.entrySet().iterator();
			while (save1.hasNext()) {
				Map.Entry pair = (Map.Entry) save1.next();
				data = "" + pair.getKey() + "\t" + pair.getValue() + "\n";
				writer.write(data.getBytes());
				writer.flush();
				save1.remove();
		}
		Iterator save2 = map2.entrySet().iterator();
		while (save2.hasNext()) {
				Map.Entry pair = (Map.Entry) save2.next();
				data = "" + pair.getKey() + "\t" + pair.getValue() + "\n";
			writer.write(data.getBytes());
				writer.flush();
				save2.remove();
			}

			contact.close();
			writer.close();
			Toast.makeText(this, "Records are Saved!", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();



		}
	}
	public boolean control(String fname){
		File doc = getBaseContext().getFileStreamPath(fname);
		return doc.exists();
	}




	public void allOps(View v) {
		contacts_list = new ArrayList<Contact>();
		String name = "", phone_number = "";
		Cursor contact = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
		while (contact.moveToNext()) {
			name = contact.getString(
					contact.getColumnIndex(
							ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			phone_number = contact.getString(
					contact.getColumnIndex(
							ContactsContract.CommonDataKinds.Phone.NUMBER));
			currentno = phone_number;
			currentname = name;

			contacts_list.add(new Contact(name, phone_number));

		}
		contact.close();
		lvContacts = (ListView) findViewById(R.id.listView);
		ContactsAdapter myAdapter = new ContactsAdapter(this, contacts_list);
		lvContacts.setAdapter(myAdapter);

	}

	public void vodafoneClick(View view) {
		contacts_list = new ArrayList<Contact>();
		String name = "", phone_number = "";
		Cursor contact = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
		while (contact.moveToNext()) {
			name = contact.getString(
					contact.getColumnIndex(
							ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			phone_number = contact.getString(
					contact.getColumnIndex(
							ContactsContract.CommonDataKinds.Phone.NUMBER));

			if (phone_number.substring(0, 3).equals("054") || phone_number.substring(0, 6).equals("+90 54")) {
				currentno = phone_number;
				currentname = name;
				contacts_list.add(new Contact(currentname, currentno));
			}


		}
			contact.close();
			lvContacts = (ListView) findViewById(R.id.listView);
			ContactsAdapter myAdapter = new ContactsAdapter(this, contacts_list);
			lvContacts.setAdapter(myAdapter);

		}

	public void aveaClick(View view) {
		contacts_list = new ArrayList<Contact>();
		String name = "", phone_number = "";
		Cursor contact = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
		while (contact.moveToNext()) {
			name = contact.getString(
					contact.getColumnIndex(
							ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			phone_number = contact.getString(
					contact.getColumnIndex(
							ContactsContract.CommonDataKinds.Phone.NUMBER));
			if (phone_number.substring(0,3).equals("055")||phone_number.substring(0,3).equals("050")||phone_number.substring(0,6).equals("+90 55")) {
				currentno = phone_number;
				currentname = name;
				contacts_list.add(new Contact(name, phone_number));
			}

		}
			contact.close();
			lvContacts = (ListView) findViewById(R.id.listView);
			ContactsAdapter myAdapter = new ContactsAdapter(this, contacts_list);
			lvContacts.setAdapter(myAdapter);

	}
	public void turkcellClick(View view) {
		contacts_list = new ArrayList<Contact>();
		String name = "", phone_number = "";
		Cursor contact = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
		while (contact.moveToNext()) {
			name = contact.getString(
					contact.getColumnIndex(
							ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			phone_number = contact.getString(
					contact.getColumnIndex(
							ContactsContract.CommonDataKinds.Phone.NUMBER));
			if(phone_number.substring(0,3).equals("053")||phone_number.substring(0,6).equals("+90 53"))
			{
				currentno = phone_number;
				currentname = name;
				contacts_list.add(new Contact(name, phone_number));
			}
		}
			contact.close();
			lvContacts = (ListView) findViewById(R.id.listView);
			ContactsAdapter myAdapter = new ContactsAdapter(this, contacts_list);
			lvContacts.setAdapter(myAdapter);



	}
	public void recovery(View v) {
		if (!control("backup.txt")) {
			Toast.makeText(this, "You should take a Back-up!", Toast.LENGTH_LONG).show();
		}

	}
}
