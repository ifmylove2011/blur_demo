package com.xter.blurdemo;

import com.xter.blurdemo.utils.BitmapUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class BlurActivity extends Activity {

	/**
	 * 整体布局
	 */
	private RelativeLayout rlBgLayout;
	/**
	 * 局部布局
	 */
	private LinearLayout llBlurLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blur);
		initView();
	}

	protected void initView() {
		rlBgLayout = (RelativeLayout) findViewById(R.id.rl_bg_layout);
		llBlurLayout = (LinearLayout) findViewById(R.id.ll_blur_layout);
		setBlur();
	}

	protected void setBlur() {
		ViewTreeObserver vto = rlBgLayout.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// 保证只调用一次
				rlBgLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				// 组件生成cache（组件显示内容）
				rlBgLayout.buildDrawingCache();
				// 得到组件显示内容
				Bitmap bitmap = rlBgLayout.getDrawingCache();
				// 局部模糊处理
				BitmapUtils.blur(getApplicationContext(), bitmap, llBlurLayout, 5);
			}
		});
	}
}
