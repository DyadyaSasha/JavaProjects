package restclient;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * класс для запросов к Rest серверу
 */
public class Requests {

    /**
     * запрос на получение данных по URL - http://iss.moex.com/iss/index.json
     * @return строка в виде JSON объекта
     * @throws BadRequestException
     */
    public String getExchangeInformation() throws BadRequestException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://iss.moex.com/iss/index.json");
        Response response = target.request().get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(String.class);
        } else {
            throw new BadRequestException();
        }

    }

}
