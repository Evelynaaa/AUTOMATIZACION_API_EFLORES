

Feature: Creacion y consulta en PetStore Order

  @CrearOrder
  Scenario Outline: Crear en Store order
    Given estando en la pagina con url "https://petstore.swagger.io/v2"
    When creo un order e ingreso los datos de id <id>, petId <petId>, quantity <quantity>, shipdate "<shipdate>", status "<status>", complete "<complete>"
    Then verifico que el codigo de respuesta sea <statuscode>
    Examples:
      | id | petId | quantity | shipdate                     | status | complete | statuscode |
      | 5  | 5     | 5        | 2024-12-16T19:47:24.000+0000 | placed | true     | 200        |
      | 7  | 7     | 7        | 2024-12-16T19:47:24.000+0000 | placed | true     | 200        |

  @ConsultarOrder
  Scenario Outline: Consultar en Store order
      Given estando en la pagina con url "https://petstore.swagger.io/v2"
      When consulto los datos segun el orderId <id>
      Then verifico que el codigo de respuesta sea <statuscode>
      And valido que los datos sean petId <petId>, quantity <quantity>, shipdate "<shipdate>", status "<status>", complete "<complete>"
      Examples:
        | id | statuscode | petId | quantity | shipdate                     | status | complete |
        | 5  | 200        | 5     | 5        | 2024-12-16T19:47:24.000+0000 | placed | true     |
        | 7  | 200        | 7     | 7        | 2024-12-16T19:47:24.000+0000 | placed | true     |