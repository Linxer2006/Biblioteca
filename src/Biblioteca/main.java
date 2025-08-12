package Biblioteca;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;

public class main {
	private static final String SECRET_KEY = "1234567890123456";
	public static String encrypt(String plainText) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
	public static LinkedList<Carte> lista_impr=new LinkedList<Carte>();
	
	public static String decrypt(String encryptedText) throws Exception {
	        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        byte[] decoded = Base64.getDecoder().decode(encryptedText);
	        byte[] decrypted = cipher.doFinal(decoded);
	        return new String(decrypted);
	    }
	public static void main(String[] args) throws Exception {
		System.out.println("Bun venit!\nOptiuni:\n 1:Autentificare\n 2:Logare\n Q:Iesire");
		Scanner scanner = new Scanner(System.in);
		File file = new File("src/Biblioteca/lista_carti.txt");
		ArrayList<Carte> lista_carti =new ArrayList<Carte>(); 
		try {
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String temp = sc.nextLine();
				if(temp.isEmpty())continue;
				String regex="[:\\,]";
				String[] arr=temp.split(regex);
				for (int i = 0; i < arr.length; i++) {
			        arr[i] = arr[i].trim();
			    	}
					lista_carti.add(new Carte(arr[0],arr[1]));
				}
			}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Warning, wrong file path!");
		}
		
		HashMap<String, String> utilizatori = new HashMap<>();
		
		String optiune=scanner.nextLine();
		while(true) {
		switch(optiune) {
		case "q":
		{
			System.exit(0);
		}
		case "Q":
		{
			System.exit(0);
		}
		case "1":
			{
				System.out.print("Utilizator:");
				String user=scanner.nextLine();
				System.out.print("Parola:");
				String tempPass=scanner.nextLine();
				System.out.print("Confirma parola:");
				String Pass=scanner.nextLine();
				
				while(!tempPass.equals(Pass)) {
					System.out.println("Parolele nu se potrivesc!");
					System.out.print("Parola:");
					tempPass=scanner.nextLine();
					
					System.out.print("Confirma parola:");
					Pass=scanner.nextLine();
					
				}
				utilizatori.put(user,encrypt(Pass));
				System.out.println("Succes!");
				break;
			}
		case "2":
		{
			System.out.print("Utilizator:");
			String user=scanner.nextLine();
			System.out.print("Parola:");
			String Pass=scanner.nextLine();
			if(utilizatori.containsKey(user))
			{
				if(encrypt(Pass).equals(utilizatori.get(user))) {
					System.out.println("Salut, "+user+"!\nOptiuni:\r\n"
							+ "1. Afisare-carti-disponibile\r\n"
							+ "2. Afisare-lista-imprumuturi\r\n"
							+ "3. Imprumuta\r\n"
							+ "4. Restituie-carti\r\n"
							+ "5. Delogare");
					System.out.print("Optiune:");
					String optiune1=scanner.nextLine();
					int testoptiune=1;
					while(testoptiune==1) {
					switch (optiune1) {
					case "5":{
						testoptiune=2;
						break;
					}
					case "1": {
						AfisareLista(lista_carti);
						break;
					}
					case "2": {
						AfisareListaImprumuturi();
						break;
					}
					case "3": {
						System.out.print("Titlu:");
						String Titlu=scanner.nextLine();
						System.out.print("Autor:");
						String Autor=scanner.nextLine();
						lista_impr.add(new Carte(Titlu, Autor));
						break;
					}
					case "4": {
						System.out.print("Titlu:");
						String Titlu=scanner.nextLine();
						for(Carte curent:lista_impr)
						{
							if(curent.getTitlu().equals(Titlu))
							{
								lista_impr.remove(curent);
							}
						}
						break;
					}
					default:
						System.out.println("Optiunea selectata nu este disponibila!");
					}System.out.println();
					if(testoptiune==1)
					{System.out.println("Salut, "+user+"!\nOptiuni:\r\n"
							+ "1. Afisare-carti-disponibile\r\n"
							+ "2. Afisare-lista-imprumuturi\r\n"
							+ "3. Imprumuta\r\n"
							+ "4. Restituie-carti\r\n"
							+ "5. Delogare");optiune1=scanner.nextLine();}
					}
				}else {
					System.out.println("Utilizator sau parola sunt gresite!");
				}
			}else {
				System.out.println("Utilizator sau parola sunt gresite!");
			}
			
			break;
		}
		default:{
			System.out.println("Optiunea selectata nu este disponibila!");
		}
		}
		System.out.println("Bun venit!\nOptiuni:\n 1:Autentificare\n 2:Logare\n Q:Iesire");
		optiune=scanner.nextLine();}
	}
	
	public static void AfisareLista(ArrayList<Carte> lista_carti) {
		for(Carte curent:lista_carti)
		{
			System.out.println("Titlu: "+ curent.getTitlu()+", Autor: "+curent.getAutor());
		}
	}
	public static void AfisareListaImprumuturi() {
		for(Carte curent:lista_impr)
		{
			System.out.println("Titlu: "+ curent.getTitlu()+", Autor: "+curent.getAutor());
		}
	}
}
	

