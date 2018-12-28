package srv.file01;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Act01
 */
@WebServlet("/file01/Act01")
@MultipartConfig()
public class Act01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Act01() {
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
		
		// InputStreamをファイルに書き込み
		try(InputStream is = part.getInputStream()){
			Files.copy(is, Paths.get(dir + filePath), StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) {
			throw new ServletException(e);
		}
		
		String page = "entry.jsp";
		req.getRequestDispatcher(page).forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void test01(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
				// POST場合の文字コード指定
				req.setCharacterEncoding("UTF-8");
				
				// ファイルテスト
				Path path = Paths.get("H:/tempfiles/test01");
				System.out.println(path.getRoot());
				Files.createDirectories(path);
				
				String name = req.getParameter("name");
				System.out.println("name:" + name);
				req.setAttribute("name", name);
				
				String page = "entry.jsp";
				req.getRequestDispatcher(page).forward(req, res);
	}
}
