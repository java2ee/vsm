/**
 * 
 */
package ru.hmt.vsm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Singleton;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import ru.funsys.avalanche.rs.RestService;
import ru.hmt.vsm.app.BinaryApplication;
import ru.hmt.vsm.model.UploadFile;

@Path("/binary")
@Tag(name = "binary", description = "${binary.description}")
@Singleton
public class BinaryService extends RestService {

	private BinaryApplication app;
	
	@Operation(summary = "${binary.save}",
			responses = {
            		@ApiResponse(responseCode = "200", description = "${http.200}"), 
    				@ApiResponse(responseCode = "403", description = "${http.403}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@POST
    @Path("/scenario/{mnemo}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = UploadFile.class)))	
    public String saveScenarioIcon(@Parameter(name="mnemo", description = "Мнемокод иконки сценария") @PathParam("mnemo") String mnemo,
    		@BeanParam UploadFile file) throws WebApplicationException {
		try {
	    	return app.saveScenarioIcon(mnemo, file);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
    }

	@Operation(summary = "${binary.delete}",
			responses = {
            		@ApiResponse(responseCode = "204", description = "${http.204}"), 
    				@ApiResponse(responseCode = "403", description = "${http.403}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@DELETE
    @Path("/scenario/{mnemo}")
    public void deleteScenarioIcon(@Parameter(name="mnemo", description = "Мнемокод иконки сценария") @PathParam("mnemo") String mnemo) throws WebApplicationException {
		try {
	    	app.deleteScenarioIcon(mnemo);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
    }

	@Operation(summary = "Служебный метод (таблицы пересоздаются): Загрузка сценариев",
			responses = {
            		@ApiResponse(responseCode = "204", description = "${http.204}"), 
    				@ApiResponse(responseCode = "401", description = "${http.401}"),
    				@ApiResponse(responseCode = "403", description = "${http.403}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@Path("/load")
	@POST
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public void loadData(HashMap<String, Object> map) throws WebApplicationException {
		try {
			app.loadData(map);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

	@Operation(summary = "Служебный метод: Посмотр таблицы",
			responses = {
            		@ApiResponse(responseCode = "204", description = "${http.204}"), 
    				@ApiResponse(responseCode = "401", description = "${http.401}"),
    				@ApiResponse(responseCode = "403", description = "${http.403}"),
    				@ApiResponse(responseCode = "500", description = "${http.500}") })
	@Path("/select")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Map<String,Object>> select(@QueryParam("table") @Parameter(description = "Имя таблицы", required = true) String table) throws WebApplicationException{
		try {
			return app.select(table);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
}
