package com.todo.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.todo.domain.User;

@Service
public class TodoAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	/**
	 * target.
	 */
	private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();

	/**
	 * service.
	 */
	@Autowired
	UserService service;

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		User user = service.read(authentication.getName());
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if(user.getReqNewPwd()){
			response.sendRedirect(request.getContextPath()+"/jsp/changepassword.jsp");
		} else {
			target.onAuthenticationSuccess(request, response, authentication);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param auth
	 * @throws IOException
	 * @throws ServletException
	 */
	public void proceed(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		target.onAuthenticationSuccess(request, response, auth);
	}

}
