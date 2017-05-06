package com.zhang.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zhang.utils.DateTimeUtil;

@Table(name = "cs_goods")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 商品类型 1课程 2旧专题 3服务 4 新专题  5 直播,6实战专题
     */
    @Column(name = "goods_type")
    private Integer goodsType;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 卖点
     */
    @Column(name = "selling_points")
    private String sellingPoints;

    /**
     * 摘要
     */
    @Column(name = "goods_summary")
    private String goodsSummary;

    /**
     * 适合对象
     */
    @Column(name = "suit_target")
    private String suitTarget;

    /**
     * 讲师ids
     */
    @Column(name = "teacher_ids")
    private String teacherIds;

    /**
     * 原价
     */
    @Column(name = "goods_price")
    private Float goodsPrice;

    /**
     * 商品有效期
     */
    @Column(name = "goods_indate")
    private Integer goodsIndate;

    /**
     * 折后价
     */
    @Column(name = "goods_rebate_price")
    private Float goodsRebatePrice;

    /**
     * 打折
     */
    @Column(name = "goods_rebate")
    private Float goodsRebate;

    /**
     * 课程ids
     */
    @Column(name = "course_ids")
    private String courseIds;

    /**
     * 销量
     */
    @Column(name = "sell_count")
    private Long sellCount;

    /**
     * 评分
     */
    private Float grade;

    /**
     * 浏览次数
     */
    private Long views;

    /**
     * 商品封面
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 封面缩略图，通过上传封面后自动裁剪
     */
    private String thumbnail;

    /**
     * 状态，1新建，2启用，3已下架，4停用
     */
    private Integer status;

    /**
     * 1已上架，2已下架
     */
    @Column(name = "show_status")
    private Integer showStatus;

    /**
     * isVIP : -1 付费     0 免费     1 VIP
     */
    @Column(name = "is_vip")
    private Integer isVip;

    /**
     * 启用时间
     */
    @Column(name = "enable_time")
    private Date enableTime;

    /**
     * 推荐指数
     */
    @Column(name = "our_ratings")
    private Integer ourRatings;

    /**
     * 商品说明
     */
    @Column(name = "goods_explain")
    private String goodsExplain;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取商品类型 1课程 2旧专题 3服务 4 新专题  5 直播,6实战专题
     *
     * @return goods_type - 商品类型 1课程 2旧专题 3服务 4 新专题  5 直播,6实战专题
     */
    public Integer getGoodsType() {
        return goodsType;
    }

    /**
     * 设置商品类型 1课程 2旧专题 3服务 4 新专题  5 直播,6实战专题
     *
     * @param goodsType 商品类型 1课程 2旧专题 3服务 4 新专题  5 直播,6实战专题
     */
    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取卖点
     *
     * @return selling_points - 卖点
     */
    public String getSellingPoints() {
        return sellingPoints;
    }

    /**
     * 设置卖点
     *
     * @param sellingPoints 卖点
     */
    public void setSellingPoints(String sellingPoints) {
        this.sellingPoints = sellingPoints;
    }

    /**
     * 获取摘要
     *
     * @return goods_summary - 摘要
     */
    public String getGoodsSummary() {
        return goodsSummary;
    }

    /**
     * 设置摘要
     *
     * @param goodsSummary 摘要
     */
    public void setGoodsSummary(String goodsSummary) {
        this.goodsSummary = goodsSummary;
    }

    /**
     * 获取适合对象
     *
     * @return suit_target - 适合对象
     */
    public String getSuitTarget() {
        return suitTarget;
    }

    /**
     * 设置适合对象
     *
     * @param suitTarget 适合对象
     */
    public void setSuitTarget(String suitTarget) {
        this.suitTarget = suitTarget;
    }

    /**
     * 获取讲师ids
     *
     * @return teacher_ids - 讲师ids
     */
    public String getTeacherIds() {
        return teacherIds;
    }

    /**
     * 设置讲师ids
     *
     * @param teacherIds 讲师ids
     */
    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    /**
     * 获取原价
     *
     * @return goods_price - 原价
     */
    public Float getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置原价
     *
     * @param goodsPrice 原价
     */
    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取商品有效期
     *
     * @return goods_indate - 商品有效期
     */
    public Integer getGoodsIndate() {
        return goodsIndate;
    }

    /**
     * 设置商品有效期
     *
     * @param goodsIndate 商品有效期
     */
    public void setGoodsIndate(Integer goodsIndate) {
        this.goodsIndate = goodsIndate;
    }

    /**
     * 获取折后价
     *
     * @return goods_rebate_price - 折后价
     */
    public Float getGoodsRebatePrice() {
        return goodsRebatePrice;
    }

    /**
     * 设置折后价
     *
     * @param goodsRebatePrice 折后价
     */
    public void setGoodsRebatePrice(Float goodsRebatePrice) {
        this.goodsRebatePrice = goodsRebatePrice;
    }

    /**
     * 获取打折
     *
     * @return goods_rebate - 打折
     */
    public Float getGoodsRebate() {
        return goodsRebate;
    }

    /**
     * 设置打折
     *
     * @param goodsRebate 打折
     */
    public void setGoodsRebate(Float goodsRebate) {
        this.goodsRebate = goodsRebate;
    }

    /**
     * 获取课程ids
     *
     * @return course_ids - 课程ids
     */
    public String getCourseIds() {
        return courseIds;
    }

    /**
     * 设置课程ids
     *
     * @param courseIds 课程ids
     */
    public void setCourseIds(String courseIds) {
        this.courseIds = courseIds;
    }

    /**
     * 获取销量
     *
     * @return sell_count - 销量
     */
    public Long getSellCount() {
        return sellCount;
    }

    /**
     * 设置销量
     *
     * @param sellCount 销量
     */
    public void setSellCount(Long sellCount) {
        this.sellCount = sellCount;
    }

    /**
     * 获取评分
     *
     * @return grade - 评分
     */
    public Float getGrade() {
        return grade;
    }

    /**
     * 设置评分
     *
     * @param grade 评分
     */
    public void setGrade(Float grade) {
        this.grade = grade;
    }

    /**
     * 获取浏览次数
     *
     * @return views - 浏览次数
     */
    public Long getViews() {
        return views;
    }

    /**
     * 设置浏览次数
     *
     * @param views 浏览次数
     */
    public void setViews(Long views) {
        this.views = views;
    }

    /**
     * 获取商品封面
     *
     * @return img_url - 商品封面
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置商品封面
     *
     * @param imgUrl 商品封面
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取封面缩略图，通过上传封面后自动裁剪
     *
     * @return thumbnail - 封面缩略图，通过上传封面后自动裁剪
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置封面缩略图，通过上传封面后自动裁剪
     *
     * @param thumbnail 封面缩略图，通过上传封面后自动裁剪
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 获取状态，1新建，2启用，3已下架，4停用
     *
     * @return status - 状态，1新建，2启用，3已下架，4停用
     */
    public Integer getStatus() {
        return status;
    }
    
    public String getStatusStr() {
    	switch(status){
    		case 1:return "新建";
    		case 2:return "启用";
    		case 3:return "隐藏";
    		case 4:return "停用";
    		default:return "未知";
    	}
    }

    /**
     * 设置状态，1新建，2启用，3已下架，4停用
     *
     * @param status 状态，1新建，2启用，3已下架，4停用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取1已上架，2已下架
     *
     * @return show_status - 1已上架，2已下架
     */
    public Integer getShowStatus() {
        return showStatus;
    }
    
    public String getShowStatusStr() {
    	switch(showStatus){
    		case 1:return "已上架";
    		case 2:return "已下架";
    		default:return "未知";
    	}
    }

    /**
     * 设置1已上架，2已下架
     *
     * @param showStatus 1已上架，2已下架
     */
    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    /**
     * 获取isVIP : -1 付费     0 免费     1 VIP
     *
     * @return is_vip - isVIP : -1 付费     0 免费     1 VIP
     */
    public Integer getIsVip() {
        return isVip;
    }
    
    public String getIsVipStr() {
    	switch(showStatus){
    		case -1:return "付费";
    		case 0:return "免费";
    		case 1:return "VIP";
    		default:return "未知";
    	}
    }

    /**
     * 设置isVIP : -1 付费     0 免费     1 VIP
     *
     * @param isVip isVIP : -1 付费     0 免费     1 VIP
     */
    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    /**
     * 获取启用时间
     *
     * @return enable_time - 启用时间
     */
    public Date getEnableTime() {
        return enableTime;
    }
    
    public String getEnableTimeStr() {
    	return	DateTimeUtil.formatDate4(enableTime);
    }


    /**
     * 设置启用时间
     *
     * @param enableTime 启用时间
     */
    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    /**
     * 获取推荐指数
     *
     * @return our_ratings - 推荐指数
     */
    public Integer getOurRatings() {
        return ourRatings;
    }

    /**
     * 设置推荐指数
     *
     * @param ourRatings 推荐指数
     */
    public void setOurRatings(Integer ourRatings) {
        this.ourRatings = ourRatings;
    }

    /**
     * 获取商品说明
     *
     * @return goods_explain - 商品说明
     */
    public String getGoodsExplain() {
        return goodsExplain;
    }

    /**
     * 设置商品说明
     *
     * @param goodsExplain 商品说明
     */
    public void setGoodsExplain(String goodsExplain) {
        this.goodsExplain = goodsExplain;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", goodsType=").append(goodsType);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", sellingPoints=").append(sellingPoints);
        sb.append(", goodsSummary=").append(goodsSummary);
        sb.append(", suitTarget=").append(suitTarget);
        sb.append(", teacherIds=").append(teacherIds);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", goodsIndate=").append(goodsIndate);
        sb.append(", goodsRebatePrice=").append(goodsRebatePrice);
        sb.append(", goodsRebate=").append(goodsRebate);
        sb.append(", courseIds=").append(courseIds);
        sb.append(", sellCount=").append(sellCount);
        sb.append(", grade=").append(grade);
        sb.append(", views=").append(views);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", thumbnail=").append(thumbnail);
        sb.append(", status=").append(status);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", isVip=").append(isVip);
        sb.append(", enableTime=").append(enableTime);
        sb.append(", ourRatings=").append(ourRatings);
        sb.append(", goodsExplain=").append(goodsExplain);
        sb.append("]");
        return sb.toString();
    }
}