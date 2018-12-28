package srv.aws01;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ot.file.AwsFile;
import ot.io.FileIf;

/**
 * Servlet implementation class AwsAct01
 */
@WebServlet("/aws01/AwsAct01")
@MultipartConfig
public class AwsAct01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AwsAct01() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
				req.setCharacterEncoding("UTF-8");
				
				final String dir ="H:/tempfiles/";
				
				// パラメータの取得も可能
				String name = req.getParameter("name");
				
				// ファイル情報の取得
				Part part = req.getPart("file");
				
				// part.getSubmittedFileName()が正常に動作しなかったので
				// ファイル名取得方法をコピー 
				String filePath = "";
				for (String cd : part.getHeader("content-disposition").split(";")) {
					if (cd.trim().startsWith("filename")) {
						System.out.println(cd);
						filePath = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
						filePath = filePath.substring(filePath .lastIndexOf('/') + 1).substring(filePath .lastIndexOf('\\') + 1); // MSIE fix.
					}
				}
				
				System.out.println("properties 設定");
				System.out.println(System.getProperty("accessKey"));
				System.out.println(System.getProperty("proxyHost"));
				
				FileIf cloud = new AwsFile();
				
				// InputStreamをファイルに書き込み
				try(InputStream is = part.getInputStream()){
					cloud.write(is, "tempfiles/" + filePath);
//					Files.copy(is, Paths.get(dir + filePath), StandardCopyOption.REPLACE_EXISTING);
				}catch(Exception e) {
					throw new ServletException(e);
				}
				
				res.getWriter().println("upload end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
