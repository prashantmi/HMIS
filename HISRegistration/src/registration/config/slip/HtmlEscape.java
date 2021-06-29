package registration.config.slip;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringEscapeUtils;


/**
* HtmlEscape in Java, which is compatible with utf-8
* @author Ulrich Jensen, http://www.htmlescape.net
* Feel free to get inspired, use or steal this code and use it in your
* own projects.
* License:
* You have the right to use this code in your own project or publish it
* on your own website.
* If you are going to use this code, please include the author lines.
* Use this code at your own risk. The author does not warrent or assume any
* legal liability or responsibility for the accuracy, completeness or usefullness of
* this program code.
*/

public class HtmlEscape {
	
			  /**
		    Escape characters for text appearing in HTML markup.
		    
		    <P>This method exists as a defence against Cross Site Scripting (XSS) hacks.
		    The idea is to neutralize control characters commonly used by scripts, such that
		    they will not be executed by the browser. This is done by replacing the control
		    characters with their escaped equivalents.  
		    See {@link hirondelle.web4j.security.SafeText} as well.
		    
		    <P>The following characters are replaced with corresponding 
		    HTML character entities :
		    <table border='1' cellpadding='3' cellspacing='0'>
		    <tr><th> Character </th><th>Replacement</th></tr>
		    <tr><td> < </td><td> &lt; </td></tr>
		    <tr><td> > </td><td> &gt; </td></tr>
		    <tr><td> & </td><td> &amp; </td></tr>
		    <tr><td> " </td><td> &quot;</td></tr>
		    <tr><td> \t </td><td> &#009;</td></tr>
		    <tr><td> ! </td><td> &#033;</td></tr>
		    <tr><td> # </td><td> &#035;</td></tr>
		    <tr><td> $ </td><td> &#036;</td></tr>
		    <tr><td> % </td><td> &#037;</td></tr>
		    <tr><td> ' </td><td> &#039;</td></tr>
		    <tr><td> ( </td><td> &#040;</td></tr> 
		    <tr><td> ) </td><td> &#041;</td></tr>
		    <tr><td> * </td><td> &#042;</td></tr>
		    <tr><td> + </td><td> &#043; </td></tr>
		    <tr><td> , </td><td> &#044; </td></tr>
		    <tr><td> - </td><td> &#045; </td></tr>
		    <tr><td> . </td><td> &#046; </td></tr>
		    <tr><td> / </td><td> &#047; </td></tr>
		    <tr><td> : </td><td> &#058;</td></tr>
		    <tr><td> ; </td><td> &#059;</td></tr>
		    <tr><td> = </td><td> &#061;</td></tr>
		    <tr><td> ? </td><td> &#063;</td></tr>
		    <tr><td> @ </td><td> &#064;</td></tr>
		    <tr><td> [ </td><td> &#091;</td></tr>
		    <tr><td> \ </td><td> &#092;</td></tr>
		    <tr><td> ] </td><td> &#093;</td></tr>
		    <tr><td> ^ </td><td> &#094;</td></tr>
		    <tr><td> _ </td><td> &#095;</td></tr>
		    <tr><td> ` </td><td> &#096;</td></tr>
		    <tr><td> { </td><td> &#123;</td></tr>
		    <tr><td> | </td><td> &#124;</td></tr>
		    <tr><td> } </td><td> &#125;</td></tr>
		    <tr><td> ~ </td><td> &#126;</td></tr>
		    </table>
		    
		    <P>Note that JSTL's {@code <c:out>} escapes <em>only the first 
		    five</em> of the above characters.
		   */
		   public static String forHTML(String aText){
		     final StringBuilder result = new StringBuilder();
		     final StringCharacterIterator iterator = new StringCharacterIterator(aText);
		     char character =  iterator.current();
		     while (character != CharacterIterator.DONE ){
		       if (character == '<') {
		         result.append("&lt;");
		       }
		       else if (character == '>') {
		         result.append("&gt;");
		       }
		       else if (character == '&') {
		         result.append("&amp;");
		      }
		       else if (character == '\"') {
		         result.append("&quot;");
		       }
		       else if (character == '\t') {
		         addCharEntity(9, result);
		       }
		       else if (character == '!') {
		         addCharEntity(33, result);
		       }
		       else if (character == '#') {
		         addCharEntity(35, result);
		       }
		       else if (character == '$') {
		         addCharEntity(36, result);
		       }
		       else if (character == '%') {
		         addCharEntity(37, result);
		       }
		       else if (character == '\'') {
		         addCharEntity(39, result);
		       }
		       else if (character == '(') {
		         addCharEntity(40, result);
		       }
		       else if (character == ')') {
		         addCharEntity(41, result);
		       }
		       else if (character == '*') {
		         addCharEntity(42, result);
		       }
		       else if (character == '+') {
		         addCharEntity(43, result);
		       }
		       else if (character == ',') {
		         addCharEntity(44, result);
		       }
		       else if (character == '-') {
		         addCharEntity(45, result);
		       }
		       else if (character == '.') {
		         addCharEntity(46, result);
		       }
		       else if (character == '/') {
		         addCharEntity(47, result);
		       }
		       else if (character == ':') {
		         addCharEntity(58, result);
		       }
		       else if (character == ';') {
		         addCharEntity(59, result);
		       }
		       else if (character == '=') {
		         addCharEntity(61, result);
		       }
		       else if (character == '?') {
		         addCharEntity(63, result);
		       }
		       else if (character == '@') {
		         addCharEntity(64, result);
		       }
		       else if (character == '[') {
		         addCharEntity(91, result);
		       }
		       else if (character == '\\') {
		         addCharEntity(92, result);
		       }
		       else if (character == ']') {
		         addCharEntity(93, result);
		       }
		       else if (character == '^') {
		         addCharEntity(94, result);
		       }
		       else if (character == '_') {
		         addCharEntity(95, result);
		       }
		       else if (character == '`') {
		         addCharEntity(96, result);
		       }
		       else if (character == '{') {
		         addCharEntity(123, result);
		       }
		       else if (character == '|') {
		         addCharEntity(124, result);
		       }
		       else if (character == '}') {
		         addCharEntity(125, result);
		       }
		       else if (character == '~') {
		         addCharEntity(126, result);
		       }
		       else {
		         //the char is not a special one
		         //add it to the result as is
		         result.append(character);
		       }
		       character = iterator.next();
		     }
		     return result.toString();
		  }
		   
