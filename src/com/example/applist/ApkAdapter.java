package com.example.applist;

import java.util.List;

import com.example.applist.ApkAdapter.ViewHolder;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ApkAdapter extends BaseAdapter {
List<PackageInfo> packageList;
Activity context;
PackageManager packagemanager;
	public ApkAdapter(Activity mainActivity,
			List<PackageInfo> packageList1, PackageManager packagemanager) {
		// TODO Auto-generated constructor stub
		super();
		this.context=mainActivity;
		this.packageList=packageList1;
		this.packagemanager=packagemanager;
	}

	public class ViewHolder{
		TextView apkname;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return packageList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return packageList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.apklist_item, null);
            holder = new ViewHolder();
 
            holder.apkname = (TextView) convertView.findViewById(R.id.appname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        PackageInfo packageInfo = (PackageInfo) getItem(position);
        Drawable appIcon = packagemanager
                .getApplicationIcon(packageInfo.applicationInfo);
        String appName = packagemanager.getApplicationLabel(
                packageInfo.applicationInfo).toString();
        appIcon.setBounds(0, 0, 40, 40);
        holder.apkname.setCompoundDrawables(appIcon, null, null, null);
        holder.apkname.setCompoundDrawablePadding(15);
        holder.apkname.setText(appName);
 
        return convertView;
		
	}
	
}
