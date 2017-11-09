package com.gentler.drawview.adapter;

/**
 * Created by jiantao on 2016/11/14.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * 所有的RecyclerView的Adapter
 *
 * @author 李建涛
 */
public class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {
  protected String TAG;
  protected List<T> datas;
  protected LayoutInflater mInflater;
  protected Context mContext;

  public BaseRecyclerAdapter(LayoutInflater mInflater) {
    this.mInflater = mInflater;
    TAG = getClass().getSimpleName();
  }

  public BaseRecyclerAdapter(Context context, LayoutInflater inflater) {
    this.mContext = context;
    this.mInflater = inflater;
    TAG = getClass().getSimpleName();
  }

  public void setData(List<T> datas) {
    this.datas = datas;
    notifyDataSetChanged();
  }

  public List<T> getData() {
    return this.datas;
  }

  @Override
  public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(VH holder, int position) {}

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getItemCount() {
    return datas != null ? datas.size() : 0;
  }
}
