package com.example.applist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	PackageManager packagemanager;
	ListView apkList;
	List<PackageInfo> packageList;
	List<PackageInfo> packageList1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		packagemanager = getPackageManager();
		 packageList = packagemanager
				.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		packageList1 = new ArrayList<PackageInfo>();
		for (PackageInfo pi : packageList) {
			boolean b = isSystemPackage(pi);
			if (!b) {
				packageList1.add(pi);
			}
		}
		apkList = (ListView) findViewById(R.id.applist);
		apkList.setAdapter(new ApkAdapter(this, packageList1, packagemanager));
		apkList.setOnItemClickListener(this);
	}

	private boolean isSystemPackage(PackageInfo pi) {
		// TODO Auto-generated method stub
		return ((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
				: false;

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		PackageInfo packageinfo = (PackageInfo) arg0.getItemAtPosition(arg2);
		AppData appdata = (AppData) getApplicationContext();
		appdata.setPackageInfo(packageinfo);

		Intent appInfo = new Intent(getApplicationContext(), ApkInfo.class);
		startActivity(appInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.apk, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_settings:
			break;
		case R.id.genapk:
			break;
		case R.id.genfile:
			WriteData wd=new WriteData();
			wd.makeFile(packageList1);
			Toast.makeText(getBaseContext(), "File stored in external memory ", Toast.LENGTH_LONG).show();
			break;
		}
			
		return super.onMenuItemSelected(featureId, item);
	}
	/*public void makeFile(List<PackageInfo> packageList1) {
		// TODO Auto-generated method stubList<PackageInfo> packageList;
		String state;
		StringBuilder data=new StringBuilder("");
		packageList = packageList1;
		state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			PackageInfo info;
          String line="";
          String l="\n";
          for(int i=0;i<packageList.size();i++){
        	  info=packageList.get(i);
        	  line=(String) (getPackageManager().getApplicationLabel(
      				info.applicationInfo));
        	  data.append(line+l);
          }
			try {
				File myFile = new File( Environment.getExternalStorageDirectory().getPath(),"apps.txt");
				myFile.createNewFile();
				FileOutputStream fOut = new FileOutputStream(myFile);
				OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
				myOutWriter.append(data.toString());
				myOutWriter.close();
				fOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}*/
}
