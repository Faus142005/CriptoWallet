package funcionesAplicacion;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

import org.json.JSONObject;

import clases.Criptomoneda;
import clases.Moneda;
import daos.FactoryDAO;
import daos.MonedaDAO;

public class FuncionesDeConexionCriptomonedas {

	private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";

	public static void actualizarPrecios() {
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();
		MonedaDAO<Moneda> monedaDAO = FactoryDAO.getMonedaDAO();
		try {
			HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
			if (respuesta.statusCode() == 200) {
				
				JSONObject json = new JSONObject(respuesta.body());
				
				Criptomoneda c;
				
				c = monedaDAO.buscarCriptomoneda("BTC");
				if(c == null)
					return;
				c.setValorDolar(json.getJSONObject("bitcoin").getDouble("usd"));
				monedaDAO.actualizarCriptomoneda(c);
				
				c = monedaDAO.buscarCriptomoneda("ETH");
				c.setValorDolar(json.getJSONObject("ethereum").getDouble("usd"));
				monedaDAO.actualizarCriptomoneda(c);
				
				c = monedaDAO.buscarCriptomoneda("USDC");
				c.setValorDolar(json.getJSONObject("usd-coin").getDouble("usd"));
				monedaDAO.actualizarCriptomoneda(c);
				
				c = monedaDAO.buscarCriptomoneda("USDT");
				c.setValorDolar(json.getJSONObject("tether").getDouble("usd"));
				monedaDAO.actualizarCriptomoneda(c);
				
				c = monedaDAO.buscarCriptomoneda("DOGE");
				c.setValorDolar(json.getJSONObject("dogecoin").getDouble("usd"));
				monedaDAO.actualizarCriptomoneda(c);

			} else {
				System.err.println("Error: " + respuesta.statusCode());
			}
		} catch (IOException | InterruptedException | SQLException e) {
			e.printStackTrace();
		}
	}
}
