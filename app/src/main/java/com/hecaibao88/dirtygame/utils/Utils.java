package com.hecaibao88.dirtygame.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Utils {

	public static Toast mToast;

	public static void showToast(Context mContext, String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
		}
		mToast.setText(msg);
		mToast.show();
	}
	public static void showToastCenter(Context mContext, String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
		}
		mToast.setGravity(Gravity.CENTER, 0, 0);
		mToast.setText(msg);
		mToast.show();

	}
	public static void showImageToastCenter(Context mContext, String msg,int resId) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
		}
		mToast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) mToast.getView();
		ImageView imageCodeProject = new ImageView(mContext);
		imageCodeProject.setImageResource(resId);
		toastView.addView(imageCodeProject, 0);
		mToast.setText(msg);
		mToast.show();


	}
	public static void openActivity(Context ctx, Class<?> cls, Bundle bundle) {

		Intent intent = new Intent(ctx, cls);
		if(bundle != null) intent.putExtras(bundle);
		ctx.startActivity(intent);
	}



	/**
	 * 获取版本号
	 * @param context
	 * @return
	 */
	public static String getVersionCode(Context context){

		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * 获取圆角bitmap,RCB means Rounded Corner Bitmap
	 * @param bitmap
	 * @param roundPX
	 * @return
	 */
	public static Bitmap getRCB(Bitmap bitmap, float roundPX) {
		if (bitmap == null || roundPX <= 0) {
			return bitmap;
		}
		Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(dstbmp);
		Paint paint = new Paint();
		RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
		Path mPath = new Path();
		float[] mCorner = new float[] { roundPX, roundPX, roundPX, roundPX,roundPX, roundPX, roundPX, roundPX};
		mPath.addRoundRect(rectF, mCorner, Path.Direction.CW);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawPath(mPath, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return dstbmp;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}


	/**
	 * dip 转换成 px
	 * @param dip
	 * @param context
	 * @return
	 */
	public static float dip2Dimension(float dip, Context context) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
	}
	/**
	 * @param dip
	 * @param context
	 * @param complexUnit {@link TypedValue#COMPLEX_UNIT_DIP} {@link TypedValue#COMPLEX_UNIT_SP}}
	 * @return
	 */
	public static float toDimension(float dip, Context context, int complexUnit) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(complexUnit, dip, displayMetrics);
	}

	/** 获取状态栏高度
	 * @param v
	 * @return
	 */
	public static int getStatusBarHeight(View v) {
		if (v == null) {
			return 0;
		}
		Rect frame = new Rect();
		v.getWindowVisibleDisplayFrame(frame);
		return frame.top;
	}

	public static String getActionName(MotionEvent event) {
		String action = "unknow";
		switch (MotionEventCompat.getActionMasked(event)) {
		case MotionEvent.ACTION_DOWN:
			action = "ACTION_DOWN";
			break;
		case MotionEvent.ACTION_MOVE:
			action = "ACTION_MOVE";
			break;
		case MotionEvent.ACTION_UP:
			action = "ACTION_UP";
			break;
		case MotionEvent.ACTION_CANCEL:
			action = "ACTION_CANCEL";
			break;
		case MotionEvent.ACTION_OUTSIDE:
			action = "ACTION_SCROLL";
			break;
		default:
			break;
		}
		return action;
	}



	/**
	 * 拍照功能
	 *
	 * @param
	 */
	public static void doTakePhoto(Activity act) {
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡
				Intent intent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				File file = getTempFileFromCamera();
				intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				act.startActivityForResult(intent, C.CAMERA_WITH_DATA);
			} else {

			}
		} catch (ActivityNotFoundException e) {
			Toast.makeText(act, "no find", Toast.LENGTH_LONG).show();
		}
	}

	public static File getTempFileFromCamera() {
		File distFile = new File(C.HEAD_IMAGE_PATH + "temp.jpg");
		if (!distFile.getParentFile().exists())
			distFile.getParentFile().mkdirs();
		return distFile;
	}

	/**
	 * 请求相册程序
	 *
	 * @param myActivity
	 */
	public static void doPickPhotoFromGallery(Activity myActivity) {
		try {
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
			intent.setType("image/*");
			myActivity.startActivityForResult(intent, C.PHOTO_PICKED_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(myActivity, "no find", Toast.LENGTH_LONG).show();
		}
	}

	public static String getVoiceName(Context ctx, String user_id) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
		stringBuilder.append("/ujoinvoice/");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);
		stringBuilder.append("/"+ MD5Util.encode(dateNowStr));
		stringBuilder.append("/"+ MD5Util.encode(user_id));
		stringBuilder.append("/"+ MD5Util.encode(String.valueOf(System.currentTimeMillis()))+".amr");
		return stringBuilder.toString();
	}

	public static String getTableName(String user_id, String other_id) {
		return "U"+user_id+"_U"+other_id;
	}


	public static String getRealFilePath(final Context context, final Uri uri ) {
		if ( null == uri ) return null;
		final String scheme = uri.getScheme();
		String data = null;
		if ( scheme == null )
			data = uri.getPath();
		else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
			data = uri.getPath();
		} else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
			Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore
					.Images.ImageColumns.DATA }, null, null, null );
			if ( null != cursor ) {
				if ( cursor.moveToFirst() ) {
					int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
					if ( index > -1 ) {
						data = cursor.getString( index );
					}
				}
				cursor.close();
			}
		}
		return data;
	}


	/**
	 * 从assets中读取文件中的内容
	 * @param context
	 * @param fileName
     * @return
     */
	public static String getFromAssets(Context context, String fileName){
		String result = "";
		try {
			InputStream in = context.getResources().getAssets().open(fileName);
			//获取文件的字节数
			int lenght = in.available();
			//创建byte数组
			byte[]  buffer = new byte[lenght];
			//将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	/**
	 * ◆ 参数名ASCII码从小到大排序（字典序）； ◆ 如果参数的值为空不参与签名； ◆ 参数名区分大小写； ◆
	 * 验证签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。 ◆
	 *
	 * @param paramsMap
	 * @param skey
	 *            密钥
	 * @return
	 */
	public static String makeSign(Map<String, String> paramsMap, String skey) {
		Map<String, String> verifyArray = new TreeMap<String, String>();
		for (String key : paramsMap.keySet()) {
			String value = paramsMap.get(key);
			if ("sign".equals(key))
				continue;
//			logger.info("key>>{}==value>>{}", key, value);
			if (!StringUtils.isEmpty(value)) {
				verifyArray.put(key, value);
			}
		}
		StringBuffer sb = new StringBuffer();
		Set<String> keySet = verifyArray.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String param = it.next();
			sb.append(param + "=" + verifyArray.get(param));
			if (it.hasNext()) {
				sb.append("&");
			}
		}
		//		for (String key : verifyArray.keySet()) {
		//			sb.append(key);
		//			sb.append("=");
		//			sb.append(verifyArray.get(key));
		//			sb.append("&");
		//		}
		if (!StringUtils.isEmpty(skey)) {
			sb.append("&key=" + skey);// mtqwsq
		}
		System.out.println(sb.toString()); // 支付商户的密钥
		return MD5Util.getMD5String(sb.toString());
	}


}
