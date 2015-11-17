package test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLParser {
	
	private String username = "s_gya10";
	private String password = "3YbNhjH3q3rR4tn6";
	private Connection con;
	
	public XMLParser(){
		String connectionUrl = "jdbc:sqlserver://cypress;" +
				"user = " + username + ";" +
				"password = " + password;
		PreparedStatement pstmt = null;
		ResultSet rs;
		Document dataDoc = null;
		ResultSetMetaData resultmetadata = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			//Create the document object
			DocumentBuilderFactory dbfactory =	DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbfactory.newDocumentBuilder();
			
			//Instantiate a new dom object
			dataDoc = docBuilder.newDocument();
			//Create a new element called
			Element dataRoot = dataDoc.createElement("GPASummary");
			String sql = "SELECT StudentNo, LastName, FirstName,GPA FROM Students";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			resultmetadata  = rs.getMetaData();
			
			while (rs.next()) {
				//For each row of data:
				Element rowEle = dataDoc.createElement("STUDENT");
				for(int i = 0; i <  resultmetadata.getColumnCount(); i++){
					String colName = resultmetadata.getColumnName(i+1);
					String colVal = rs.getString(i+1);
					if(colVal == null || colVal.compareToIgnoreCase("NULL") == 0){
						colVal = "N/A";
					}
					else if(colVal != null){
						colVal = colVal.trim();
					}
					Element dataEle = dataDoc.createElement(colName);
					dataEle.appendChild(dataDoc.createTextNode(colVal));
					rowEle.appendChild(dataEle);
				}
				dataRoot.appendChild(rowEle);
			}
			
			dataDoc.appendChild(dataRoot);
			dataDoc.setXmlStandalone(true);
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.STANDALONE, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			Result output = new StreamResult(new File("./data/Summary.xml"));
			Source input = new DOMSource(dataDoc);
			
			t.transform(input, output);
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		XMLParser parser = new XMLParser();
	}
}
