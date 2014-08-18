package com.example.applist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class adfd extends Activity{
ExpandableListView expand;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandlist);
		expand=(ExpandableListView)findViewById(R.id.elv1);
		//expand.setAdapter(new ApkAdapter())
	}
 
}