		   private static void addCharEntity(Integer aIdx, StringBuilder aBuilder){
			    String padding = "";
			    if( aIdx <= 9 ){
			       padding = "00";
			    }
			    else if( aIdx <= 99 ){
			      padding = "0";
			    }
			    else {
			      //no prefix
			    }
			    String number = padding + aIdx.toString();
			    aBuilder.append("&#" + number + ";");
			  }
		   
		   /**
		   * Takes UTF-8 strings and encodes non-ASCII as
		   * ampersand-octothorpe-digits-semicolon
		   * HTML-encoded characters
		   *
		   * @param string
		   * @return HTML-encoded String
		   */
		   public static String htmlEncode(final String string) {
		     final StringBuffer stringBuffer = new StringBuffer();
		     for (int i = 0; i < string.length(); i++) {
		       final Character character = string.charAt(i);
		       if (CharUtils.isAscii(character)) {
		         // Encode common HTML equivalent characters
		         stringBuffer.append(
		             StringEscapeUtils.escapeHtml4(character.toString()));
		       } else {
		         // Why isn't this done in escapeHtml4()?
		         stringBuffer.append(
		             String.format("&#x%x;",
		                 Character.codePointAt(string, i)));
		       }
		     }
		     return stringBuffer.toString();
		   }
}
