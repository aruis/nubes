package mock.controllers.params;

import io.vertx.ext.web.RoutingContext;

import java.util.Date;

import com.github.aesteve.vertx.nubes.annotations.Controller;
import com.github.aesteve.vertx.nubes.annotations.params.Header;
import com.github.aesteve.vertx.nubes.annotations.routing.Path;

@Controller("/headers/")
public class HeadersControllerTest {

    @Path("mandatory")
    public void mandatoryHeader(RoutingContext context, @Header(value = "X-Date", mandatory = true) Date date) {
        context.response().end(Long.toString(date.getTime()));
    }

    @Path("facultative")
    public void facultativeHeader(RoutingContext context, @Header("X-Date") Date date) {
        if (date == null) { // allowed
            context.response().end("null");
        } else {
            context.response().end(Long.toString(date.getTime()));
        }
    }
}
