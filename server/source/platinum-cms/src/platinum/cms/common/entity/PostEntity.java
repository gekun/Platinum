package platinum.cms.common.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import platinum.cms.common.PostStatus;
import platinum.cms.common.PostType;
import platinum.cms.common.util.URLResolver;
import platinum.framework.entity.StandardEntity;

@Entity(name = "Post")
@Table(name = "PTT_POST", schema = "PT_CMS")
public class PostEntity extends StandardEntity
{
	private String _homeSubcategoryId = null;
	@Column(name = "HOME_SUBCATEGORY_ID")
	public String getHomeSubcategoryId()
	{
		return _homeSubcategoryId;
	}
	public void setHomeSubcategoryId(String value)
	{
		_homeSubcategoryId = value;
	}
	
	private String _subcategoryId = null;
	@Column(name = "SUBCATEGORY_ID")
	public String getSubcategoryId()
	{
		return _subcategoryId;
	}
	public void setSubcategoryId(String value)
	{
		_subcategoryId = value;
	}
	
	private String _categoryID = null;
	@Column(name = "CATEGORY_ID")
	public String getCategoryId()
	{
		return _categoryID;
	}
	public void setCategoryId(String value)
	{
		_categoryID = value;
	}

	private PostContentEntity _content = null;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_CONTENT_ID")
	public PostContentEntity getContent()
	{
		if (_content == null)
		{
			_content = new PostContentEntity();
		}
		return _content;
	}
	public void setContent(PostContentEntity value)
	{
		_content = value;
	}	
	@Transient
	public String getContentText()
	{
		return getContent().getText();
	}
	public void setContentText(String value)
	{
		getContent().setText(value);
	}
	

	private PostViewsEntity _views = null;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_VIEWS_ID")
	public PostViewsEntity getViews()
	{
		if (_views == null)
		{
			_views = new PostViewsEntity();
		}
		return _views;
	}
	public void setViews(PostViewsEntity value)
	{
		_views = value;
	}
	@Transient
	public int getViewCount() 
	{
		return getViews().getCount();
	}
	public void setViewCount(int value)
	{
		getViews().setCount(value);
	}
	
	private String _title;
	@Column(name = "TITLE")
	public String getTitle()
	{
		return _title;
	}
	public void setTitle(String value)
	{
		_title = value;
	}
	
	private String _summary = null;
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "SUMMARY")
	public String getSummary()
	{
		return _summary;
	}
	public void setSummary(String value)
	{
		_summary = value;
	}
	
	private PostType _postType = PostType.INFORMATION;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "POST_TYPE")
	public PostType getPostType()
	{
		return _postType;
	}
	public void setPostType(PostType value)
	{
		_postType = value;
	}
	
	private PostStatus _postStatus = PostStatus.DRAFTED;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "POST_STATUS")
	public PostStatus getPostStatus()
	{
		return _postStatus;
	}
	public void setPostStatus(PostStatus value)
	{
		_postStatus = value;
	} 

	private String _publisher = null;
	@Column(name = "PUBLISHER")
	public String getPublisher()
	{
		return _publisher;
	}
	public void setPublisher(String value)
	{
		_publisher = value;
	}
	
	private String _source = null;
	@Column(name = "SOURCE")
	public String getSource()
	{
		return _source;
	}
	public void setSource(String value)
	{
		_source = value;
	}

	private String _photoURL = null;
	@Column(name = "PHOTO_URL")
	public String getPhotoURL()
	{
		return _photoURL;
	}
	public void setPhotoURL(String value)
	{
		_photoURL = value;
	}
	
	
	@Transient
	public String getLink()
	{
		return URLResolver.getPostlink(getId(), getCategoryId());
	}
	
	@Transient
	public String getAbsoluteLink()
	{
		return URLResolver.getAbsolutePostlink(getId(), getCategoryId());
	}
	
	
	
	private List<PostAttachmentEntity> _attachments = new ArrayList<PostAttachmentEntity>();
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("CREATE_TIME")
	public List<PostAttachmentEntity> getAttachments()
	{
		return _attachments;
	}
	public void setAttachments(List<PostAttachmentEntity> value)
	{
		_attachments = value;
	}
}
