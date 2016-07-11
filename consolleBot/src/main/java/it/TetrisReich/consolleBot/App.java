package it.TetrisReich.consolleBot;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class App {
    public static void main( String[] args ) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	String in = "";
    	boolean chatSelected = false;
    	String chat = "";
    	File f = new File("recent.dat");
    	if(f.exists() && !f.isDirectory()) {} else f.createNewFile();
    	/*String recent[] = new String[countLines("recent.dat")];
    	logger("Inserit the token of the bot.\nRecent tokens: \n");
    	try(BufferedReader br = new BufferedReader(new FileReader("recent.dat"))) {
    		int i = 0;
    	    for(String line; (line = br.readLine()) != null; ) {
    	    	String[] res = line.split(";");
    	        recent[i] = res[0];
    	        logger("/"+i+" "+line);
    	        i++;
    	    }
    	}*/
    	FileReader fileReader;
        fileReader = new FileReader("recent.dat");
		BufferedReader bufferedReader = new BufferedReader( fileReader );
        String line = bufferedReader.readLine();
        bufferedReader.close();
    	logger("Programmed by Stranck.\nInserit the token of the bot.\nRecent token: \n"+line);
    	loggerL("\nUse /r for select a recent token.\n\n\nToken: ");
    	String temp = sc.nextLine();
    	if(temp.equalsIgnoreCase("/r")) temp = line; else {
    		File file = new File("recent.dat");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(temp);
			fileWriter.flush();
			fileWriter.close();
    	}
    	TelegramBot bot = TelegramBotAdapter.build(temp);
    	/*boolean already = true;
    	if(temp.charAt(0)=='/') temp = recent[Character.getNumericValue(temp.charAt(1))];
    	TelegramBot bot = TelegramBotAdapter.build(temp);
    	if(already){
    		File fout = new File("recent.dat");
    		FileOutputStream fos = new FileOutputStream(fout);
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
    		try(BufferedReader br = new BufferedReader(new FileReader("recent.dat"))) {
    		    for(String line; (line = br.readLine()) != null; ) {
    		    	logger("This is a new token! Name for save it? ");
    		        bw.write(line +";  ("+ sc.nextLine()+")");
    		        bw.newLine();
    		    }
    		    bw.write(temp);
    		    bw.close();
    		}
    	}*/
    	while(true){
        	loggerL(chat+">");
        	in = sc.nextLine();
        	String[] splited = in.split("\\s+");
        	if(splited[0].equalsIgnoreCase("/token")) {String[] t = new String[0];main(t);}
        	if(splited[0].equalsIgnoreCase("/chat")){
        		if(splited.length==1) {
        			chatSelected = false; 
        			chat = "";
        		} else {chatSelected = true; chat = splited[1];}
        	} else {
        		if(chatSelected==false) {
        			chat = splited[0];
        			in = "";
        			for(int i=1;i<splited.length;i++) {
        				in = in + splited[i];
        			}
        		}
        		SendResponse sendResponse = bot.execute(new SendMessage(chat, in).parseMode(ParseMode.HTML));
				Message message = sendResponse.message();
				try {
					logger("Message id: " + message.messageId());
				} catch(java.lang.NullPointerException e){
					logger("ERROR!");
				}
        		if(chatSelected==false) chat = "";
        	}
    	}
    }
    public static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
    public static void logger(String text){
    	System.out.println(text);
    }
    public static void loggerL(String text){
    	System.out.print(text);
    }
    //@MultychatNews [CLINIC OF EVIL #1 w/Debe - Fra  &#91CUSTOM MAP #71&#93](https://youtu.be/RSgsaYSGikI)
    //@MultychatNews <a href="https://youtu.be/RSgsaYSGikI">CLINIC OF EVIL #1 w/Debe - Fra  [CUSTOM MAP #71]</a>
}