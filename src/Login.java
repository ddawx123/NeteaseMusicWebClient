

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "LoginServlet", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String token = request.getParameter("token");
		
		if (null != token && !"undefined".equalsIgnoreCase(token)) {
			//response.getWriter().append("Served at: ").append(token);
			StringBuffer sb = new StringBuffer();
			InputStreamReader isr = null;
	        BufferedReader br = null;
	        try {
	            URL url = new URL("https://passport.dingstudio.cn/sso/api?format=json&action=verify&token=" + token + "&reqtime=none&ver=simple");
	            URLConnection urlConnection = url.openConnection();
	            urlConnection.setAllowUserInteraction(false);
	            isr = new InputStreamReader(url.openStream());
	            br = new BufferedReader(isr);
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	        } catch (IOException e) {
	        	response.getWriter().append(e.getMessage());
	            e.printStackTrace();
	        }
	        String retData = sb.toString();
	        if (retData.equals("pass@dingstudio")) {
	        	Cookie cookie = new Cookie("ssoclient_token", token);
	        	cookie.setDomain(request.getServerName());
	        	cookie.setPath("/");
	        	cookie.setMaxAge(3600);
	        	cookie.setHttpOnly(true);
	        	response.addCookie(cookie);
	        	response.sendRedirect("./index.jsp?token=" + token);
	        }
		}
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String accessToken = "";
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equals("ssoclient_token")) {
				if (!cookie.getValue().equals("")) {
					accessToken = cookie.getValue();
					response.sendRedirect("./index.jsp");
				}
				else {
					String curl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1) + "login";
					response.sendRedirect("https://passport.dingstudio.cn/sso/login?returnUrl=" + java.net.URLEncoder.encode(curl, "utf-8"));
				}
			}
		}
		
		if (accessToken.equals("")) {	
			String curl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1) + "login";
			try {
				response.sendRedirect("https://passport.dingstudio.cn/sso/login?returnUrl=" + java.net.URLEncoder.encode(curl, "utf-8"));
			} catch(Exception e) {
				e.printStackTrace();
			}
			
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
