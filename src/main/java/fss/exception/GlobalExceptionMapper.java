package fss.exception;

import fss.api.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.stream.Collectors;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {
    
    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);
    
    @Override
    public Response toResponse(Exception e) {
        // Recursos no encontrados
        if (e instanceof NotFoundException || e instanceof ResourceNotFoundException) {
            LOG.warn("Recurso no encontrado: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                .entity(ApiResponse.error("Recurso no encontrado: " + e.getMessage()))
                .build();
        }
        
        // Errores de validación
        if (e instanceof ValidationException) {
            LOG.warn("Error de validación: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(ApiResponse.error(e.getMessage()))
                .build();
        }
        
        // Errores de validación de Bean Validation
          if (e instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) e;
            String message = cve.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
                
            LOG.warn("Errores de validación: " + message);
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(ApiResponse.error("Errores de validación: " + message))
                .build();
        }
        
        // Error de acceso denegado
        if (e instanceof ForbiddenException) {
            LOG.warn("Acceso denegado: " + e.getMessage());
            return Response.status(Response.Status.FORBIDDEN)
                .entity(ApiResponse.error("No tiene permisos para realizar esta operación"))
                .build();
        }
        
        // Error de autenticación
        if (e instanceof UnauthorizedException) {
            LOG.warn("Usuario no autenticado: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                .entity(ApiResponse.error("Debe autenticarse para acceder a este recurso"))
                .build();
        }
        
        // Método HTTP no permitido
        if (e instanceof NotAllowedException) {
            LOG.warn("Método no permitido: " + e.getMessage());
            return Response.status(Response.Status.METHOD_NOT_ALLOWED)
                .entity(ApiResponse.error("Método no permitido para este recurso"))
                .build();
        }
        
        // Tipo de contenido no soportado
        if (e instanceof NotSupportedException) {
            LOG.warn("Tipo de contenido no soportado: " + e.getMessage());
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE)
                .entity(ApiResponse.error("Tipo de contenido no soportado"))
                .build();
        }
        
        
        
        // Errores inesperados
        LOG.error("Error no controlado", e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(ApiResponse.error("Ha ocurrido un error interno en el servidor"))
            .build();
    }
}