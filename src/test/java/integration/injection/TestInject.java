package integration.injection;

import static org.junit.Assert.*;
import integration.TestVerticle;
import integration.VertxNubesTestBase;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import mock.domains.Dog;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class TestInject extends VertxNubesTestBase {

    @Test
    public void testInjectService(TestContext context) {
        Async async = context.async();
        int i = 2;
        Dog dog = TestVerticle.dogService.getDog(i);
        getJSON("/inject/service?idx=" + i, response -> {
            response.bodyHandler(buff -> {
                JsonObject json = new JsonObject(buff.toString());
                assertEquals(dog.getName(), json.getString("name"));
                async.complete();
            });
        });
    }

    @Test
    public void testInjectClass(TestContext context) {
        Async async = context.async();
        Dog snoop = TestVerticle.SNOOPY;
        getJSON("/inject/class", response -> {
            response.bodyHandler(buff -> {
                JsonObject json = new JsonObject(buff.toString());
                assertEquals(snoop.getName(), json.getString("name"));
                async.complete();
            });
        });
    }

}