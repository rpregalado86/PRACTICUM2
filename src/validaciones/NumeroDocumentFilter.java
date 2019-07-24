/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Walter Mora
 */
public class NumeroDocumentFilter extends DocumentFilter {
	
   public void insertString(DocumentFilter.FilterBypass fb, 
		   					int offset, 
		   					String text,
		   					AttributeSet attr) throws BadLocationException {
	   
	   for (int i=0; i< text.length();i++)
		   if (!Character.isDigit(text.charAt(i)))
			   return;
	   fb.insertString(offset, text, attr);
   }
	
   public void replace(DocumentFilter.FilterBypass fb,
		   			   int offset,
		   			   int length, 
		   			   String text,
		   			   AttributeSet attrs) throws BadLocationException {
	   for (int i=0; i< text.length();i++)
		   if (!Character.isDigit(text.charAt(i)))
			   return;
	   fb.replace(offset, length, text, attrs);
   }
}