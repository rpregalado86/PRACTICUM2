/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author NestorDaniel
 */
public class NumeroDecimalDocumentFilter extends PlainDocument
{
	private static final long serialVersionUID = 1L;

	public static final String FLOAT = "0123456789.";

	protected String acceptedChars = null;

	protected boolean negativeAccepted = false;

	public NumeroDecimalDocumentFilter() {
		this(FLOAT);
	}

	public NumeroDecimalDocumentFilter(String acceptedchars) {
		acceptedChars = acceptedchars;
	}

	public void setNegativeAccepted(boolean negativeaccepted) {
		if (acceptedChars.equals(FLOAT)) {
			negativeAccepted = negativeaccepted;
			acceptedChars += "-";
		}
	}

	@SuppressWarnings("static-access")
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		for (int i = 0; i < str.length(); i++) {
			if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1)
				return;
		}

		if (acceptedChars.equals(FLOAT) || (acceptedChars.equals(FLOAT + "-") && negativeAccepted)) {
			if (str.indexOf(".") != -1) {
				if (getText(0, getLength()).indexOf(".") != -1) {
					return;
				}
			}
		}

		if (negativeAccepted && str.indexOf("-") != -1) {
			if (str.indexOf("-") != 0 || offset != 0) {
				return;
			}
		}

		super.insertString(offset, str, attr);
	}
}