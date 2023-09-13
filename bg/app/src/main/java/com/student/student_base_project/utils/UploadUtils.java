package com.student.student_base_project.utils;

import android.text.TextUtils;

import com.student.student_base_project.bean.UploadBean;
import com.student.student_base_project.net.ApiSubscriber;
import com.student.student_base_project.net.FileLoadListener;
import com.student.student_base_project.net.HttpManager;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 文件上传工具类
 * Created by hhh on 2018/7/17.
 */

public class UploadUtils {

//    /**
//     * 发送多个图片 这里不需要管理进度条
//     *
//     * @param mListPath        多个图片的陆军
//     * @param imgType          文件类型
//     * @param fileLoadListener
//     */
//    public static void uploadultipartFile(ArrayList<String> mListPath, long imgType, FileLoadListener fileLoadListener) {
//        List<File> files = PathToFileUtis.pathToUtils(mListPath);
//        Observable.fromIterable(files).flatMap((Function<File, ObservableSource<JSONObject>>) file -> {
//            RequestBody requestFile;
//            String format = FileUtils.getFileFormat(file.getPath());
//            if (imgType == MsgType.IMAGE) {
//                Bitmap bitmap = PictureUtil.getSmallBitmap(file.getPath());
//                File fileStr = PictureUtil.compressImage(bitmap, file.getName());
//                requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileStr);
//            } else {
//                requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            }
//            Map<String, RequestBody> requestBodyMap = new HashMap<>();
//            requestBodyMap.put("file\"; filename=\"" + System.currentTimeMillis() + "." + format, requestFile);
//            return HttpUploadManager.getInstance(fileLoadListener, requestFile).uploadFile(requestBodyMap).subscribeOn(Schedulers.io());
//        }).map(new Function<JSONObject, String>() {
//            @Override
//            public String apply(JSONObject jsonObject) {
//                //这里需要处理多图片的操作
//                return null;
//            }
//        }).lastOrError().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(String s) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
//
//    }
//
//    //意见反馈上传图片
//    public static void uploadFile(File file, FileLoadListener fileLoadListener) {
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part part = MultipartBody.Part.createFormData("picUrl", file.getName(), requestFile);
//        HttpManager.getInstance().uploadPic(part).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiSubscriber<UploadFeedbackPicBean>() {
//
//            @Override
//            public void onSuccess(UploadFeedbackPicBean uploadFeedbackPicBean) {
//                String imgUrl = uploadFeedbackPicBean.getData();
//                if (!TextUtils.isEmpty(imgUrl)) {
//                    fileLoadListener.onImgOrFileSuccess(imgUrl);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                fileLoadListener.onFail();
//                Log.d("Dong", "---> onFailure" + e.getMessage());
//            }
//
//            @Override
//            public void onStart() {
//                Log.d("Dong", "---> onStart");
//                fileLoadListener.onProgressUpdate(1, 1, true);
//            }
//
//            @Override
//            public void onDisposable(Disposable d) {
//                Log.d("Dong", "---> Disposable");
//            }
//        });
//    }
//
    //头像上传图片
    public static void uploadHeadFile(File file, FileLoadListener fileLoadListener) {
        int userId = (int) SPUtil.get(ConstantUtils.userId, 0);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        HttpManager.getInstance().upload(part).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiSubscriber<UploadBean>() {

            @Override
            public void onSuccess(UploadBean uploadBean) {
                UploadBean.DataBean dataBean = uploadBean.getData();
                if (dataBean != null) {
                    fileLoadListener.onImgOrFileSuccess(dataBean);
                }
            }

            @Override
            public void onFailure(Throwable e) {
                fileLoadListener.onFail();
            }

            @Override
            public void onStart() {
                fileLoadListener.onStart();
            }

            @Override
            public void onDisposable(Disposable d) {

            }
        });
    }
