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
public class LetraDocumentFilter extends DocumentFilter {

    public void insertString(DocumentFilter.FilterBypass fb,
            int offset,
            String text,
            AttributeSet attr) throws BadLocationException {

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!(Character.isLetter(c) || c == ' ' || c == 8)) {
                return;
            }
        }
        fb.insertString(offset, text.toUpperCase(), attr);
    }

    public void replace(DocumentFilter.FilterBypass fb,
            int offset,
            int length,
            String text,
            AttributeSet attrs) throws BadLocationException {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!(Character.isLetter(c) || c == ' ' || c == 8)) {
                return;
            }
        }
        fb.replace(offset, length, text.toUpperCase(), attrs);
    }
}
