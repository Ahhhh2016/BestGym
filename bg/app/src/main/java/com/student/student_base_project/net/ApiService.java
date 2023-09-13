package com.student.student_base_project.net;


import com.student.student_base_project.bean.QueryNoticeListBean;
import com.student.student_base_project.bean.AddAddressBean;
import com.student.student_base_project.bean.AddCommentBean;
import com.student.student_base_project.bean.AddShopCartBean;
import com.student.student_base_project.bean.CommitOrderBean;
import com.student.student_base_project.bean.DeleteAddressBean;
import com.student.student_base_project.bean.DeleteShopCartBean;
import com.student.student_base_project.bean.EditUserInfoBean;
import com.student.student_base_project.bean.LoginBean;
import com.student.student_base_project.bean.OperationCollectionBean;
import com.student.student_base_project.bean.OrderListBean;
import com.student.student_base_project.bean.QueryAddressListBean;
import com.student.student_base_project.bean.QueryCollectListBean;
import com.student.student_base_project.bean.QueryCommentListBean;
import com.student.student_base_project.bean.QueryGoodsCategoryListBean;
import com.student.student_base_project.bean.QueryGoodsDetailBean;
import com.student.student_base_project.bean.QueryProductListBean;
import com.student.student_base_project.bean.QueryShopCartListBean;
import com.student.student_base_project.bean.RegisterBean;
import com.student.student_base_project.bean.UploadBean;
import com.student.student_base_project.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    /***
     * 注册
     * @param requestBody
     * @return
     */
    @POST("user/regist")
    Observable<RegisterBean> register(@Body RequestBody requestBody);

    /***
     * 登录
     * @param requestBody
     * @return
     */
    @POST("user/login")
    Observable<LoginBean> login(@Body RequestBody requestBody);


    /***
     * 登录
     * @param requestBody
     * @return
     */
    @POST("user/getDetail")
    Observable<UserInfoBean> queryUserInfoById(@Body RequestBody requestBody);

    /***
     * 用户信息编辑
     */
    @POST("user/editUser")
    Observable<EditUserInfoBean> editUserInfo(@Body RequestBody requestBody);

    /**
     * 上传图片
     *
     * @param multipartBody
     * @return
     */
    @Multipart
    @POST("v1.1/upload")
    Observable<UploadBean> upload(@Part MultipartBody.Part multipartBody);

    /***
     * 商品列表接口
     */
    @POST("goods/getGoodsList")
    Observable<QueryProductListBean> queryProductList();

    /***
     * 商品列表接口
     */
    @POST("goods/getGoodsList")
    Observable<QueryProductListBean> queryProductList(@Body RequestBody requestBody);

    /***
     * 商品分类
     */
    @POST("goods/getGoodsCategoryList")
    Observable<QueryGoodsCategoryListBean> queryGoodsCategoryList();

    /***
     * 商品详情接口
     */
    @POST("goods/getGoodsDetail")
    Observable<QueryGoodsDetailBean> queryGoodsDetail(@Body RequestBody requestBody);

    /***
     * 收藏和取消收藏
     */
    @POST("goods/goodsCollection")
    Observable<OperationCollectionBean> operationCollection(@Body RequestBody requestBody);

    /***
     * 添加到购物车 goods/addShopCart
     */
    @POST("goods/addShopCart")
    Observable<AddShopCartBean> addShopCart(@Body RequestBody requestBody);

    /***
     * 购物车列表goods/getShopCartList
     */
    @POST("goods/getShopCartList")
    Observable<QueryShopCartListBean> queryShopCartList(@Body RequestBody requestBody);

    /***
     * 购物车商品删除 goods/cancelShopCart
     */
    @POST("goods/cancelShopCart")
    Observable<DeleteShopCartBean> deleteShopCart(@Body RequestBody requestBody);

    /***
     * 快递地址 添加 goods/addAddress
     */
    @POST("goods/addAddress")
    Observable<AddAddressBean> addAddress(@Body RequestBody requestBody);

    /***
     * 快递地址列表 goods/getAddressList
     */
    @POST("goods/getAddressList")
    Observable<QueryAddressListBean> queryAddressList(@Body RequestBody requestBody);

    /***
     * 快递地址删除 goods/cancelAddress
     */
    @POST("goods/cancelAddress")
    Observable<DeleteAddressBean> deleteAddress(@Body RequestBody requestBody);

    /***
     * 订单列表数据 goods/getOrderList
     */
    @POST("goods/getOrderList")
    Observable<OrderListBean> queryOrderList(@Body RequestBody requestBody);

    /***
     * 订单提交接口 sumbitOrder
     */
    @POST("goods/sumbitOrder")
    Observable<CommitOrderBean> commitOrder(@Body RequestBody requestBody);

    /***
     * 收藏列表
     */
    @POST("goods/getGoodsCollectionList")
    Observable<QueryCollectListBean> queryCollectList(@Body RequestBody requestBody);

    /***
     * 评论
     */
    @POST("comment/addComment ")
    Observable<AddCommentBean> addComment(@Body RequestBody requestBody);

    /***
     * 获取评论列表 comment/getCommentList
     */
    @POST("comment/getCommentList ")
    Observable<QueryCommentListBean> queryCommentList(@Body RequestBody requestBody);

    /***
     * 通知列表 comment/getNoticeList
     */
    @POST("comment/getNoticeList ")
    Observable<QueryNoticeListBean> queryNoticeList();


}