//
//
//    //客户头像或者背景图上传图片
//    public static void uploadCustomerHeadOrBgFile(File file, FileLoadListener fileLoadListener, String type, String customerId) {
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("type", type);
//        map.put("customerId", customerId);
//
//        HttpManager.getInstance().setCustomerHeadOrBg(part, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiSubscriber<UploadFeedbackPicBean>() {
//
//            @Override
//            public void onSuccess(UploadFeedbackPicBean uploadFeedbackPicBean) {
//                String imgUrl = uploadFeedbackPicBean.getData();
//                if (!TextUtils.isEmpty(imgUrl)) {
//                    fileLoadListener.onImgOrFileSuccess(imgUrl);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                Log.d("Dong", "---> onFailure" + e.getMessage());
//                fileLoadListener.onFail();
//            }
//
//            @Override
//            public void onStart() {
//                Log.d("Dong", "---> onStart");
//            }
//
//            @Override
//            public void onDisposable(Disposable d) {
//                Log.d("Dong", "---> Disposable");
//            }
//        });
//    }
//
//    //扫描名片
//    public static void setCustomerScanCard(File file, FileLoadListener fileLoadListener) {
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//        HttpManager.getInstance().setCustomerScanCard(part).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiSubscriber<ScanCardBean>() {
//            @Override
//            public void onSuccess(ScanCardBean uploadFeedbackPicBean) {
//                fileLoadListener.onScanCardSuccess(uploadFeedbackPicBean);
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                fileLoadListener.onFail();
//            }
//
//            @Override
//            public void onStart() {
//            }
//
//            @Override
//            public void onDisposable(Disposable d) {
//            }
//        });
//    }
//
//    /***
//     * 上传证件信息
//     * @param file
//     * @param flag 1 上传转gif图片
//     * @param fileLoadListener
//     */
//    public static void uploadHeadFile(File file, int flag, FileLoadListener fileLoadListener) {
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//        Map<String, Object> map = new HashMap<>();
//        map.put("flag", flag);
//        HttpManager.getInstance().uploadInsuranceCerificate(part, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiSubscriber<UploadInsuranceCerificateBean>() {
//            @Override
//            public void onSuccess(UploadInsuranceCerificateBean uploadFeedbackPicBean) {
//                String imgUrl = uploadFeedbackPicBean.getData().getUrl();
//                if (!TextUtils.isEmpty(imgUrl)) {
//                    fileLoadListener.onImgOrFileSuccess(imgUrl);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                fileLoadListener.onFail();
//                Log.d("Dong", "---> onFailure" + e.getMessage());
//            }
//
//            @Override
//            public void onStart() {
//                Log.d("Dong", "---> onStart");
//            }
//
//            @Override
//            public void onDisposable(Disposable d) {
//                Log.d("Dong", "---> Disposable");
//            }
//        });
//    }
//
//    /***
//     * 上传证件信息
//     * @param file
//     * @param fileLoadListener
//     */
//    public static void uploadOCRFile(File file ,FileLoadListener fileLoadListener) {
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//        Map<String, Object> map = new HashMap<>();
//        HttpManager.getInstance().uploadInsuranceCerificate(part, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiSubscriber<UploadInsuranceCerificateBean>() {
//            @Override
//            public void onSuccess(UploadInsuranceCerificateBean uploadFeedbackPicBean) {
//                String imgUrl = uploadFeedbackPicBean.getData().getUrl();
//                if (!TextUtils.isEmpty(imgUrl)) {
//                    fileLoadListener.onImgOrFileSuccess(imgUrl);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                fileLoadListener.onFail();
//                Log.d("Dong", "---> onFailure" + e.getMessage());
//            }
//
//            @Override
//            public void onStart() {
//                Log.d("Dong", "---> onStart");
//            }
//
//            @Override
//            public void onDisposable(Disposable d) {
//                Log.d("Dong", "---> Disposable");
//            }
//        });
//    }
}
