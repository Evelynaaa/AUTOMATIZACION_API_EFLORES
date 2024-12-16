package com.nttdata.glue;

import com.nttdata.steps.PetStoreOrderStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class PetStoreOrderStepdefs {

    @Steps
    PetStoreOrderStep StoreOrder;

    @Given("estando en la pagina con url {string}")
    public void estandoEnLaPaginaConUrl(String URL) {
        StoreOrder.obtenerURL(URL);
    }

    @When("creo un order e ingreso los datos de id {}, petId {}, quantity {}, shipdate {string}, status {string}, complete {string}")
    public void creoUnOrderEIngresoLosDatosDeIdPetIdQuantityShipdateStatusComplete(int id, int petId, int quantity, String shipdate, String status, String complete) {
        StoreOrder.ingresarDatosRegistro(id,petId,quantity,shipdate,status,complete);
    }

    @When("consulto los datos segun el orderId {}")
    public void consultoLosDatosSegunElOrderId(String orderId) {
        StoreOrder.ingresarOrderId(orderId);
    }

    @Then("verifico que el codigo de respuesta sea {int}")
    public void verificoQueElCodigoDeRespuestaSea(int statusCode) {
        StoreOrder.verificarStatusCode(statusCode);
    }

    @And("valido que los datos sean petId {}, quantity {}, shipdate {string}, status {string}, complete {string}")
    public void validoQueLosDatosSeanPetIdQuantityShipdateStatusComplete(int petId, int quantity, String shipdate, String status, String complete) {
        StoreOrder.verificarDatos(petId,quantity,shipdate,status,complete);
    }
}


