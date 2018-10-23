package de.danielprinz.technikum.wordcounter;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class PreviewHandler {

    private static final String PREFIX_TEXT = "<span style='font-family: \"Arial\", sans-serif; font-size: 10px;'>";
    private static final String SUFFIX_TEXT = "</span>";

    private static final DefaultHighlighter.DefaultHighlightPainter DEFAULT_HIGHLIGHT_PAINTER = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

    /**
     * Sets the text into the preview panel
     * @param text The text
     */
    public static void setText(String text) {
        Main.jEditorPane.setText(PREFIX_TEXT + text + SUFFIX_TEXT);
        Main.jEditorPane.setCaretPosition(0);
    }


    /**
     * Highlights all given strings in the preview field. Undoes all rpeviously selected text automatically
     * @param textToSelect String array of the words
     */
    public static void selectText(String... textToSelect) {

        resetText();

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < textToSelect.length; i++) {
            stringBuilder.append(i > 0 ? "|" : "");
            stringBuilder.append(textToSelect[i]);
        }


        Pattern PATTERN = Pattern.compile("(?<=^|[^a-zA-Z0-9_])" + stringBuilder.toString() + "(?=$|[^a-zA-Z0-9_])", Pattern.CASE_INSENSITIVE);
        try {
            String content = Main.jEditorPane.getDocument().getText(0, Main.jEditorPane.getDocument().getLength());
            Matcher matcher = PATTERN.matcher(content);
            while(matcher.find()){
                Main.jEditorPane.getHighlighter().addHighlight(matcher.start(), matcher.end(), DEFAULT_HIGHLIGHT_PAINTER);
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }

    }

    private static void resetText() {
        Main.jEditorPane.getHighlighter().removeAllHighlights();
    }

}
