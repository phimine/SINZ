package cn.funion.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author aixc
 * @email dev@4union.cn
 * @date 2018-11-28 21:07:11
 */
@TableName("article_info")
public class ArticleInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String tag;
	/**
	 * 
	 */
	private String articleUrl;
	/**
	 * 
	 */
	private String articleTitle;
	/**
	 * 
	 */
	@TableField("_created_time")
	private Date createdTime;
	/**
	 * 0:正常 1：删除
	 */
	@TableField("_status")
	private Integer status;
	/**
	 * 
	 */
	private String iconUrl;
	/**
	 * 
	 */
	@TableField("_group_ids")
	private String groupIds;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
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
	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}
	/**
	 * 获取：
	 */
	public String getArticleUrl() {
		return articleUrl;
	}
	/**
	 * 设置：
	 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	/**
	 * 获取：
	 */
	public String getArticleTitle() {
		return articleTitle;
	}
	/**
	 * 设置：
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * 设置：0:正常 1：删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0:正常 1：删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	/**
	 * 获取：
	 */
	public String getIconUrl() {
		return iconUrl;
	}
	/**
	 * 设置：
	 */
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	/**
	 * 获取：
	 */
	public String getGroupIds() {
		return groupIds;
	}
}
