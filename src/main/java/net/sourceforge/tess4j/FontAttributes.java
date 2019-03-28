package net.sourceforge.tess4j;

public class FontAttributes {
    public static enum FontFamily {
        SERIF, SANS_SERIF, MONOSPACE;
    }

    private final String fontName;
    private final int pt;
    private final boolean bold;
    private final boolean italic;
    private final boolean underlined;
    private final boolean monospace;
    private final boolean serif;
    private final boolean smallcaps;

    public FontAttributes(String fontName, int pt, boolean bold, boolean italic, boolean underlined, boolean monospace, boolean serif, boolean smallcaps) {
        super();
        this.fontName = fontName;
        this.pt = pt;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.monospace = monospace;
        this.serif = serif;
        this.smallcaps = smallcaps;
    }

    public String getFontName() {
        return fontName;
    }

    public int getPt() {
        return pt;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public boolean isMonospace() {
        return monospace;
    }

    public boolean isSerif() {
        return serif;
    }

    public boolean isSmallcaps() {
        return smallcaps;
    }

    public FontFamily getFontFamily() {
        if (monospace) {
            return FontFamily.MONOSPACE;
        } else if (serif) {
            return FontFamily.SERIF;
        } else {
            return FontFamily.SANS_SERIF;
        }
    }

}
