/**
 * 
 */
package ru.hmt.vsm.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Singleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import ru.funsys.avalanche.Messages;
import ru.funsys.avalanche.rs.RestService;
import ru.hmt.vsm.app.UserApplication;

/**
 * 
 */
@Path("")
@Tag(name = "authentication", description = "${auth.descrioption}")
@Singleton
public class AuthenticationService extends RestService {

	private UserApplication app;
	
	@Operation(summary = "${auth.login}",
			responses = {
            		@ApiResponse(responseCode = "204", description = "${http.204}"), 
    				@ApiResponse(responseCode = "401", description = "${http.401}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@Path("/login")
	@POST
	public void login(@Context HttpServletRequest request,
			@QueryParam("name") @Parameter(description = "${auth.loginParameter}", required = true) String name,
			@QueryParam("password") @Parameter(description = "${auth.passwordParameter}", required = true) String password) throws WebApplicationException {
		String login = request.getRemoteUser();
		if (login == null) {
			login = app.getLogin(name);
			if (login == null) throw new WebApplicationException(Messages.getMessage("VSM0003E", new Object[] {name}), 401);
			try {
				request.getSession(true);
				request.login(login.trim(), password);
			} catch (ServletException e) {
				throw new WebApplicationException(Messages.getMessage("VSM0003E", new Object[] {name}), e, 401);
			}
			
		}
	}
	
	@Operation(summary = "Завершить сессию пользователя",
			responses = {
            		@ApiResponse(responseCode = "204", description = "${http.204}"), 
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@Path("/logout")
	@POST
	public void logout(@Context HttpServletRequest request) throws WebApplicationException {
		HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();
	}
	
}
