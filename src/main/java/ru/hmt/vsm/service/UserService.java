/**
 * 
 */
package ru.hmt.vsm.service;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import ru.funsys.avalanche.Messages;
import ru.funsys.avalanche.rs.RestService;
import ru.hmt.vsm.app.UserApplication;
import ru.hmt.vsm.model.Employee;

/**
 * 
 */
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
@Tag(name = "user", description = "${user.description}")
@Singleton

public class UserService extends RestService {

	private UserApplication app;

	@Operation(summary = "Получить данные текущего пользователя",
			responses = {
            		@ApiResponse(responseCode = "200", description = "${http.200}", content = @Content(schema = @Schema(description = "$(user.current)", implementation = Employee.class))), 
    				@ApiResponse(responseCode = "401", description = "${http.401}"),
    				@ApiResponse(responseCode = "403", description = "${http.403}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@GET
	public Employee currentUser(@Context HttpServletRequest request) throws WebApplicationException{
		String user = request.getRemoteUser();
		if (user == null) throw new WebApplicationException(Messages.getMessage("VSM0004E"), 401);
		try {
			return app.getEmployee(request.getRemoteUser());
		} catch (Exception e) {
			new WebApplicationException(e, 500);
		}
		return null;
	}

	
	@Operation(summary = "Загрузка данных любых данных их смежных систем",
			responses = {
            		@ApiResponse(responseCode = "204", description = "${http.204}"), 
    				@ApiResponse(responseCode = "401", description = "${http.401}"),
    				@ApiResponse(responseCode = "403", description = "${http.403}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@Path("/load")
	@POST
	public void loadData(HashMap<String, Object> map) {
		// проанализировать корневые атрибуты принятых данных, по которым распознать тип принятых данных
		
		// создать экзепляр класса соответсвующий типу принятых данных и передать конструктору полученный экземпляр map
		// для выбора только тредуемых атрибутов 
		
		// передать заполненный экзепляр класса для записи в БД
		
	}
}
