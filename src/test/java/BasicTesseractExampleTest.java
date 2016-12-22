import org.bytedeco.javacpp.*;
import org.junit.Test;

import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;
import static org.junit.Assert.assertTrue;

public class BasicTesseractExampleTest {
    
    @Test
    public void givenTessBaseApi_whenImageOcrd_thenTextDisplayed() throws Exception {
        BytePointer outText;
        PIX image;

        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(".", "spa_old") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }

        for (int i = 300; i < 400; i += 10) {
            System.out.println("test_1_" + i + ".png");
            run(api, "test_1_" + i + ".png");
            System.out.println("test_2_" + i + ".png");
            run(api, "test_2_" + i + ".png");
        }
    }

    private void run(TessBaseAPI api, String src) {
        PIX image;
        BytePointer outText;
        if (api.Init(".", "spa_old") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
        image = pixRead(src);
        api.SetImage(image);
        // Get OCR result
        outText = printResult(api.GetUTF8Text());
        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
    }

    private BytePointer printResult(BytePointer outText) {
        String string = outText.getString();
        assertTrue(!string.isEmpty());
        System.out.println("OCR output:\n" + string);
        return outText;
    }
}