package prod.modelo.util;

import prod.modelo.reproductor.ManoReproductor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public static List<ManoReproductor> leeArchivoPokerStars(String rutaAlArchivo) {
		return null;
	}
}
