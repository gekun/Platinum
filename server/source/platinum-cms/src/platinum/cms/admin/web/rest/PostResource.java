package platinum.cms.admin.web.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import platinum.cms.admin.service.PostAdminService;
import platinum.cms.common.dao.PostDAOQuery;
import platinum.cms.common.vo.PostDetailVO;
import platinum.cms.common.vo.PostSimpleVO;
import platinum.common.PTList;
import platinum.framework.web.rest.AbstractResource;

@Path("admin/post")
public class PostResource extends AbstractResource
{
	@GET
	@Path("/")
	public Response loadPosts(
			@QueryParam("categoryId") String p_categoryId,
			@QueryParam("pageIndex") @DefaultValue("0") int p_pageIndex
			) throws JSONException
	{
		PostDAOQuery query = new PostDAOQuery();
		query.setPageSize(50);
		query.setPageIndex(p_pageIndex);
		PTList<PostSimpleVO> posts = PostAdminService.getInstance().loadPostsByCategory(query);
		return responseWithJSONArray(posts.toJSONArray());
	}
	
	@GET
	@Path("/{id}")
	public Response getPost(
			@PathParam("id") String p_id
			) throws JSONException
	{
		PostDetailVO post = PostAdminService.getInstance().getPostById(p_id);
		return responseWithJSONObject(post.toJSONObject());
	}
}
