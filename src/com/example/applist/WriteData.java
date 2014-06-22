package com.example.applist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Environment;

public class WriteData extends Activity{
	//List<PackageInfo> packageList;
	String state;
	StringBuilder data=new StringBuilder("");
   
	public void makeFile(List<PackageInfo> packageList1) {
		// TODO Auto-generated method stub
		//packageList = packageList1;
		state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			PackageInfo info;
          String line="";
          String l="\n";
          for(PackageInfo packageList:packageList1){
        	  info=packageList;
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
	}
}
