package com.example.applist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {
PackageManager packagemanager;
ListView apkList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		packagemanager=getPackageManager();
		List<PackageInfo> packageList=packagemanager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		List<PackageInfo> packageList1=new ArrayList<PackageInfo>();
		for(PackageInfo pi:packageList){
			boolean b=isSystemPackage(pi);
			if(!b){
				packageList1.add(pi);
			}
		}
		apkList=(ListView)findViewById(R.id.applist);
		apkList.setAdapter(new ApkAdapter(this,packageList1,packagemanager));
		apkList.setOnItemClickListener(this);
	}

	private boolean isSystemPackage(PackageInfo pi) {
		// TODO Auto-generated method stub
		return((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)!= 0)? true:false;
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
	PackageInfo packageinfo =(PackageInfo)arg0.getItemAtPosition(arg2);
	AppData appdata=(AppData)getApplicationContext();
	appdata.setPackageInfo(packageinfo);
	
	Intent appInfo=new Intent(getApplicationContext(),ApkInfo.class);
	startActivity(appInfo);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.apk, menu);
		return true;
	}




}
