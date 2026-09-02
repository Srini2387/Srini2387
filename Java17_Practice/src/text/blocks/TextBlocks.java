package text.blocks;

public class TextBlocks {

	public static void main(String[] args) {
		String html =
				"<html>\n" +
				"   <body>\n" +
				"      <h1>Hello</h1>\n" +
				"   </body>\n" +
				"</html>";

		System.out.println(html);
		
		
		String html2 = """
			    <html>
			        <body>
			            <h1>Hello</h1>
			        </body>
			    </html>
			    """;

			System.out.println(html2);

		
	}

}
