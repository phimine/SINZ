package cn.funion.modules.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 
 * 
 * @author Alvin
 * @email aixc@4union.cn
 * @date 2018-11-29 23:11:11
 */
@TableName("ims_mc_mapping_fans")
public class ImsMcMappingFansEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer fanid;
	/**
	 * 
	 */
	private Integer acid;
	/**
	 * 
	 */
	private Integer uniacid;
	/**
	 * 
	 */
	private Integer uid;
	/**
	 * 
	 */
	private String openid;
	/**
	 * 
	 */
	private String salt;
	/**
	 * 
	 */
	private Integer follow;
	/**
	 * 
	 */
	private Integer followtime;
	/**
	 * 
	 */
	private Integer unfollowtime;
	/**
	 * 
	 */
	private String tag;
	/**
	 * 
	 */
	private Integer groupid;
	/**
	 * 
	 */
	private Integer updatetime;
	/**
	 * 
	 */
	private String nickname;
	/**
	 * 
	 */
	private String unionid;
	/**
	 * 
	 */
	private String longitude;
	/**
	 * 
	 */
	private String latitude;
	/**
	 * 
	 */
	private String address;
	/**
	 * 
	 */
	private Integer updated;

	/**
	 * 设置：
	 */
	public void setFanid(Integer fanid) {
		this.fanid = fanid;
	}
	/**
	 * 获取：
	 */
	public Integer getFanid() {
		return fanid;
	}
	/**
	 * 设置：
	 */
	public void setAcid(Integer acid) {
		this.acid = acid;
	}
	/**
	 * 获取：
	 */
	public Integer getAcid() {
		return acid;
	}
	/**
	 * 设置：
	 */
	public void setUniacid(Integer uniacid) {
		this.uniacid = uniacid;
	}
	/**
	 * 获取：
	 */
	public Integer getUniacid() {
		return uniacid;
	}
	/**
	 * 设置：
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 获取：
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * 设置：
	 */
	public void setFollow(Integer follow) {
		this.follow = follow;
	}
	/**
	 * 获取：
	 */
	public Integer getFollow() {
		return follow;
	}
	/**
	 * 设置：
	 */
	public void setFollowtime(Integer followtime) {
		this.followtime = followtime;
	}
	/**
	 * 获取：
	 */
	public Integer getFollowtime() {
		return followtime;
	}
	/**
	 * 设置：
	 */
	public void setUnfollowtime(Integer unfollowtime) {
		this.unfollowtime = unfollowtime;
	}
	/**
	 * 获取：
	 */
	public Integer getUnfollowtime() {
		return unfollowtime;
	}
	/**
	 * 设置：
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * 获取：
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * 设置：
	 */
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	/**
	 * 获取：
	 */
	public Integer getGroupid() {
		return groupid;
	}
	/**
	 * 设置：
	 */
	public void setUpdatetime(Integer updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：
	 */
	public Integer getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	/**
	 * 获取：
	 */
	public String getUnionid() {
		return unionid;
	}
	/**
	 * 设置：
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * 设置：
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：
	 */
	public void setUpdated(Integer updated) {
		this.updated = updated;
	}
	/**
	 * 获取：
	 */
	public Integer getUpdated() {
		return updated;
	}
}
