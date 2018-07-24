

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Servlet implementation class SysApi
 */
@WebServlet("/api")
public class SysApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SysApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String accessToken = "";
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equals("ssoclient_token")) {
				if (!cookie.getValue().equals("")) {
					accessToken = cookie.getValue();
					String action = request.getParameter("action");
					if (null == action || "undefined".equalsIgnoreCase(action)) {
						response.getWriter().append("{\"code\":404,\"message\":\"Module parameter not found.\"}");
						return;
					}
					switch (action) {
						case "search":
							searchMusic(request, response, request.getParameter("keyword"));
							break;
						case "resource":
							getMusicUrl(request, response, request.getParameter("rid"));
							break;
						default:
							response.getWriter().append("{\"code\":405,\"message\":\"Method not found.\"}");
							break;
					}
				}
				else {
					response.getWriter().append("{\"code\":401,\"message\":\"Permission denied.\"}");
				}
			}
		}
		
		if (accessToken.equals("")) {	
			response.getWriter().append("{\"code\":401,\"message\":\"Permission denied.\"}");
		}
	}
	
	private void getMusicUrl(HttpServletRequest request, HttpServletResponse response, String musicId) {
		OkHttpClient client = new OkHttpClient();
		Request req = new Request.Builder()
		  .url("http://api.dingstudio.cn/api?format=json&mod=NeteaseMusic&id=" + musicId)
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .removeHeader("User-Agent")
          .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0")
		  .build();
		try {
			Response res = client.newCall(req).execute();
			response.getWriter().append(res.body().string());
		} catch(IOException e) {
			try {
				response.getWriter().append("{\"code\":502,\"message\":\"" + e.getMessage() + "\"}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	private void searchMusic(HttpServletRequest request, HttpServletResponse response, String musicName) {
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		String key = "";
		try {
			key = java.net.URLEncoder.encode(musicName, "utf-8");
		} catch(Exception e) {
			key = musicName;
		}
		RequestBody body = RequestBody.create(mediaType, "s=" + key + "&type=1&offset=0");
		Request req = new Request.Builder()
		  .url("http://music.163.com/api/search/pc")
		  .post(body)
		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
		  .addHeader("Cache-Control", "no-cache")
		  .removeHeader("User-Agent")
          .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0")
		  .build();
		try {
			Response res = client.newCall(req).execute();
			response.getWriter().append(res.body().string());
		} catch(IOException e) {
			try {
				response.getWriter().append("{\"code\":502,\"message\":\"" + e.getMessage() + "\"}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
