package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class PetStoreOrderStep {

    Response response;
    private String URL_BASE;

    public void obtenerURL(String url) {
        URL_BASE = url;
    }


    public void ingresarDatosRegistro(int id, int petId, int quantity, String shipdate, String status, String complete) {
        String Body = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"petId\": "+petId+",\n" +
                "  \"quantity\": "+quantity+",\n" +
                "  \"shipDate\": \""+shipdate+"\",\n" +
                "  \"status\": \""+status+"\",\n" +
                "  \"complete\": "+complete+"\n" +
                "}";

        response = RestAssured
                .given()
                .baseUri(URL_BASE)
                .header("Content-Type","application/json")
                .body(Body)
                .log().all()
                .post("/store/order")
                .then()
                .extract().response();
    }

    public void verificarStatusCode(int statusCode) {
        Assert.assertEquals("Validacion de Respuesta", statusCode, response.statusCode());
    }

    public void ingresarOrderId(String orderId) {
        response = RestAssured
                .given()
                //.relaxedHTTPSValidation()
                .baseUri(URL_BASE)
                .log().all()
                .get("/store/order/"+orderId)// path params
                .then()
                .log().all()
                .extract().response();
    }

    public void verificarDatos(int petId, int quantity, String shipdate, String status, String complete) {
        int responsepetId = response.jsonPath().getInt("petId");
        int responsequantity = response.jsonPath().getInt("quantity");
        String responseshipdate = response.jsonPath().getString("shipDate");
        String responsestatus = response.jsonPath().getString("status");
        String responsecomplete = response.jsonPath().getString("complete");

        Assert.assertEquals("El petId no coincide", petId, responsepetId);
        Assert.assertEquals("El quantity no coincide", quantity, responsequantity);
        Assert.assertEquals("El shipdate no coincide", shipdate, responseshipdate);
        Assert.assertEquals("El status no coincide", status, responsestatus);
        Assert.assertEquals("El complete no coincide", complete, responsecomplete);

        System.out.println("Verificación completada de datos");
    }
}
