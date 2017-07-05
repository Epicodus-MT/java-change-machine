import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/result", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      ChangeMachine myChangeMachine = new ChangeMachine();
      Float flCash = Float.valueOf(request.queryParams("cash"));
      model.put("cash", request.queryParams("cash"));
      model.put("flCash", flCash);
      model.put("template", "templates/result.vtl");
      model.put("finalResult", myChangeMachine.makeChange(flCash));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
