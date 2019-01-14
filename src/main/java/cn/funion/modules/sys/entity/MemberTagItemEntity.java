package cn.funion.modules.sys.entity;

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
@TableName("member_tag_item")
public class MemberTagItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long tagId;
	/**
	 * 
	 */
	private String fieldName;
	/**
	 * 
	 */
	private String fieldValue;

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
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	/**
	 * 获取：
	 */
	public Long getTagId() {
		return tagId;
	}
	/**
	 * 设置：
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * 获取：
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * 设置：
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	/**
	 * 获取：
	 */
	public String getFieldValue() {
		return fieldValue;
	}
}
