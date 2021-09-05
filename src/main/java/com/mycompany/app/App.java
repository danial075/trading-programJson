// Made by Danial Sheikh 05/09/21
package com.mycompany.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.google.gson.Gson;

import javax.management.loading.PrivateClassLoader;
import javax.swing.*;

import org.w3c.dom.UserDataHandler;

import java.awt.*;
import java.awt.event.*;

public class App implements ActionListener {
	private String Stock_Name;
	private String Stock_Ticker;
	private double Stock_Price;
	private ArrayList<String> names; // creates an Arraylist to store stock names
	private ArrayList<String> tickers; // creates an Arraylist to store ticker names
	private ArrayList<Double> prices; // creates an Arraylist to store stock prices
	private static JLabel userLabel; //
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel successful;

	public App() {
		names = new ArrayList<String>();
		tickers = new ArrayList<String>();
		prices = new ArrayList<Double>();
	}

	// This method gets ran when the login button is clicked by the user
	@Override
	public void actionPerformed(ActionEvent e) {
		String user = userText.getText();
		String password = passwordText.getText();

		if (user.equals("Danman") && password.equals("Goat1")) {
			successful.setText("Log in accepted");
			Startup();
		}
       
	}

	public void GUI() {
		JPanel panel = new JPanel(); // Allows use to add things to the frame and acts like a invisble container
		JFrame frame = new JFrame(); // creates a new Jframe which can be moved and resized
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the window automatically

		frame.add(panel);

		panel.setLayout(null);

		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);

		button = new JButton("Login");
		button.setBounds(10, 80, 80, 25);
		button.addActionListener(new App()); // Jumps to the action performed method
		panel.add(button);

		successful = new JLabel("");
		successful.setBounds(10, 110, 300, 25);
		panel.add(successful);
		frame.setVisible(true); // Makes the window visible

	}

	public void Startup() {

		System.out.println("Welcome to our trading application");
		System.out.println("\nStocks avaliable to trade:");

		try {
			Reader reader = new FileReader(
					"/Users/danialsheikh/Desktop/Stocks.json");
			Gson g = new Gson(); // This is used as a parser
			App[] stocks = g.fromJson(reader, App[].class); // On the LHS new array of type stocks and on the rhs
															// using the parser and fromJson method which allows
															// us to read the jsonfile in an array format
			for (App i : stocks) {
				names.add(i.getStock_Name());
				tickers.add(i.getStock_Ticker());
				prices.add(i.getStock_Price());
			}
			int len = tickers.size();
			int i = 0;
			while (i < len) {
				System.out.println(names.get(i) + ", " + tickers.get(i) + ", " + prices.get(i));
				i++;
				
			
			}
			
			RunMenu();	
		} catch (FileNotFoundException e) {
			System.out.println("oops check if filepath is correct");
		}

	}

	public void RunMenu() {

		Header();

	}

	// The method below is private for security purposes and ensuring it is not
	// altered
	private void Header() {
		try {

			System.out.println("\n Please select one of the following options:");
			System.out.println("\n1-Buying stocks ");
			System.out.println("\n2-Selling stocks ");

			Scanner buySell = new Scanner(System.in);
			int option = buySell.nextInt();
			if (option == 1) {
				buyOption();
			} else if (option == 2) {
				sellOption();
			} else {
				System.out.println("Type in a relevant number bitch");
				Header();
			}

			buySell.close();

		} catch (InputMismatchException e) {
			System.out.println("Type in a relevant number bitch");
			Header();
		}
	}

	// This buy method allows the user to select the stock and number of shares they
	// would like to purchase
	public void buyOption() {
		System.out.println("Please enter the name or stock ticker of the stock you would like to purchase?");
		Scanner scan = new Scanner(System.in);
		String userStock = scan.next();

		if (names.contains(userStock) || tickers.contains(userStock)) {
			System.out.println("Please enter how much " + userStock + " you would like to buy?");
			int amount = scan.nextInt();
			System.out.println("You have succesfully purchased " + amount + " shares of " + userStock);
		} else {
			System.out.println("Please enter a valid name");

			buyOption();

		}
		scan.close();
	}

	// This sell method allows the user to select the stock and number of shares
	// they would like to sell
	public void sellOption() {
		System.out.println("Please enter the name or stock ticker of the stock you would like to sell?");
		Scanner scan = new Scanner(System.in);
		String userStock = scan.next();
		if (names.contains(userStock) || tickers.contains(userStock)) {
			System.out.println("Please enter how much " + userStock + " you would like to sell?");
			int amount = scan.nextInt();
			System.out.println("You have succesfully sold " + amount + " shares of " + userStock);
		} else {
			System.out.println("Please enter a valid name");

			sellOption();
		}
		scan.close();
	}

	public String getStock_Name() {
		return Stock_Name;
	}

	public void setStock_Name(String stock_Name) {
		Stock_Name = stock_Name;
	}

	public String getStock_Ticker() {
		return Stock_Ticker;
	}

	public void setStock_Ticker(String stock_Ticker) {
		Stock_Ticker = stock_Ticker;
	}

	public double getStock_Price() {
		return Stock_Price;
	}

	public void setStock_Price(double stock_Price) {
		Stock_Price = stock_Price;
	}

}
