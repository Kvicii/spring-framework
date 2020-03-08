package org.springframework.web.servlet.view.document;

import com.google.common.collect.Maps;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.junit.jupiter.api.Test;
import org.springframework.web.testfixture.servlet.MockHttpServletRequest;
import org.springframework.web.testfixture.servlet.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author kyushu
 * @date 2020/1/30 13:51
 * @description PDF测试类
 */
public class PdfViewTests {

	private final MockHttpServletRequest request = new MockHttpServletRequest();

	private final MockHttpServletResponse response = new MockHttpServletResponse();

	private final String pdf = "This is a PDF";

	@Test
	public void testPdf() throws Exception {

		AbstractPdfView pdfView = new AbstractPdfView() {
			@Override
			protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) {
				document.add(new Paragraph(pdf));
			}
		};

		pdfView.render(Maps.newHashMap(), request, response);
		assertEquals(response.getContentType(), "application/pdf", "==");
		byte[] content = response.getContentAsByteArray();
		assertEquals(response.getContentLength(), content.length, "==");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4);
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		writer.setViewerPreferences(PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage);

		document.open();
		document.add(new Paragraph(pdf));
		document.close();

		byte[] bytes = baos.toByteArray();
		assertEquals(content.length, bytes.length, "==");
		int diff = 0;
		for (int i = 0; i < content.length; i++) {
			if (content[i] != bytes[i]) {
				diff++;
			}
		}
		assertTrue(diff < 70, "==");
	}
}
