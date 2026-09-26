/**
 * 
 */
package ru.hmt.vsm.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import ru.funsys.avalanche.rs.RestService;
import ru.hmt.vsm.app.ScenarioApplication;
import ru.hmt.vsm.model.Scenario;

/**
 * 
 */
@Path("/scenario")
@Tag(name = "scenario", description = "${scenario.description}")
@Singleton
public class ScenarioService extends RestService {

	private ScenarioApplication app;
	
	@Operation(summary = "${scenario.getScenario}",
			responses = {
            		@ApiResponse(responseCode = "200", description = "${http.200}", content = @Content(schema = @Schema(implementation = Scenario.class))), 
    				@ApiResponse(responseCode = "403", description = "${http.403}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@GET
	@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
	public Scenario getScenario(@QueryParam("mnemo") @Parameter(description = "Мнемо код сценария", required = true) String mnemo) throws WebApplicationException {
		// TODO Вопрос, есть сцинарии которые находятся в разработке
		try {
			return app.getScenario(mnemo);
		} catch (Exception e) {
			throw new WebApplicationException(e, 500);
		}
	}

	@Operation(summary = "${scenario.getScenarios}",
			responses = {
            		@ApiResponse(responseCode = "200", description = "${http.200}", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Scenario.class)))), 
    				@ApiResponse(responseCode = "403", description = "${http.403}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@Path("/list")
	@GET
	@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
	public Scenario[] getScenarios() throws WebApplicationException {
		// TODO Вопрос, есть сцинарии которые находятся в разработке
		try {
			return app.getScenarios();
		} catch (Exception e) {
			throw new WebApplicationException(e, 500);
		}
	}

	@Operation(summary = "Добавить сценарий",
			responses = {
            		@ApiResponse(responseCode = "200", description = "${http.200}"), 
    				@ApiResponse(responseCode = "403", description = "${http.403}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@POST
	public void addScenario(@QueryParam("mnemo") @Parameter(description = "Мнемо код сценария", required = true) String mnemo,
			@QueryParam("title") @Parameter(description = "Название сценария", required = true) String title,
			@QueryParam("symmary") @Parameter(description = "Описание сценария", required = true) String symmary) throws WebApplicationException {
		try {
			app.addScenario(mnemo, title, symmary);
		} catch (Exception e) {
			throw new WebApplicationException(e, 500);
		}
	}

}
