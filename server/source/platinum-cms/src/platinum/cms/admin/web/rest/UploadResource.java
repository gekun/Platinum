package platinum.cms.admin.web.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import platinum.cms.admin.service.PostAdminManager;
import platinum.cms.common.entity.PostAttachmentEntity;
import platinum.cms.common.serialization.AttachmentJSONSerializer;
import platinum.common.PTEnvironment;
import platinum.common.util.ImageUtil;
import platinum.common.util.StringUtil;
import platinum.framework.web.rest.AbstractResource;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("admin/upload")
public class UploadResource extends AbstractResource
{
	private static List<String> IMAGE_EXTENSION_LIST = null;
	
	@POST
	@Path("image")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadImage(
			@FormDataParam("postId") String p_postId,
			@FormDataParam("file") InputStream p_inputStream,
			@FormDataParam("file") FormDataContentDisposition p_fileDisposition)
	{
		if (StringUtil.isNullOrEmpty(p_postId))
		{
			return responseWithException("缺少 postId 参数。");
		}
		
		String fileName = p_fileDisposition.getFileName().toLowerCase();
		String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		if (!_checkExtension(ext, "images"))
		{
			return responseWithException("不合法的图片扩展名。");
		}
		
		try
		{
			String relativePath = _uploadFile(p_inputStream, "images", p_postId, fileName);
			String localPath = PTEnvironment.getSharedPath(relativePath);
			if (localPath.endsWith(".jpg"))
			{
				ImageUtil.resize(localPath, 1f, 580, 720);
			}
			return responseWithText(relativePath);
		}
		catch (Exception e)
		{
			return responseWithException("无法读取上传的内容。");
		}
	}

	@POST
	@Path("attachment")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadAttachment(
			@FormDataParam("postId") String p_postId,
			@FormDataParam("file") InputStream p_inputStream,
			@FormDataParam("file") FormDataContentDisposition p_fileDisposition)
	{
		String fileName = p_fileDisposition.getFileName().toLowerCase();
		String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		if (!_checkExtension(ext, "attachments"))
		{
			return responseWithException("不合法的文件扩展名。");
		}
		
		try
		{
			String relativePath = _uploadFile(p_inputStream, "attachments", p_postId, fileName);
			File file = PTEnvironment.getSharedFile(relativePath);
			if (file.exists())
			{
				PostAttachmentEntity attachment = new PostAttachmentEntity(fileName, file.length(), relativePath);
				PostAdminManager.getInstance().uploadPostAttachment(attachment, p_postId);
				return responseWithJSONObject(AttachmentJSONSerializer.toSimpleObject(attachment));
			}
			return responseWithException("无法完成上传操作。");
		}
		catch (Exception e)
		{
			return responseWithException("无法读取上传的内容。");
		}
	}
	
	@DELETE
	@Path("attachment/{id}")
	public Response uploadAttachment(@PathParam("id") String p_attachmentId)
	{
		PostAdminManager.getInstance().deletePostAttachment(p_attachmentId);
		return responseOK();
	}

	private String _uploadFile(InputStream p_inputStream,
			String p_folderName, String p_postId, String p_fileName) throws IOException
	{
		String folderPath = "/uploads/" + p_postId + "/" + p_folderName;
		File folder = new File(PTEnvironment.getSharedPath(folderPath));
		if (!folder.exists())
		{
			folder.mkdirs();
		}
		
		File localFile = new File(folder, p_fileName);
		
		OutputStream outputStream = new FileOutputStream(localFile);
		int length = 0;
		byte[] bytes = new byte[10240];
		while ((length = p_inputStream.read(bytes)) != -1)
		{
			outputStream.write(bytes, 0, length);
		}
		outputStream.flush();
		outputStream.close();

		return folderPath + "/" + p_fileName;
	}
	

	private boolean _checkExtension(String p_extension, String p_type)
	{
		if (p_type.equals("images"))
		{
			if (IMAGE_EXTENSION_LIST == null)
			{
				IMAGE_EXTENSION_LIST = new ArrayList<String>();
				IMAGE_EXTENSION_LIST.add("jpg");
				IMAGE_EXTENSION_LIST.add("png");
				IMAGE_EXTENSION_LIST.add("gif");
			}
			return IMAGE_EXTENSION_LIST.contains(p_extension);
		}
		else
		{
			return true;
		}
	}
}
