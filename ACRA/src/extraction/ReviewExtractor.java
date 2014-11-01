package extraction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReviewExtractor {
	private static String commentURL = "http://www.amazon.com/ss/customer-reviews/";
	
	public static void main(String[] args) throws IOException, JSONException{
        Validate.isTrue(args.length == 1, "usage: supply url of product page on Amazon to fetch customer reviews");
        String url = args[0];
        
        String productCode = url.substring(url.indexOf("dp")+3);
        if(productCode.contains("/")){
            productCode = productCode.substring(0,productCode.indexOf("/"));
        }
        FileWriter file = new FileWriter("C:\\Users\\legolaptop\\git\\ACRA\\Reviews\\" + productCode + ".json");
        
        System.out.println("Fetching reviews of product code: " + productCode + "...");
        
        Document doc = Jsoup.connect(commentURL + productCode).get();
        
        int reviews = 0;
        
        while(hasNext(doc)){
        	doc = getNext(doc);
        	reviews += extractReviews(doc, file);
        	System.out.println("Extracted: " + Integer.toString(reviews) + " reviews");
        }
        
		file.close();
	}
	
	private static boolean hasNext(Document doc){
		if(doc.getElementsContainingOwnText("Next").first().attr("href").equals("")){
			return false;
		}
		return true;
	}
	
	private static Document getNext(Document doc) throws IOException{
		return Jsoup.connect(doc.getElementsContainingOwnText("Next").first().attr("href")).get();
	}
	
	private static int extractReviews(Document doc, FileWriter file) throws JSONException, IOException{
		Elements elements = doc.select("div.reviewText");
        Elements reviews = new Elements();
        for(Element element : elements){
        	if(element.className().equals("reviewText")){
        		reviews.add(element.parent());
        	}
        }
        
        Scanner scan;
        
        StringBuilder jsonobjects = new StringBuilder();
        
        for(Element review : reviews){
        	Element profile = review.select("a[href~=profile").first();
        	String username = profile.child(0).text();
        	String userprofile = profile.attr("href");

        	scan = new Scanner(review.text());
        	double peopleVotedHelpful = 0;
        	double peopleVoted = 0;
        	try{
        		peopleVotedHelpful = Integer.parseInt(scan.next());
            	scan.next();
            	peopleVoted = Integer.parseInt(scan.next());
        	}
        	catch(Exception e){}
        	scan.close();
        	
        	scan = new Scanner(review.select("span.swSprite").first().text());
        	double stars = Double.parseDouble(scan.next());
        	scan.close();
        	
        	boolean verified = false;
        	if(review.html().contains("Verified Purchase")){
        		verified = true;
        	}
        	
        	String reviewText = review.select("div.reviewText").first().text();
        	
        	JSONObject reviewContainer = new JSONObject();
        	reviewContainer.put("Username", username);
        	reviewContainer.put("Profile", userprofile);
        	reviewContainer.put("Star Rating", stars);
        	reviewContainer.put("Review", reviewText);
        	reviewContainer.put("Helpful Votes", Double.toString(peopleVotedHelpful) + "/" + Double.toString(peopleVoted));
        	reviewContainer.put("Verified Purchase",verified);
        	
        	jsonobjects.append(reviewContainer.toString());
        	jsonobjects.append("\n");
        }

		file.write(jsonobjects.toString());
		file.flush();
		
		return reviews.size();
	}
}