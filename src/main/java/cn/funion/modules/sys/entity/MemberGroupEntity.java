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
@TableName("member_group")
public class MemberGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 多个以英文逗号隔开
	 */
	private String tagIds;
	/**
	 * 
	 */
	private Date createdTime;

	@TableField(exist = false)
	private Long[] tagArray;

	@TableField(exist = false)
	private Integer num;

    @TableField(exist = false)
    private String tagNames;

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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：多个以英文逗号隔开
	 */
	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}
	/**
	 * 获取：多个以英文逗号隔开
	 */
	public String getTagIds() {
		return tagIds;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long[] getTagArray() {
		return tagArray;
	}

	public void setTagArray(Long[] tagArray) {
		this.tagArray = tagArray;
	}

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }
}
