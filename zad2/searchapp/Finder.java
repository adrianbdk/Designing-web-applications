package searchapp;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {

    public static String getContent(String url) {
        String body = "";
        try {
            URL page = new URL(url);
            try {
                InetAddress address = InetAddress.getByName(new URL(url).getHost());
                HttpURLConnection conn = (HttpURLConnection) page.openConnection();
                conn.connect();
                for (BufferedReader buff = new BufferedReader(new InputStreamReader((InputStream) conn.getContent()));
                     buff.ready();
                     body = body.concat(buff.readLine())) {
                }
                conn.disconnect();
                return body;

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (MalformedURLException malformedUrlException) {
            malformedUrlException.printStackTrace();
        }
        return body;
    }

    static HashSet<String> searchForEmails(String body) {


        Pattern EMAIL_REGEX =
                Pattern.compile(
                        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
                                + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])"
                                + "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[("
                                + "?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
                                + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");

        HashSet<String> emailList = new HashSet<>();


        Matcher matcherEmail = EMAIL_REGEX.matcher(body);

        while (matcherEmail.find()) {
            emailList.add(matcherEmail.group());
        }

        return emailList;
    }

    static HashSet<String> searchForURLs(String body) {

        Pattern URL_REGEX =
                Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

        HashSet<String> urlList = new HashSet<>();

        Matcher matcherUrl = URL_REGEX.matcher(body);

        while (matcherUrl.find()) {
            urlList.add(matcherUrl.group());
        }

        return urlList;
    }

    public static void printEmails(String url) {
        HashSet<String> emails = searchForEmails(url);

        for (String mail : emails) {
            System.out.println(mail);
        }
    }

    public static void printURLs(String url) {
        HashSet<String> urls = searchForURLs(url);

        for (String address : urls) {
            System.out.println(address);
        }
    }

    public static void saveDataToFile(String url, String body) {
        Pattern head_REGEX =
                Pattern.compile("<head>(.*)</head>");
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        String filename = "zad2/searchapp/logFiles/" + formatter.format(date) + ".txt";

        try {
            URL page = new URL(url);
            InetAddress address = InetAddress.getByName(new URL(url).getHost());
            Matcher matcherBody = head_REGEX.matcher(body);

            try {
                System.out.println(filename);
                File file = new File(filename);
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
                FileWriter myWriter = new FileWriter(filename);
                myWriter.write("IP address: " + address.getHostAddress() + "\n");

                while (matcherBody.find()) {
                    myWriter.write(matcherBody.group());
                }

                //Map<String, List<String>> headers = conn.getHeaderFields();
//            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
//            for(Map.Entry<String, List<String>> entry: entrySet){
//                String headerName = entry.getKey();
//                //myWriter.write("Header name: " + headerName);
//                List<String> headerValues = entry.getValue();
//                for (String value : headerValues){
//                    SmyWriter.write("Header Name:" + headerName + " ---> Value: " + value);
//                }
//            }

                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String contentEmail = getContent("https://www.x-kom.pl/centrum-pomocy");
        String contentURL = getContent("https://www.google.pl");

        printEmails(contentEmail);
        printURLs(contentURL);

        saveDataToFile("https://www.google.pl", contentURL);
    }
}

