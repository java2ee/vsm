/**
 * 
 */
package ru.hmt.vsm.service;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Singleton;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import ru.funsys.avalanche.rs.RestService;
import ru.hmt.vsm.app.BinaryApplication;
import ru.hmt.vsm.model.UploadFile;

@Path("/binary")
@Tag(name = "binary", description = "${binary.descrioption}")
@Singleton
public class BinaryService extends RestService {

	private BinaryApplication app;
	
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

	@DELETE
    @Path("/scenario/{mnemo}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
    public void deleteScenarioIcon(@Parameter(name="mnemo", description = "Мнемокод иконки сценария") @PathParam("mnemo") String mnemo) throws WebApplicationException {
		try {
	    	app.deleteScenarioIcon(mnemo);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
    }

}
