package com.bontsi.ndiraphutcha.product.view;


/**
 * Created by kaede on 2015/5/18.
 */
public interface IProductView {
	public void onClearText();

	public void onAddProductResult(Boolean result, int code);

	public void onSetProgressBarVisibility(int visibility);

	public void toast(String msg);
}
