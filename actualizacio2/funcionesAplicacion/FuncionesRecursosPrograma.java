package funcionesAplicacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import clases.Criptomoneda;

public class FuncionesRecursosPrograma {

	private static HashMap<String, ImageIcon> iconosCriptomoneda = new HashMap<>();
	private static HashMap<String, ImageIcon> iconosFIAT = new HashMap<>();

	public static void cargarImagenesCriptomonedas() {
		iconosCriptomoneda.clear();
		List<Criptomoneda> monedas = new ArrayList<>();
		monedas.add(new Criptomoneda(1, "Bitcoin", "BTC", 50000.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "Etherium", "ETH", 10.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "DogeCoin", "DOGE", 55.5555555551,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "Solana", "SOL", 250.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Bitcoin.png", 0));
		monedas.add(new Criptomoneda(1, "Ripple", "XRP", 1.20,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Ripple.png", 0));
		monedas.add(new Criptomoneda(1, "Litecoin", "LTC", 180.5,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Litecoin.png", 0));
		monedas.add(new Criptomoneda(1, "Cardano", "ADA", 0.45,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Cardano.png", 0));
		monedas.add(new Criptomoneda(1, "Polkadot", "DOT", 7.50,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Polkadot.png", 0));
		monedas.add(new Criptomoneda(1, "Chainlink", "LINK", 30.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Chainlink.png", 0));
		monedas.add(new Criptomoneda(1, "Bitcoin Cash", "BCH", 700.0,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/BitcoinCash.png", 0));
		monedas.add(new Criptomoneda(1, "Stellar", "XLM", 0.25,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Stellar.png", 0));
		monedas.add(new Criptomoneda(1, "TRON", "TRX", 0.08,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/Tron.png", 0));
		monedas.add(new Criptomoneda(1, "VeChain", "VET", 0.10,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/VeChain.png", 0));
		monedas.add(new Criptomoneda(1, "Shiba Inu", "SHIB", 0.000007,
				"/mnt/DiscoD/Programaciones/Eclipse/CriptoWallet/resources/ShibaInu.png", 0));

		for (Criptomoneda c : monedas) {
			iconosCriptomoneda.put(c.getNomenclatura(), new ImageIcon(c.getNombreIcono()));
		}
	}

	public static HashMap<String, ImageIcon> obtenerIconsDeCriptomonedas() {

		return iconosCriptomoneda;
	}

	public static void cargarImagenesFIAT() {

	}

	public static HashMap<String, ImageIcon> obtenerIconsDeFIATS() {
		return iconosFIAT;
	}
}
