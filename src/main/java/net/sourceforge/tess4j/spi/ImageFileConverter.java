package net.sourceforge.tess4j.spi;

import java.io.File;
import java.util.Collection;

public interface ImageFileConverter {

    /**
     * The supported file extensions this converter supports.
     * <p>
     * E.g. "pdf" to convert an input PDF to TIFF format.
     * 
     * @return
     */
    Collection<String> supportedFileExtensions();

    /**
     * Return any image readable by Leptonica including BMP, PNM, PNG, JFIF, JPEG, and TIFF. GIF is not supported http://www.leptonica.com/library-overview.html
     *
     * @param inputFile
     * @return
     */
    File convertToImage(File inputFile);
}
