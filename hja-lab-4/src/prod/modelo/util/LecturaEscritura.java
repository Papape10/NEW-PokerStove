package prod.modelo.util;

import prod.baraja.Carta;
import prod.modelo.reproductor.EstadoJugador;
import prod.modelo.reproductor.EstadoMesa;
import prod.modelo.reproductor.ManoReproductor;
import prod.modelo.reproductor.acciones.AccionReproductor;
import prod.modelo.reproductor.acciones.I_AccionReproductor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LecturaEscritura {

	public static HashMap<String, String[]> leeRankings() {
		
		HashMap<String, String[]> rankings = new HashMap<String, String[]>();
		String cadena, nombreRanking;
		String[] manosRanking;
		
		File archivo = new File("src/archivos/rankings.txt");
		if(!archivo.exists())
			System.out.println("El archivo no existe.");
			
		else {
			BufferedReader bf;
			try {
				
				bf = new BufferedReader(new FileReader(archivo));
				while ((cadena = bf.readLine()) != null) {
					nombreRanking = cadena;
					cadena = bf.readLine();
					manosRanking = cadena.split(" ");
					rankings.put(nombreRanking, manosRanking);
				}
				bf.close();
				
			} catch (FileNotFoundException e1) {
				
			} catch (IOException e) {
				
			} finally {
				
			}
		}
		
		return rankings;
	}
	
	public static HashMap<String, Float> leeRangos() {
		HashMap<String, Float> rangos = new HashMap<String, Float>();
		String cadena, nombreRango;
		String[] datos;
		Float porcentaje;
		
		File archivo = new File("src/archivos/rangos.txt");
		if(!archivo.exists())
			System.out.println("El archivo no existe.");
			
		else {
			BufferedReader bf;
			try {
				
				bf = new BufferedReader(new FileReader(archivo));
				while ((cadena = bf.readLine()) != null) {
					datos = cadena.split(" : ");
					nombreRango = datos[0];
					porcentaje = Float.parseFloat(datos[1]);
					rangos.put(nombreRango, porcentaje);
				}
				bf.close();
				
			} catch (FileNotFoundException e1) {
				
			} catch (IOException e) {
				
			}
		}
		
		return rangos;
	}
	
	public static void escribeRanking(String nombre, String[] manos) {
		FileWriter fw = null;
        BufferedWriter bw = null;
        String line = "";
        
        try {
            fw = new FileWriter("src/archivos/rankings.txt", true);
            bw = new BufferedWriter(fw);
            
            bw.newLine();
            bw.write(nombre);
            
            bw.newLine();
            for (int i = 0; i < manos.length; i++) {
				line += manos[i] + " ";
			}
            bw.write(line);
           
            bw.close();
            fw.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public static void escribeRango(String line) {
		FileWriter fw = null;
        BufferedWriter bw = null;
        
        try {
            fw = new FileWriter("src/archivos/rangos.txt", true);
            bw = new BufferedWriter(fw);
            
            bw.newLine();
            bw.write(line);
            
            bw.close();
            fw.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	public static String leeAyuda() {
		String cadena, texto = "";
		
		File archivo = new File("src/archivos/help.txt");
		if(!archivo.exists())
			System.out.println("El archivo no existe.");
			
		else {
			BufferedReader bf;
			try {
				
				bf = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF8"));
				while ((cadena = bf.readLine()) != null) {
					texto += cadena + '\n';
				}
				bf.close();
				
			} catch (FileNotFoundException e1) {
				
			} catch (IOException e) {
				
			} finally {
				
			}
		}
		
		return texto;
	}

	public static List<ManoReproductor> leeArchivoPokerStars(File archivo) {
		List<ManoReproductor> manos = new ArrayList<>();
		List<I_AccionReproductor> acciones = new ArrayList<>();

		if(!archivo.exists()) {
			System.out.println("El archivo no existe.");

		} else {

			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(archivo));
				// Variables locales
				int seatDelBoton = 0;
				List<String> nombresJugadores = new ArrayList<>();
				List<Double> stacksJugadores = new ArrayList<>();
				List<Double> apuestasJugadores = new ArrayList<>();
				String nombreHero = "";
				List<Carta> cartasHero = new ArrayList<>();
				List<Carta> cartasEnMesa = new ArrayList<>();
				List<Boolean> hasFolded = new ArrayList<>();

				double pot = 0.0;

				// Bucle de lectura
				String linea;
				String[] tokens; // Solo se hace split cuando hace falta.
				while ((linea = reader.readLine()) != null) {
					// Casos de parseo
					if (linea.matches("(\\** SUMMARY \\**)")) {
						//Resetea acciones, y crea una nueva mano
						manos.add(new ManoReproductor(new ArrayList<I_AccionReproductor>(acciones)));
						acciones.clear();
						//Vaciar los arrays y demas
						seatDelBoton = 0;
						nombresJugadores.clear();
						stacksJugadores.clear();
						apuestasJugadores.clear();
						cartasHero.clear();
						cartasEnMesa.clear();
						hasFolded.clear();
						pot  = 0;
					} else if (linea.matches("(.* Seat #\\d is the button)"))  { // Numero de jugadores, seat del button
						seatDelBoton = parseaSeatDelBoton(linea);
					} else if (linea.matches("(Seat \\d:.* in chips.\\s*)")){
						tokens = linea.split(" ");
						nombresJugadores.add(parseaNombreJugador(linea, tokens));
						stacksJugadores.add(parseaStackJugador(linea, tokens));
					} else if (linea.matches("(.* posts small blind .*)")) {
						tokens = linea.split(" ");
						for (String i : nombresJugadores) {
							apuestasJugadores.add(0.0);
						}
						for (String i : nombresJugadores) {
							hasFolded.add(false);
						}
						double ciegaPequena = parseaCiegaPequena(linea, tokens);
						int seatCiegaPequena = (seatDelBoton + 1) % stacksJugadores.size();
						pot += ciegaPequena;
						stacksJugadores.set(seatCiegaPequena, stacksJugadores.get(seatCiegaPequena) - ciegaPequena);
						apuestasJugadores.set(seatCiegaPequena, ciegaPequena);
					} else if (linea.matches("(.* posts big blind .*)")){
						tokens = linea.split(" ");
						double ciegaGrande = parseaCiegaGrande(linea, tokens);
						int seatCiegaGrande = (seatDelBoton + 2) % stacksJugadores.size();
						pot += ciegaGrande;
						stacksJugadores.set(seatCiegaGrande, stacksJugadores.get(seatCiegaGrande) - ciegaGrande);
						apuestasJugadores.set(seatCiegaGrande, ciegaGrande);
					} else if (linea.matches("(Dealt to .*\\s[\\[][AKQJT2-9][hscd] [AKQJT2-9][hscd].)")) {
						tokens = linea.split(" ");
						nombreHero = parseaNombreHero(linea, tokens);
						cartasHero = parseaCartasHero(linea, tokens);
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
														apuestasJugadores, hasFolded, seatDelBoton, nombreHero, cartasHero));
					} else if (linea.matches("(.*: raises .*)")) {
						tokens = linea.split(" ");
						int iraiser = nombresJugadores.indexOf(tokens[0].substring(0, tokens[0].length()-1));
						double apuesta = parseaApuestaRaise(linea, tokens);
						stacksJugadores.set(iraiser, stacksJugadores.get(iraiser) - apuesta);
						apuestasJugadores.set(iraiser, apuestasJugadores.get(iraiser) + apuesta);
						pot += apuesta;
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, seatDelBoton, nombreHero, cartasHero));
					} else if (linea.matches("(.*: folds .*)")) {
						tokens = linea.split(" ");
						int ifolded = nombresJugadores.indexOf(tokens[0].substring(0, tokens[0].length()-1));
						hasFolded.set(ifolded, true);
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, seatDelBoton, nombreHero, cartasHero));
					} else if (linea.matches("(Uncalled bet \\(.*\\) returned to .*)")) {
						tokens = linea.split(" ");
						int iganador = nombresJugadores.indexOf(tokens[tokens.length - 1]);
						double collected = parseaUncalledBet(linea, tokens);
						stacksJugadores.set(iganador, stacksJugadores.get(iganador) + collected);
					} else if (linea.matches("(.* collected .* from pot)")) {
						tokens = linea.split(" ");
						int iganador = nombresJugadores.indexOf(tokens[0]);
						double collected = parseaGanadoDelPot(linea, tokens);
						stacksJugadores.set(iganador, stacksJugadores.get(iganador) + collected);
						for (int i = 0; i < apuestasJugadores.size(); i++) {
							apuestasJugadores.set(i, 0.0);
						}
						pot = 0;
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, seatDelBoton, nombreHero, cartasHero));
					}
				}
				reader.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return manos;
	}

	private static AccionReproductor construyeAccion(String linea,
													 double pot,
													 List<Carta> cartasEnMesa,
													 List<String> nombresJugadores,
													 List<Double> stacksJugadores,
													 List<Double> apuestasJugadores,
													 List<Boolean> hasFolded,
													 int seatButton,
													 String nombreHero,
													 List<Carta> cartasHero) {

		List<EstadoJugador> estadosJugadores = new ArrayList<>();
		boolean isHero, isButton;
		for (int i = 0; i < nombresJugadores.size(); i++) {
			isHero = (nombresJugadores.get(i).equals(nombreHero));
			isButton = (i == seatButton);

			estadosJugadores.add(new EstadoJugador(nombresJugadores.get(i),
													(isHero ? new ArrayList<Carta>(cartasHero) : null),
													stacksJugadores.get(i),
													apuestasJugadores.get(i),
													hasFolded.get(i),
													isButton));

		}

		EstadoMesa estadoMesa = new EstadoMesa(estadosJugadores, pot, (cartasEnMesa.size() > 0 ? new ArrayList<>(cartasEnMesa) : null));
		AccionReproductor accion = new AccionReproductor(estadoMesa, linea);

		return accion;
	}

	private static List<Carta> parseaCartasHero(String linea, String[] tokens) {
		List<Carta> res = new ArrayList<>(2);
		res.add(new Carta(tokens[3].substring(1)));
		res.add(new Carta(tokens[4].substring(0, 3)));
		return res;
	}

	private static String parseaNombreHero(String linea, String[] tokens) {
		return tokens[2];
	}

	private static double parseaCiegaGrande(String linea, String[] tokens) {
		return Double.parseDouble(tokens[4].substring(3));
	}

	private static double parseaUncalledBet(String linea, String[] tokens) {
		return Double.parseDouble(tokens[2].substring(4, tokens[2].length() - 1));
	}

	private static double parseaCiegaPequena(String linea, String[] tokens) {
		return Double.parseDouble(tokens[4].substring(3));
	}

	private static double parseaStackJugador(String linea, String[] tokens) {
		return Double.parseDouble(tokens[3].substring(4));
	}

	private static String parseaNombreJugador(String linea, String[] tokens) {
		return tokens[2];
	}

	private static int parseaSeatDelBoton(String linea) {
		int i = linea.indexOf('#');
		return Integer.parseInt(linea.substring(i + 1, i + 2)) - 1;
	}

	private static double parseaApuestaRaise(String linea, String[] tokens) {
		return Double.parseDouble(tokens[tokens.length - 1].substring(3));
	}

	private static double parseaGanadoDelPot(String linea, String[] tokens) {
		return Double.parseDouble(tokens[2].substring(3));
	}
}
